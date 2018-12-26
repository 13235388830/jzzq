package com.ami.makemoneyby.utils;

import com.ami.makemoneyby.R;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.ProgressStyle;

/**
 * Created by Administrator on 2017/3/22.
 * RecyclerView 样式类
 */

public class RecyclerViewUtil {


    public static void setStyle(LRecyclerView lRecyclerView) {
        lRecyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        lRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);
        lRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        //设置头部加载颜色
        lRecyclerView.setHeaderViewColor(R.color.colorPrimary, R.color.colorPrimary, android.R.color.white);
        //设置底部加载颜色
        lRecyclerView.setFooterViewColor(R.color.colorPrimary, R.color.colorPrimary, android.R.color.white);
        //设置底部加载文字提示
        lRecyclerView.setFooterViewHint("拼命加载中", "已经全部为你呈现了", "网络不给力啊，点击再试一次吧");
    }
}
