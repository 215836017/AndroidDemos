package com.test.demosensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AppMainActivity extends AppCompatActivity {

    private final String TAG = "AppMainActivity.java";

    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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

    private void startListen() {

        // sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_FASTEST); // 10ms输出一次
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_GAME); // 20ms输出一次
//        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_UI); // 60ms -- 80ms输出一次
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);// 200ms输出一次
    }

    private void stopListen() {
        sensorManager.unregisterListener(sensorEventListener);
    }

    SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            int x_uncalib = (int) event.values[0];//x轴方向的
            int y_uncalib = (int) event.values[1];//y轴方向的
            int z_uncalib = (int) event.values[2];//z轴方向的

//            int x_bias = (int) event.values[3];//
//            int y_bias = (int) event.values[4];//
//            int z_bias = (int) event.values[5];//

            LogUtil.d(TAG, "x = " + x_uncalib + ", y = " + y_uncalib + ", z = " + z_uncalib);
//            LogUtil.d(TAG, "x_bias = " + x_bias + ", y_bias = " + y_bias + ", z_bias = " + z_bias);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}
