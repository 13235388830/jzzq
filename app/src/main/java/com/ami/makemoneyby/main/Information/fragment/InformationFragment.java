package com.ami.makemoneyby.main.Information.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ami.makemoneyby.BaseFragment;
import com.ami.makemoneyby.R;
import com.ami.makemoneyby.http.CallUrls;
import com.ami.makemoneyby.http.HttpUtils;
import com.ami.makemoneyby.main.Home.activity.HomeActivity;
import com.ami.makemoneyby.main.Home.adapter.HomeAdapter;
import com.ami.makemoneyby.main.Home.bean.HomeBean;
import com.ami.makemoneyby.main.Home.fragment.HomeFragment;
import com.ami.makemoneyby.main.Information.activity.InformationActivity;
import com.ami.makemoneyby.main.Information.adapter.InformationAdapter;
import com.ami.makemoneyby.main.Information.bean.InforamtionBean;
import com.ami.makemoneyby.utils.RecyclerViewUtil;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.mingle.widget.ShapeLoadingDialog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InformationFragment extends BaseFragment {

    private TextView toolText;
    private LRecyclerView informationRecyclerView;
    private int page = 1;
    private int pageSize = 10;
    private List<InforamtionBean.DataBean> mList = new ArrayList<>();
    private InformationAdapter informationAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private ShapeLoadingDialog shapeLoadingDialog;

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_information;
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
            InformationFragment informationFragment = (InformationFragment) weakReference.get();
            switch (msg.what) {
                case 1:
                    if (msg.obj != null) {
                        InforamtionBean bean = (InforamtionBean) msg.obj;
                        if (informationFragment.page == 1) {
                            informationFragment.mList.clear();
                            informationFragment.informationRecyclerView.setNoMore(false);
                        }
                        informationFragment.shapeLoadingDialog.dismiss();
                        informationFragment.mList.addAll(bean.getData());
                        informationFragment.setData(informationFragment.mList);

                        break;
                    }
            }
        }
    }

    private void setData(final List<InforamtionBean.DataBean> list) {
        informationAdapter = new InformationAdapter(getActivity(), list);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(informationAdapter);
        informationRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        informationRecyclerView.setAdapter(lRecyclerViewAdapter);
        informationRecyclerView.refreshComplete(1);
        lRecyclerViewAdapter.notifyDataSetChanged();
        RecyclerViewUtil.setStyle(informationRecyclerView);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), InformationActivity.class);
                intent.putExtra("id", list.get(position).getId()+"");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        toolText = view.findViewById(R.id.toolbar_text);
        toolText.setText("资讯");

        informationRecyclerView = view.findViewById(R.id.information_recyclerview);

        requestData();
    }

    @Override
    protected void initListener() {
        super.initListener();
        informationRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                requestData();

            }
        });
        informationRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (page * pageSize < 35) {
                    page++;
                    requestData();
                } else {
                    informationRecyclerView.setNoMore(true);
                }
            }
        });
    }

    private void requestData() {
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(getActivity())
                .loadText("加载中...")
                .build();
        shapeLoadingDialog.setCancelable(false);
        shapeLoadingDialog.show();
        Map<String, Object> map = new HashMap<>();
        map.put("uId", "345727");
        map.put("moduleId", "2");
        map.put("page", String.valueOf(page));
        map.put("pageSize", pageSize + "");
        ;
        HttpUtils.get(getActivity(), CallUrls.PFURL3, map, handler, InforamtionBean.class, 1);
    }

}
