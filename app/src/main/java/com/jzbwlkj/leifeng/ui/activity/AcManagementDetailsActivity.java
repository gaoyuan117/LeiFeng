package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
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
import com.jzbwlkj.leifeng.ui.bean.MySelfModel;
import com.jzbwlkj.leifeng.ui.bean.ProjectDetialBean;
import com.jzbwlkj.leifeng.utils.RoundCornesTransFormation;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AcManagementDetailsActivity extends BaseActivity {


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
    WebView etLaunchEventDemand;
    @BindView(R.id.et_launch_event_details)
    WebView etLaunchEventDetails;
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
    private List<MySelfModel> mylist = new ArrayList<>();
    private AcManagementDetailsAdapter adapter;
    private List<EditText> etList = new ArrayList<>();

    private View viewType;
    private ListView lvContent;
    private PopupWindow popType;
    private int id;

    @Override
    public int getLayoutId() {
        id = getIntent().getIntExtra("id",0);
        return R.layout.activity_launch_event;
    }

    @Override
    public void initView() {
        initPop();
        setCenterTitle("活动管理");
        llNumber.setVisibility(View.VISIBLE);
        for (int i = 0; i < 6; i++) {
            MySelfModel model = new MySelfModel();
            model.setName("条件"+i);
            model.setId(i+"");
            model.setSelected(false);
            mylist.add(model);
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

    }

    @Override
    public void initData() {
        getNetData();
        getPeople();
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
        }
//        List<String> typeList = new ArrayList<>();
//        typeList.add("助老爱残");
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, typeList);
//        spLaunchEventType.setAdapter(adapter);

        List<String> rangeList = new ArrayList<>();
        rangeList.add("300米");
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rangeList);
        spLaunchEventRange.setAdapter(adapter2);

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
                            Glide.with(AcManagementDetailsActivity.this).load(path).bitmapTransform(new RoundCornesTransFormation(getActivity(), 16, 16)).error(R.color.green).into(imgLaunchEvent);
                        }
                        //        .setText(projectDetialBean.getService_hour() + "小时");
                        etLaunchEventName.setText(projectDetialBean.getTitle());
                        tvProjectType.setText(projectDetialBean.getService_type_text());
                        etLaunchEventBaomingTime.setText(projectDetialBean.getJoin_time_s_text());
                        etLaunchEventJiezhiTime.setText(projectDetialBean.getJoin_time_e_text());
                        etLaunchEventStartTime.setText(projectDetialBean.getStart_time_text());
                        etLaunchEventEndTime.setText(projectDetialBean.getEnd_time_text());
                        etLaunchEventLengthTime.setText(projectDetialBean.getService_hour() + "小时");
                        etLaunchEventNumber.setText(projectDetialBean.getService_num() + "人");
                        etLaunchEventAddress.setText(projectDetialBean.getAddress());
                        //        tvAcRange.setText(projectDetialBean.getSign_scope() + "米");
                        etLaunchEventUnit.setText(projectDetialBean.getTeam_name());
                        etLaunchEventLinkman.setText(projectDetialBean.getContact());
                        etLaunchEventLinkphone.setText(projectDetialBean.getContact_mobile());
                        etLaunchEventEmail.setText("缺少字段");
                        setweb(etLaunchEventDetails, projectDetialBean.getContent());
                        setweb(etLaunchEventDemand, projectDetialBean.getRequirement());
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
    private void getPeople() {
        RetrofitClient.getInstance().createApi().userList(BaseApp.token, String.valueOf(id))
                .compose(RxUtils.<HttpResult<List<JoinProjectBean>>>io_main())
                .subscribe(new BaseObjObserver<List<JoinProjectBean>>(this, "已加入") {
                    @Override
                    protected void onHandleSuccess(List<JoinProjectBean> joinProjectBeans) {

                    }
                });
    }

    /**
     * 初始化popupwindow
     */
    private void initPop(){
        viewType = LayoutInflater.from(this).inflate(R.layout.pop_list,null);
        lvContent = viewType.findViewById(R.id.lv_content);
        popType = new PopupWindow();
        popType.setFocusable(true);
        popType.setContentView(viewType);

    }

}
