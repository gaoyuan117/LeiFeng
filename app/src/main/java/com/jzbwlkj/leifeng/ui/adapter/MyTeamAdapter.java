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
        setWebData(ss,(WebView) holder.getView(R.id.tv_my_team_address));
        holder.setText(R.id.tv_my_team_time, FormatUtils.formatTime(item.getAdd_time()));
    }

    private void setWebData(String goodsDesc,WebView bWebView) {
        if (bWebView==null){
            return;
        }
        WebSettings webSettings = bWebView.getSettings();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(false); // 支持缩放
        webSettings.setLoadWithOverviewMode(true);
//        bWebView.loadDataWithBaseURL("", data, "text/html", "UTF-8","");
//        bWebView.setWebViewClient(new SimpleWebViewClient(title));
        bWebView.getSettings().setDefaultTextEncodingName("utf-8");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            bWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            bWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        //支持获取手势焦点，输入用户名、密码或其他
//        webview.requestFocusFromTouch();
        if (TextUtils.isEmpty(goodsDesc)) {
            bWebView.loadUrl("file:///android_asset/html5/webview404.html");
        }else if (goodsDesc.startsWith("http")){
            bWebView.loadUrl(goodsDesc);
        }else {
            bWebView.loadData(getHtmlData(goodsDesc), "text/html; charset=utf-8", "utf-8");
        }
    }

    // 绘制HTML
    private String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>img{max-width: 100%; width:auto; height:auto;}</style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }
}
