package com.jzbwlkj.leifeng.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.HelpListBean;
import com.jzbwlkj.leifeng.utils.FormatUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

public class HelpHistoryAdapter extends BaseQuickAdapter<HelpListBean, BaseViewHolder> {

    public HelpHistoryAdapter(int layoutResId, @Nullable List<HelpListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HelpListBean item) {

       long pubTime = item.getAdd_time();
       helper.setText(R.id.tv_time, FormatUtils.formatTime(pubTime));
       helper.setText(R.id.tv_describe,item.getContent());
       helper.setText(R.id.tv_help_review_name,"平台：");
       helper.setText(R.id.tv_help_review,item.getReply_content());
       if(item.getIs_anonymous() == 0){
           helper.setText(R.id.tv_name,item.getFullname());
       }else{
           helper.setText(R.id.tv_name,"匿名发布");
       }
    }
}
