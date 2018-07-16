package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.HomeBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */

public class HomeAdapter extends BaseQuickAdapter<HomeBean.NewsRecommendListBean, BaseViewHolder> {
    private Activity activity;
    public HomeAdapter(Activity activity,int layoutResId, @Nullable List<HomeBean.NewsRecommendListBean> data) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeBean.NewsRecommendListBean s) {
        baseViewHolder.setText(R.id.tv_title,s.getTitle());
        Glide.with(activity).load(s.getPic()).error(R.mipmap.logo).into((ImageView) baseViewHolder.getView(R.id.img_home));
    }
}
