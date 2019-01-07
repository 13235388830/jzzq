package com.example.jzzq.http;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.ex.HttpException;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.UnrecoverableKeyException;
import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/3/27.
 */

public class HttpUtils {

    private volatile static HttpUtils instance;
    private Handler handler;


    private HttpUtils() {
        handler = new Handler(Looper.getMainLooper());
    }

    public static HttpUtils getInstance() {
        if (instance == null) {
            synchronized (HttpUtils.class) {
                if (instance == null) {
                    instance = new HttpUtils();
                }
            }
        }
        return instance;
    }

    public static <T> void post(final Context context, final String callUrls, Map<String, Object> params, final Handler hander, final Class<T> t, final int whats) {
        final RequestParams requestParams = new RequestParams(callUrls);
        requestParams.setConnectTimeout(5000);
        requestParams.setMaxRetryCount(0);
        for (String in : params.keySet()) {
            Log.e("body", in + ":" + params.get(in));
            requestParams.addBodyParameter(in, (String) params.get(in));
        }
        x.http().post(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(requestParams.getUri(), result);
                Log.e("result", result);
                String str2 = new String(Base64.decode(result.getBytes(), Base64.DEFAULT));
                if (hander != null) {
                    Message msg = new Message();
                    if (msg != null) {
                        Gson gson = new Gson();
                        T obj = gson.fromJson(str2, t);
                        msg.obj = obj;
                    }
                    msg.what = whats;
                    hander.sendMessage(msg);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {


            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }



    public static <T> void get(final Context context, final String callUrls, Map<String, Object> params, final Handler hander, final Class<T> t, final int whats) {
        final RequestParams requestParams = new RequestParams(callUrls);
        requestParams.setConnectTimeout(5000);
        requestParams.setMaxRetryCount(0);
        for (String in : params.keySet()) {
            Log.e("body", in + "" + params.get(in));
            requestParams.addBodyParameter(in, (String) params.get(in));
        }
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e(requestParams.getUri().toString(), result);
                Message msg = new Message();
                if (t != null) {
                    Gson gson = new Gson();
                    T obj = gson.fromJson(result, t);
                    msg.obj = obj;
                }
                Log.e("WeChat",result);
                msg.what = whats;
                hander.sendMessage(msg);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {


            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });


    }


    /**
     * get有参请求
     */
    public static <T> void doYGet(CallUrls callUrls, final List<Object> map, final Handler hander, final Class<T> t, final int whats) {
        StringBuffer sb = new StringBuffer();
        for (Object str : map) {
            sb.append("/" + str);
        }
        final RequestParams params = new RequestParams(callUrls.getUrl() + sb);
        params.setConnectTimeout(5000);
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                if (hander != null) {
                    Message msg = new Message();
                    if (t != null) {
                        Gson gson = new Gson();
                        Log.e("------------", result.toString());
                        T obj = gson.fromJson(result, t);
                        msg.obj = obj;
                    }
                    msg.what = whats;
                    hander.sendMessage(msg);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                Log.e("","");
            }
        });
    }

    public static <T> void doPostJson(final CallUrls callUrls, JSONObject json, final List<Object> map, final Handler handler, final Class<T> tClass, final int whats) {
        StringBuffer sb = new StringBuffer();
        for (Object str : map) {
            sb.append("/" + str);
        }
        final RequestParams params = new RequestParams(callUrls.getUrl() + sb);
        params.setConnectTimeout(5000);
        params.setBodyContent(json.toString());
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("result", result);
                if (handler != null) {
                    Message msg = new Message();
                    if (msg != null) {
                        try {
                            Gson gson = new Gson();
                            T obj = gson.fromJson(result, tClass);
                            msg.obj = obj;
                        } catch (Exception e) {
                            result = result.replace("\"data\":", "\"data1\":");
                            Gson gson = new Gson();
                            T obj = gson.fromJson(result, tClass);
                            msg.obj = obj;
                        }
                    }
                    msg.what = whats;
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }



}