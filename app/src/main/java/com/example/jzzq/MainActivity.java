package com.example.jzzq;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jzzq.bean.FragmentBean;
import com.example.jzzq.inter.LocationInterface;
import com.example.jzzq.main.Home.fragment.HomeFragment;
import com.example.jzzq.main.Information.fragment.InformationFragment;
import com.example.jzzq.main.My.fragment.MyFragment;
import com.example.jzzq.utils.LocationUtil;
import com.example.jzzq.utils.ToastUtil;
import com.example.jzzq.weiget.FragmentTabHost;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mTabHost;
    private LayoutInflater mInflater;
    private List<FragmentBean> mFragmentList = new ArrayList<>(3);
    private ImageView mHengWen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initTab();
    }


    private void initView() {
        mInflater = LayoutInflater.from(this);
        mHengWen=findViewById(R.id.iv_hw);
        mTabHost = findViewById(android.R.id.tabhost);
        RequestOptions options = new RequestOptions() .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(MainActivity.this).load("http://img.027cgb.com/608340/caipiaoQQ/%E5%AE%A2%E6%9C%8DQQ.gif").apply(options).into(mHengWen);

    }



    private void initTab() {
        FragmentBean homeFragment = new FragmentBean(R.drawable.selector_icon_home, R.string.home, HomeFragment.class);
        FragmentBean scoreFragment = new FragmentBean(R.drawable.selector_icon_information, R.string.information, InformationFragment.class);
        FragmentBean myFragment = new FragmentBean(R.drawable.selector_icon_my, R.string.my, MyFragment.class);

        mFragmentList.add(homeFragment);
        mFragmentList.add(scoreFragment);
        mFragmentList.add(myFragment);

        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        for (FragmentBean tab : mFragmentList) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(String.valueOf(tab.getTitle()));
            tabSpec.setIndicator(builderIndicator(tab));
            mTabHost.addTab(tabSpec, tab.getFragment(), null);
        }

        //去除旁边横线
        mTabHost.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);

        //默认选中
        mTabHost.setCurrentTab(0);
    }

    private View builderIndicator(FragmentBean tab) {
        View view = mInflater.inflate(R.layout.tab_indicator, null);
        ImageView imageView = view.findViewById(R.id.icon_tab);
        TextView textView = view.findViewById(R.id.txt_indicator);
        imageView.setImageResource(tab.getImg());
        textView.setText(tab.getTitle());
        return view;
    }

    private static Boolean mIsExit = false;

    /**
     * 返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!mIsExit) {
                mIsExit = true;
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mIsExit = false;
                    }
                }, 2000);
                return false;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
