package com.ami.makemoneyby.main.My.activity;

import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ami.makemoneyby.BaseActivity;
import com.ami.makemoneyby.R;
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

public class LoginYYActivity extends BaseActivity implements View.OnClickListener {
    EditText Etphone;
    TextView btSend;
    String phone;
    EditText Etyzm;
    TextView Txhqyzm;
    private Toolbar toolbar;
    private TextView toolText;
    private EditText mLoginYzmCode;
    private boolean isHqYzm = false;
    ImageView mLoginYzm;
    private CountDownTimerUtils mCountDownTimerUtils;
    private SharedPreferences sp;
    private AVCaptchaDigest avCaptchaDigest;
    private String mValidateToken;
    @Override
    protected void initView() {
        super.initView();
        toolbar = findViewById(R.id.tool_bar);
        toolText = findViewById(R.id.toolbar_text);
        toolText.setText("语音短信登录");
        toolbar.setNavigationIcon(R.mipmap.back);
        Txhqyzm = findViewById(R.id.hqyzm);
        findViewById(R.id.hqyzm).setOnClickListener(this);
        mLoginYzm = findViewById(R.id.login_yzm_img);
        mLoginYzmCode = findViewById(R.id.login_yzm_ed);
        mLoginYzm.setOnClickListener(this);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btSend = findViewById(R.id.btn_send);
        btSend.setOnClickListener(this);
        Etphone = findViewById(R.id.phone);
        Etyzm = findViewById(R.id.yzm);
        AVCaptchaOption option = new AVCaptchaOption();
        option.setWidth(85);
        option.setHeight(30);
        AVCaptcha.requestCaptchaInBackground(option, new AVCallback<AVCaptchaDigest>() {
            @Override
            protected void internalDone0(AVCaptchaDigest captchaDigest, AVException exception) {
                if (null == exception) {
                    avCaptchaDigest = captchaDigest;
                    // 请求成功，可以通过 captchaDigest.getUrl() 获取图片
                    Glide.with(LoginYYActivity.this).load(captchaDigest.getUrl()).into(mLoginYzm);
                }
            }
        });
    }

    @Override
    protected void initListener() {
        super.initListener();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_yy_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send:
                String loginPicCode = mLoginYzmCode.getText().toString().trim();
                phone = Etphone.getText() + "";
                final String yzm = Etyzm.getText() + "";
                if (phone.equals("")) {
                    Toast.makeText(LoginYYActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!isMobileNO(phone)) {
                    Toast.makeText(LoginYYActivity.this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                } else if (loginPicCode.isEmpty()) {
                    Toast.makeText(LoginYYActivity.this, "请先输入图像验证码", Toast.LENGTH_LONG).show();
                    return;
                }
                else if (yzm.equals("")) {
                    Toast.makeText(LoginYYActivity.this, "请输入验证码", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (isHqYzm) {
                        AVSMS.verifySMSCodeInBackground(yzm, phone, new AVMobilePhoneVerifyCallback() {
                            @Override
                            public void done(AVException e) {
                                if (e == null) {
                                    sp = LoginYYActivity.this.getSharedPreferences("User", 0);
                                    SharedPreferences.Editor edit = sp.edit();
                                    edit.putString("phone", phone);
                                    edit.commit();
                                    finish();
                                } else {
                                    Toast.makeText(LoginYYActivity.this, "验证码不正确", Toast.LENGTH_LONG).show();

                                }
                            }
                        });
                    }else {
                        Toast.makeText(LoginYYActivity.this, "请先获取验证码", Toast.LENGTH_LONG).show();

                    }
                }


                break;
            case R.id.hqyzm:
                phone = Etphone.getText() + "";
                isHqYzm = true;
                loginPicCode = mLoginYzmCode.getText().toString().trim();
                if (phone.equals("")) {
                    Toast.makeText(LoginYYActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!isMobileNO(phone)) {
                    Toast.makeText(LoginYYActivity.this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (loginPicCode.isEmpty()) {
                    Toast.makeText(LoginYYActivity.this, "图形验证码不能为空", Toast.LENGTH_LONG).show();
                    return;
                }
                AVCaptcha.verifyCaptchaCodeInBackground(loginPicCode, avCaptchaDigest, new AVCallback<String>() {
                    @Override
                    protected void internalDone0(String validateToken, AVException exception) {
                        if (null == exception) {
                            // 请求成功，validateToken 所请求的到的返回值
                            mValidateToken = validateToken;
                            AVSMSOption option = new AVSMSOption();
                            option.setValidationToken(mValidateToken);
                            option.setSmsType(AVSMSOption.AVSMS_TYPE.VOICE_SMS);
                            AVSMS.requestSMSCodeInBackground(phone, option, new RequestMobileCodeCallback() {
                                @Override
                                public void done(AVException e) {
                                    if (null == e) {
                                        /* 验证成功 */
                                        mCountDownTimerUtils = new CountDownTimerUtils(LoginYYActivity.this, Txhqyzm, 60000, 1000);
                                        mCountDownTimerUtils.start();
                                        Toast.makeText(LoginYYActivity.this, "发送语音成功", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Log.e("WeChat",e.toString());
                                        // 发送失败
                                        Toast.makeText(LoginYYActivity.this, "发送语音失败", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });

                        } else {
                            Toast.makeText(LoginYYActivity.this, "图形验证码不正确", Toast.LENGTH_LONG).show();

                        }
                    }
                });

                break;
            case R.id.login_yzm_img:
                AVCaptchaOption option1 = new AVCaptchaOption();
                option1.setWidth(85);
                option1.setHeight(30);
                AVCaptcha.requestCaptchaInBackground(option1, new AVCallback<AVCaptchaDigest>() {
                    @Override
                    protected void internalDone0(AVCaptchaDigest captchaDigest, AVException exception) {
                        if (null == exception) {
                            avCaptchaDigest = captchaDigest;
                            // 请求成功，可以通过 captchaDigest.getUrl() 获取图片
                            Glide.with(LoginYYActivity.this).load(captchaDigest.getUrl()).into(mLoginYzm);
                        }
                    }
                });
                break;
        }

    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobiles) {
		/*
		移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		联通：130、131、132、152、155、156、185、186
		电信：133、153、180、189、（1349卫通）/^0?1[3|4|5|7|8][0-9]\d{8}$/
		总结起来就是第一位必定为1，第二位必定为3或5或8或7（电信运营商），其他位置的可以为0-9
		*/
        String telRegex = "[1][34578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isHqYzm = false;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        isHqYzm = false;

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
