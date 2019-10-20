package com.test.democalculatedistance;

import com.baidu.location.BDLocation;

import java.util.ArrayList;
import java.util.List;

/**
 * 保存和获取一次定位中的多次定位结果
 */
public class LocationResult {

    private final String tag = "LocationResult.java";
    private List<BDLocation> locations;

    public LocationResult() {
        locations = new ArrayList<BDLocation>();
    }

    /**
     * 存入一个定位结果
     */
    public synchronized void addLocation(BDLocation bdLocation) {

        synchronized (locations) {
            LogUtil.i(tag, "addLocation() radius = " + bdLocation.getRadius());
            locations.add(bdLocation);
        }
    }

    /**
     * 清空所有的定位结果
     */
    public void clearLocations() {

        if (null != locations && locations.size() > 0) {
            locations.clear();
        }
    }

    /**
     * 获取连续定位中的最新一次定位结果
     */
    public synchronized BDLocation getLastLocation() {
        synchronized (locations) {
            if (null != locations && locations.size() > 0) {

                return locations.get(locations.size() - 1);

            } else {
                return null;
            }
        }
    }

    /**
     * 在存入的定位中获取一个精度最小的，也就是定位最精确的
     */
    public synchronized BDLocation getBestLocation() {

        synchronized (locations) {
            if (null != locations && locations.size() > 0) {

                BDLocation location = null;

                for (BDLocation loc : locations) {
                    if (isLocSucc(loc)) {
                        if (null == location) {
                            location = loc;
                        } else {
                            if (loc.getRadius() < location.getRadius()) {
                                location = loc;
                            }
                        }
                    }
                }

                LogUtil.i(tag, "getBestLocation() -- radius = " + location.getRadius() + ", time = " + location.getTime());
                return location;
            } else {
                return null;
            }
        }
    }

    private boolean isLocSucc(BDLocation location) {

        boolean isSucc = true;

        double lat = location.getLatitude();
        double lng = location.getLongitude();
        if (location.getLocType() == BDLocation.TypeCriteriaException
                || location.getLocType() == BDLocation.TypeNetWorkException
                || location.getLocType() == BDLocation.TypeNone
                || location.getLocType() == BDLocation.TypeOffLineLocationFail
                || location.getLocType() == BDLocation.TypeServerCheckKeyError
                || location.getLocType() == BDLocation.TypeServerDecryptError
                || location.getLocType() == BDLocation.TypeServerError) {

            // 定位失败 或 错误, 协议中的错误码：CIVIL_RESULT_GPS_LOCATE_FAILED	0x00106102
            isSucc = false;
            //中国的经纬度范围大约为:纬度3.86~53.55,经度73.66~135.05
            LogUtil.i(tag, "MyLocationListener -- 根据定位类型判断出定位失败或错误, location.getLocType() = " + location.getLocType());
        }

        float latFloat = (float) lat;
        float lngFloat = (float) lng;
        // double支持科学计数法，但是float不支持科学计数法，所以科学计数法强转为float后值为0
        if (latFloat == 0 || lngFloat == 0) {
            // 定位失败或错误
            LogUtil.i(tag, "MyLocationListener -- 根据定位的经纬度数值判断出定位失败或错误, lat = " + lat + " lng = " + lng);
            isSucc = false;
        }

        return isSucc;
    }
}
