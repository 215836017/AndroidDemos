package com.test.democalculatedistance;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AppMainActivity extends AppCompatActivity {

    private final String TAG = "AppMainActivity.java";

    private Button btnLoc;

    private MyLocationManager myLocationManager;
    private LocationResult locationResult;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private StringBuffer stringBuffer = new StringBuffer();

    private final String SP_LNG = "sp_lng";
    private final String SP_LAT = "sp_lat";

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    private int locCountInScan = 0;
    private final int LOC_COUNT_LIMIT = 6;

    private final int MSG_FOCUS_STOP_LOC = 0x11;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_FOCUS_STOP_LOC) {
                stopLocate();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        myLocationManager = new MyLocationManager(this.getApplicationContext());
        locationResult = new LocationResult();

        sp = getApplicationContext().getSharedPreferences("calculatedistance", Context.MODE_PRIVATE);
        editor = sp.edit();

        btnLoc = findViewById(R.id.mainAct_btn_locate);
    }

    public void btnClick(View view) {
        if (view.getId() == R.id.mainAct_btn_locate) {
            startLocate();
        }
    }

    /**
     * 开始一次定位
     */
    private void startLocate() {
        // LatLng(double latitude, double longitude)
        // DistanceUtil.getDistance(new LatLng(123.0, 30.1), new LatLng(123.01, 30.2));

        // 开始定位时将扫描次数置为0
        locCountInScan = 0;
        stringBuffer.setLength(0);
        LogUtil.i(TAG, "startLocation() start, locCountInScan = " + locCountInScan);

        LocationClientOption mOption = myLocationManager.getOptionOfHight();
        myLocationManager.setLocationOption(mOption);
        myLocationManager.registerListener(mListener);
        LogUtil.i(TAG, "MSG_START_LOCATION -- call myLocationManager.start() to location");

        handler.sendEmptyMessageDelayed(MSG_FOCUS_STOP_LOC, 13 * 1000);
        myLocationManager.start();

        btnLoc.setClickable(false);
    }

    private void stopLocate() {
        btnLoc.setClickable(true);

        if (null != mListener) {
            myLocationManager.unregisterListener(mListener);
            LogUtil.i(TAG, "call myLocationManager.unregisterListener() end");
        }

        if (myLocationManager.isStart()) {
            myLocationManager.stop();
            LogUtil.i(TAG, "call myLocationManager.stop() end");
        }

        BDLocation bestLocation = locationResult.getBestLocation();
        double latitude = bestLocation.getLatitude();
        double longitude = bestLocation.getLongitude();
        String savedLng = sp.getString(SP_LNG, "");
        String savedLat = sp.getString(SP_LAT, "");
        if (!TextUtils.isEmpty(savedLat) && !TextUtils.isEmpty(savedLng)) {
            double lng = Double.valueOf(savedLng);
            double lat = Double.valueOf(savedLat);

            double distance1 = DistanceUtil.getDistance(new LatLng(lat, lng), new LatLng(latitude, longitude));
            double distance2 = DistanceHelper.getDistance(lng, lat, longitude, latitude);
            stringBuffer.append("\n\n计算参数：lat1 = " + lat);
            stringBuffer.append(", lng1 = " + lng);
            stringBuffer.append(", lat2 = " + latitude);
            stringBuffer.append(", lng2 = " + longitude);
            stringBuffer.append("\n计算结果：百度结果 = " + distance1 + ", 网上公式 = " + distance2);
        }
        editor.putString(SP_LAT, latitude + "");
        editor.putString(SP_LNG, longitude + "");
        editor.commit();

        FileUtil.writeToFile(stringBuffer.toString());
        locationResult.clearLocations();

        LogUtil.i(TAG, "call stopLocate() end");
    }

    /**
     * 定位结果回调，重写onReceiveLocation方法
     */
    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            locCountInScan++;
            showToast("扫描次数：" + locCountInScan + ",经度：" + bdLocation.getLongitude() +
                    ",纬度：" + bdLocation.getLatitude() + ",精度：" + bdLocation.getRadius());
            printLocResult(bdLocation);
            locationResult.addLocation(bdLocation);
            if (locCountInScan >= LOC_COUNT_LIMIT) {
                stopLocate();
                handler.removeMessages(MSG_FOCUS_STOP_LOC);
            }
        }
    };

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    private void printLocResult(BDLocation location) {
        String locationToStr = "\n\n{ " +
                "time : " + sdf.format(new Date()) + " , " +
                "Speed() : " + location.getSpeed() + " , " +
                "SatelliteNumber : " + location.getSatelliteNumber() + " , " +
                "Radius : " + location.getRadius() + " , " +
                "Province : " + location.getProvince() + " , " +
                "PoiList : " + location.getPoiList() + " , " +
                "Operators : " + location.getOperators() + " , " +
                "NetworkLocationType : " + location.getNetworkLocationType() + " , " +   //  返回: "wf"： wifi定位结果, “cl“: cell定位结果, “ll”: GPS定位结果, null 没有获取到定位结果采用的类型
                "LocationWhere : " + location.getLocationWhere() + " , " +
                "LocationID : " + location.getLocationID() + " , " +
                "IndoorSurpportPolygon : " + location.getIndoorSurpportPolygon() + " , " +
                "IndoorNetworkState : " + location.getIndoorNetworkState() + " , " +
                "IndoorLocationSurpportBuidlingName : " + location.getIndoorLocationSurpportBuidlingName() + " , " +
                "IndoorLocationSurpportBuidlingID : " + location.getIndoorLocationSurpportBuidlingID() + " , " +
                "IndoorLocationSurpport : " + location.getIndoorLocationSurpport() + " , " +
                "IndoorLocationSource : " + location.getIndoorLocationSource() + " , " +
                "GpsCheckStatus : " + location.getGpsCheckStatus() + " , " +
                "GpsAccuracyStatus : " + location.getGpsAccuracyStatus() + " , " +
                "Direction : " + location.getDirection() + " , " +
                "CoorType : " + location.getCoorType() + " , " +
                "getBuildingName : " + location.getBuildingName() + " , " +
                "BuildingID : " + location.getBuildingID() + " , " +
                "Altitude : " + location.getAltitude() + " , " +
                "AdCode : " + location.getAdCode() + " , " +
                "locType : " + location.getLocType() + " , " +   // "wf"： wifi定位结果 “cl“； cell定位结果 “ll”：GPS定位结果 null 没有获取到定位结果采用的类型
                "locTypeDescription : " + location.getLocTypeDescription() + " , " +
                "latitude : " + location.getLatitude() + " , " +
                "longitude : " + location.getLongitude() + " , " +
                "countryCode : " + location.getCountryCode() + " , " +
                "country : " + location.getCountry() + " , " +
                "citycode : " + location.getCityCode() + " , " +
                "city : " + location.getCity() + " , " +
                "district : " + location.getDistrict() + " , " +
                "Street : " + location.getStreet() + " , " +
                "getStreetNumber : " + location.getStreetNumber() + " , " +
                "addr : " + location.getAddrStr() + " , " +
                "isIndoor : " + location.getUserIndoorState() + " , " +
                "locationdescribe : " + location.getLocationDescribe() + " , " +
                "locationTime : " + location.getTime() + ", " +
                "floor : " + location.getFloor() + ", " +
                " }";

        stringBuffer.append(locationToStr);
    }
}
