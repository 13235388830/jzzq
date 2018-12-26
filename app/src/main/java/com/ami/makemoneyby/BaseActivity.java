package com.ami.makemoneyby;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.xutils.x;


public class BaseActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        x.view().inject(this);
        initView();
        initListener();
    }

    protected int getLayoutID(){
        return 0;
    }

    protected void initView() {
    }

    protected void initListener() {
    }
}