package com.ami.makemoneyby;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ami.makemoneyby.main.My.activity.LoginActivity;
import com.ami.makemoneyby.utils.CountDownTimerUtils;
import com.avos.avoscloud.AVCallback;
import com.avos.avoscloud.AVCaptcha;
import com.avos.avoscloud.AVCaptchaDigest;
import com.avos.avoscloud.AVCaptchaOption;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVSMS;
import com.avos.avoscloud.AVSMSOption;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.bumptech.glide.Glide;
import com.dalimao.corelibrary.VerificationCodeInput;

public class SplashThirdActivity extends BaseActivity implements View.OnClickListener {
    private String phone;
    private TextView mPhone;
    TextView Txhqyzm;
    Dialog dialog;
    ImageView mLoginYzm;
    private AVCaptchaDigest avCaptchaDigest;
    private String mValidateToken;
    private CountDownTimerUtils mCountDownTimerUtils;
    VerificationCodeInput verificationCodeInput;
    private SharedPreferences sp;

    @Override
    protected void initView() {
        super.initView();
        phone = getIntent().getStringExtra("phone");
        mPhone = findViewById(R.id.tv_phone);
        Txhqyzm = findViewById(R.id.hqyzm);
        Txhqyzm.setOnClickListener(this);
        verificationCodeInput = findViewById(R.id.vci_verificationCodeInput);
        verificationCodeInput.setVisibility(View.GONE);
        verificationCodeInput.setOnCompleteListener(new VerificationCodeInput.Listener() {
            @Override
            public void onComplete(String content) {
                AVSMS.verifySMSCodeInBackground(content, phone, new AVMobilePhoneVerifyCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            sp = SplashThirdActivity.this.getSharedPreferences("User", 0);
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString("phone", phone);
                            edit.commit();
                            startActivity(new Intent(SplashThirdActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(SplashThirdActivity.this, "验证码不正确", Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }

        });
        findViewById(R.id.tv_yydx).setOnClickListener(this);
        mPhone.setText("接受验证码的手机手机号是" + phone);
        showDialog(SplashThirdActivity.this);
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
        switch (v.getId()) {
            case R.id.hqyzm:
                showDialog(SplashThirdActivity.this);
                break;
        }
    }

    private void showDialog(Context context) {
        dialog = new Dialog(context, R.style.MyDialog);
        final View view = LayoutInflater.from(context).inflate(R.layout.activity_splash_third_dialog, null, false);
        mLoginYzm = view.findViewById(R.id.login_yzm_img);
        AVCaptchaOption option1 = new AVCaptchaOption();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        option1.setWidth(85);
        option1.setHeight(30);
        AVCaptcha.requestCaptchaInBackground(option1, new AVCallback<AVCaptchaDigest>() {
            @Override
            protected void internalDone0(AVCaptchaDigest captchaDigest, AVException exception) {
                if (null == exception) {
                    avCaptchaDigest = captchaDigest;
                    // 请求成功，可以通过 captchaDigest.getUrl() 获取图片
                    Glide.with(SplashThirdActivity.this).load(captchaDigest.getUrl()).into(mLoginYzm);
                }
            }
        });
//        mLoginYzm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AVCaptchaOption option1 = new AVCaptchaOption();
//                option1.setWidth(85);
//                option1.setHeight(30);
//                AVCaptcha.requestCaptchaInBackground(option1, new AVCallback<AVCaptchaDigest>() {
//                    @Override
//                    protected void internalDone0(AVCaptchaDigest captchaDigest, AVException exception) {
//                        if (null == exception) {
//                            avCaptchaDigest = captchaDigest;
//                            // 请求成功，可以通过 captchaDigest.getUrl() 获取图片
//                            Glide.with(SplashThirdActivity.this).load(captchaDigest.getUrl()).into(mLoginYzm);
//                        }
//                    }
//                });
//            }
//        });
        VerificationCodeInput input = (VerificationCodeInput) view.findViewById(R.id.verificationCodeInput);
        input.setOnCompleteListener(new VerificationCodeInput.Listener() {
            @Override
            public void onComplete(String content) {
                AVCaptcha.verifyCaptchaCodeInBackground(content, avCaptchaDigest, new AVCallback<String>() {
                    @Override
                    protected void internalDone0(String validateToken, AVException exception) {
                        if (null == exception) {
                            // 请求成功，validateToken 所请求的到的返回值
                            mValidateToken = validateToken;
                            AVSMSOption option = new AVSMSOption();
                            option.setTemplateName("Arui");
                            option.setSignatureName("sign_BuyBuyBuy");
                            option.setApplicationName(SplashThirdActivity.this.getResources().getString(R.string.app_name));
                            option.setTtl(10);
                            option.setValidationToken(mValidateToken);
                            AVSMS.requestSMSCodeInBackground(phone, option, new RequestMobileCodeCallback() {
                                @Override
                                public void done(AVException e) {
                                    if (null == e) {
                                        /* 验证成功 */
                                        mCountDownTimerUtils = new CountDownTimerUtils(SplashThirdActivity.this, Txhqyzm, 60000, 1000);
                                        mCountDownTimerUtils.start();
                                        Toast.makeText(SplashThirdActivity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
                                        verificationCodeInput.setVisibility(View.VISIBLE);
                                        dialog.dismiss();

                                    } else {
                                        /* 验证失败 */
                                        Toast.makeText(SplashThirdActivity.this, "图形验证码不正确或操作太频繁稍后再试", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else {
                            Toast.makeText(SplashThirdActivity.this, "操作太频繁稍后再试", Toast.LENGTH_SHORT).show();

                        }
                    }

                });

            }
        });
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialog.setCanceledOnTouchOutside(false);
        lp.width = LinearLayout.LayoutParams.MATCH_PARENT;
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.show();
    }
}

