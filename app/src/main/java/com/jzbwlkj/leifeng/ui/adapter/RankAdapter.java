package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.RankBean;
import com.jzbwlkj.leifeng.view.OnDyClickListener;

import java.util.List;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/4/10.
 */

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.MyViewHolder> {

    private Activity activity;
    private List<RankBean.RankUserBean> date;
    private OnDyClickListener listener;
    public RankAdapter(List<RankBean.RankUserBean> data, Activity activity) {
        this.activity = activity;
        this.date = data;
    }

    public void setOnDyCLickListener(OnDyClickListener listener){
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_rank, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        RankBean.RankUserBean item = date.get(position);
        if (position == 0) {
            holder.tvRankNum.setText("");
            holder.tvRankNum.setBackgroundResource(R.mipmap.rank_one);
        } else if (position== 1) {
            holder.tvRankNum.setText("");
            holder.tvRankNum.setBackgroundResource(R.mipmap.rank_two);
        } else if (position == 2) {
            holder.tvRankNum.setText("");
            holder.tvRankNum.setBackgroundResource(R.mipmap.rank_three);
        } else {
            holder.tvRankNum.setText(position + 1 + "");
            holder.tvRankNum.setBackgroundResource(R.color.white);
        }
        String path = item.getAvatar();
        if (!TextUtils.isEmpty(path) && !TextUtils.equals("null", path)) {
            Glide.with(activity).load(path).error(R.mipmap.avatar_default).into(holder.imgRankAvatar);
        } else {
            Glide.with(activity).load("xxx").error(R.mipmap.avatar_default).into(holder.imgRankAvatar);
        }
        int ss = item.getPolital_status();
        if (ss == 0) {//党员
            holder.imgRankIdentity.setVisibility(View.VISIBLE);
            holder.imgRankIdentity.setImageResource(R.mipmap.dangyuan);
        } else if (ss == 1) {//团员
            holder.imgRankIdentity.setVisibility(View.VISIBLE);
            holder.imgRankIdentity.setImageResource(R.mipmap.paihang);
        } else {
            holder.imgRankIdentity.setVisibility(View.GONE);
        }
        holder.tvRankName.setText(item.getUser_nickname());
        holder.tvRankTime.setText(item.getService_hour() + "小时");
        String sex = "男";
        if (item.getSex() == 1) {
            sex = "男";
        } else {
            sex = "女";
        }
        holder.tvRankSex.setText(sex);
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
