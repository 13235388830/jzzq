package com.ami.makemoneyby;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ami.makemoneyby.main.My.activity.LoginActivity;
import com.ami.makemoneyby.utils.AddressPickTask;

import cn.addapp.pickers.entity.Province;

public class SplashSecondActivity extends BaseActivity implements View.OnClickListener {
    private ImageView manImg, maleImg;
    private String sex = "";
    private boolean isOpen = false;
    private TextView mSf, mYear, mMaster,mSubmit;
    private EditText mName, mPhone;
    private boolean isSelect = false,isName=false,isPhone=false,isSex=false;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void initView() {
        super.initView();
        maleImg = (ImageView) findViewById(R.id.iv_male);
        manImg = findViewById(R.id.iv_man);
        manImg.setOnClickListener(this);
        maleImg.setOnClickListener(this);
        findViewById(R.id.tv_zkk).setOnClickListener(this);
        findViewById(R.id.tv_submit).setOnClickListener(this);
        mName=findViewById(R.id.et_name);
        mSubmit=findViewById(R.id.tv_submit);
        mPhone=findViewById(R.id.et_phone);
        mSf = findViewById(R.id.tv_sf);
        mYear = findViewById(R.id.tv_year);
        mMaster = findViewById(R.id.tv_master);
        findViewById(R.id.ll_select).setOnClickListener(this);
        mName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                     if (s.length()>0){
                         isName=true;
                     }else {
                         isName=false;
                     }
                     if (isName&&isPhone&&isSex&&isSelect){
                         mSubmit.setTextColor(getResources().getColor(R.color.white));
                         mSubmit.setBackground(getResources().getDrawable(R.drawable.splash_second_bg_button_wc));
                     }else {
                         mSubmit.setTextColor(getResources().getColor(R.color.black));
                         mSubmit.setBackground(getResources().getDrawable(R.drawable.splash_second_bg_button));

                     }
            }
        });
        mPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>0){
                    isPhone=true;
                }else {
                    isPhone=false;
                }
                if (isName&&isPhone&&isSex&&isSelect){
                    mSubmit.setTextColor(getResources().getColor(R.color.white));
                    mSubmit.setBackground(getResources().getDrawable(R.drawable.splash_second_bg_button_wc));
                }else {
                    mSubmit.setTextColor(getResources().getColor(R.color.black));
                    mSubmit.setBackground(getResources().getDrawable(R.drawable.splash_second_bg_button));

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
        return R.layout.activty_splash_second;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_man:
                sex = "1";
                isSex=true;

                manImg.setBackground(this.getResources().getDrawable(R.mipmap.man_select));
                maleImg.setBackground(this.getResources().getDrawable(R.mipmap.male_no_select));
                if (isName&&isPhone&&isSex&&isSelect){
                    mSubmit.setTextColor(getResources().getColor(R.color.white));
                    mSubmit.setBackground(getResources().getDrawable(R.drawable.splash_second_bg_button_wc));
                }else {
                    mSubmit.setTextColor(getResources().getColor(R.color.black));
                    mSubmit.setBackground(getResources().getDrawable(R.drawable.splash_second_bg_button));

                }
                break;
            case R.id.iv_male:
                sex = "2";
                isSex=true;
                manImg.setBackground(this.getResources().getDrawable(R.mipmap.man_no_select));
                maleImg.setBackground(this.getResources().getDrawable(R.mipmap.male_select));
                if (isName&&isPhone&&isSex&&isSelect){
                    mSubmit.setTextColor(getResources().getColor(R.color.white));
                    mSubmit.setBackground(getResources().getDrawable(R.drawable.splash_second_bg_button_wc));
                }else {
                    mSubmit.setTextColor(getResources().getColor(R.color.black));
                    mSubmit.setBackground(getResources().getDrawable(R.drawable.splash_second_bg_button));

                }
                break;
            case R.id.ll_select:
                if (!isOpen) {
                    isOpen = true;
                    AddressPickTask task = new AddressPickTask(SplashSecondActivity.this);
                    task.setHideProvince(false);
                    task.setHideCounty(false);
                    task.setCallback(new AddressPickTask.Callback() {

                        @Override
                        public void onAddressPicked(final Province.DataBean province, final Province.DataBean.YearBean city, final Province.DataBean.YearBean.MasterBean county) {
                            runOnUiThread(new Runnable() {
                                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                @Override
                                public void run() {
                                    mSf.setText(province.getName()+"");
                                    mYear.setText(city.getValue()+"");
                                    mMaster.setText(county.getValue()+"");
                                    isSelect = true;
                                    if (isName&&isPhone&&isSex&&isSelect){
                                        mSubmit.setTextColor(getResources().getColor(R.color.white));
                                        mSubmit.setBackground(getResources().getDrawable(R.drawable.splash_second_bg_button_wc));
                                    }else {
                                        mSubmit.setTextColor(getResources().getColor(R.color.black));
                                        mSubmit.setBackground(getResources().getDrawable(R.drawable.splash_second_bg_button));

                                    }
                                }
                            });
                        }

                        @Override
                        public void onCancel() {
                            isOpen = false;
                        }

                        @Override
                        public void onSubmit() {
                            isOpen = false;
                        }

                        @Override
                        public void onAddressInitFailed() {

                        }


                    });
                    task.execute("", "", "");
                }
                break;
            case R.id.tv_zkk:
                startActivity(new Intent(SplashSecondActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.tv_submit:
                String phone=mPhone.getText().toString().trim();
                if (!isName){
                    Toast.makeText(SplashSecondActivity.this, "名字不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isPhone){
                    Toast.makeText(SplashSecondActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isMobileNO(phone)){
                    Toast.makeText(SplashSecondActivity.this, "手机号格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isSex){
                    Toast.makeText(SplashSecondActivity.this, "请先选择性别", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!isSelect){
                    Toast.makeText(SplashSecondActivity.this, "请选择身份", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent=new Intent(SplashSecondActivity.this,SplashThirdActivity.class);
                intent.putExtra("phone",phone);
                startActivity(intent);
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
}
