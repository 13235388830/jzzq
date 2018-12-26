package com.ami.makemoneyby.main.My.activity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ami.makemoneyby.BaseActivity;
import com.ami.makemoneyby.R;


public class FeedbackActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TextView toolBarTitle;


    @Override
    protected int getLayoutID() {
        return R.layout.feedback_activity;
    }

    @Override
    protected void initView() {
        super.initView();

        mToolbar = findViewById(R.id.tool_bar);
        toolBarTitle = findViewById(R.id.toolbar_text);
        toolBarTitle.setText("意见反馈");
        mToolbar.setNavigationIcon(R.mipmap.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
