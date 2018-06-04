package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.AuditListBean;
import com.jzbwlkj.leifeng.utils.FormatUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/4/13.
 */

public class LeaveWordAdapter extends BaseQuickAdapter<AuditListBean, BaseViewHolder> {
    private Activity activity;
    public LeaveWordAdapter(int layoutResId, @Nullable List<AuditListBean> data, Activity activity) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder holder, AuditListBean item) {
        String path = item.getAvatar();
        if(!TextUtils.isEmpty(path)&&!TextUtils.equals("null",path)){
            Glide.with(activity).load(path).error(R.mipmap.avatar_default).into((ImageView) holder.getView(R.id.img_leave_word_avatar));
        }
        if(item.getStatus() == 1){
            holder.setImageResource(R.id.img_leave_word_agree,R.mipmap.duihao);
            holder.setImageResource(R.id.img_leave_word_refuse,R.mipmap.chahaohui);
        }else if(item.getStatus() == 0){
            holder.setImageResource(R.id.img_leave_word_agree,R.mipmap.duihaolan);
            holder.setImageResource(R.id.img_leave_word_refuse,R.mipmap.chahaohui);
        }else{
            holder.setImageResource(R.id.img_leave_word_agree,R.mipmap.duihaolan);
            holder.setImageResource(R.id.img_leave_word_refuse,R.mipmap.chahaohong);
        }
        holder.setText(R.id.tv_leave_word_des,item.getContent());
        holder.setText(R.id.img_leave_word_name,item.getUser_nickname());
        holder.setText(R.id.tv_leave_word_time, FormatUtils.formatTime(item.getAdd_time()));
        holder.addOnClickListener(R.id.img_leave_word_refuse)
                .addOnClickListener(R.id.img_leave_word_agree);

    }
}
