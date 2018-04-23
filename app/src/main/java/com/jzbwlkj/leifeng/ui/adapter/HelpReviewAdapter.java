package com.jzbwlkj.leifeng.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.LiuYanBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

public class HelpReviewAdapter extends BaseQuickAdapter<LiuYanBean.ReplyInfoBean,BaseViewHolder>{

    public HelpReviewAdapter(int layoutResId, @Nullable List<LiuYanBean.ReplyInfoBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, LiuYanBean.ReplyInfoBean item) {
        holder.setText(R.id.tv_help_review_name,item.getUser_nickname()+":");
        holder.setText(R.id.tv_help_review,item.getContent());
    }
}
