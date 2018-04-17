package com.jzbwlkj.leifeng.ui.activity;

import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.utils.CommonApi;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 志愿培训详情
 */
public class TrainingDetailsActivity extends BaseActivity {

    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.tv_training_detail)
    TextView tvTrainingDetail;


    @Override
    public int getLayoutId() {
        return R.layout.activity_training_details;
    }

    @Override
    public void initView() {
        setCenterTitle("关爱老人");
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }

    @OnClick(R.id.tv_training_detail)
    public void onViewClicked() {
        CommonApi.commonDialog(activity, "确认报名培训吗？", "确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToastMsg("报名成功");
                finish();
            }
        });
    }
}
