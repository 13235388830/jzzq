package com.ami.makemoneyby;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ami.makemoneyby.main.My.activity.LoginActivity;
import com.ami.makemoneyby.utils.CountDownTimerUtils;

public class SplashThirdActivity extends BaseActivity  implements View.OnClickListener{
    private String phone;
    private TextView mPhone;
    TextView Txhqyzm;
     Dialog dialog;
    private CountDownTimerUtils mCountDownTimerUtils;
    @Override
    protected void initView() {
        super.initView();
        phone=getIntent().getStringExtra("phone");
        mPhone=findViewById(R.id.tv_phone);
        Txhqyzm = findViewById(R.id.hqyzm);
        Txhqyzm.setOnClickListener(this);
        mCountDownTimerUtils = new CountDownTimerUtils(SplashThirdActivity.this, Txhqyzm, 60000, 1000);
        mCountDownTimerUtils.start();
        mPhone.setText("接受验证码的手机手机号是"+phone);
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.splash_activity_third;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {

    }
    private  void showDialog( Context context){
        dialog=new Dialog(context,R.style.MyDialog);
        View view= LayoutInflater.from(context).inflate(R.layout.activity_splash_third_dialog,null,false);
        dialog.setContentView(view);
        Window dialogWindow  = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow .getAttributes();
        dialog.setCanceledOnTouchOutside(false);
        lp.width= LinearLayout.LayoutParams.MATCH_PARENT;
        lp.height=LinearLayout.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.show();
    }
}
