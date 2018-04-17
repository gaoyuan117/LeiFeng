package com.jzbwlkj.leifeng.ui.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;

import butterknife.BindView;

/**
 * 关于我们
 */
public class AboutActivity extends BaseActivity {


    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.img_about_logo)
    ImageView imgAboutLogo;

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
//        AboutBean bean = (AboutBean) getIntent().getSerializableExtra("content");
//        tvContent.setText(bean.getContent());
//        tvVertion.setText(bean.getVersion());
//        CommonApi.glideUtils(imgAboutLogo, bean.getLogo());
    }


    @Override
    public void configViews() {

    }


}
