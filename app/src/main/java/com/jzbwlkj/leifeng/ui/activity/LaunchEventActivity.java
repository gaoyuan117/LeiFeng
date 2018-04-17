package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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

    @BindView(R.id.et_launch_event_name)
    EditText etLaunchEventName;
    @BindView(R.id.sp_launch_event_type)
    Spinner spLaunchEventType;
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
    @BindView(R.id.sp_launch_event_range)
    Spinner spLaunchEventRange;
    @BindView(R.id.et_launch_event_unit)
    EditText etLaunchEventUnit;
    @BindView(R.id.et_launch_event_linkman)
    EditText etLaunchEventLinkman;
    @BindView(R.id.et_launch_event_linkphone)
    EditText etLaunchEventLinkphone;
    @BindView(R.id.et_launch_event_email)
    EditText etLaunchEventEmail;
    @BindView(R.id.et_launch_event_demand)
    EditText etLaunchEventDemand;
    @BindView(R.id.et_launch_event_details)
    EditText etLaunchEventDetails;
    @BindView(R.id.img_launch_event)
    ImageView imgLaunchEvent;
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
