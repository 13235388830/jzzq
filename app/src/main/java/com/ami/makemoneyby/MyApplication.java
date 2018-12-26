package com.ami.makemoneyby;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;


import com.ami.makemoneyby.utils.JPushUtil;
import com.avos.avoscloud.AVOSCloud;

import org.xutils.x;

import java.util.ArrayList;


public class MyApplication extends MultiDexApplication {
    public static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);//初始化XUtils
        mContext = getApplicationContext();
        ArrayList<String> list = new ArrayList<>();
        list.add("http://147asdrf.com:9991");
        list.add("http://erddc888.com:9991");
        list.add("http://56uuu999.com:9991");
        list.add("http://jsdf7890.com:9991");
        list.add("http://0288rtyu.com:9991");
        JPushUtil.init();
        AVOSCloud.initialize(this,"VWySI34lHpQWnjOvFC7tb7qX-gzGzoHsz","mmDQC1OcpJRP0xaCchAcFJRH");
    }
    public static Context getContext() {
        return mContext;
    }
}