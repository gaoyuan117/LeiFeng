
package com.jzbwlkj.leifeng.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.utils.CommonApi;
import com.jzbwlkj.leifeng.utils.FormatUtils;

import butterknife.BindView;

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
    private String id;
    private String title;
    private String content;
    private long time;

    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detal;
    }

    @Override
    public void initView() {
        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");
        time = getIntent().getLongExtra("time", 0);
        setCenterTitle("新闻详情");

        setData();
        setRightTitle("分享").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonApi.share(activity, "", null);
            }
        });
    }

    @Override
    public void initData() {
        id = getIntent().getStringExtra("id");

    }


    @Override
    public void configViews() {

    }

    private void setData() {
        if (TextUtils.isEmpty(title)) return;
        tvNewsDetailTitle.setText(title);
        tvNewsDetailTime.setText(FormatUtils.formatTime(time));
        String linkCss = "<style type=\"text/css\"> " +
                "img {" +
                "width:100%;" +
                "height:auto;" +
                "}" +
                "</style>";

        String html = "<html><header>" + linkCss + "</header>" + content + "</body></html>";
        webview.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
    }
}
