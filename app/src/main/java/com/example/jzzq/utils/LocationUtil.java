package com.example.jzzq.utils;

import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.example.jzzq.MyApplication;
import com.example.jzzq.inter.LocationInterface;

import cn.addapp.pickers.common.AppConfig;

public class LocationUtil {

    private static LocationUtil sInstance;

    private AMapLocationClient mLocationClient;


    public void setLocationInterface(LocationInterface locationInterface) {
        this.locationInterface = locationInterface;
    }

    private LocationInterface locationInterface;
    private LocationUtil() {
        initAMap();
    }

    public static LocationUtil getInstance() {
        if (sInstance == null) {
            synchronized (LocationUtil.class) {
                if (sInstance == null) {
                    sInstance = new LocationUtil();
                }
            }
        }
        return sInstance;
    }


    private void initAMap() {
        //初始化定位
        mLocationClient = new AMapLocationClient(MyApplication.getContext());
        //初始化定位参数
        AMapLocationClientOption option = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //设置是否返回地址信息（默认返回地址信息）
        option.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        option.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        option.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        option.setMockEnable(false);
        //设置定位间隔10分钟,单位毫秒,默认为2000ms
        option.setInterval(10 * 60 * 1000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(option);

        //设置定位回调监听
        mLocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                Log.e("高德定位", "---当前位置--->" + amapLocation);
                if (amapLocation != null) {
                    if (amapLocation.getErrorCode() == 0) {
                        //定位成功回调信息，设置相关消息
                        locationInterface.getCity(amapLocation.getCity());
                    }

                }
            }
        });

    }

    //启动定位
    public void startLocation() {
        mLocationClient.startLocation();
    }

    //停止定位
    public void stopLocation() {
        mLocationClient.stopLocation();
    }

}
