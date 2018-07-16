package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.AppConfig;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.NewsBean;
import com.jzbwlkj.leifeng.utils.CommonApi;
import com.jzbwlkj.leifeng.utils.RoundCornesTransFormation;

import java.util.List;

/**
 * Created by Administrator on 2018/3/31.
 */

public class NewsAdapter extends BaseQuickAdapter<NewsBean.NewsListBean, BaseViewHolder> {
    private Activity activity;
    public NewsAdapter(int layoutResId, @Nullable List<NewsBean.NewsListBean> data,Activity activity) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsBean.NewsListBean s) {
        ImageView imageView = baseViewHolder.getView(R.id.img_news);
        String path = s.getPic();
        if(!TextUtils.equals("null",path)&&!TextUtils.isEmpty(path)){
            if(!path.contains("http")){
                if (!path.contains("upload")) {
                    path = AppConfig.BASE_URL + "/upload/" + path;
                }
            }
            Glide.with(activity).load(path).bitmapTransform(new RoundCornesTransFormation(activity,10,10))
                    .error(R.mipmap.logo).into(imageView);
        }
        baseViewHolder.setText(R.id.tv_news_title, s.getTitle())
                .setText(R.id.tv_news_des, s.getDesc());
    }
}
