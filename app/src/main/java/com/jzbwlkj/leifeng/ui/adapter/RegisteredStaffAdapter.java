package com.jzbwlkj.leifeng.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;

import java.util.List;

/**
 * Created by Administrator on 2018/4/13.
 */

public class RegisteredStaffAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public RegisteredStaffAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, String s) {
        ImageView imageView = baseViewHolder.getView(R.id.img_register_staff);
        TextView tvAgree = baseViewHolder.getView(R.id.tv_register_staff_agree);
        TextView tvRefuse = baseViewHolder.getView(R.id.tv_register_staff_refuse);
        TextView tvStatus = baseViewHolder.getView(R.id.tv_register_staff_status);

        if (s.equals("")) {
            tvAgree.setVisibility(View.VISIBLE);
            tvRefuse.setVisibility(View.VISIBLE);
            tvStatus.setVisibility(View.GONE);
        } else {
            tvAgree.setVisibility(View.GONE);
            tvRefuse.setVisibility(View.GONE);
            tvStatus.setVisibility(View.VISIBLE);
            tvStatus.setText("已拒绝");
        }

        baseViewHolder.setText(R.id.tv_register_staff_name, "小明")
                .addOnClickListener(R.id.tv_register_staff_agree)
                .addOnClickListener(R.id.tv_register_staff_refuse);
    }
}
