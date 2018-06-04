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
 * Created by Administrator on 2018/4/10.
 */

public class RankAdapter extends BaseQuickAdapter<RankBean.RankUserBean, BaseViewHolder> {
    private Activity activity;
    public RankAdapter(int layoutResId, @Nullable List<RankBean.RankUserBean> data,Activity activity) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, RankBean.RankUserBean item) {
        TextView tvRankNum = baseViewHolder.getView(R.id.tv_rank_num);
        ImageView ivShenFen = baseViewHolder.getView(R.id.img_rank_identity);
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
        String path = item.getAvatar();
        if(!TextUtils.isEmpty(path)&&!TextUtils.equals("null",path)){
            Glide.with(activity).load(path).error(R.mipmap.avatar_default).into((ImageView) baseViewHolder.getView(R.id.img_rank_avatar));
        }else{
            Glide.with(activity).load("xxx").error(R.mipmap.avatar_default).into((ImageView) baseViewHolder.getView(R.id.img_rank_avatar));
        }
        int ss = item.getPolital_status();
        if( ss == 0){
            ivShenFen.setVisibility(View.GONE);
        }else if(ss == 1){//党员
            ivShenFen.setVisibility(View.VISIBLE);
            ivShenFen.setImageResource(R.mipmap.dangyuan);
        }else{//团员
            ivShenFen.setVisibility(View.VISIBLE);
            ivShenFen.setImageResource(R.mipmap.paihang);
        }
        baseViewHolder.setText(R.id.tv_rank_name,item.getUser_nickname());
        baseViewHolder.setText(R.id.tv_rank_time,item.getService_hour()+"小时");
        String sex = "男";
        if(item.getSex() == 1){
            sex = "男";
        }else{
            sex = "女";
        }
        baseViewHolder.setText(R.id.tv_rank_sex,sex);

    }
}
