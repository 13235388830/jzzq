package com.example.jzzq.utils;

import com.example.jzzq.MyApplication;

import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by cxf on 2017/8/3.
 */

public class JPushUtil {

    public static final String TAG = "极光推送";
    public static boolean isSetAlians;

    public static void init() {
        JPushInterface.init(MyApplication.getContext());
    }

    public static void stopPush() {
        isSetAlians=false;
    }

}
