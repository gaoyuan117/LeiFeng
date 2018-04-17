package com.jzbwlkj.leifeng.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/13.
 */

public class LeaveWordAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public LeaveWordAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        baseViewHolder.addOnClickListener(R.id.img_leave_word_refuse)
                .addOnClickListener(R.id.img_leave_word_agree);

    }
}
