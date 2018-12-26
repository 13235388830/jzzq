package com.ami.makemoneyby;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ami.makemoneyby.http.CallUrls;
import com.ami.makemoneyby.http.HttpUtils;
import com.ami.makemoneyby.main.Home.bean.SplashBean;
import com.ami.makemoneyby.notification.NotificationsUtils;
import com.ami.makemoneyby.utils.CountDownTimerUtils2;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/10/12.
 */
public class SplashActivity extends BaseActivity {
    @ViewInject(R.id.miao)
    private TextView miao;
    @ViewInject(R.id.relat)
    private RelativeLayout relat;
    @ViewInject(R.id.tiaoguo)
    private TextView tiaoguo;
    private CountDownTimerUtils2 mCountDownTimerUtils;
    private Handler handler = new MyHandler(this);
    private SharedPreferences spf;
    SharedPreferences.Editor editor;
    private class MyHandler extends Handler {
        private WeakReference<Activity> weakReference = null;

        public MyHandler(Activity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SplashActivity splashActivity = (SplashActivity) weakReference.get();

            switch (msg.what) {
                case 1:

                    if (msg != null && msg.obj != null) {
                        SplashBean bean = (SplashBean) msg.obj;
                        if (bean.getIsshowwap() != null) {
                            if (bean.getIsshowwap().equals("1") && bean.getStatus() == 1) {
                                String apkurl = bean.getWapurl();
                                if (apkurl.substring(apkurl.length() - 3, apkurl.length()).equals("apk")) {
                                    Intent intent = new Intent(splashActivity, UpdateActivity.class);
                                    intent.putExtra("url", "https://bxvip.oss-cn-zhangjiakou.aliyuncs.com/369/369caizy.apk");
                                    splashActivity.startActivity(intent);
                                } else {
                                    Intent intent = new Intent(splashActivity, WebViewActivity.class);
                                    intent.putExtra("url", apkurl);
                                    intent.putExtra("isFromSplash", "1");
                                    splashActivity.startActivity(intent);
                                }
                            } else {

                                if (spf.getBoolean("isFirst",false)) {
                                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                    finish();
                                } else {
                                    editor.putBoolean("isFirst",true);
                                    editor.commit();
                                    startActivity(new Intent(SplashActivity.this, SplashSecondActivity.class));
                                    //111122
                                    finish();
                                }
                            }

                        } else {
                            if (spf.getBoolean("isFirst",false)) {
                                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                finish();
                            } else {
                                editor.putBoolean("isFirst",true);
                                editor.commit();
                                startActivity(new Intent(SplashActivity.this, SplashSecondActivity.class));
                                finish();
                            }
                        }
                    }
                    finish();
                    break;
                case 10:
                    startActivity(new Intent(splashActivity, MainActivity.class));
                    finish();
                    break;
            }
        }
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        super.initView();
        spf = getSharedPreferences("isFirst", Context.MODE_PRIVATE);
       editor = spf.edit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        relat.setVisibility(View.VISIBLE);
        mCountDownTimerUtils = new CountDownTimerUtils2(this, miao, 3000, 1000);
        mCountDownTimerUtils.start();
        tiaoguo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCountDownTimerUtils.cancel();
                requestData();
            }
        });
    }

    public void requestData() {
        final Map<String, Object> map = new HashMap<>();
        map.put("appid", "15643423");
        HttpUtils.post(SplashActivity.this, CallUrls.PFURL0, map, handler, SplashBean.class, 1);
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

}
