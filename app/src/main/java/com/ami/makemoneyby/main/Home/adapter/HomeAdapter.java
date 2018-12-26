package com.ami.makemoneyby.main.Home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ami.makemoneyby.R;
import com.ami.makemoneyby.main.Home.bean.HomeBean;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private Context mContext;
    private List<HomeBean.DataBean.ListBean> list;

    public HomeAdapter(Context mContext, List<HomeBean.DataBean.ListBean> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.home_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.location.setText(list.get(position).getCity());
        holder.money.setText(list.get(position).getSalary());
        holder.type.setText(list.get(position).getType());
        holder.address.setText(list.get(position).getArea());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView title,location,money,type,address;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            location = itemView.findViewById(R.id.location);
            money = itemView.findViewById(R.id.money);
            type = itemView.findViewById(R.id.type);
            address = itemView.findViewById(R.id.address);
        }
    }
}
