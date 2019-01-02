package com.example.jzzq.main.My.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.jzzq.R;


public class MessageActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TextView toolBarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initView();
    }

    private void initView() {
        mToolbar = findViewById(R.id.tool_bar);
        toolBarTitle = findViewById(R.id.toolbar_text);
        toolBarTitle.setText("我的消息");
        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
