package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.constraint.solver.Goal;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.RankBean;
import com.jzbwlkj.leifeng.view.OnDyClickListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/5/4.
 */

public class RankTeamAdapter extends RecyclerView.Adapter<RankTeamAdapter.MyViewHolder> {
    private Activity activity;
    private List<RankBean.RankTeamBean> date;
    private OnDyClickListener listener;

    public RankTeamAdapter(List<RankBean.RankTeamBean> data, Activity activity) {
        this.activity = activity;
        this.date = data;
    }

    public void setOnDyCLickListener(OnDyClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RankTeamAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_rank, parent, false);
        return new RankTeamAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RankTeamAdapter.MyViewHolder holder, final int position) {
        RankBean.RankTeamBean item = date.get(position);
        holder.tvRankSex.setVisibility(View.GONE);
        holder.imgRankIdentity.setVisibility(View.GONE);
        if (position == 0) {
            holder.tvRankNum.setText("");
            holder.tvRankNum.setBackgroundResource(R.mipmap.rank_one);
        } else if (position == 1) {
            holder.tvRankNum.setText("");
            holder.tvRankNum.setBackgroundResource(R.mipmap.rank_two);
        } else if (position == 2) {
            holder.tvRankNum.setText("");
            holder.tvRankNum.setBackgroundResource(R.mipmap.rank_three);
        } else {
            holder.tvRankNum.setText(position + 1 + "");
            holder.tvRankNum.setBackgroundResource(R.color.white);
        }

        String path = item.getPic();
        if (!TextUtils.isEmpty(path) && !TextUtils.equals("null", path)) {
            Glide.with(activity).load(path).error(R.mipmap.avatar_default).into(holder.imgRankAvatar);
        } else {
            Glide.with(activity).load("xxx").error(R.mipmap.avatar_default).into(holder.imgRankAvatar);

        }
        holder.tvRankName.setText(item.getTeam_name());
        holder.tvRankTime.setText(item.getService_hour() + "小时");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null){
                    listener.onClick(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return date.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvRankNum;
        CircleImageView imgRankAvatar;
        ImageView imgRankIdentity;
        TextView tvRankName;
        TextView tvRankSex;
        TextView tvRankTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvRankNum = itemView.findViewById(R.id.tv_rank_num);
            imgRankAvatar = itemView.findViewById(R.id.img_rank_avatar);
            imgRankIdentity = itemView.findViewById(R.id.img_rank_identity);
            tvRankName = itemView.findViewById(R.id.tv_rank_name);
            tvRankSex = itemView.findViewById(R.id.tv_rank_sex);
            tvRankTime = itemView.findViewById(R.id.tv_rank_time);
        }
    }
}
