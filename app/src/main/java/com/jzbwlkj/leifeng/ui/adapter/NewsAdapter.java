package com.jzbwlkj.leifeng.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.NewsBean;
import com.jzbwlkj.leifeng.utils.CommonApi;

import java.util.List;

/**
 * Created by Administrator on 2018/3/31.
 */

public class NewsAdapter extends BaseQuickAdapter<NewsBean.NewsListBean, BaseViewHolder> {

    public NewsAdapter(int layoutResId, @Nullable List<NewsBean.NewsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsBean.NewsListBean s) {
        ImageView imageView = baseViewHolder.getView(R.id.img_news);

        CommonApi.glideUtils(mContext,imageView,s.getPic());
        baseViewHolder.setText(R.id.tv_news_title, s.getTitle())
                .setText(R.id.tv_news_des, s.getDesc());
    }
}
