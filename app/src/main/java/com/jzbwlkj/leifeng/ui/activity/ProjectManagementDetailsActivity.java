package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.adapter.AcManagementDetailsAdapter;
import com.jzbwlkj.leifeng.ui.bean.JoinProjectBean;
import com.jzbwlkj.leifeng.ui.bean.ProjectDetialBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProjectManagementDetailsActivity extends BaseActivity {


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
    WebView etPublishProjectDemand;
    @BindView(R.id.et_publish_project_details)
    WebView etPublishProjectDetails;
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
    private int id;

    @Override
    public int getLayoutId() {
        id = getIntent().getIntExtra("id", 0);
        return R.layout.activity_project_management_detail;
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
        getNetData();
        getPeople();
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
        //这里只是进行展示信息和进行结束操作，所以展示的相关内容均不可进行操作
        for (int i = 0; i < etList.size(); i++) {
            etList.get(i).setFocusable(false);
            etList.get(i).setEnabled(false);
        }
        spPublishProjectType.setClickable(false);
        spPublishProjectCycle.setClickable(false);
        spPublishProjectRange.setClickable(false);
        spPublishProjectUnit.setClickable(false);
//        List<String> typeList = new ArrayList<>();
//        typeList.add("助老爱残");
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, typeList);
//        spPublishProjectType.setAdapter(adapter);
//
//        List<String> rangeList = new ArrayList<>();
//        rangeList.add("300米");
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rangeList);
//        spPublishProjectCycle.setAdapter(adapter2);
//        spPublishProjectRange.setAdapter(adapter2);
//        spPublishProjectUnit.setAdapter(adapter2);
//
//
//        CheckBox cbPublishProjectCantie;
//        CheckBox cbPublishProjectTraffic;
//        CheckBox cbPublishProjectInsurance;
//        CheckBox cbPublishProjectTraining;
    }

    /**
     * 获取网络数据
     */
    private void getNetData() {
        RetrofitClient.getInstance().createApi().projectDetial(BaseApp.token, String.valueOf(id))
                .compose(RxUtils.<HttpResult<ProjectDetialBean>>io_main())
                .subscribe(new BaseObjObserver<ProjectDetialBean>(this, "活动详情") {
                    @Override
                    protected void onHandleSuccess(ProjectDetialBean projectDetialBean) {
                        if (projectDetialBean == null) {
                            return;
                        }
                        String path = projectDetialBean.getPic();
                        if (!TextUtils.isEmpty(path) && !TextUtils.equals("null", path)) {
                            Glide.with(ProjectManagementDetailsActivity.this).load(path).error(R.color.green).into(imgPublishProject);
                        }
                        //        .setText(projectDetialBean.getService_hour() + "小时");
                        etPublishProjectName.setText(projectDetialBean.getTitle());
                        //        spPublishProjectType.(projectDetialBean.getService_type_text());
                        etPublishProjectBaomingTime.setText(projectDetialBean.getJoin_time_s_text());
                        etPublishProjectJiezhiTime.setText(projectDetialBean.getJoin_time_e_text());
                        etPublishProjectStartTime.setText(projectDetialBean.getStart_time_text());
                        etPublishProjectEndTime.setText(projectDetialBean.getEnd_time_text());
                        etPublishProjectLengthTime.setText(projectDetialBean.getService_hour() + "小时");
                        etPublishProjectNumber.setText(projectDetialBean.getService_num() + "人");
                        etPublishProjectAddress.setText(projectDetialBean.getAddress());
                        //        tvAcRange.setText(projectDetialBean.getSign_scope() + "米");
                        etPublishProjectSignUpAddress.setText(projectDetialBean.getAddress());
                        etPublishProjectLinkman.setText(projectDetialBean.getContact());
                        etPublishProjectLinkphone.setText(projectDetialBean.getContact_mobile());
                        etPublishProjectEmail.setText("缺少字段");
                        setweb(etPublishProjectDetails, projectDetialBean.getContent());
                        setweb(etPublishProjectDemand, projectDetialBean.getRequirement());
                    }
                });
    }

    /**
     * 对webview赋值
     *
     * @param web     需要复制的webview
     * @param content 内容
     */
    private void setweb(WebView web, String content) {
        String linkCss = "<style type=\"text/css\"> " +
                "img {" +
                "width:100%;" +
                "height:auto;" +
                "}" +
                "</style>";

        String html = "<html><header>" + linkCss + "</header>" + content + "</body></html>";
        web.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
    }

    /**
     * 获取已加入人数
     */
    private void getPeople(){
        RetrofitClient.getInstance().createApi().userList(BaseApp.token,String.valueOf(id))
                .compose(RxUtils.<HttpResult<List<JoinProjectBean>>>io_main())
                .subscribe(new BaseObjObserver<List<JoinProjectBean>>(this,"已加入") {
                    @Override
                    protected void onHandleSuccess(List<JoinProjectBean> joinProjectBeans) {

                    }
                });
    }
}
