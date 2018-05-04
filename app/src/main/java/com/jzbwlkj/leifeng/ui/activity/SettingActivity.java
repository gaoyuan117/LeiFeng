package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.utils.CleanMessageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.tv_set_version)
    TextView tvSetVersion;
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
    @BindView(R.id.tv_set_clear)
    TextView tvSetClear;
    @BindView(R.id.tv_set_update)
    TextView tvSetUpdate;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        setCenterTitle("系统设置");
    }

    @Override
    public void initData() {
        //   tvSetVersion.setText(BaseApp.config.get);  现在还没有版本相关信息
        getCache();
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.tv_set_clear, R.id.tv_set_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_set_clear:
                CleanMessageUtil.clearAllCache(getApplicationContext());
                getCache();
                break;
            case R.id.tv_set_update:
                break;
        }
    }

   private void getCache(){
       try {
           String huancun = CleanMessageUtil.getTotalCacheSize(getApplicationContext());
           tvSetClear.setText(huancun);
       } catch (Exception e) {
           tvSetClear.setText("0 k");
       }
   }
}
