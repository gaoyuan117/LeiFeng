package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.UserBean;
import com.jzbwlkj.leifeng.ui.bean.UserSignBean;
import com.jzbwlkj.leifeng.utils.FormatUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/6/9.
 */

public class UserDatialAdapter extends BaseQuickAdapter<UserSignBean.DataBean,BaseViewHolder> {
    private Activity activity;

    public UserDatialAdapter(int layoutResId, @Nullable List<UserSignBean.DataBean> data, Activity activity) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, UserSignBean.DataBean item) {
        long ss = item.getTime_s();
        long ee = item.getTime_e();
        if(ss <= 0){
            helper.setText(R.id.tv_start, "尚未签到");
        }else{
            helper.setText(R.id.tv_start, "签到时间："+FormatUtils.formatTime(ss));
        }

        if(ee <= 0){
            helper.setText(R.id.tv_end, "尚未签退");
        }else{
            helper.setText(R.id.tv_end, "签退时间："+FormatUtils.formatTime(ee));
        }
        helper.setText(R.id.tv_title,item.getActivity_name());
        helper.setText(R.id.tv_service_time,"服务时长："+item.getService_hour()+"小时");
 //       helper.setText(R.id.tv_service_team,item.get());
    }
}
