package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.ChatListBean;
import com.jzbwlkj.leifeng.utils.FormatUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */

public class ChatListAdapter extends BaseQuickAdapter<ChatListBean.AllListBean, BaseViewHolder> {
    private Activity act;

    public ChatListAdapter(int layoutResId, @Nullable List<ChatListBean.AllListBean> data, Activity act) {
        super(layoutResId, data);
        this.act = act;
    }

    @Override
    protected void convert(BaseViewHolder holder, ChatListBean.AllListBean item) {
        holder.setText(R.id.tv_title, item.getTitle());
        holder.setText(R.id.tv_chat_time, FormatUtils.formatTime(item.getAdd_time()));
        holder.setText(R.id.tv_chat_des, item.getDesc());
        //接口没有返回相关数据
        String path = item.getPic();
        if (!TextUtils.isEmpty(path)&&!TextUtils.equals("null",path)) {
            Glide.with(act).load(path).error(R.mipmap.logo).into((ImageView) holder.getView(R.id.img_logo));
        }
    }
}
