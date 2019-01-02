package com.example.jzzq.main.Home.fragment;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
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

import com.example.jzzq.BaseFragment;
import com.example.jzzq.MainActivity;
import com.example.jzzq.R;
import com.example.jzzq.SplashSecondActivity;
import com.example.jzzq.http.CallUrls;
import com.example.jzzq.http.HttpUtils;
import com.example.jzzq.inter.LocationInterface;
import com.example.jzzq.main.Home.activity.HeadActivity;
import com.example.jzzq.main.Home.activity.HomeActivity;
import com.example.jzzq.main.Home.adapter.HomeAdapter;
import com.example.jzzq.main.Home.bean.HomeBean;
import com.example.jzzq.utils.AddressPickTask;
import com.example.jzzq.utils.LocationUtil;
import com.example.jzzq.utils.RecyclerViewUtil;
import com.example.jzzq.utils.ToastUtil;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.Inflater;

import cn.addapp.pickers.entity.Province;

public class HomeFragment extends BaseFragment {

    private LRecyclerView homeRecycleview;
    private HomeAdapter homeAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<HomeBean.DataBean.ListBean> mList = new ArrayList<>();
    private static final int REQUEST_LOCATION_PERMISSION = 100;//请求定位权限的请求码
    private static final int REQUEST_READ_PERMISSION = 101;//请求文件读写权限的请求码
    private TextView switchText;
    private ImageView ivHead;
    private boolean isOpen = false;
    private  String mCity="";
    private boolean isLocation=false;
    private  boolean isFirst=true;
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
            switch (msg.what) {
                case 1:
                    if (msg.obj != null) {
                        HomeBean bean = (HomeBean) msg.obj;
                        if (bean.getCode() == 0) {
                            homeFragment.mList.clear();
                            for (int i = 0; i < bean.getData().getList().size(); i++) {
                                if (homeFragment.isLocation) {
                                    bean.getData().getList().get(i).setCity(homeFragment.mCity);
                                }
                                homeFragment.mList.add(bean.getData().getList().get(i));
                            }
                            homeFragment.isLocation=false;
                            homeFragment.setData(homeFragment.mList);
                        }
                        break;
                    }
            }
        }
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        homeRecycleview = view.findViewById(R.id.home_recycleview);
        switchText = view.findViewById(R.id.switch_text);
        ivHead = view.findViewById(R.id.iv_head);
        requestData();
        startLocation();
        LocationUtil.getInstance().setLocationInterface(new LocationInterface() {
            @Override
            public void getCity(String city) {
                mCity=city;
                showDialog(city,getActivity());
            }
        });
    }
    private  void showDialog( final String s, Context context){
        String content="定位到"+s+",是否切换？";
        final Dialog dialog=new Dialog(context,R.style.MyDialog);
        View view= LayoutInflater.from(context).inflate(R.layout.qh_location,null,false);
        TextView mContent= (TextView) view.findViewById(R.id.tv_content);
        mContent.setText(content);
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
                isLocation=true;
                switchText.setText(s);
                requestData();
            }
        });
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
    @Override
    protected void initListener() {
        super.initListener();
        switchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });

        homeRecycleview.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                homeRecycleview.refreshComplete(1);
            }
        });

        ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), HeadActivity.class));
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
    private void setData(List<HomeBean.DataBean.ListBean> list) {
        homeAdapter = new HomeAdapter(getActivity(), list);
        View headView=  LayoutInflater.from(getActivity()).inflate(R.layout.home_adpter_head,null);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(homeAdapter);
        lRecyclerViewAdapter.addHeaderView(headView);
        homeRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeRecycleview.setAdapter(lRecyclerViewAdapter);
        homeRecycleview.refreshComplete(1);
        lRecyclerViewAdapter.notifyDataSetChanged();
        homeRecycleview.setLoadMoreEnabled(false);
        homeRecycleview.setPullRefreshEnabled(false);
        RecyclerViewUtil.setStyle(homeRecycleview);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.putExtra("id", mList.get(position).getId());
                startActivity(intent);
            }
        });
    }

    public void requestData() {
        int min=1;
        int max=4;
        Random random = new Random();
        int num = random.nextInt(max)%(max-min+1) + min;
        final Map<String, Object> map = new HashMap<>();
        if (isFirst){
            map.put("city", "1");
            isFirst=false;
            switchText.setText("北京");
        }else {
            map.put("city", String.valueOf(num));
        }
        HttpUtils.post(getActivity(), CallUrls.PFURL1, map, handler, HomeBean.class, 1);

    }


//    private void initDialog() throws InterruptedException {
//        TextView bjTxt, shTxt, szTxt, gzTxt, qxTxt;
//        final Dialog dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
//        //填充对话框的布局
//        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.home_dialog, null);
//        bjTxt = inflate.findViewById(R.id.bj_text);
//        shTxt = inflate.findViewById(R.id.sh_text);
//        szTxt = inflate.findViewById(R.id.sz_text);
//        gzTxt = inflate.findViewById(R.id.gz_text);
//        qxTxt = inflate.findViewById(R.id.qx_text);
//        bjTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switchText.setText("北京");
//                requestData("1");
//                dialog.dismiss();
//            }
//        });
//        shTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switchText.setText("上海");
//                requestData("3");
//                dialog.dismiss();
//            }
//        });
//        szTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switchText.setText("深圳");
//                requestData("2");
//                dialog.dismiss();
//            }
//        });
//        gzTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                switchText.setText("广州");
//                requestData("4");
//                dialog.dismiss();
//            }
//        });
//        qxTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dialog.dismiss();
//            }
//        });
//        //将布局设置给Dialog
//        dialog.setContentView(inflate);
//        dialog.setCanceledOnTouchOutside(true);
//        //获取当前Activity所在的窗体
//        Window dialogWindow = dialog.getWindow();
//        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
//        lp.width = -1;
//        dialogWindow.setAttributes(lp);
//        //设置Dialog从窗体底部弹出
//        dialogWindow.setGravity(Gravity.BOTTOM);
//        dialog.show();//显示对话框
//    }

}
