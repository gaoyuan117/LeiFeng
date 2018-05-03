package com.jzbwlkj.leifeng.ui.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
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
import com.jzbwlkj.leifeng.ui.adapter.ListViewAdapter;
import com.jzbwlkj.leifeng.ui.bean.JoinProjectUserBean;
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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ll_number)
    LinearLayout llNumber;
    @BindView(R.id.tv_launch_event_publish)
    TextView tvLaunchEventPublish;
    @BindView(R.id.tv_no_data)
    TextView tvNoData;
    private List<JoinProjectUserBean> mList = new ArrayList<>();
    private List<MySelfModel> showList = new ArrayList<>();

    private List<MySelfModel> typeList = new ArrayList<>();//用来展示类型数据的集合
    private List<MySelfModel> disList = new ArrayList<>();//用来展示签到范围的集合
    private AcManagementDetailsAdapter adapter;
    private List<EditText> etList = new ArrayList<>();

    private View viewType;
    private ListView lvContent;
    private PopupWindow popType;
    private int id;
    private int flag = 0;
    private ListViewAdapter lvAdapter;

    @Override
    public int getLayoutId() {
        id = getIntent().getIntExtra("id", 0);
        return R.layout.activity_launch_event;
    }

    @Override
    public void initView() {
        initPop();
        setCenterTitle("活动管理");
        llNumber.setVisibility(View.VISIBLE);
        for (int i = 0; i < 6; i++) {
            MySelfModel model = new MySelfModel();
            model.setName("条件" + (i + 1));
            model.setId((i + 1) + "");
            model.setSelected(false);
            typeList.add(model);

            MySelfModel model2 = new MySelfModel();
            model2.setName((i + 1) + "00" + "米");
            model2.setId((i + 1) + "");
            model2.setSelected(false);
            disList.add(model2);

        }
        adapter = new AcManagementDetailsAdapter(R.layout.item_ac_management_details, mList, this);
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

    @OnClick({R.id.img_launch_event, R.id.tv_number, R.id.tv_launch_event_publish, R.id.tv_project_type, R.id.tv_range})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_launch_event:

                break;
            case R.id.tv_number://已报名人数
                Intent intent = new Intent(this, RegisteredStaffActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                break;
            case R.id.tv_launch_event_publish:
                break;

            case R.id.tv_project_type:
                flag = 1;
                showList.clear();
                showList.addAll(typeList);
                lvAdapter.notifyDataSetChanged();
                popType.setWidth(tvProjectType.getMeasuredWidth() + 30);
                if (showList.size() > 6) {
                    popType.setHeight(500);
                } else {
                    popType.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                }
                popType.showAsDropDown(tvProjectType, -12, 20);
                break;

            case R.id.tv_range:
                flag = 2;
                showList.clear();
                showList.addAll(disList);
                lvAdapter.notifyDataSetChanged();
                popType.setWidth(tvProjectType.getMeasuredWidth() + 30);
                if (showList.size() > 6) {
                    popType.setHeight(500);
                } else {
                    popType.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                }
                popType.showAsDropDown(tvRange, -12, 20);
                break;
        }
    }

    private void setData() {
        for (int i = 0; i < etList.size(); i++) {
            etList.get(i).setFocusable(false);
            etList.get(i).setEnabled(false);
        }
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
                        tvRange.setText(projectDetialBean.getSign_scope() + "米");
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
                .compose(RxUtils.<HttpResult<List<JoinProjectUserBean>>>io_main())
                .subscribe(new BaseObjObserver<List<JoinProjectUserBean>>(this, "已加入") {
                    @Override
                    protected void onHandleSuccess(List<JoinProjectUserBean> joinProjectBeans) {
                        if (joinProjectBeans.size() > 0) {
                            recyclerView.setVisibility(View.VISIBLE);
                            tvNoData.setVisibility(View.GONE);
                            mList.addAll(joinProjectBeans);
                            adapter.notifyDataSetChanged();
                        } else {
                            recyclerView.setVisibility(View.GONE);
                            tvNoData.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    /**
     * 初始化popupwindow
     */
    private void initPop() {
        viewType = LayoutInflater.from(this).inflate(R.layout.pop_list, null);
        lvContent = viewType.findViewById(R.id.lv_content);
        lvAdapter = new ListViewAdapter(showList, this);
        lvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MySelfModel model = showList.get(position);
                for (MySelfModel model1 : showList) {
                    if (TextUtils.equals(model.getName(), model1.getName())) {
                        model1.setSelected(true);
                    } else {
                        model1.setSelected(false);
                    }
                }
                lvAdapter.notifyDataSetChanged();
                popType.dismiss();
                if (flag == 1) {
                    tvProjectType.setText(model.getName());
                } else if (flag == 2) {
                    tvRange.setText(model.getName());
                }
            }
        });
        lvContent.setAdapter(lvAdapter);
        popType = new PopupWindow(this);
        popType.setFocusable(true);
        popType.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景防止出现黑色边框
        popType.setFocusable(true);
        popType.setContentView(viewType);
    }

}
