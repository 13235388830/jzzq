package com.ami.makemoneyby.main.Information.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ami.makemoneyby.R;
import com.ami.makemoneyby.main.Information.bean.InforamtionBean;

import java.util.List;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.MyViewHolder> {

    private Context mContext;
    private List<InforamtionBean.DataBean> mList;

    public InformationAdapter(Context mContext, List<InforamtionBean.DataBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InformationAdapter.MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.information_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(mContext).load(mList.get(position).getPublisherIcon()).into(holder.img);
        holder.title.setText(mList.get(position).getTitle());
        if(!mList.get(position).getIntroduce().equals("")) {
            holder.details.setText(mList.get(position).getIntroduce());
        }
        holder.data.setText(mList.get(position).getCreateTime());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView title,details,data;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            details = itemView.findViewById(R.id.details);
            data = itemView.findViewById(R.id.data);
        }
    }
}
