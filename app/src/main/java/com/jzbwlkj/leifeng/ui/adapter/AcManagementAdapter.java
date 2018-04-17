package com.jzbwlkj.leifeng.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */

public class AcManagementAdapter extends BaseQuickAdapter<String, BaseViewHolder> {


    public AcManagementAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        LinearLayout layout = baseViewHolder.getView(R.id.ll_my_team_refuse);

        layout.setVisibility(View.GONE);

    }
}
