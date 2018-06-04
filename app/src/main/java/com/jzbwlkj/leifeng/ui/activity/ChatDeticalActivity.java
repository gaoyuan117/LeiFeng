package com.jzbwlkj.leifeng.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.bean.ChatListDeticalBean;
import com.jzbwlkj.leifeng.utils.CommonApi;
import com.jzbwlkj.leifeng.utils.FormatUtils;

import butterknife.BindView;

public class ChatDeticalActivity extends BaseActivity {

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

    @Override
    public int getLayoutId() {
        return R.layout.activity_chat_detical;
    }

    @Override
    public void initView() {
        id = getIntent().getIntExtra("id", 0);
        setCenterTitle("消息详情");
//        setRightTitle("分享").setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CommonApi.share(activity, title, null);
//            }
//        });
    }

    @Override
    public void initData() {
        getNetData();
    }


    @Override
    public void configViews() {

    }

    private void setData() {
        //因为接口返回原因title一直为空，现在先不使用当前判断，数据正常后可放开
        if (!TextUtils.isEmpty(title)) {
            tvNewsDetailTitle.setText(title);
        };
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

    /**
     * 获取网络数据
     */
    private void getNetData() {
        RetrofitClient.getInstance().createApi().chatlistDetical(String.valueOf(id), BaseApp.token)
                .compose(RxUtils.<HttpResult<ChatListDeticalBean>>io_main())
                .subscribe(new BaseObjObserver<ChatListDeticalBean>(this, "消息详情") {
                    @Override
                    protected void onHandleSuccess(ChatListDeticalBean chatListDeticalBean) {
                        time = chatListDeticalBean.getAdd_time();
                        title = chatListDeticalBean.getTitle();
                        content = chatListDeticalBean.getContent();
                        if(!TextUtils.isEmpty(content)){
                            content = Html.fromHtml(content).toString();
                        }else{
                            content = "当前消息无内容";
                        }
                        setData();
                    }
                });
    }
}
