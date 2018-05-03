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
import com.jzbwlkj.leifeng.ui.bean.JoinProjectUserBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/13.
 */

public class RegisteredStaffAdapter extends BaseQuickAdapter<JoinProjectUserBean, BaseViewHolder> {
    private Activity activity;
    public RegisteredStaffAdapter(int layoutResId, @Nullable List<JoinProjectUserBean> data,Activity activity) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder holder, JoinProjectUserBean item) {
        int status = item.getStatus();
        if(status == 1&&item.getMyStatus() == 0){//通过
            holder.setVisible(R.id.tv_register_staff_agree,false);
            holder.setVisible(R.id.tv_register_staff_refuse,false);
            holder.setVisible(R.id.tv_register_staff_status,true);
            holder.setText(R.id.tv_register_staff_status,"已通过");
            holder.setBackgroundRes(R.id.tv_register_staff_agree,R.drawable.shape_gray);
            holder.setBackgroundRes(R.id.tv_register_staff_refuse,R.drawable.shape_gray);
            holder.setTextColor(R.id.tv_register_staff_agree,activity.getResources().getColor(R.color.gray));
            holder.setTextColor(R.id.tv_register_staff_refuse,activity.getResources().getColor(R.color.gray));
        }else if(status == -1&&item.getMyStatus() == 0){//拒绝
            holder.setVisible(R.id.tv_register_staff_agree,false);
            holder.setVisible(R.id.tv_register_staff_refuse,false);
            holder.setVisible(R.id.tv_register_staff_status,true);
            holder.setText(R.id.tv_register_staff_status,"已拒绝");
            holder.setBackgroundRes(R.id.tv_register_staff_agree,R.drawable.shape_gray);
            holder.setBackgroundRes(R.id.tv_register_staff_refuse,R.drawable.shape_gray);
            holder.setTextColor(R.id.tv_register_staff_agree,activity.getResources().getColor(R.color.gray));
            holder.setTextColor(R.id.tv_register_staff_refuse,activity.getResources().getColor(R.color.gray));
        }else if(status == 0&&item.getMyStatus() == 0){
            holder.setVisible(R.id.tv_register_staff_agree,true);
            holder.setVisible(R.id.tv_register_staff_refuse,true);
            holder.setVisible(R.id.tv_register_staff_status,false);
            holder.setBackgroundRes(R.id.tv_register_staff_agree,R.drawable.shape_gray);
            holder.setBackgroundRes(R.id.tv_register_staff_refuse,R.drawable.shape_gray);
            holder.setTextColor(R.id.tv_register_staff_agree,activity.getResources().getColor(R.color.gray));
            holder.setTextColor(R.id.tv_register_staff_refuse,activity.getResources().getColor(R.color.gray));
        }else if(status == 1&&item.getMyStatus() == 1){//点击通过
            holder.setVisible(R.id.tv_register_staff_agree,true);
            holder.setVisible(R.id.tv_register_staff_refuse,true);
            holder.setVisible(R.id.tv_register_staff_status,false);
            holder.setBackgroundRes(R.id.tv_register_staff_agree,R.drawable.shape_global);
            holder.setBackgroundRes(R.id.tv_register_staff_refuse,R.drawable.shape_gray);
            holder.setTextColor(R.id.tv_register_staff_agree,activity.getResources().getColor(R.color.global));
            holder.setTextColor(R.id.tv_register_staff_refuse,activity.getResources().getColor(R.color.gray));
        }else if(status == -1&&item.getMyStatus() == 2){//点击拒绝
            holder.setVisible(R.id.tv_register_staff_agree,true);
            holder.setVisible(R.id.tv_register_staff_refuse,true);
            holder.setVisible(R.id.tv_register_staff_status,false);
            holder.setBackgroundRes(R.id.tv_register_staff_agree,R.drawable.shape_gray);
            holder.setBackgroundRes(R.id.tv_register_staff_refuse,R.drawable.shape_global);
            holder.setTextColor(R.id.tv_register_staff_agree,activity.getResources().getColor(R.color.gray));
            holder.setTextColor(R.id.tv_register_staff_refuse,activity.getResources().getColor(R.color.global));
        }
        String path = item.getAvatar();
        if(!TextUtils.isEmpty(path)&&!TextUtils.equals("null",path)){
            Glide.with(activity).load(path).error(R.mipmap.avatar_default).into((ImageView) holder.getView(R.id.img_register_staff));
        }

        holder.setText(R.id.tv_register_staff_name, item.getUser_nickname())
                .addOnClickListener(R.id.tv_register_staff_agree)
                .addOnClickListener(R.id.tv_register_staff_refuse);
    }
}
