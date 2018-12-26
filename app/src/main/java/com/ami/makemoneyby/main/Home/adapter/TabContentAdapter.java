package com.ami.makemoneyby.main.Home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ami.makemoneyby.R;
import com.ami.makemoneyby.main.Home.bean.HomeBean;
import com.ami.makemoneyby.main.Home.bean.TabContentBean;

import java.util.List;

public class TabContentAdapter extends RecyclerView.Adapter<TabContentAdapter.MyViewHolder> {

    private Context mContext;
    private List<TabContentBean.ResultDataBean> mList;

    public TabContentAdapter(Context mContext, List<TabContentBean.ResultDataBean> list) {
        this.mContext = mContext;
        this.mList = list;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TabContentAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.head_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.mTitle.setText(mList.get(position).getProductName());
        holder.mDanjia.setText(String.valueOf(mList.get(position).getProductPrice()));
        holder.mYongjin.setText("￥ " + (int) Math.round(mList.get(position).getProductPrice() * 0.28));
        holder.mDealNum.setText("￥ " + Math.round(mList.get(position).getDealNum()));
        Glide.with(mContext).load(mList.get(position).getProductImg()).into(holder.mHead);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle, mDanjia, mYongjin, mDealNum;
        private ImageView mHead;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mDanjia = (TextView) itemView.findViewById(R.id.tv_danjia);
            mYongjin = (TextView) itemView.findViewById(R.id.tv_yongjin);
            mDealNum = (TextView) itemView.findViewById(R.id.tv_deal_num);
//            mTuiguan = (TextView) itemView.findViewById(R.id.tv_tuiguang);
            mHead = (ImageView) itemView.findViewById(R.id.iv_head);
        }
    }
}
