package com.example.jzzq;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jzzq.downloadapp.DownloadService;
import com.example.jzzq.downloadapp.NumberProgressBar;
import com.example.jzzq.downloadapp.VersionUpdate;
import com.example.jzzq.downloadapp.VersionUpdateImpl;
import com.example.jzzq.utils.ADIntentUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Created by admin on 2017/7/5.
 */

public class WebViewActivity extends BaseActivity implements VersionUpdateImpl {
    @ViewInject(R.id.content_id)
    private LinearLayout content_layout;
    @ViewInject(R.id.relat_bar)
    private RelativeLayout relat_bar;
    @ViewInject(R.id.back)
    private ImageView back;
    @ViewInject(R.id.close)
    private ImageView close;
    @ViewInject(R.id.title)
    private TextView titles;
    @ViewInject(R.id.webview)
    private WebView webView;
    @ViewInject(R.id.myProgressBar)
    private ProgressBar bar;
    @ViewInject(R.id.ll_dialog)
    LinearLayout mLlDialog;
    private boolean isTmast = false;
    private ValueCallback<Uri> uploadMessage;
    private ValueCallback<Uri[]> uploadMessageAboveL;
    private final static int FILE_CHOOSER_RESULT_CODE = 10000;
    private String title = "";
    private String Durl = "";
    private String img_url = "";
    private ADIntentUtils adIntentUtils = null;
    private String isFromSplash = "0";
    private NumberProgressBar bnp;
    private boolean isBindService;
    String url = "";

    @Override
    protected int getLayoutID() {
        return R.layout.activity_webview;
    }

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DownloadService.DownloadBinder binder = (DownloadService.DownloadBinder) service;
            DownloadService downloadService = binder.getService();

            //接口回调，下载进度
            downloadService.setOnProgressListener(new DownloadService.OnProgressListener() {
                @Override
                public void onProgress(float fraction) {
//                    LogUtil.i(TAG, "下载进度：" + fraction);
                    bnp.setProgress((int) (fraction * 100));
                    mLlDialog.setVisibility(View.VISIBLE);
                    //判断是否真的下载完成进行安装了，以及是否注册绑定过服务
                    if (fraction == DownloadService.UNBIND_SERVICE && isBindService) {
                        unbindService(conn);
                        mLlDialog.setVisibility(View.GONE);
                        isBindService = false;
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void initSetting(String url) {
        WebSettings webSetting = webView.getSettings();
        webSetting.setAllowFileAccess(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSetting.setSupportZoom(true);
        webSetting.setBuiltInZoomControls(true);
        webSetting.setUseWideViewPort(true);
        webSetting.setSupportMultipleWindows(false);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setDatabaseEnabled(true);
        webSetting.setDomStorageEnabled(true);
        webSetting.setJavaScriptEnabled(true);
        webSetting.setGeolocationEnabled(true);
        webSetting.setAppCacheMaxSize(Long.MAX_VALUE);
        webSetting.setAppCachePath(this.getDir("appcache", 0).getPath());
        webSetting.setDatabasePath(this.getDir("databases", 0).getPath());
        webSetting.setGeolocationDatabasePath(this.getDir("geolocation", 0).getPath());
        // webSetting.setPageCacheCapacity(IX5WebSettings.DEFAULT_CACHE_CAPACITY);
        webSetting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        // webSetting.setRenderPriority(WebSettings.RenderPriority.HIGH);
        // webSetting.setPreFectch(true);
        webView.addJavascriptInterface(new WebAppInterface(), "Android");

//        webView.loadUrl("http://h5.yibeixxkj.com/tt.html");
        webView.loadUrl(url);
        adIntentUtils = new ADIntentUtils(WebViewActivity.this);
        CookieSyncManager.createInstance(this);
        CookieSyncManager.getInstance().sync();
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                if (url.substring(url.length() - 3, url.length()).equals("apk")) {
                    VersionUpdate.checkVersion(WebViewActivity.this,url);
                }
                if (adIntentUtils.shouldOverrideUrlLoadingByApp(view, url)) {
                    isTmast = true;
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    bar.setVisibility(View.GONE);
                } else {
                    if (View.INVISIBLE == bar.getVisibility()) {
                        bar.setVisibility(View.VISIBLE);
                    }
                    bar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

            // For Android < 3.0
            public void openFileChooser(ValueCallback<Uri> valueCallback) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }

            // For Android  >= 3.0
            public void openFileChooser(ValueCallback valueCallback, String acceptType) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }

            //For Android  >= 4.1
            public void openFileChooser(ValueCallback<Uri> valueCallback, String acceptType, String capture) {
                uploadMessage = valueCallback;
                openImageChooserActivity();
            }

            // For Android >= 5.0
            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                uploadMessageAboveL = filePathCallback;
                openImageChooserActivity();
                return true;
            }
        });
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(final String arg0, String arg1, String arg2, String arg3, long arg4) {
                if (isTmast) {
                    if (!TextUtils.isEmpty(arg0) && arg0.startsWith("http://") || arg0.startsWith("https://")) {
                        Durl = arg0;
                        if (!isFromSplash.equals("1")) {

                        }
                    } else {
                        Uri uri = Uri.parse(arg0);
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                } else {
                    Durl = arg0;
                    if (!isFromSplash.equals("1")) {
                    }
                }
            }
        });
    }

    @Override
    protected void initView() {
        title = getIntent().getStringExtra("title");
        img_url = getIntent().getStringExtra("img_url");

        if (!TextUtils.isEmpty(title)) {
            titles.setText(title);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (webView != null && webView.canGoBack()) {
                    webView.goBack();
                } else {
                    finish();
                }

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (getIntent() != null) {
            url = getIntent().getStringExtra("url");
            if (getIntent().getStringExtra("isFromSplash") != null) {
                isFromSplash = getIntent().getStringExtra("isFromSplash");
            }
        }
        bnp = (NumberProgressBar) findViewById(R.id.number_bar);


    }

    private void openImageChooserActivity() {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "Image Chooser"), FILE_CHOOSER_RESULT_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_CHOOSER_RESULT_CODE) {
            if (null == uploadMessage && null == uploadMessageAboveL) return;
            Uri result = data == null || resultCode != RESULT_OK ? null : data.getData();
            if (uploadMessageAboveL != null) {
                onActivityResultAboveL(requestCode, resultCode, data);
            } else if (uploadMessage != null) {
                uploadMessage.onReceiveValue(result);
                uploadMessage = null;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initSetting(url);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void onActivityResultAboveL(int requestCode, int resultCode, Intent intent) {
        if (requestCode != FILE_CHOOSER_RESULT_CODE || uploadMessageAboveL == null)
            return;
        Uri[] results = null;
        if (resultCode == Activity.RESULT_OK) {
            if (intent != null) {
                String dataString = intent.getDataString();
                ClipData clipData = intent.getClipData();
                if (clipData != null) {
                    results = new Uri[clipData.getItemCount()];
                    for (int i = 0; i < clipData.getItemCount(); i++) {
                        ClipData.Item item = clipData.getItemAt(i);
                        results[i] = item.getUri();
                    }
                }
                if (dataString != null)
                    results = new Uri[]{Uri.parse(dataString)};
            }
        }
        uploadMessageAboveL.onReceiveValue(results);
        uploadMessageAboveL = null;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView != null && webView.canGoBack()) {
                webView.goBack();
                return true;
            } else
                return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        if (webView != null)
//            webView.destroy();
        super.onDestroy();
    }

    private Dialog dialog;


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 112:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
//                    Intent it = new Intent(WebViewActivity.this, AppDownloadManagerActivity.class);
//                    it.putExtra("name", title);
//                    it.putExtra("url", Durl);
//                    it.putExtra("img_url", img_url);
//                    startActivity(it);
                } else goToSettingActivity();
                break;
            default:
                break;
        }
    }


    @Override
    public void bindService(String apkUrl) {
        Intent intent = new Intent(this, DownloadService.class);
        intent.putExtra(DownloadService.BUNDLE_KEY_DOWNLOAD_URL, apkUrl);
        isBindService = bindService(intent, conn, BIND_AUTO_CREATE);
    }

    public class WebAppInterface {


        public WebAppInterface() {
        }

        @JavascriptInterface
        public void close() {
            WebViewActivity.this.finish();
        }

        @JavascriptInterface
        public void openQQ(String qq) {
            String url = "mqqwpa://im/chat?chat_type=wpa&uin=" + qq;
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        }
    }

    /**
     * 打开权限设置页面
     */
    private void goToSettingActivity() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(WebViewActivity.this);
        builder.setTitle("温馨提示");
        builder.setMessage("您拒绝了应用使用存储权限，将无法正常使用下载功能，是否修改权限设置？");
        builder.setNegativeButton("否", null);
//        builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                JumpPermissionManagement.GoToSetting(WebViewActivity.this);
//            }
//        });
        builder.show();
    }

}
