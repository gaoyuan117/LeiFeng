package com.jzbwlkj.leifeng.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.bean.MainNewsBean;
import com.jzbwlkj.leifeng.utils.CommonApi;
import com.jzbwlkj.leifeng.utils.FormatUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowBannerActivity extends BaseActivity {

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
    @BindView(R.id.wen_link)
    WebView wenLink;
    @BindView(R.id.tv_news_detail_title)
    TextView tvNewsDetailTitle;
    @BindView(R.id.tv_news_detail_time)
    TextView tvNewsDetailTime;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.ll_text)
    LinearLayout llText;
    private int type;//0 超链接   1  本地新闻
    private String id;//0的时候超链接本身   1的时候新闻id

    @Override
    public int getLayoutId() {
        return R.layout.activity_show_banner;
    }

    @Override
    public void initView() {
        type = getIntent().getIntExtra("flag", 0);
        id = getIntent().getStringExtra("url");
        centerTitleTv.setText("信息详情");
        tvRightText.setText("分享");
    }

    @Override
    public void initData() {
        if (type == 0) {
            llText.setVisibility(View.GONE);
            wenLink.setVisibility(View.VISIBLE);
            initWeb(wenLink, id);
        } else {
            llText.setVisibility(View.VISIBLE);
            wenLink.setVisibility(View.GONE);
            getNetData();
        }
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.tv_right_text})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_right_text:
                CommonApi.share(activity, null, null);
                break;
        }

    }

    private void setWebData(String goodsDesc, WebView bWebView) {
        if (bWebView == null) {
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
        } else if (goodsDesc.startsWith("http")) {
            bWebView.loadUrl(goodsDesc);
        } else {
//
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

    private void initWeb(WebView web, String url) {
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setBuiltInZoomControls(true); //设置支持缩放
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBlockNetworkLoads(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setDisplayZoomControls(false);
        web.setHorizontalScrollBarEnabled(false);//水平不显示
        web.setVerticalScrollBarEnabled(false); //垂直不显示
        web.setWebChromeClient(new WebChromeClient());

        web.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        web.loadUrl(url);
    }

    /**
     * 获取网络数据
     */
    private void getNetData() {
        RetrofitClient.getInstance().createApi().getNewsInfo(id)
                .compose(RxUtils.<HttpResult<MainNewsBean>>io_main())
                .subscribe(new BaseObjObserver<MainNewsBean>(this) {
                    @Override
                    protected void onHandleSuccess(MainNewsBean bean) {
                        tvNewsDetailTitle.setText(bean.getTitle());
                        tvNewsDetailTime.setText(FormatUtils.formatTime(bean.getAdd_time()));
                        setWebData(bean.getContent(),webview);
                    }
                });
    }
}
