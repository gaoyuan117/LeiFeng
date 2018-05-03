package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.MySelfModel;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */

public class AreaAdapter extends BaseQuickAdapter<MySelfModel,BaseViewHolder>{
    private Activity activity;
    public AreaAdapter(int layoutResId, @Nullable List<MySelfModel> data, Activity activity) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MySelfModel s) {
        baseViewHolder.setText(R.id.tv_select_area,s.getName());
        if(s.getSelected()){
            baseViewHolder.setTextColor(R.id.tv_select_area,activity.getResources().getColor(R.color.text_red));
        }
    }
}
