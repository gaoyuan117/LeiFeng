package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.LiuYanBean;
import com.jzbwlkj.leifeng.utils.FormatUtils;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/4/2.
 */

public class AcDetailAdapter extends BaseQuickAdapter<LiuYanBean, BaseViewHolder> {

    private Activity activity;
    public AcDetailAdapter(int layoutResId, @Nullable List<LiuYanBean> data,Activity activity) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, LiuYanBean item) {

        helper.setText(R.id.tv_name,item.getUser_nickname());
        long tt = item.getAdd_time();
        helper.setText(R.id.tv_time, FormatUtils.formatTime(tt));
        helper.setText(R.id.tv_describe,item.getContent());
        String path = item.getAvatar();
        if(!TextUtils.isEmpty(path)&&!TextUtils.equals("null",path)){
            Glide.with(activity).load(path).error(R.color.global).into((CircleImageView)helper.getView(R.id.img_ac_comment_avatar));
        }

        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        helper.addOnClickListener(R.id.img_ac_comment);
        item.getReply_info();
        List<LiuYanBean.ReplyInfoBean> list = new ArrayList<>();
        if(item.getReply_info().size()>0){
            list.addAll(item.getReply_info());
        }
        HelpReviewAdapter adapter = new HelpReviewAdapter(R.layout.item_history_review, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
    }
}
