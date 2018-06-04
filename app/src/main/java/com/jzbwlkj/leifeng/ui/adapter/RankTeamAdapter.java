package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.RankBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/4.
 */

public class RankTeamAdapter extends BaseQuickAdapter<RankBean.RankTeamBean, BaseViewHolder> {
    private Activity activity;

    public RankTeamAdapter(int layoutResId, @Nullable List<RankBean.RankTeamBean> data, Activity activity) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RankBean.RankTeamBean item) {
        TextView tvRankNum = baseViewHolder.getView(R.id.tv_rank_num);
        TextView tvRankSex = baseViewHolder.getView(R.id.tv_rank_sex);
        tvRankSex.setVisibility(View.GONE);
        baseViewHolder.setVisible(R.id.img_rank_identity,false);
        if (baseViewHolder.getAdapterPosition() == 0) {
            tvRankNum.setText("");
            tvRankNum.setBackgroundResource(R.mipmap.rank_one);
        } else if (baseViewHolder.getAdapterPosition() == 1) {
            tvRankNum.setText("");
            tvRankNum.setBackgroundResource(R.mipmap.rank_two);
        } else if (baseViewHolder.getAdapterPosition() == 2) {
            tvRankNum.setText("");
            tvRankNum.setBackgroundResource(R.mipmap.rank_three);
        } else {
            tvRankNum.setText(baseViewHolder.getAdapterPosition() + 1 + "");
            tvRankNum.setBackgroundResource(R.color.white);
        }

        String path = item.getPic();
        if(!TextUtils.isEmpty(path)&&!TextUtils.equals("null",path)){
            Glide.with(activity).load(path).error(R.mipmap.avatar_default).into((ImageView) baseViewHolder.getView(R.id.img_rank_avatar));
        }else{
            Glide.with(activity).load("xxx").error(R.mipmap.avatar_default).into((ImageView) baseViewHolder.getView(R.id.img_rank_avatar));

        }
//        if(item.get){
//
//        }
        baseViewHolder.setText(R.id.tv_rank_name,item.getTeam_name());
        baseViewHolder.setText(R.id.tv_rank_time,item.getService_hour()+"小时");

    }
}
