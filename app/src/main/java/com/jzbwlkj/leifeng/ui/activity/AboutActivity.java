package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 关于我们
 */
public class AboutActivity extends BaseActivity {


    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.img_about_logo)
    ImageView imgAboutLogo;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.exit_layout)
    LinearLayout exitLayout;
    @BindView(R.id.tv_left_title)
    TextView tvLeftTitle;
    @BindView(R.id.center_title_tv)
    TextView centerTitleTv;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.iv_right2)
    ImageView ivRight2;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.title_linLayout)
    LinearLayout titleLinLayout;
    @BindView(R.id.web)
    WebView web;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    public void initView() {
        setCenterTitle("关于我们");
    }

    @Override
    public void initData() {
        setweb(web, BaseApp.config.getAboutus());
    }


    @Override
    public void configViews() {

    }


    /**
     * 对webview赋值
     *
     * @param web     需要复制的webview
     * @param content 内容
     */
    private void setweb(WebView web, String content) {
        String linkCss = "<style type=\"text/css\"> " +
                "img {" +
                "width:100%;" +
                "height:auto;" +
                "}" +
                "</style>";

        String html = "<html><header>" + linkCss + "</header>" + content + "</body></html>";
        web.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
    }
}
