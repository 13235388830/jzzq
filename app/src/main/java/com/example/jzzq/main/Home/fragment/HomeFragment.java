package com.example.jzzq.main.Home.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jzzq.BaseFragment;
import com.example.jzzq.HomeWebViewActivity;
import com.example.jzzq.R;
import com.example.jzzq.SplashSecondActivity;
import com.example.jzzq.bean.HotCityBean;
import com.example.jzzq.inter.LocationInterface;
import com.example.jzzq.main.Home.activity.HeadActivity;
import com.example.jzzq.utils.AddressPickTask2;
import com.example.jzzq.utils.LocationUtil;
import com.example.jzzq.utils.PinyinUtils;
import com.example.jzzq.utils.RecyclerViewUtil;
import com.example.jzzq.utils.ToastUtil;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import cn.addapp.pickers.entity.Province;
import cn.addapp.pickers.entity.Province2;

public class HomeFragment extends BaseFragment {
    private static final int REQUEST_LOCATION_PERMISSION = 100;//请求定位权限的请求码
    private static final int REQUEST_READ_PERMISSION = 101;//请求文件读写权限的请求码
    private TextView switchText;
    private ImageView ivHead;
    private boolean isOpen = false;
    private String mCity = "";
    private WebView mWebView;
    List<HotCityBean> mList = new ArrayList<>();

    //    View headView;
    @Override
    protected int getLayoutID() {
        return R.layout.fragment_home;
    }

    private MyHandler handler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private WeakReference<Fragment> weakReference = null;

        public MyHandler(Fragment activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            HomeFragment homeFragment = (HomeFragment) weakReference.get();
//            switch (msg.what) {
//                case 1:
//                    if (msg.obj != null) {
//                        HomeBean bean = (HomeBean) msg.obj;
//                        if (bean.getCode() == 0) {
//                            homeFragment.mList.clear();
//                            for (int i = 0; i < bean.getData().getList().size(); i++) {
//                                if (homeFragment.isLocation) {
//                                    bean.getData().getList().get(i).setCity(homeFragment.mCity);
//                                }
//                                homeFragment.mList.add(bean.getData().getList().get(i));
//                            }
//                            homeFragment.isLocation=false;
//                            homeFragment.setData(homeFragment.mList);
//                        }
//                        break;
//                    }
//            }
        }
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        startLocation();
        mList.add(new HotCityBean("北京","bj"));
        mList.add(new HotCityBean("上海","sh"));
        mList.add(new HotCityBean("广州","gz"));
        mList.add(new HotCityBean("深圳","sz"));
        mList.add(new HotCityBean("石家庄","sjz"));
        mList.add(new HotCityBean("郑州","zz"));
        mList.add(new HotCityBean("济南","jn"));
        mList.add(new HotCityBean("青岛","qd"));
        mList.add(new HotCityBean("合肥","hf"));
        mList.add(new HotCityBean("太原","ty"));
        mList.add(new HotCityBean("西安","xa"));
        mList.add(new HotCityBean("武汉","wf"));
        mList.add(new HotCityBean("长沙","cs"));
        mList.add(new HotCityBean("长春","cc"));
        mList.add(new HotCityBean("福州","fz"));
        mList.add(new HotCityBean("昆明","km"));
        mList.add(new HotCityBean("天津","tj"));
        mList.add(new HotCityBean("重庆","cq"));
        mList.add(new HotCityBean("杭州","hz"));
        mList.add(new HotCityBean("贵阳","gy"));
        mList.add(new HotCityBean("成都","cd"));
        mList.add(new HotCityBean("沈阳","sy"));
        mList.add(new HotCityBean("大连","dl"));
        mList.add(new HotCityBean("南京","nj"));
        mList.add(new HotCityBean("苏州","sz"));
        mList.add(new HotCityBean("南昌","nc"));
        mList.add(new HotCityBean("厦门","xm"));
        mList.add(new HotCityBean("济南","jn"));
        switchText = view.findViewById(R.id.switch_text);
        mWebView = view.findViewById(R.id.wv_view);
        switchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpen) {
                    isOpen = true;
                    AddressPickTask2 task = new AddressPickTask2(getActivity());
                    task.setHideProvince(false);
                    task.setHideCounty(true);
                    task.setCallback(new AddressPickTask2.Callback() {


                        @Override
                        public void onAddressPicked(Province2.DataBean province, Province2.DataBean.ChildsBeanX city, Province2.DataBean.ChildsBeanX.ChildsBean county) {
                            mCity = city.getCode();
                            switchText.setText(city.getName());
                            initSettingUrl(mCity);
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
                            isOpen = false;
                        }

                    });
                    task.execute("", "", "");

                }
            }
        });
        ivHead = view.findViewById(R.id.iv_head);
        view.findViewById(R.id.iv_jrzr).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeWebViewActivity.class);
                intent.putExtra("title", "今日最热");
                intent.putExtra("url", "https://m.doumi.com/bj/x/post/post_20181022123141/?ca_campaign=post_20181022123141&from=yyw_mobile_hot&from=yyw_mobile_hot&domain=shanghai");
                startActivity(intent);
            }
        });
        view.findViewById(R.id.iv_zjty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeWebViewActivity.class);
                intent.putExtra("title", "在家躺赢");
                intent.putExtra("url", "https://m.doumi.com/bj/x/post/post_20181009112124/?ca_campaign=post_20181009112124&from=yyw_mobile_taoke&domain=" + mCity);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.iv_qsjz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HomeWebViewActivity.class);
                intent.putExtra("title", "轻松赚钱");
                intent.putExtra("url", "https://m.doumi.com/bj/x/post/post_20181009112441/?ca_campaign=post_20181009112441&from=yyw_mobile_qingsong&domain=" + mCity);
                startActivity(intent);
            }
        });
        LocationUtil.getInstance().setLocationInterface(new LocationInterface() {
            @Override
            public void getCity(String city) {
                mCity = city;
                switchText.setText(mCity);
                showDialog(city, getActivity());
            }
        });

    }

    private void showDialog(final String s, Context context) {
        String content = "已经定位到";
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.qh_location, null, false);
        TextView mContent = (TextView) view.findViewById(R.id.tv_content);
        TextView mCity = view.findViewById(R.id.tv_city);
        mContent.setText(content);
        mCity.setText(s);
        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                switchText.setText(s);
                for (int i = 0; i < mList.size(); i++) {
                    if (s.equals(mList.get(i).getName() + "市")) {
                        initSettingUrl(mList.get(i).getPy());
                        return;
                    }
                }
                initSettingUrl(PinyinUtils.toPinyinString(s.substring(0,s.length()-1)));

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

    @Override
    protected void initListener() {
        super.initListener();
        ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HeadActivity.class));
            }
        });

    }

    private void initSettingUrl(String s) {

        WebSettings webSettings = mWebView.getSettings();
//支持缩放，默认为true。
        webSettings.setSupportZoom(false);
//调整图片至适合webview的大小
        webSettings.setUseWideViewPort(true);
// 缩放至屏幕的大小
        webSettings.setLoadWithOverviewMode(true);
//设置默认编码
        webSettings.setDefaultTextEncodingName("utf-8");
////设置自动加载图片
        webSettings.setLoadsImagesAutomatically(true);
        //多窗口
        webSettings.supportMultipleWindows();
//获取触摸焦点
        mWebView.requestFocusFromTouch();
//允许访问文件
        webSettings.setAllowFileAccess(true);
//开启javascript
        webSettings.setJavaScriptEnabled(true);
        //支持通过JS打开新窗口
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
//提高渲染的优先级
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        //支持内容重新布局
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//关闭webview中缓存
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.loadUrl("https://m.doumi.com/bj/x/post/post_20181022123141/?ca_campaign=post_20181022123141&from=yyw_mobile_hot&from=yyw_mobile_hot&domain=" + s);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

//                view.loadUrl(url);

                Intent intent=new Intent(getActivity(),HomeWebViewActivity.class);
                intent.putExtra("url",url);
                intent.putExtra("title", "详情");
                getActivity().startActivity(intent);
//                return super.shouldOverrideUrlLoading(view, url);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                url = "javascript:function hideOther() {" + "" +
//                        "document.getElementsByClassName('mod-header logo-header')[0].style.display = 'none';}";
//
//                //创建方法
//                view.loadUrl(url);
//                //加载方法
//                view.loadUrl("javascript:hideOther();");
            }

            @Override
            public void onPageFinished(WebView view, String url) {


            }
        });
    }

    /**
     * 开启定位
     */
    private void startLocation() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION_PERMISSION);
            } else {
                LocationUtil.getInstance().startLocation();
            }
        } else {
            LocationUtil.getInstance().startLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LocationUtil.getInstance().startLocation();
                } else {
                    ToastUtil.show(getString(R.string.location_permission_refused));
                }
                break;
            case REQUEST_READ_PERMISSION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    ToastUtil.show(getString(R.string.storage_permission_refused));
                }
                break;
        }
    }

}
