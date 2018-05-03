
package com.jzbwlkj.leifeng.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.bean.ChatListBean;
import com.jzbwlkj.leifeng.ui.bean.ChatListDeticalBean;
import com.jzbwlkj.leifeng.utils.CommonApi;
import com.jzbwlkj.leifeng.utils.FormatUtils;

import java.util.HashMap;

import butterknife.BindView;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;

public class NewsDetalActivity extends BaseActivity {

    public static void toActivity(Context activity, String title, String content, long time) {
        Intent intent = new Intent(activity, NewsDetalActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("content", content);
        intent.putExtra("time", time);
        activity.startActivity(intent);
    }

    @BindView(R.id.tv_news_detail_title)
    TextView tvNewsDetailTitle;
    @BindView(R.id.tv_news_detail_time)
    TextView tvNewsDetailTime;
    @BindView(R.id.webview)
    WebView webview;
    private int id;
    private String title;
    private String content;
    private long time;
    private int flag = 0;//1 学习园地  0
    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detal;
    }

    @Override
    public void initView() {
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        time = getIntent().getLongExtra("time", 0);
        id = getIntent().getIntExtra("id", 0);
        flag = getIntent().getIntExtra("flag",0);
        String title1 = "";
        if(flag == 1){
            title1 = "学习园地";
        }else if(flag == 2){
            title1 = "使用帮助";
        }else{
            title1 = "新闻详情";
            setRightTitle("分享").setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CommonApi.share(activity, title, null);
                }
            });
        }
        setCenterTitle(title1);
        tvNewsDetailTitle.setText(title);
        tvNewsDetailTime.setText(FormatUtils.formatTime(time));
        setWebData(content,webview);

    }

    @Override
    public void initData() {
    }


    @Override
    public void configViews() {

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
