package com.example.jzzq.main.Information.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.jzzq.BaseActivity;
import com.example.jzzq.R;
import com.example.jzzq.http.CallUrls;
import com.example.jzzq.http.HttpUtils;
import com.example.jzzq.main.Information.bean.InformationActivityBean;
import com.mingle.widget.ShapeLoadingDialog;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class InformationActivity extends BaseActivity {
    private String id;
    private Toolbar toolbar;
    private TextView toolText;

    private WebView webView;
    private WebSettings webSettings;
    private String javascripts;
    private ShapeLoadingDialog shapeLoadingDialog;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_information;
    }

    private MyHandler handler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private WeakReference<Activity> weakReference = null;

        public MyHandler(Activity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            InformationActivity informationActivity = (InformationActivity) weakReference.get();
            switch (msg.what) {
                case 1:
                    if (msg.obj != null) {
                        InformationActivityBean bean = (InformationActivityBean) msg.obj;
                        if (bean.getStatus() == 0) {
                            informationActivity.initWeb(bean.getData().getContentUrl());
                        }
                        break;
                    }
            }
        }
    }


    @Override
    protected void initView() {
        super.initView();

        id = getIntent().getStringExtra("id");

        toolbar = findViewById(R.id.tool_bar);
        toolText = findViewById(R.id.toolbar_text);
        toolText.setText("详情内容");
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        webView = findViewById(R.id.web_view);

        requestData();
    }

    public void requestData() {
        final Map<String, Object> map = new HashMap<>();
        map.put("uId", "345727");
        map.put("id", String.valueOf(id));
        HttpUtils.get(this, CallUrls.PFURL9, map, handler, InformationActivityBean.class, 1);

    }

    private void initWeb(String url) {
        webView.loadUrl(url);
        webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);//启用加速，否则滑动界面不流畅
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setBuiltInZoomControls(false); // 设置显示缩放按钮
        webSettings.setAppCacheEnabled(true);//是否使用缓存
        webSettings.setDatabaseEnabled(true);
        webSettings.setSupportZoom(false); // 支持缩放
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                shapeLoadingDialog = new ShapeLoadingDialog.Builder(InformationActivity.this)
                        .loadText("加载中...")
                        .build();
                shapeLoadingDialog.setCancelable(false);
                shapeLoadingDialog.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);

                javascripts = "javascript:function hideOther() {" + "" +
                        "document.getElementsByClassName('phone-header')[0].style.display = 'none';}";

                //创建方法
                view.loadUrl(javascripts);
                //加载方法
                view.loadUrl("javascript:hideOther();");
                webView.setVisibility(View.VISIBLE);
                shapeLoadingDialog.dismiss();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                if (url.equals("http://m.310win.com")) {
                    view.loadUrl("https://m.dididapiao.com/live/football/score?matchType=1&gameId=407&agentId=100107");
                    return true;
                }
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                view.loadUrl("file:///android_asset/error.html");//加载本地网页的路径
            }
        });

    }
}
