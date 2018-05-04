package com.jzbwlkj.leifeng.ui.adapter;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.JoinTeamListBean;
import com.jzbwlkj.leifeng.ui.bean.ProjectBean;
import com.jzbwlkj.leifeng.utils.FormatUtils;
import com.jzbwlkj.leifeng.utils.RemoveLableUtil;
import com.jzbwlkj.leifeng.utils.RoundCornesTransFormation;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MyTeamAdapter extends BaseQuickAdapter<JoinTeamListBean.ListBean, BaseViewHolder> {
    private String type;
    private Activity activity;
    public MyTeamAdapter(int layoutResId, @Nullable List<JoinTeamListBean.ListBean> data, String type,Activity activity) {
        super(layoutResId, data);
        this.type = type;
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder holder, JoinTeamListBean.ListBean item) {
        LinearLayout layout = holder.getView(R.id.ll_my_team_refuse);
        if (type.equals("2")) {
            layout.setVisibility(View.VISIBLE);
        } else {
            layout.setVisibility(View.GONE);
        }

        holder.addOnClickListener(R.id.tv_my_team_resend);
        String path = item.getPic();
        if(!TextUtils.isEmpty(path)&&!TextUtils.equals("null",path)){
            Glide.with(activity).load(path).bitmapTransform(new RoundCornesTransFormation(activity,10,10))
                    .error(R.mipmap.xiaotouxiang).into((ImageView) holder.getView(R.id.img_my_team_avatar));
        }
        holder.setText(R.id.tv_my_team_name,item.getTeam_name());
        String ss = Html.fromHtml(item.getDesc()).toString();
        ss = RemoveLableUtil.delHTMLTag(ss);
        holder.setText(R.id.tv_my_team_address,ss);
        holder.setText(R.id.tv_my_team_time, FormatUtils.formatTime(item.getAdd_time()));
    }

}
