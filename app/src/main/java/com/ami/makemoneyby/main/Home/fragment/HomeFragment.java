package com.ami.makemoneyby.main.Home.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ami.makemoneyby.BaseFragment;
import com.ami.makemoneyby.R;
import com.ami.makemoneyby.http.CallUrls;
import com.ami.makemoneyby.http.HttpUtils;
import com.ami.makemoneyby.main.Home.activity.HeadActivity;
import com.ami.makemoneyby.main.Home.activity.HomeActivity;
import com.ami.makemoneyby.main.Home.adapter.HomeAdapter;
import com.ami.makemoneyby.main.Home.bean.HomeBean;
import com.ami.makemoneyby.utils.RecyclerViewUtil;
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

public class HomeFragment extends BaseFragment {

    private LRecyclerView homeRecycleview;
    private HomeAdapter homeAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private List<HomeBean.DataBean.ListBean> mList = new ArrayList<>();

    private TextView switchText;
    private ImageView ivHead;

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
                                homeFragment.mList.add(bean.getData().getList().get(i));
                            }
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
        requestData("1");
    }

    @Override
    protected void initListener() {
        super.initListener();
        switchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    initDialog();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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

    private void setData(List<HomeBean.DataBean.ListBean> list) {
        homeAdapter = new HomeAdapter(getActivity(), list);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(homeAdapter);
        homeRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeRecycleview.setAdapter(lRecyclerViewAdapter);
        homeRecycleview.refreshComplete(1);
        lRecyclerViewAdapter.notifyDataSetChanged();
        homeRecycleview.setLoadMoreEnabled(false);
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

    public void requestData(String num) {
        final Map<String, Object> map = new HashMap<>();
        map.put("city", num);
        HttpUtils.post(getActivity(), CallUrls.PFURL1, map, handler, HomeBean.class, 1);

    }


    private void initDialog() throws InterruptedException {
        TextView bjTxt, shTxt, szTxt, gzTxt, qxTxt;
        final Dialog dialog = new Dialog(getActivity(), R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.home_dialog, null);
        bjTxt = inflate.findViewById(R.id.bj_text);
        shTxt = inflate.findViewById(R.id.sh_text);
        szTxt = inflate.findViewById(R.id.sz_text);
        gzTxt = inflate.findViewById(R.id.gz_text);
        qxTxt = inflate.findViewById(R.id.qx_text);
        bjTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchText.setText("北京");
                requestData("1");
                dialog.dismiss();
            }
        });
        shTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchText.setText("上海");
                requestData("3");
                dialog.dismiss();
            }
        });
        szTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchText.setText("深圳");
                requestData("2");
                dialog.dismiss();
            }
        });
        gzTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchText.setText("广州");
                requestData("4");
                dialog.dismiss();
            }
        });
        qxTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        dialog.setCanceledOnTouchOutside(true);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = -1;
        dialogWindow.setAttributes(lp);
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialog.show();//显示对话框
    }

}
