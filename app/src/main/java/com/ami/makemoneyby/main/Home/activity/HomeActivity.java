package com.ami.makemoneyby.main.Home.activity;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ami.makemoneyby.BaseActivity;
import com.ami.makemoneyby.R;
import com.ami.makemoneyby.http.CallUrls;
import com.ami.makemoneyby.http.HttpUtils;
import com.ami.makemoneyby.main.Home.bean.HomeActivityBean;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends BaseActivity {

    private int id;
    private Toolbar toolbar;
    private TextView toolText;

    private TextView title, moeny, type, tel, adrees, sqtj;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_home;
    }

    private MyHandler handler = new MyHandler(HomeActivity.this);

    private static class MyHandler extends Handler {
        private WeakReference<Activity> weakReference = null;

        public MyHandler(Activity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HomeActivity homeActivity = (HomeActivity) weakReference.get();
            switch (msg.what) {
                case 1:
                    if (msg.obj != null) {
                        HomeActivityBean bean = (HomeActivityBean) msg.obj;
                        if (bean.getCode() == 0) {
                            homeActivity.setData(bean.getData().getDetail());
                        }
                        break;
                    }
            }
        }
    }


    @Override
    protected void initView() {
        super.initView();
        id = getIntent().getIntExtra("id", 0);

        toolbar = findViewById(R.id.tool_bar);
        toolText = findViewById(R.id.toolbar_text);
        toolText.setText("详情内容");
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        title = findViewById(R.id.title);
        moeny = findViewById(R.id.moeny);
        type = findViewById(R.id.type);
        tel = findViewById(R.id.tel);
        adrees = findViewById(R.id.adrees);
        sqtj = findViewById(R.id.sqtj);
        requestData(id);
    }

    private void setData(HomeActivityBean.DataBean.DetailBean bean) {
        title.setText(bean.getTitle());
        moeny.setText(bean.getSalary());
        type.setText(bean.getType());
        String s = bean.getContact_info().substring(8, 19);
        tel.setText(s);
        adrees.setText(bean.getArea());
        sqtj.setText(bean.getDetail());
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    private void requestData(int id) {
        final Map<String, Object> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        HttpUtils.post(HomeActivity.this, CallUrls.PFURL2, map, handler, HomeActivityBean.class, 1);
    }
}
