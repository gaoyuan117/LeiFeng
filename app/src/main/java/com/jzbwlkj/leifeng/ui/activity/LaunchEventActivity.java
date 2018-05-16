package com.jzbwlkj.leifeng.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.haibin.calendarview.Calendar;
import com.haibin.calendarview.CalendarView;
import com.jzbwlkj.leifeng.AppConfig;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.CommonBean;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.adapter.ListViewAdapter;
import com.jzbwlkj.leifeng.ui.bean.ConfigBean;
import com.jzbwlkj.leifeng.ui.bean.MySelfModel;
import com.jzbwlkj.leifeng.ui.bean.TeamListBean;
import com.jzbwlkj.leifeng.ui.bean.UploadBean;
import com.jzbwlkj.leifeng.utils.LogUtils;
import com.jzbwlkj.leifeng.utils.RoundCornesTransFormation;
import com.jzbwlkj.leifeng.utils.StringCheckUtil;
import com.jzbwlkj.leifeng.utils.ToastUtils;
import com.jzbwlkj.leifeng.view.CustomDatePicker;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.Call;

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
    TextView etLaunchEventBaomingTime;
    @BindView(R.id.et_launch_event_jiezhi_time)
    TextView etLaunchEventJiezhiTime;
    @BindView(R.id.et_launch_event_start_time)
    TextView etLaunchEventStartTime;
    @BindView(R.id.et_launch_event_end_time)
    TextView etLaunchEventEndTime;
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
    EditText etLaunchEventDemand;
    @BindView(R.id.et_launch_event_details)
    EditText etLaunchEventDetails;
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
    @BindView(R.id.tv_cycle_time)
    TextView tvCycleTime;
    @BindView(R.id.cb_can)
    CheckBox cbCan;
    @BindView(R.id.cb_jiaotong)
    CheckBox cbJiaotong;
    @BindView(R.id.cb_bao)
    CheckBox cbBao;
    @BindView(R.id.cb_pei)
    CheckBox cbPei;
    @BindView(R.id.et_can_num)
    EditText etCanNum;
    @BindView(R.id.et_tra_num)
    EditText etTraNum;
    @BindView(R.id.et_bao_num)
    EditText etBaoNum;
    @BindView(R.id.tv_project_area)
    TextView tvProjectArea;
    @BindView(R.id.tv_team)
    TextView tvTeam;
    @BindView(R.id.et_day_start)
    EditText etDayStart;
    @BindView(R.id.et_day_end)
    EditText etDayEnd;
    private Map<String, Object> map = new HashMap<>();
    private int flag = 0;//1  餐补  2 交通补  3 保险   4  培训
    private int flag2 = 0;// 1 类型  2 服务周期  3  签到范围   4 城市   5 队伍
    private View butieView;
    private TextView tvTongyi;
    private TextView tvDIY;
    private PopupWindow butiePop;
    private List<MySelfModel> typeList = new ArrayList<>();
    private List<MySelfModel> serviceList = new ArrayList<>();
    private List<MySelfModel> rangeList = new ArrayList<>();
    private List<MySelfModel> unitList = new ArrayList<>();
    private List<MySelfModel> cityList = new ArrayList<>();
    private ListViewAdapter lvAdapter;
    private List<MySelfModel> showList = new ArrayList<>();
    private View viewType;
    private ListView lvContent;
    private PopupWindow popType;

    private LatLng location;//活动的经纬度
    private String picPath;//活动宣传图片
    private String now;
    private int type = 0;
    private CustomDatePicker customDatePicker1;

    private View riliView;
    private CalendarView calendarView;
    private Dialog riliDialog;

    private TextView tvCancel;
    private TextView tvOk;

    @Override
    public int getLayoutId() {
        return R.layout.activity_launch_event;
    }

    @Override
    public void initView() {
        initRili();
        setCenterTitle("活动发布");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());
        etLaunchEventBaomingTime.setText(now.split(" ")[0]);
        etLaunchEventEndTime.setText(now.split(" ")[0]);
        etLaunchEventJiezhiTime.setText(now.split(" ")[0]);
        etLaunchEventStartTime.setText(now.split(" ")[0]);
        initTimeDialog();
        initPop();
        initCheck();
    }

    @Override
    public void initData() {
        showList.clear();
        map.put("canbu", "-1");
        map.put("baoxianbuzu", "-1");
        map.put("peixun", "-1");
        map.put("jiaotongbuzu", "-1");
        getTeamList();
        for (int i = 0; i < BaseApp.config.getService_type().size(); i++) {
            String ss = BaseApp.config.getService_type().get(i);
            MySelfModel model = new MySelfModel();
            model.setPid(i + "");
            model.setId(i + "");
            model.setName(ss);
            model.setSelected(false);
            typeList.add(model);
        }

        for (int i = 0; i < BaseApp.config.getSign_scope().size(); i++) {
            String ss = BaseApp.config.getSign_scope().get(i);
            MySelfModel model = new MySelfModel();
            model.setPid(i + "");
            model.setId(i + "");
            model.setName(ss + "米");
            model.setSelected(false);
            rangeList.add(model);
        }

        for (int i = 0; i < BaseApp.config.getService_time().size(); i++) {
            String ss = BaseApp.config.getService_time().get(i);
            MySelfModel model = new MySelfModel();
            model.setPid(i + "");
            model.setId(i + "");
            model.setName(ss);
            model.setSelected(false);
            serviceList.add(model);
        }

        for (int i = 0; i < BaseApp.config.getCity_list().size(); i++) {
            ConfigBean.CityListBean ss = BaseApp.config.getCity_list().get(i);
            MySelfModel model = new MySelfModel();
            model.setPid(ss.getPid() + "");
            model.setId(ss.getId() + "");
            model.setName(ss.getName());
            model.setSelected(false);
            cityList.add(model);
        }
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.img_launch_event, R.id.tv_launch_event_publish, R.id.tv_range, R.id.tv_project_type,
            R.id.tv_cycle_time, R.id.tv_team, R.id.tv_project_area, R.id.et_launch_event_baoming_time,
            R.id.et_launch_event_jiezhi_time, R.id.et_launch_event_end_time, R.id.et_launch_event_start_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_launch_event:
                MultiImageSelector.create().single().origin(new ArrayList<String>()).start(this, AppConfig.REQUEST_IMAGE);
                break;
            case R.id.tv_launch_event_publish:
                if (checkCondition()) {
                    postProject(map);
                }
                break;
            case R.id.tv_project_type:
                flag2 = 1;
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
                flag2 = 3;
                showList.clear();
                showList.addAll(rangeList);
                lvAdapter.notifyDataSetChanged();
                popType.setWidth(tvRange.getMeasuredWidth() + 30);
                if (showList.size() > 6) {
                    popType.setHeight(500);
                } else {
                    popType.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                }
                popType.showAsDropDown(tvRange, -12, 20);
                break;
            case R.id.tv_cycle_time:
//                flag2 = 2;
//                showList.clear();
//                showList.addAll(serviceList);
//                lvAdapter.notifyDataSetChanged();
//                popType.setWidth(tvCycleTime.getMeasuredWidth() + 30);
//                if (showList.size() > 6) {
//                    popType.setHeight(500);
//                } else {
//                    popType.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//                }
//                popType.showAsDropDown(tvCycleTime, -12, 20);
                list.clear();
                riliDialog.show();
                break;

            case R.id.tv_team:
                flag2 = 5;
                showList.clear();
                showList.addAll(unitList);
                lvAdapter.notifyDataSetChanged();
                popType.setWidth(tvTeam.getMeasuredWidth() + 30);
                if (showList.size() > 6) {
                    popType.setHeight(500);
                } else {
                    popType.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                }
                popType.showAsDropDown(tvTeam, -12, 20);
                break;


            case R.id.tv_project_area:
                flag2 = 4;
                showList.clear();
                showList.addAll(cityList);
                lvAdapter.notifyDataSetChanged();
                popType.setWidth(tvProjectArea.getMeasuredWidth() + 30);
                if (showList.size() > 6) {
                    popType.setHeight(500);
                } else {
                    popType.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                }
                popType.showAsDropDown(tvProjectArea, -12, 20);
                break;

            case R.id.et_launch_event_baoming_time:
                type = 1;
                customDatePicker1.show(etLaunchEventBaomingTime.getText().toString());
                break;
            case R.id.et_launch_event_jiezhi_time:
                type = 2;
                customDatePicker1.show(etLaunchEventJiezhiTime.getText().toString());
                break;
            case R.id.et_launch_event_start_time:
                type = 3;
                customDatePicker1.show(etLaunchEventStartTime.getText().toString());
                break;
            case R.id.et_launch_event_end_time:
                type = 4;
                customDatePicker1.show(etLaunchEventEndTime.getText().toString());
                break;
        }
    }

    /**
     * 获取队伍
     */
    private void getTeamList() {
        RetrofitClient.getInstance().createApi().getTeamList("")
                .compose(RxUtils.<HttpResult<List<TeamListBean>>>io_main())
                .subscribe(new BaseObjObserver<List<TeamListBean>>(activity) {
                    @Override
                    protected void onHandleSuccess(List<TeamListBean> list) {
                        if (isEmpty(list)) return;
                        //所属机构
                        for (int i = 0; i < list.size(); i++) {
                            TeamListBean teamListBean = list.get(i);
                            MySelfModel model = new MySelfModel();
                            model.setSelected(false);
                            model.setName(teamListBean.getTeam_name());
                            model.setId(teamListBean.getId() + "");
                            unitList.add(model);
                        }
                    }
                });
    }

    /**
     * 活动提交
     *
     * @param map
     */
    private void postProject(Map<String, Object> map) {
        RetrofitClient.getInstance().createApi().publicProject(map)
                .compose(RxUtils.<HttpResult<CommonBean>>io_main())
                .subscribe(new BaseObjObserver<CommonBean>(this, "发布活动") {
                    @Override
                    protected void onHandleSuccess(CommonBean commonBean) {
                        showToastMsg("活动发布成功");
                        finish();
                    }
                });
    }

    /**
     * 检查是否符合发布条件
     *
     * @return
     */
    private boolean checkCondition() {

        String name = etLaunchEventName.getText().toString();
        String type = tvProjectType.getText().toString();
        String baotime = etLaunchEventBaomingTime.getText().toString();
        String jietime = etLaunchEventJiezhiTime.getText().toString();
        String starttime = etLaunchEventStartTime.getText().toString();
        String endtime = etLaunchEventEndTime.getText().toString();
        String onetime = etLaunchEventLengthTime.getText().toString();
        String zhouqi = tvCycleTime.getText().toString();
        String personNum = etLaunchEventNumber.getText().toString();
        String address = etLaunchEventAddress.getText().toString();
        String range = tvRange.getText().toString();
        String startt = etDayStart.getText().toString();
        String linkMan = etLaunchEventLinkman.getText().toString();
        String linkPhone = etLaunchEventLinkphone.getText().toString();
        String email = etLaunchEventEmail.getText().toString();
        String yaoqiu = etLaunchEventDemand.getText().toString();
        String detial = etLaunchEventDetails.getText().toString();

        String city = tvProjectArea.getText().toString();
        String endt = etDayEnd.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showToastMsg("您还没有填写活动名称");
            return false;
        } else if (TextUtils.isEmpty(type) || TextUtils.isEmpty((String) map.get("service_type"))) {
            showToastMsg("您还没有选择服务类型");
            return false;
        } else if (TextUtils.isEmpty(baotime)) {
            showToastMsg("请填写报名开始时间");
            return false;
        } else if (TextUtils.isEmpty(jietime)) {
            showToastMsg("请填写报名结束时间");
            return false;
        } else if (TextUtils.isEmpty(starttime)) {
            showToastMsg("请填写活动开始时间");
            return false;
        } else if (TextUtils.isEmpty(endtime)) {
            showToastMsg("请填写活动结束时间");
            return false;
        } else if (TextUtils.isEmpty(onetime)) {
            showToastMsg("请填写单次服务时长");
            return false;
        } else if (TextUtils.isEmpty(zhouqi)) {
            showToastMsg("请选择服务周期");
            return false;
        } else if (TextUtils.isEmpty(personNum)) {
            showToastMsg("请填写活动招募人数");
            return false;
        } else if (TextUtils.isEmpty(address)) {
            showToastMsg("请填写活动地址");
            return false;
        } else if (TextUtils.isEmpty(range)) {
            showToastMsg("请选择签到范围");
            return false;
        } else if (TextUtils.isEmpty(startt)) {
            showToastMsg("请填写每天开始时间");
            return false;
        } else if (TextUtils.isEmpty(linkMan)) {
            showToastMsg("请填写联系人");
            return false;
        } else if (TextUtils.isEmpty(linkPhone)) {
            showToastMsg("请填写联系人电话");
            return false;
        } else if (!StringCheckUtil.isMobileNO(linkPhone)) {
            showToastMsg("联系人电话不正确");
            return false;
        } else if (TextUtils.isEmpty(email)) {
            showToastMsg("请填写联系邮箱");
            return false;
        } else if (!StringCheckUtil.isEmail(email)) {
            showToastMsg("联系邮箱格式不正确");
            return false;
        } else if (TextUtils.isEmpty(endt)) {
            showToastMsg("请填写每天结束时间");
            return false;
        } else if (TextUtils.isEmpty(city) || TextUtils.isEmpty((String) map.get("city_id"))) {
            showToastMsg("请选择活动区域");
            return false;
        } else if (TextUtils.isEmpty((String) map.get("pic"))) {
            showToastMsg("请上传活动宣传图片");
            return false;
        } else if (TextUtils.isEmpty((String) map.get("lat")) || TextUtils.isEmpty((String) map.get("lng"))) {
            showToastMsg("位置信息获取失败，请重新填写");
            return false;
        } else {
            if(etCanNum.getVisibility() == View.GONE){
                map.put("canbu","-1");
            }else if(etCanNum.getVisibility() == View.VISIBLE){
                String ss = etCanNum.getText().toString();
                if(!TextUtils.isEmpty(ss)&&TextUtils.equals("统一安排",ss)){
                    map.put("canbu","0");
                }else if(!TextUtils.isEmpty(ss)&&isNumeric(ss)){
                    map.put("canbu",ss);
                }else{
                    map.put("canbu","-1");
                }
            }

            if(etTraNum.getVisibility() == View.GONE){
                map.put("jiaotongbuzu","-1");
            }else if(etTraNum.getVisibility() == View.VISIBLE){
                String ss = etTraNum.getText().toString();
                if(!TextUtils.isEmpty(ss)&&TextUtils.equals("统一安排",ss)){
                    map.put("jiaotongbuzu","0");
                }else if(!TextUtils.isEmpty(ss)&&isNumeric(ss)){
                    map.put("jiaotongbuzu",ss);
                }else{
                    map.put("jiaotongbuzu","-1");
                }
            }

            if(etBaoNum.getVisibility() == View.GONE){
                map.put("baoxianbuzu","-1");
            }else if(etBaoNum.getVisibility() == View.VISIBLE){
                String ss = etBaoNum.getText().toString();
                if(!TextUtils.isEmpty(ss)&&TextUtils.equals("统一安排",ss)){
                    map.put("baoxianbuzu","0");
                }else if(!TextUtils.isEmpty(ss)&&isNumeric(ss)){
                    map.put("baoxianbuzu",ss);
                }else{
                    map.put("baoxianbuzu","-1");
                }
            }
            map.put("team_token", BaseApp.token);
            map.put("type", "1");
            map.put("title", name);
            map.put("join_time_s", baotime);
            map.put("join_time_e", jietime);
            map.put("start_time", starttime);
            map.put("end_time", endtime);
            map.put("date_str", zhouqi);
            map.put("service_hour", onetime);
            map.put("service_num", personNum);
            map.put("address", address);
            map.put("sign_scope", range);
            map.put("requirement", yaoqiu);
            map.put("content", detial);
            map.put("note", "无");
            map.put("day_start_time", startt);//      服务当天开始时间   dddd
            map.put("day_end_time", endt);
            return true;
        }
    }

    //用正则表达式判断字符串是否为数字（含负数）
    public static boolean isNumeric(String str) {
        String regEx = "^-?[0-9]+$";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(str);
        if (mat.find()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * 检测四个选择框的选种情况  未选时 没有  选中时弹pop选统一还是自定义，自定义的话输入框展示，输入标准
     * <p>
     * map.put("canbu", "-1");
     * map.put("jiaotongbuzu", "-1");
     * map.put("baoxianbuzu", "-1");
     * map.put("peixun", "-1");
     */
    private void initCheck() {
        cbCan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                flag = 1;
                if (isChecked) {//选中
                    etCanNum.setVisibility(View.VISIBLE);
                    butiePop.setWidth(250);
                    butiePop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    butiePop.showAsDropDown(cbCan, -6, 10);
                } else {
                    etCanNum.setText("");
                    etCanNum.setVisibility(View.INVISIBLE);
//                    map.put("canbu", "-1");
                }
            }
        });

        cbJiaotong.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                flag = 2;
                if (isChecked) {//选中
                    etTraNum.setVisibility(View.VISIBLE);
                    butiePop.setWidth(250);
                    butiePop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    butiePop.showAsDropDown(cbJiaotong, -6, 10);
                } else {
                    etTraNum.setText("");
                    etTraNum.setVisibility(View.INVISIBLE);
//                    map.put("jiaotongbuzu", "-1");
                }
            }
        });

        cbBao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                flag = 3;
                if (isChecked) {//选中
                    etBaoNum.setVisibility(View.VISIBLE);
                    butiePop.setWidth(250);
                    butiePop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                    butiePop.showAsDropDown(cbBao, -6, 10);
                } else {
                    etBaoNum.setVisibility(View.INVISIBLE);
                    etBaoNum.setText("");
//                    map.put("baoxianbuzu", "-1");
                }
            }
        });

        cbPei.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                flag = 4;
                if (isChecked) {//选中
                    map.put("peixun", "1");
                } else {
                    map.put("peixun", "0");
                }
            }
        });

        etLaunchEventAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String ss = etLaunchEventAddress.getText().toString();
                if (!TextUtils.isEmpty(ss)) {
                    if (!hasFocus) {
                        if (ss.contains("市")) {
                            final String[] cc = ss.split("市");
                            if (cc.length == 2) {
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        getjingWei(cc[0], cc[1]);
                                    }
                                }).start();

                            } else {
                                showToastMsg("请按照xx市xx区来填写地址");
                            }
                        } else {
                            showToastMsg("请按照xx市xx区来填写地址");
                        }

                    }
                }
            }
        });
        etCanNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 初始化 列表的popupwindow   补贴的popupwindow
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
                if (flag2 == 1) {
                    tvProjectType.setText(model.getName());
                    map.put("service_type", model.getId());
                } else if (flag2 == 2) {
                    //                tvCycleTime.setText(model.getName());
                } else if (flag2 == 3) {
                    tvRange.setText(model.getName());
                } else if (flag2 == 4) {
                    tvProjectArea.setText(model.getName());
                    map.put("city_id", model.getId());
                } else if (flag2 == 5) {
                    tvTeam.setText(model.getName());
                    map.put("team_id", model.getId());
                }
            }
        });
        lvContent.setAdapter(lvAdapter);
        popType = new PopupWindow(this);
        popType.setFocusable(true);
        popType.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景防止出现黑色边框
        popType.setFocusable(true);
        popType.setContentView(viewType);


        butieView = LayoutInflater.from(this).inflate(R.layout.pop_buite_layout, null);
        tvTongyi = butieView.findViewById(R.id.tv_tongyi);
        tvDIY = butieView.findViewById(R.id.tv_diy);
        tvTongyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1) {
                    etCanNum.setText("统一安排");
                    etCanNum.setFocusable(false);
                } else if (flag == 2) {
                    etTraNum.setText("统一安排");
                    etTraNum.setFocusable(false);
                } else if (flag == 3) {
                    etBaoNum.setText("统一安排");
                    etBaoNum.setFocusable(false);
                }

                butiePop.dismiss();
            }
        });

        tvDIY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                butiePop.dismiss();
                if (flag == 1) {
                    etCanNum.setText("");
                    etEdit(etCanNum);
                    showKeyboard(etCanNum);
                } else if (flag == 2) {
                    etTraNum.setText("");
                    etEdit(etTraNum);
                    showKeyboard(etTraNum);
                } else if (flag == 3) {
                    etBaoNum.setText("");
                    etEdit(etBaoNum);
                    showKeyboard(etBaoNum);
                }
            }
        });

        butiePop = new PopupWindow(this);
        butiePop.setFocusable(true);
        butiePop.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景防止出现黑色边框
        butiePop.setFocusable(true);
        butiePop.setContentView(butieView);

    }

    private void etEdit(EditText editText){
        editText.setFocusable(true);
        editText.setCursorVisible(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
    }

    /**
     * 展示软键盘
     *
     * @param et
     */
    private void showKeyboard(final EditText et) {
        et.requestFocus(); //edittext是一个EditText控件
        Timer timer = new Timer(); //设置定时器
        timer.schedule(new TimerTask() {
            @Override
            public void run() { //弹出软键盘的代码
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(et, InputMethodManager.RESULT_SHOWN);
                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
            }
        }, 300); //设置300毫秒的时长
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            List<String> list = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            Glide.with(activity).load(list.get(0)).bitmapTransform(new RoundCornesTransFormation(this, 10, 10))
                    .into(imgLaunchEvent);
            updateAvatar(list);
        }
    }

    /**
     * 上传图片
     *
     * @param paths
     */
    public void updateAvatar(final List<String> paths) {
        OkHttpUtils.post()
                .addFile("file", "file.jpg", new File(paths.get(0)))
                .url(AppConfig.BASE_URL + "/api/file/upload")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("结果：" + response);
                        UploadBean uploadBean = new Gson().fromJson(response, UploadBean.class);
                        if (uploadBean.getCode() == 200) {
                            picPath = uploadBean.getData().getFile().getUrl();
                            map.put("pic", picPath);
                        }
                    }
                });
    }

    /**
     * 获取经纬度
     */

    private void getjingWei(String shi, String detical) {
        GeoCoder mSearch = GeoCoder.newInstance();
        OnGetGeoCoderResultListener listener = new OnGetGeoCoderResultListener() {
            public void onGetGeoCodeResult(GeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showToast("当前位置无法获取经纬度信息，请重新填写");
                        }
                    });
                } else {
                    location = result.getLocation();
                    map.put("lat", location.latitude + "");
                    map.put("lng", location.longitude + "");
                }
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    new Handler().post(new Runnable() {
                        @Override
                        public void run() {
                            ToastUtils.showToast("当前位置无法获取经纬度信息，请重新填写");
                        }
                    });

                } else {
                    location = result.getLocation();
                    map.put("lat", location.latitude + "");
                    map.put("lng", location.longitude + "");
                }

            }
        };
        mSearch.setOnGetGeoCodeResultListener(listener);
        mSearch.geocode(new GeoCodeOption()
                .city(shi)
                .address(detical));
        mSearch.destroy();
    }

    /**
     * 初始化时间选择器
     */
    private void initTimeDialog() {

        customDatePicker1 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                if (type == 1) {
                    etLaunchEventBaomingTime.setText(time.split(" ")[0]);
                } else if (type == 2) {
                    etLaunchEventJiezhiTime.setText(time.split(" ")[0]);
                } else if (type == 3) {
                    etLaunchEventStartTime.setText(time.split(" ")[0]);
                } else if (type == 4) {
                    etLaunchEventEndTime.setText(time.split(" ")[0]);
                }
            }
        }, now, "3000-12-31 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker1.showSpecificTime(false); // 不显示时和分
        customDatePicker1.setIsLoop(false); // 不允许循环滚动
    }

    private List<Calendar> list = new ArrayList<>();

    /**
     * 初始化日历控件
     */
    private void initRili() {
        riliView = LayoutInflater.from(this).inflate(R.layout.dialog_rili, null);
        calendarView = riliView.findViewById(R.id.calendarView);
        tvOk = riliView.findViewById(R.id.tv_ok);
        tvCancel = riliView.findViewById(R.id.tv_cancle);
        calendarView.setSchemeDate(list);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                riliDialog.dismiss();
            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss = null;
                for (Calendar calendar : list) {
                    if (TextUtils.isEmpty(ss)) {
                        ss = calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay();
                    } else {
                        ss = ss + "," + calendar.getYear() + "-" + calendar.getMonth() + "-" + calendar.getDay();
                    }
                }
                tvCycleTime.setText(ss);
                riliDialog.dismiss();
            }
        });
        //       calendarView.setSelectedColor(getResources().getColor(R.color.text_red),getResources().getColor(R.color.white),getResources().getColor(R.color.text_red));
        calendarView.setSchemeColor(getResources().getColor(R.color.text_red), getResources().getColor(R.color.text_blue), getResources().getColor(R.color.text_blue));
        calendarView.setOnDateSelectedListener(new CalendarView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Calendar calendar, boolean isClick) {
                list.add(calendar);
                calendarView.setSchemeDate(list);
            }
        });

        riliDialog = new Dialog(this, R.style.wx_dialog);
        riliDialog.setContentView(riliView);
        riliDialog.setCanceledOnTouchOutside(false);

        ViewGroup.LayoutParams layoutParams = riliView.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        riliView.setLayoutParams(layoutParams);
        riliDialog.getWindow().setGravity(Gravity.BOTTOM);
        riliDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
    }

}
