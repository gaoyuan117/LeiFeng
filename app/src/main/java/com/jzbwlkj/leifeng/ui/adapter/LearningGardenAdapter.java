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
import com.jzbwlkj.leifeng.ui.bean.NewsBean;
import com.jzbwlkj.leifeng.ui.bean.StudyBean;
import com.jzbwlkj.leifeng.utils.RemoveLableUtil;
import com.jzbwlkj.leifeng.utils.RoundCornesTransFormation;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */

public class LearningGardenAdapter extends BaseQuickAdapter<StudyBean,BaseViewHolder>{
    private Activity activity;
    public LearningGardenAdapter(int layoutResId, @Nullable List<StudyBean> data,Activity activity) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, StudyBean item) {
        String path = item.getPic();
        if(!TextUtils.equals("null",path)&&!TextUtils.isEmpty(path)){
            Glide.with(activity).load(path).bitmapTransform(new RoundCornesTransFormation(activity,10,10))
                    .error(R.mipmap.cover_default).into((ImageView)baseViewHolder.getView(R.id.img_news));
        }
        baseViewHolder.setText(R.id.tv_news_title,item.getTitle());
        String ss = item.getDesc();
        ss = RemoveLableUtil.delHTMLTag(ss);
        baseViewHolder.setText(R.id.tv_news_des,ss);
    }
}
