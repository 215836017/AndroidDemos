package com.test.demosensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class AppMainActivity extends AppCompatActivity {

    private final String TAG = "AppMainActivity.java";

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private Sensor gyroscopeSensor;
    private Sensor magneticFieldSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        showAllSensors();

        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
//        magneticFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        startListen();
    }

    @Override
    protected void onStop() {
        stopListen();
        super.onStop();
    }

    public void btnClick(View view) {

        if (view.getId() == R.id.mianAct_btn_start) {
            startListen();
        } else if (view.getId() == R.id.mianAct_btn_stop) {
            stopListen();
        }
    }

    private void showAllSensors() {
        List<Sensor> ACCsensorList = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        List<Sensor> GYRsensorList = sensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);
        List<Sensor> MAGsensorList = sensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
        System.out.println("showAllSensors() --- ACCsensorList.size = " + ACCsensorList.size()
                + ", GYRsensorList.size = " + GYRsensorList.size() + ", MAGsensorList.size = " + MAGsensorList.size());

    }

    private void startListen() {
        System.out.println("startListen() --- start..12222222221212121212");

        // sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_FASTEST); // 10ms输出一次
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_GAME); // 20ms输出一次
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_UI); // 60ms -- 80ms输出一次
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);// 200ms输出一次
//        sensorManager.registerListener(sensorEventListener, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);// 200ms输出一次
//        sensorManager.registerListener(sensorEventListener, magneticFieldSensor, SensorManager.SENSOR_DELAY_NORMAL);// 200ms输出一次
    }

    private void stopListen() {
        sensorManager.unregisterListener(sensorEventListener);
    }

    private float x_uncalib;
    private float y_uncalib;
    private float z_uncalib;
    private float result = 0f;
    private String type;

    private int count = 1;
    private float abd = 0f;

    SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            x_uncalib = event.values[0];//x轴方向的
            y_uncalib = event.values[1];//y轴方向的
            z_uncalib = event.values[2];//z轴方向的
            result += x_uncalib + y_uncalib + z_uncalib;
            count++;
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                type = "TYPE_ACCELEROMETER";

                if (count == 20) {
                    abd = result / count;
                    count = 1;
                    result = 0f;
                    LogUtil.i(TAG, "abd = " + abd);
                }

            } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
                type = "TYPE_GYROSCOPE";

            } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                type = "TYPE_MAGNETIC_FIELD";
            }

//            int x_bias = (int) event.values[3];//
//            int y_bias = (int) event.values[4];//
//            int z_bias = (int) event.values[5];//

            LogUtil.d(TAG, "x = " + x_uncalib + ", y = " + y_uncalib
                    + ", z = " + z_uncalib + ", result = " + result + ", type = " + type);
//            LogUtil.d(TAG, "x_bias = " + x_bias + ", y_bias = " + y_bias + ", z_bias = " + z_bias);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
