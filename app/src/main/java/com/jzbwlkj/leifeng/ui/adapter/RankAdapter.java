package com.jzbwlkj.leifeng.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */

public class RankAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RankAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        TextView tvRankNum = baseViewHolder.getView(R.id.tv_rank_num);
        TextView tvRankSex = baseViewHolder.getView(R.id.tv_rank_sex);
        if (TextUtils.isEmpty(s)) {
            tvRankSex.setVisibility(View.VISIBLE);
        } else {
            tvRankSex.setVisibility(View.GONE);
        }

        if (baseViewHolder.getAdapterPosition() == 0) {
            tvRankNum.setText("");
            tvRankNum.setBackgroundResource(R.mipmap.rank_one);
        } else if (baseViewHolder.getAdapterPosition() == 1) {
            tvRankNum.setText("");
            tvRankNum.setBackgroundResource(R.mipmap.rank_two);
        } else if (baseViewHolder.getAdapterPosition() == 2) {
            tvRankNum.setText("");
            tvRankNum.setBackgroundResource(R.mipmap.rank_three);
        } else {
            tvRankNum.setText(baseViewHolder.getAdapterPosition() + 1 + "");
            tvRankNum.setBackgroundResource(R.color.white);
        }
    }
}
