package com.jzbwlkj.leifeng.ui.activity;

import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.saomiao.decoding.Intents;
import com.jzbwlkj.leifeng.ui.bean.ChatListDeticalBean;
import com.jzbwlkj.leifeng.ui.bean.CommitBean;
import com.jzbwlkj.leifeng.utils.CommonApi;
import com.jzbwlkj.leifeng.utils.FormatUtils;
import com.jzbwlkj.leifeng.utils.RoundCornesTransFormation;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 志愿培训详情
 */
public class TrainingDetailsActivity extends BaseActivity {

    @BindView(R.id.iv_banner)
    ImageView ivBanner;
    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.tv_training_detail)
    TextView tvTrainingDetail;
    private int id;
    private String title;
    private String content;
    private int sstatus = -1;//报名状态
    @Override
    public int getLayoutId() {
        id = getIntent().getIntExtra("id",0);
        title = getIntent().getStringExtra("title");
        return R.layout.activity_training_details;
    }

    @Override
    public void initView() {
        setCenterTitle(title);
    }

    @Override
    public void initData() {
        getNetData();
    }

    @Override
    public void configViews() {

    }

    @OnClick(R.id.tv_training_detail)
    public void onViewClicked() {
        if( sstatus == 0){
            showToastMsg("报名审核中");
        }else if(sstatus == 1){
            showToastMsg("已报名");
        }else if(sstatus == -1){
            CommonApi.commonDialog(activity, "确认报名培训吗？", "确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postTrain();
                }
            });
        }else{
            CommonApi.commonDialog(activity, "确认报名培训吗？", "确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    postTrain();
                }
            });
        }

    }

    /**
     * 提交报名培训
     */
    private void postTrain(){
        RetrofitClient.getInstance().createApi().joinTraining(BaseApp.token,String.valueOf(id))
                .compose(RxUtils.<HttpResult<CommitBean>>io_main())
                .subscribe(new BaseObjObserver<CommitBean>(this,"提交报名") {
                    @Override
                    protected void onHandleSuccess(CommitBean commitBean) {
                        showToastMsg("报名成功");
                        finish();
                    }
                });
    }

    /**
     * 获取网络数据
     */
    private void getNetData() {
        RetrofitClient.getInstance().createApi().chatlistDetical(String.valueOf(id),BaseApp.token)
                .compose(RxUtils.<HttpResult<ChatListDeticalBean>>io_main())
                .subscribe(new BaseObjObserver<ChatListDeticalBean>(this, "消息详情") {
                    @Override
                    protected void onHandleSuccess(ChatListDeticalBean chatListDeticalBean) {
                        String path = chatListDeticalBean.getPic();
                        if(TextUtils.isEmpty(path)||TextUtils.equals("null",path)){
                            path = "ss";
                        }
                        Glide.with(activity).load(path).error(R.mipmap.logo).into(ivBanner);
                        content = chatListDeticalBean.getContent();
                        String ss  = Html.fromHtml(content).toString();
                        setWebData(ss,web);
                        if(chatListDeticalBean.getApply_info() != null){
                            sstatus = chatListDeticalBean.getApply_info().getStatus();
                        }
                        if( sstatus == -1){
                            tvTrainingDetail.setText("报名培训");
                        }else if(sstatus == 0){
                            tvTrainingDetail.setText("报名审核中");
                        }else if(sstatus == 1){
                            tvTrainingDetail.setText("已报名");
                        }else{
                            tvTrainingDetail.setText("报名培训");
                        }
                    }
                });
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
