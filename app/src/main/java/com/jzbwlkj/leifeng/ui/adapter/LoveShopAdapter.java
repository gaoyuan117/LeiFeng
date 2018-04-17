package com.jzbwlkj.leifeng.ui.adapter;

import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */

public class LoveShopAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public LoveShopAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        TextView tvLearningGardenTitle = baseViewHolder.getView(R.id.tv_learning_garden_title);
        ForegroundColorSpan span = new ForegroundColorSpan(mContext.getResources().getColor(R.color.global));
        SpannableString spannableString = new SpannableString("物品名称：毛巾");
        spannableString.setSpan(span, 5, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvLearningGardenTitle.setText(spannableString);
    }

}
