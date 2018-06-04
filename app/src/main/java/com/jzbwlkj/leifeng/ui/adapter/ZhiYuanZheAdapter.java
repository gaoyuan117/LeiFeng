package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.ProjectDetialBean;
import com.jzbwlkj.leifeng.ui.bean.UserBean;
import com.jzbwlkj.leifeng.utils.FormatUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/6/1.
 */

public class ZhiYuanZheAdapter extends BaseQuickAdapter<UserBean,BaseViewHolder> {
    private Activity activity;
    public ZhiYuanZheAdapter(int layoutResId, @Nullable List<UserBean> data, Activity activity) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, UserBean item) {
        helper.setText(R.id.img_leave_word_name,item.getUser_nickname());
        helper.setText(R.id.tv_leave_word_time,"服务时长："+item.getService_hour()+"时");
        String path = item.getAvatar();
        if (!TextUtils.isEmpty(path)&&path != "null") {
            Glide.with(activity).load(path).error(R.mipmap.logo).into((ImageView) helper.getView(R.id.img_leave_word_avatar));
        }

        helper.addOnClickListener(R.id.tv_refuse);
        helper.addOnClickListener(R.id.tv_agree);
    }
}
