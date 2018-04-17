package com.jzbwlkj.leifeng.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.ui.adapter.AcManagementDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AcManagementDetailsActivity extends BaseActivity {

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
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_number)
    LinearLayout llNumber;
    @BindView(R.id.tv_launch_event_publish)
    TextView tvLaunchEventPublish;

    private List<String> mList = new ArrayList<>();
    private AcManagementDetailsAdapter adapter;
    private List<EditText> etList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_launch_event;
    }

    @Override
    public void initView() {
        setCenterTitle("活动管理");
        llNumber.setVisibility(View.VISIBLE);
        for (int i = 0; i < 10; i++) {
            mList.add("");
        }
        adapter = new AcManagementDetailsAdapter(R.layout.item_ac_management_details, mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);

        etList.add(etLaunchEventName);
        etList.add(etLaunchEventBaomingTime);
        etList.add(etLaunchEventJiezhiTime);
        etList.add(etLaunchEventStartTime);
        etList.add(etLaunchEventEndTime);
        etList.add(etLaunchEventLengthTime);
        etList.add(etLaunchEventNumber);
        etList.add(etLaunchEventAddress);
        etList.add(etLaunchEventUnit);
        etList.add(etLaunchEventLinkman);
        etList.add(etLaunchEventLinkphone);
        etList.add(etLaunchEventEmail);
        etList.add(etLaunchEventDemand);
        etList.add(etLaunchEventDetails);

    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {
        setData();
    }

    @OnClick({R.id.img_launch_event, R.id.tv_number, R.id.tv_launch_event_publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_launch_event:
                break;
            case R.id.tv_number://已报名人数
                toActivity(RegisteredStaffActivity.class);
                break;
            case R.id.tv_launch_event_publish:
                break;
        }
    }

    private void setData() {
        for (int i = 0; i < etList.size(); i++) {
            etList.get(i).setFocusable(false);
            etList.get(i).setEnabled(false);
            etList.get(i).setText("123");
        }
        List<String> typeList = new ArrayList<>();
        typeList.add("助老爱残");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, typeList);
        spLaunchEventType.setAdapter(adapter);

        List<String> rangeList = new ArrayList<>();
        rangeList.add("300米");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rangeList);
        spLaunchEventRange.setAdapter(adapter2);

    }
}
