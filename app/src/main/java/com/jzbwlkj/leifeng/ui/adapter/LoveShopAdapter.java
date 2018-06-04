package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.MallBean;
import com.jzbwlkj.leifeng.utils.RoundCornesTransFormation;

import java.util.List;

/**
 * Created by Administrator on 2018/4/8.
 */

public class LoveShopAdapter extends BaseQuickAdapter<MallBean.ListBean, BaseViewHolder> {
    private Activity activity;
    public LoveShopAdapter(int layoutResId, @Nullable List<MallBean.ListBean> data, Activity activity) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder holder, MallBean.ListBean item) {
//        TextView tvLearningGardenTitle = baseViewHolder.getView(R.id.tv_learning_garden_title);
//        ForegroundColorSpan span = new ForegroundColorSpan(mContext.getResources().getColor(R.color.global));
//        SpannableString spannableString = new SpannableString("物品名称：毛巾");
//        spannableString.setSpan(span, 5, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        tvLearningGardenTitle.setText(spannableString);
        String path = item.getPic();
        if(!TextUtils.isEmpty(path)&&!TextUtils.equals("null",path)){
            Glide.with(activity).load(path).bitmapTransform(new RoundCornesTransFormation(activity,10,10)).
                    error(R.color.red).into((ImageView) holder.getView(R.id.img_learning_garden));
        }
        holder.setText(R.id.tv_learning_garden_title,"物品名称："+item.getGoods_name());
        holder.setText(R.id.tv_learning_garden_price,item.getPrice()+"");
        holder.setText(R.id.tv_learning_garden_unit,"豆/"+item.getUnit());
    }

}
