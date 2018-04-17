package com.jzbwlkj.leifeng.ui.activity;

import android.view.View;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity {

    @BindView(R.id.tv_set_version)
    TextView tvSetVersion;

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

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.tv_set_clear, R.id.tv_set_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_set_clear:
                break;
            case R.id.tv_set_update:
                break;
        }
    }
}
