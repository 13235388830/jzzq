package com.ami.makemoneyby.main.My.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ami.makemoneyby.BaseFragment;
import com.ami.makemoneyby.R;
import com.ami.makemoneyby.main.My.activity.AboutActivity;
import com.ami.makemoneyby.main.My.activity.FeedbackActivity;
import com.ami.makemoneyby.main.My.activity.LoginActivity;
import com.ami.makemoneyby.main.My.activity.MessageActivity;
import com.ami.makemoneyby.utils.GlideCatchUtil;
import com.thuongnh.zprogresshud.ZProgressHUD;

public class MyFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout messageLayout, yjfkLayout, gywmLayout, qchcLayout, dqbb_layout;
    private TextView tv;
    private TextView login;
    RelativeLayout table5;
    //声明Sharedpreferenced对象
    private SharedPreferences sp;
    private boolean isNoHc;
    protected ZProgressHUD progressHUD;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_my;
    }


    @Override
    protected void initView(View view) {
        super.initView(view);

        messageLayout = view.findViewById(R.id.message_layout);
        yjfkLayout = view.findViewById(R.id.yjfk_layout);
        gywmLayout = view.findViewById(R.id.gywm_layout);
        qchcLayout = view.findViewById(R.id.qchc_layout);
        dqbb_layout = view.findViewById(R.id.dqbb_layout);
        login = view.findViewById(R.id.login);
        table5 = view.findViewById(R.id.table5);
        tv = view.findViewById(R.id.tv);
        login.setOnClickListener(this);
        messageLayout.setOnClickListener(this);
        yjfkLayout.setOnClickListener(this);
        gywmLayout.setOnClickListener(this);
        qchcLayout.setOnClickListener(this);
        dqbb_layout.setOnClickListener(this);
        table5.setOnClickListener(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("User", 0);
        String phone = sharedPreferences.getString("phone", "");
        if (!phone.equals("")) {
            login.setText(phone + "");
            table5.setVisibility(View.VISIBLE);

        } else {
            table5.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_layout:
                startActivity(new Intent(getActivity(), MessageActivity.class));
                break;
            case R.id.yjfk_layout:
                startActivity(new Intent(getActivity(), FeedbackActivity.class));
                break;
            case R.id.gywm_layout:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.qchc_layout:
                clearCache();
                break;
            case R.id.dqbb_layout:
                Toast.makeText(getActivity(), "已是最新版本~", Toast.LENGTH_LONG).show();
                break;
            case R.id.login:
                Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent1);
                break;
            case R.id.table5:
                table5.setVisibility(View.GONE);
                login.setText("登录");
                sp = getActivity().getSharedPreferences("User", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = sp.edit();
                edit.putString("phone", "");
                edit.commit();
                break;
        }
    }

    private void clearCache() {
        if (isNoHc) {
            Toast.makeText(getActivity(), "无缓存清除", Toast.LENGTH_LONG).show();
            return;
        }
        GlideCatchUtil.getInstance().clearImageAllCache();
        progressHUD = new ZProgressHUD(getActivity(), 2, "清除缓存中...");
        progressHUD.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressHUD.dismiss();
                Toast.makeText(getActivity(), "清除完成", Toast.LENGTH_LONG).show();
                tv.setText(getCacheSize());
            }
        }, 2000);
    }

    private String getCacheSize() {
        String cache = GlideCatchUtil.getInstance().getCacheSize();
        if ("0.0Byte".equalsIgnoreCase(cache)) {
            cache = getString(R.string.no_cache);
            isNoHc = true;
        } else {
            isNoHc = false;
        }
        return cache;
    }

}
