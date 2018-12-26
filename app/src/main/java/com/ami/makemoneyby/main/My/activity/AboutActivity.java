package com.ami.makemoneyby.main.My.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ami.makemoneyby.BaseActivity;
import com.ami.makemoneyby.R;


public class AboutActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TextView toolBarTitle;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        super.initView();
        mToolbar = findViewById(R.id.tool_bar);
        toolBarTitle = findViewById(R.id.toolbar_text);
        toolBarTitle.setText("关于我们");
        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
