package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发布活动
 */
public class LaunchEventActivity extends BaseActivity {


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
    @BindView(R.id.et_launch_event_name)
    EditText etLaunchEventName;
    @BindView(R.id.tv_project_type)
    TextView tvProjectType;
    @BindView(R.id.et_launch_event_baoming_time)
    EditText etLaunchEventBaomingTime;
    @BindView(R.id.et_launch_event_jiezhi_time)
    EditText etLaunchEventJiezhiTime;
    @BindView(R.id.et_launch_event_start_time)
    EditText etLaunchEventStartTime;
    @BindView(R.id.et_launch_event_end_time)
    EditText etLaunchEventEndTime;
    @BindView(R.id.et_launch_event_length_time)
    EditText etLaunchEventLengthTime;
    @BindView(R.id.et_launch_event_number)
    EditText etLaunchEventNumber;
    @BindView(R.id.et_launch_event_address)
    EditText etLaunchEventAddress;
    @BindView(R.id.tv_range)
    TextView tvRange;
    @BindView(R.id.et_launch_event_unit)
    EditText etLaunchEventUnit;
    @BindView(R.id.et_launch_event_linkman)
    EditText etLaunchEventLinkman;
    @BindView(R.id.et_launch_event_linkphone)
    EditText etLaunchEventLinkphone;
    @BindView(R.id.et_launch_event_email)
    EditText etLaunchEventEmail;
    @BindView(R.id.et_launch_event_demand)
    WebView etLaunchEventDemand;
    @BindView(R.id.et_launch_event_details)
    WebView etLaunchEventDetails;
    @BindView(R.id.img_launch_event)
    ImageView imgLaunchEvent;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_number)
    LinearLayout llNumber;
    @BindView(R.id.tv_launch_event_publish)
    TextView tvLaunchEventPublish;

    @Override
    public int getLayoutId() {
        return R.layout.activity_launch_event;
    }

    @Override
    public void initView() {
        setCenterTitle("活动发布");
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.img_launch_event, R.id.tv_launch_event_publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_launch_event:
                break;
            case R.id.tv_launch_event_publish:
                break;
        }
    }

}
