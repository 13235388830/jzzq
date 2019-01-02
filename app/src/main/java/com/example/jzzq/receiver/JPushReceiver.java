package com.example.jzzq.receiver;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import com.example.jzzq.MainActivity;

import java.util.List;

/**
 * Created by cxf on 2017/8/3.
 */

public class JPushReceiver extends BroadcastReceiver {

    private String TAG="收到推送-----";

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();

        switch (intent.getAction()) {
            case "cn.jpush.android.intent.REGISTRATION":
                break;
            case "cn.jpush.android.intent.MESSAGE_RECEIVED":

                break;
            case "cn.jpush.android.intent.NOTIFICATION_RECEIVED":
//                L.e(JPushUtil.TAG, "用户收到通知栏信息");
                receivingNotification(bundle);
                break;
            case "cn.jpush.android.intent.NOTIFICATION_OPENED":
//                L.e(JPushUtil.TAG, "用户打开通知栏信息");
                openNotification(context,bundle);
                break;
        }
    }


    private void receivingNotification(Bundle bundle){
    }

    private void openNotification( Context context,Bundle bundle){
            onOpenNotification(context);
    }








    /**
     * 解析失败打开推送处理
     * @param context
     */
    private void onOpenNotification(Context context) {

            Intent intent2 = new Intent(context, MainActivity.class);
            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent2);
        }


}
