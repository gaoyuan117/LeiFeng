package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.ChatListBean;
import com.jzbwlkj.leifeng.utils.RoundCornesTransFormation;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */

public class TrainingAdapter extends BaseQuickAdapter<ChatListBean.AllListBean,BaseViewHolder>{
    private Activity activity;

    public TrainingAdapter(int layoutResId, @Nullable List<ChatListBean.AllListBean> data, Activity activity) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ChatListBean.AllListBean bean) {
        String path = bean.getPic();
        if(TextUtils.isEmpty(path)||TextUtils.equals("null",path)){
            Glide.with(activity).load("xxx").bitmapTransform(new RoundCornesTransFormation(activity,10,10))
                    .error(R.mipmap.logo).into((ImageView) baseViewHolder.getView(R.id.img_home));
        }else{
            Glide.with(activity).load(path).bitmapTransform(new RoundCornesTransFormation(activity,10,10))
                    .error(R.mipmap.logo).into((ImageView) baseViewHolder.getView(R.id.img_home));
        }

        baseViewHolder.setText(R.id.tv_title,bean.getTitle());
    }
}
