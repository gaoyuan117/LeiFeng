package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.JoinProjectUserBean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/12.
 */

public class AcManagementDetailsAdapter extends BaseQuickAdapter<JoinProjectUserBean,BaseViewHolder>{

    private Activity activity;
    public AcManagementDetailsAdapter(int layoutResId, @Nullable List<JoinProjectUserBean> data, Activity activity) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder holder, JoinProjectUserBean bean) {
        String path = bean.getAvatar();
        if(!TextUtils.isEmpty(path)&&!TextUtils.equals("null",path)){
            Glide.with(activity).load(path).error(R.mipmap.avatar_default).into((ImageView) holder.getView(R.id.img_ac_management_details));
        }
    }
}
