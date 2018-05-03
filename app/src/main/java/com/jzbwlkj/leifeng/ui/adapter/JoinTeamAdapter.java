package com.jzbwlkj.leifeng.ui.adapter;

import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.ui.bean.TeamListBean;
import com.jzbwlkj.leifeng.utils.LogUtils;

import java.util.List;

/**
 * Created by Administrator on 2018/4/9.
 */

public class JoinTeamAdapter extends BaseQuickAdapter<TeamListBean, BaseViewHolder> {

    public JoinTeamAdapter(int layoutResId, @Nullable List<TeamListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, TeamListBean s) {
        ImageView imageView = baseViewHolder.getView(R.id.img_join_team_avatar);
        Glide.with(mContext).load(s.getPic()).error(R.mipmap.logo).into(imageView);
        String kk = Html.fromHtml(s.getDesc()).toString();
        String ss = kk.replace("<p>","").replace("</p>","");
        baseViewHolder.setText(R.id.tv_join_team_name, s.getTeam_name())
                .setText(R.id.tv_join_team_address, ss);
    }
}
