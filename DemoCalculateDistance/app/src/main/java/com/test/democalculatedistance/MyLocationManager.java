package com.test.democalculatedistance;

import android.content.Context;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

public class MyLocationManager {

    private final String tag = "MyLocationManager.java";

    private LocationClient client = null;
    private Object objLock = new Object();

    public MyLocationManager(Context context) {
        synchronized (objLock) {
            if (null == client) {
                client = new LocationClient(context.getApplicationContext());
            }
        }
    }

    public boolean registerListener(BDAbstractLocationListener listener) {
        boolean isSuccess = false;
        if (listener != null) {
            LogUtil.i(tag, "registerListener() ");
            client.registerLocationListener(listener);
            isSuccess = true;
        }
        return isSuccess;
    }

    public void unregisterListener(BDAbstractLocationListener listener) {
        if (listener != null) {
            LogUtil.i(tag, "unregisterListener()");
            client.unRegisterLocationListener(listener);
        }
    }

    /***
     * 把配置好的参数集（option）设置到LocationClient中
     * @param option
     * @return isSuccessSetOption
     */
    public boolean setLocationOption(LocationClientOption option) {
        boolean isSuccess = false;
        if (option != null) {
            if (client.isStarted()) {
                client.stop();
            }
            client.setLocOption(option);
        }
        return isSuccess;
    }

    /**
     * 获取省电模式的 LocationClientOption
     */
    public LocationClientOption getOptionOfSaving() {
        LogUtil.i(tag, "getOptionOfSaving() start ");
        LocationClientOption mOption = getOption();
        mOption.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
//        mOption.setScanSpan(0);
        mOption.setEnableSimulateGps(false);  // 设置是否允许模拟GPS true:允许； false:不允许
        mOption.setOpenGps(false); // 设置是否打开gps进行定位
        return mOption;
    }

    /**
     * 获取高精度模式的 LocationClientOption
     */
    public LocationClientOption getOptionOfHight() {

        LogUtil.i(tag, "getOptionOfHight() start ");
        LocationClientOption mOption = getOption();

        mOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//        mOption.setScanSpan(2000);
        mOption.setEnableSimulateGps(true);  // 设置是否允许模拟GPS true:允许； false:不允许
        mOption.setOpenGps(true); // 设置是否打开gps进行定位
        mOption.setLocationNotify(true);  //可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        mOption.setPriority(LocationClientOption.GpsFirst);

        return mOption;
    }

    /**
     * 获取一个设置好的参数集合
     */
    public LocationClientOption getOption() {

        LocationClientOption mOption = new LocationClientOption();
        /**
         * 设置坐标类型,取值有3个： 返回国测局经纬度坐标系：gcj02 返回百度墨卡托坐标系 ：bd09 返回百度经纬度坐标系 ：bd09ll
         *                 可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
         */
        mOption.setCoorType("bd09ll");
        mOption.setIsNeedAltitude(true); // 设置是否需要返回海拔高度信息，可以在BDLocation.getAltitude() 中得到数据，GPS定位结果中默认返回，默认值Double.MIN_VALUE
        mOption.setIsNeedLocationDescribe(true); // 设置是否需要返回位置语义化信息，可以在BDLocation.getLocationDescribe() 中得到数据，比如: "在天安门附近"，可以用作地址信息的补充
        mOption.setIsNeedLocationPoiList(true); // 设置是否需要返回位置POI信息，可以在BDLocation.getPoiList() 中得到数据
        /**
         *  设置定位模式 有:
         *  Hight_Accuracy 高精度定位模式：这种定位模式下，会同时使用网络定位和GPS定位，优先返回最高精度的定位结果；
         *  Battery_Saving 低功耗定位模式：这种定位模式下，不会使用GPS，只会使用网络定位（Wi-Fi和基站定位）
         *  Device_Sensors 仅用设备定位模式：这种定位模式下，不需要连接网络，只使用GPS进行定位，这种模式下不支持室内环境的定位
         */
//        mOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//        mOption.setEnableSimulateGps(true);  // 设置是否允许模拟GPS true:允许； false:不允许
//        mOption.setOpenGps(true); // 设置是否打开gps进行定位
        mOption.setScanSpan(2000);//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
        mOption.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        mOption.setIsNeedAddress(true); //可选，设置是否需要地址信息，默认不需要。 这里务必要打开，查询天气需要
        mOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        mOption.setNeedDeviceDirect(false); // 在网络定位时，是否需要设备方向 true:需要 ; false:不需要。

        return mOption;

    }

    /**
     * 开始定位
     */
    public void start() {
        synchronized (objLock) {
            if (client != null && !client.isStarted()) {
                client.start();
            }
        }
    }

    /**
     * 结束定位
     */
    public void stop() {
        synchronized (objLock) {
            if (client != null && client.isStarted()) {
                client.stop();
            }
        }
    }

    /**
     * 是否在进行定位
     */
    public boolean isStart() {
        return client.isStarted();
    }
}
