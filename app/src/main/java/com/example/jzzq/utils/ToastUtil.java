package com.example.jzzq.utils;

import android.widget.Toast;

import com.example.jzzq.MyApplication;

/**
 * Created by cxf on 2017/8/3.
 */

public class ToastUtil {

    private static Toast sToast;

    static {
        sToast = Toast.makeText(MyApplication.getContext(), "", Toast.LENGTH_SHORT);
    }

    public static void show(String s) {
        sToast.setText(s);
        sToast.show();
    }

}
