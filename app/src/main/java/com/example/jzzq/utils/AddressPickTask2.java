package com.example.jzzq.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Looper;
import android.text.TextUtils;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;
import com.google.gson.Gson;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import cn.addapp.pickers.entity.Province;
import cn.addapp.pickers.entity.Province2;
import cn.addapp.pickers.listeners.OnLinkageListener2;
import cn.addapp.pickers.picker.AddressPicker2;


/**
 * 获取地址数据并显示地址选择器
 * @author matt
 * blog: addapp.cn
 */
public class AddressPickTask2 extends AsyncTask<String, Void, ArrayList<Province2.DataBean>> {
    private Activity activity;
    private ProgressDialog dialog;
    private Callback callback;
    private String selectedProvince = "", selectedCity = "", selectedCounty = "";
    private boolean hideProvince = false;
    private boolean hideCounty = false;
    public  AddressPickTask2(Activity activity) {
        this.activity = activity;
    }

    public void setHideProvince(boolean hideProvince) {
        this.hideProvince = hideProvince;
    }

    public void setHideCounty(boolean hideCounty) {
        this.hideCounty = hideCounty;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(activity, null, "正在初始化数据...", true, true);
    }

    @Override
    protected ArrayList<Province2.DataBean> doInBackground(String... params) {
        if (params != null) {
            switch (params.length) {
                case 1:
                    selectedProvince = params[0];
                    break;
                case 2:
                    selectedProvince = params[0];
                    selectedCity = params[1];
                    break;
                case 3:
                    selectedProvince = params[0];
                    selectedCity = params[1];
                    selectedCounty = params[2];
                    break;
                default:
                    break;
            }
        }
        final ArrayList<Province2.DataBean> data = new ArrayList<>();
        AVQuery<AVObject> avQuery = new AVQuery<>("provincial_data");
        avQuery.getInBackground("5c2ee81a67f35600630492e4", new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                // object 就是 id 为 558e20cbe4b060308e3eb36c 的 Todo 对象实例
                String location = avObject.getString("data");
                Province2 province=new Gson().fromJson(location,Province2.class);
                for (int i=0;i<province.getData().size();i++){
                    data.add(province.getData().get(i));
                }
                onPostExecute(data);
            }
        });
        return data;
    }

    @Override
    protected void onPostExecute(ArrayList<Province2.DataBean> result) {

        dialog.dismiss();
        if (result.size() > 0) {
            AddressPicker2 picker = new AddressPicker2(activity, result);
            picker.setCanLoop(false);
            picker.setHideProvince(hideProvince);
            picker.setHideCounty(hideCounty);
            picker.setLineVisible(true);
            picker.setWheelModeEnable(true);
            if (hideCounty) {
                picker.setColumnWeight(1 / 3.0f, 2 / 3.0f);//将屏幕分为3份，省级和地级的比例为1:2
            } else {
                picker.setColumnWeight(2 / 8.0f, 3 / 8.0f, 3 / 8.0f);//省级、地级和县级的比例为2:3:3
            }
            picker.setSelectedItem(selectedProvince, selectedCity, selectedCounty);
            picker.setOnLinkageListener(callback);
            picker.show();
        } else {
            callback.onAddressInitFailed();
        }

    }

    public   interface Callback extends OnLinkageListener2 {
        void onAddressInitFailed();

    }
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
