package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
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
 * 发布项目
 */
public class PublishProjectActivity extends BaseActivity {

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
    @BindView(R.id.tv_publish_project_publish)
    TextView tvPublishProjectPublish;


    @Override
    public int getLayoutId() {
        return R.layout.activity_publish_project;
    }

    @Override
    public void initView() {
        setCenterTitle("项目发布");
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.img_publish_project, R.id.tv_publish_project_publish})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_publish_project:
                break;
            case R.id.tv_publish_project_publish:
                break;
        }
    }
}
