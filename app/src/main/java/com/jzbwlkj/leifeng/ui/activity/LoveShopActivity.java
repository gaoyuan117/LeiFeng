package com.jzbwlkj.leifeng.ui.activity;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.base.ViewPagerAdapter;
import com.jzbwlkj.leifeng.ui.fragment.LoveShopFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoveShopActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
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

    private ViewPagerAdapter mAdapter;
    private List<String> mTitles = new ArrayList<>();
    private List<Fragment> mList = new ArrayList<>();


    private View infoView;
    private TextView web;
    private TextView tvTitle;
    private WebView webDui;
    private CheckBox cbCondition;
    private TextView tvButton;
    private Dialog infoDialog;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_team;
    }

    @Override
    public void initView() {
        setCenterTitle("爱心义仓");
        setRightTitle("说明").setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!infoDialog.isShowing()){
                    infoDialog.show();
                }
            }
        });
    }

    @Override
    public void initData() {
        initDialog();
        infoDialog.show();
    }


    @Override
    public void configViews() {
        setTabViewPager();
    }


    private void setTabViewPager() {
        for (int i = 0; i < 3; i++) {
            LoveShopFragment f = new LoveShopFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", i + "");
            f.setArguments(bundle);
            mList.add(f);
        }

        mTitles.add("生活用品");
        mTitles.add("学习用品");
        mTitles.add("党务用品");

        mAdapter = new ViewPagerAdapter(this.getSupportFragmentManager(), this, mList, mTitles);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabTextColors(ContextCompat.getColor(getActivity(), R.color.text_black), ContextCompat.getColor(getActivity(), R.color.global));
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.global));
        mTabLayout.addOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            if (mTabLayout.getTabAt(i) == tab) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /**
     * 初始化说明弹窗
     */
    private void initDialog() {
        infoView = LayoutInflater.from(this).inflate(R.layout.dialog_reason, null);
        tvTitle = infoView.findViewById(R.id.tv_title);
        tvTitle.setText("《义仓物品兑换办法》");
        web = infoView.findViewById(R.id.web);
        webDui = infoView.findViewById(R.id.web_duihuan);
        cbCondition = infoView.findViewById(R.id.cb_xieyi);
        cbCondition.setVisibility(View.GONE);
        tvButton = infoView.findViewById(R.id.tv_sure);
        tvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                infoDialog.dismiss();
            }
        });
        web.setVisibility(View.GONE);
        webDui.setVisibility(View.VISIBLE);
        setWebData(BaseApp.config.getAboutgoods(),webDui);
        infoDialog = new Dialog(this, R.style.wx_dialog);
        infoDialog.setContentView(infoView);

        ViewGroup.LayoutParams layoutParams = infoView.getLayoutParams();
        layoutParams.height = getResources().getDisplayMetrics().widthPixels+200;
        infoView.setLayoutParams(layoutParams);
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
