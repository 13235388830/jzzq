package com.example.jzzq.main.Home.activity;

import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jzzq.BaseActivity;
import com.example.jzzq.R;
import com.mingle.widget.ShapeLoadingDialog;

public class TabContentActivity extends BaseActivity {

    private String url;
    private Toolbar toolbar;
    private TextView toolText;

    private WebView webView;
    private WebSettings webSettings;
    private String javascripts;
    private ShapeLoadingDialog shapeLoadingDialog;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_tabcontent;
    }

    @Override
    protected void initView() {
        super.initView();

        url = getIntent().getStringExtra("url");

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

        initWeb(url);

        findViewById(R.id.tv_fuzhi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(TabContentActivity.this, v, url);
            }
        });
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
                shapeLoadingDialog = new ShapeLoadingDialog.Builder(TabContentActivity.this)
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

    private void showDialog(Context context, View v, final String url) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_tishi, null, false);
        view.findViewById(R.id.tv_fuzhi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(url);
                Toast.makeText(TabContentActivity.this, "复制成功，可以发给朋友们了。", Toast.LENGTH_LONG).show();
            }
        });
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialog.setCanceledOnTouchOutside(true);
        lp.width = LinearLayout.LayoutParams.MATCH_PARENT;
        lp.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.show();
    }

}
