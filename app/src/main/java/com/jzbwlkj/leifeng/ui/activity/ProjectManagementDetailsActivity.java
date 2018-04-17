package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
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
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectManagementDetailsActivity extends BaseActivity {

    @BindView(R.id.et_publish_project_name)
    EditText etPublishProjectName;
    @BindView(R.id.sp_publish_project_type)
    Spinner spPublishProjectType;
    @BindView(R.id.et_publish_project_baoming_time)
    EditText etPublishProjectBaomingTime;
    @BindView(R.id.et_publish_project_jiezhi_time)
    EditText etPublishProjectJiezhiTime;
    @BindView(R.id.sp_publish_project_cycle)
    Spinner spPublishProjectCycle;
    @BindView(R.id.et_publish_project_start_time)
    EditText etPublishProjectStartTime;
    @BindView(R.id.et_publish_project_end_time)
    EditText etPublishProjectEndTime;
    @BindView(R.id.et_publish_project_length_time)
    EditText etPublishProjectLengthTime;
    @BindView(R.id.et_publish_project_number)
    EditText etPublishProjectNumber;
    @BindView(R.id.et_publish_project_address)
    EditText etPublishProjectAddress;
    @BindView(R.id.et_publish_project_sign_up_address)
    EditText etPublishProjectSignUpAddress;
    @BindView(R.id.sp_publish_project_range)
    Spinner spPublishProjectRange;
    @BindView(R.id.et_publish_project_sign_up_valid_time)
    EditText etPublishProjectSignUpValidTime;
    @BindView(R.id.cb_publish_project_cantie)
    CheckBox cbPublishProjectCantie;
    @BindView(R.id.et_publish_project_fee)
    EditText etPublishProjectFee;
    @BindView(R.id.cb_publish_project_traffic)
    CheckBox cbPublishProjectTraffic;
    @BindView(R.id.cb_publish_project_insurance)
    CheckBox cbPublishProjectInsurance;
    @BindView(R.id.cb_publish_project_training)
    CheckBox cbPublishProjectTraining;
    @BindView(R.id.sp_publish_project_unit)
    Spinner spPublishProjectUnit;
    @BindView(R.id.et_publish_project_linkman)
    EditText etPublishProjectLinkman;
    @BindView(R.id.et_publish_project_linkphone)
    EditText etPublishProjectLinkphone;
    @BindView(R.id.et_publish_project_email)
    EditText etPublishProjectEmail;
    @BindView(R.id.et_publish_project_demand)
    EditText etPublishProjectDemand;
    @BindView(R.id.et_publish_project_details)
    EditText etPublishProjectDetails;
    @BindView(R.id.img_publish_project)
    ImageView imgPublishProject;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_number)
    LinearLayout llNumber;
    @BindView(R.id.tv_publish_project_publish)
    TextView tvPublishProjectPublish;

    private List<String> mList = new ArrayList<>();
    private AcManagementDetailsAdapter adapter;
    private List<EditText> etList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_publish_project;
    }

    @Override
    public void initView() {
        setCenterTitle("项目管理");
        llNumber.setVisibility(View.VISIBLE);

        llNumber.setVisibility(View.VISIBLE);
        for (int i = 0; i < 10; i++) {
            mList.add("");
        }
        adapter = new AcManagementDetailsAdapter(R.layout.item_ac_management_details, mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {
        etList.add(etPublishProjectName);
        etList.add(etPublishProjectBaomingTime);
        etList.add(etPublishProjectJiezhiTime);
        etList.add(etPublishProjectStartTime);
        etList.add(etPublishProjectEndTime);
        etList.add(etPublishProjectLengthTime);
        etList.add(etPublishProjectNumber);
        etList.add(etPublishProjectAddress);
        etList.add(etPublishProjectSignUpAddress);
        etList.add(etPublishProjectSignUpValidTime);
        etList.add(etPublishProjectFee);
        etList.add(etPublishProjectLinkman);
        etList.add(etPublishProjectLinkphone);
        etList.add(etPublishProjectEmail);
        etList.add(etPublishProjectDemand);
        etList.add(etPublishProjectDetails);

        setData();
    }

    @OnClick({R.id.img_publish_project, R.id.tv_publish_project_publish, R.id.tv_number})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_publish_project:
                break;
            case R.id.tv_number:
                toActivity(RegisteredStaffActivity.class);
                break;
            case R.id.tv_publish_project_publish:
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
        spPublishProjectType.setAdapter(adapter);

        List<String> rangeList = new ArrayList<>();
        rangeList.add("300米");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rangeList);
        spPublishProjectCycle.setAdapter(adapter2);
        spPublishProjectRange.setAdapter(adapter2);
        spPublishProjectUnit.setAdapter(adapter2);


        CheckBox cbPublishProjectCantie;
        CheckBox cbPublishProjectTraffic;
        CheckBox cbPublishProjectInsurance;
        CheckBox cbPublishProjectTraining;
    }
}
