package com.example.jzzq.main.Home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.jzzq.BaseFragment;
import com.example.jzzq.R;
import com.example.jzzq.http.CallUrls;
import com.example.jzzq.http.HttpUtils;
import com.example.jzzq.main.Home.activity.HomeActivity;
import com.example.jzzq.main.Home.activity.TabContentActivity;
import com.example.jzzq.main.Home.adapter.TabContentAdapter;
import com.example.jzzq.main.Home.bean.TabContentBean;
import com.example.jzzq.utils.RecyclerViewUtil;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabContentFragment extends BaseFragment {

    private static final String EXTRA_CONTENT = "content";
    private String content;
    private List<TabContentBean.ResultDataBean> list = new ArrayList<>();
    private TabContentAdapter tabContentAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private LRecyclerView tabContentRecyclerView;
    private LinearLayout empty_view;

    public static TabContentFragment newInstance(String content) {
        Bundle arguments = new Bundle();
        arguments.putString(EXTRA_CONTENT, content);
        TabContentFragment tabContentFragment = new TabContentFragment();
        tabContentFragment.setArguments(arguments);
        return tabContentFragment;
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_tablayout;
    }

    private MyHandler handler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private WeakReference<android.support.v4.app.Fragment> weakReference = null;

        public MyHandler(android.support.v4.app.Fragment activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            TabContentFragment tabContentFragment = (TabContentFragment) weakReference.get();
            switch (msg.what) {
                case 1:
                    if (msg.obj != null) {
                        TabContentBean bean = (TabContentBean) msg.obj;
                        if (bean.isSuccess()) {
                            tabContentFragment.list.clear();
                            for (int i = 0; i < bean.getResultData().size(); i++) {
                                tabContentFragment.list.add(bean.getResultData().get(i));
                            }
                            tabContentFragment.setData(tabContentFragment.list);
                        }
                        break;
                    }
            }
        }
    }


    private void setData(final List<TabContentBean.ResultDataBean> list) {
        Log.e("--------------",list.size()+"");
        if(list.size()==0){
            empty_view.setVisibility(View.VISIBLE);
            tabContentRecyclerView.setVisibility(View.GONE);
        }else{
            tabContentRecyclerView.setVisibility(View.VISIBLE);
            empty_view.setVisibility(View.GONE);
        }
        tabContentAdapter = new TabContentAdapter(getActivity(), list);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(tabContentAdapter);
        tabContentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tabContentRecyclerView.setAdapter(lRecyclerViewAdapter);
        tabContentRecyclerView.refreshComplete(1);
        lRecyclerViewAdapter.notifyDataSetChanged();
        tabContentRecyclerView.setLoadMoreEnabled(false);
        RecyclerViewUtil.setStyle(tabContentRecyclerView);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), TabContentActivity.class);
                intent.putExtra("url", list.get(position).getFurl());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        content = getArguments().getString(EXTRA_CONTENT);
        tabContentRecyclerView = view.findViewById(R.id.recycler_view);
        empty_view = view.findViewById(R.id.empty_view);
        requestData(content);
    }

    private void requestData(String s) {
        Map<String, Object> map = new HashMap<>();
        switch (s) {
            case "精选":
                HttpUtils.get(getActivity(), CallUrls.PFURL4, map, handler, TabContentBean.class, 1);
                break;
            case "热卖":
                HttpUtils.get(getActivity(), CallUrls.PFURL5, map, handler, TabContentBean.class, 1);
                break;
           /* case "日用":
                HttpUtils.get(getActivity(), CallUrls.PFURL7, map, handler, TabContentBean.class, 1);
                break;*/
            case "零食":
                HttpUtils.get(getActivity(), CallUrls.PFURL6, map, handler, TabContentBean.class, 1);
                break;
            case "服装":
                HttpUtils.get(getActivity(), CallUrls.PFURL8, map, handler, TabContentBean.class, 1);
                break;
        }
    }

}
