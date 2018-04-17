package com.jzbwlkj.leifeng.ui.adapter;

import android.support.annotation.Nullable;
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

    public HomeAdapter(int layoutResId, @Nullable List<HomeBean.NewsRecommendListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, HomeBean.NewsRecommendListBean s) {
        ImageView imageView = baseViewHolder.getView(R.id.img_home);
        Glide.with(mContext).load(s.getPic()).error(R.mipmap.cover_default).into(imageView);
    }
}
