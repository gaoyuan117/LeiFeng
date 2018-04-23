package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.ProjectBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MyTeamAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private String type;
    public MyTeamAdapter(int layoutResId, @Nullable List<String> data, String type) {
        super(layoutResId, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder holder, String item) {
        LinearLayout layout = holder.getView(R.id.ll_my_team_refuse);
        if (type.equals("2")) {
            layout.setVisibility(View.VISIBLE);
        } else {
            layout.setVisibility(View.GONE);
        }

        holder.addOnClickListener(R.id.tv_my_team_resend);


    }
}
