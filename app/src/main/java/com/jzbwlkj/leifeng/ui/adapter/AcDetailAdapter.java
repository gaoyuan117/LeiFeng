package com.jzbwlkj.leifeng.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

public class AcDetailAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public AcDetailAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String s) {

        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        HelpReviewAdapter adapter = new HelpReviewAdapter(R.layout.item_history_review, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adapter);
    }
}
