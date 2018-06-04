package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.ProjectBean;
import com.jzbwlkj.leifeng.utils.RoundCornesTransFormation;

import java.util.List;

/**
 * Created by Administrator on 2018/4/21.
 */

public class ProjectAdapter extends BaseQuickAdapter<ProjectBean.DataBean, BaseViewHolder> {
    private String type;
    private Activity activity;
    public ProjectAdapter(int layoutResId, @Nullable List<ProjectBean.DataBean> data, String type, Activity activity) {
        super(layoutResId, data);
        this.type = type;
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder holder, ProjectBean.DataBean item) {
        LinearLayout layout = holder.getView(R.id.ll_my_team_refuse);
        if (type.equals("2")) {
            layout.setVisibility(View.GONE);
        } else {
            layout.setVisibility(View.GONE);
        }

        holder.addOnClickListener(R.id.tv_my_team_resend);

        String path = item.getPic();
        if(!TextUtils.isEmpty(path)&&!TextUtils.equals("null",path)){
            Glide.with(activity).load(path)
                    .error(R.mipmap.logo).into((ImageView) holder.getView(R.id.img_my_ac));
        }else{
            Glide.with(activity).load("xxx")
                    .error(R.mipmap.logo).into((ImageView) holder.getView(R.id.img_my_ac));

        }

        holder.setText(R.id.tv_my_ac_time,item.getService_hour()+"小时");
        holder.setText(R.id.tv_my_ac_title,item.getTitle());
        holder.setText(R.id.tv_my_ac_des,item.getTeam_name());
        holder.setText(R.id.tv_my_ac_num,item.getJoin_num()+"/"+item.getService_num());
    }
}
