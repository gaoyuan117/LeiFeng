package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.DaiQianBean;
import com.jzbwlkj.leifeng.ui.bean.JoinProjectUserBean;
import com.jzbwlkj.leifeng.ui.bean.ProjectDetialBean;
import com.jzbwlkj.leifeng.utils.FormatUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/6/2.
 */

public class DaiQianAdapter extends BaseQuickAdapter<JoinProjectUserBean, BaseViewHolder> {

    private Activity activity;
    public DaiQianAdapter(int layoutResId, @Nullable List<JoinProjectUserBean> data, Activity activity) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, JoinProjectUserBean item) {
        String path = item.getAvatar();
        if(!TextUtils.isEmpty(path)||!TextUtils.equals("null",path)){
            Glide.with(activity).load(path).error(R.mipmap.logo).into((ImageView) helper.getView(R.id.iv_pic));
        }else{
            Glide.with(activity).load("xxx").error(R.mipmap.logo).into((ImageView) helper.getView(R.id.iv_pic));
        }
        helper.setText(R.id.tv_name,item.getUser_nickname());
        if(item.getStatus() ==1){
            helper.setText(R.id.tv_qiandao,"已签到");
            helper.setText(R.id.tv_qiantui,"签退");
        }else if(item.getStatus() == 2){
            helper.setText(R.id.tv_qiandao,"已签到");
            helper.setText(R.id.tv_qiantui,"已签退");
        }else{
            helper.setText(R.id.tv_qiandao,"签到");
            helper.setText(R.id.tv_qiantui,"签退");
        }
    }
}
