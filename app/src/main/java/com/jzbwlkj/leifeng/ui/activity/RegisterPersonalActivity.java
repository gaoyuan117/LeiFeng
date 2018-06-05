package com.jzbwlkj.leifeng.ui.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
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
import com.jzbwlkj.leifeng.utils.ToastUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.Call;

import static com.jzbwlkj.leifeng.utils.ToastUtils.showToast;

public class RegisterPersonalActivity extends BaseActivity {


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
    @BindView(R.id.et_personal_name)
    EditText etPersonalName;
    @BindView(R.id.rb_personal_man)
    RadioButton rbPersonalMan;
    @BindView(R.id.rb_personal_women)
    RadioButton rbPersonalWomen;
    @BindView(R.id.tv_national)
    TextView tvNational;
    @BindView(R.id.tv_card_type)
    TextView tvCardType;
    @BindView(R.id.et_personal_no)
    EditText etPersonalNo;
    @BindView(R.id.et_personal_phone)
    EditText etPersonalPhone;
    @BindView(R.id.tv_shenfen)
    TextView tvShenfen;
    @BindView(R.id.et_personal_job)
    EditText etPersonalJob;
    @BindView(R.id.tv_job)
    TextView tvJob;
    @BindView(R.id.et_personal_address)
    EditText etPersonalAddress;
    @BindView(R.id.et_personal_email)
    EditText etPersonalEmail;
    @BindView(R.id.et_personal_qq)
    EditText etPersonalQq;
    @BindView(R.id.et_personal_wx)
    EditText etPersonalWx;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.et_personal_nengli)
    EditText etPersonalNengli;
    @BindView(R.id.ll_nengli)
    LinearLayout llNengli;
    @BindView(R.id.img_personal)
    ImageView imgPersonal;
    @BindView(R.id.ll_zhengshu)
    LinearLayout llZhengshu;
    @BindView(R.id.et_personal_pwd)
    EditText etPersonalPwd;
    @BindView(R.id.et_personal_repwd)
    EditText etPersonalRepwd;
    @BindView(R.id.cb_personal)
    CheckBox cbPersonal;
    @BindView(R.id.tv_personal_register)
    TextView tvPersonalRegister;
    private String type;

    private int sex = 1;//1男 2女
    private List<TeamListBean> teamList;
    private int is_personnel;
    private String picUrl;

    private View viewType;
    private ListView lvContent;
    private PopupWindow popType;
    private int flag = 0;//1  民族  2  证件  3 政治面貌  4  职业  5城市  6  机构
    private String unitid;//单位Id
    private String nationalId = null;//民族id
    private String cardtype;//证件类型
    private String shenfen;//政治面貌
    private String jobid = null;//职业Id
    private String cityId;//城市id
    private ListViewAdapter lvAdapter;
    private List<MySelfModel> showList = new ArrayList<>();
    private List<MySelfModel> mingzuList = new ArrayList<>();
    private List<MySelfModel> cardList = new ArrayList<>();
    private List<MySelfModel> shenfenList = new ArrayList<>();
    private List<MySelfModel> jobList = new ArrayList<>();
    private List<MySelfModel> cityList = new ArrayList<>();
    private List<MySelfModel> unitList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_register_personal;
    }

    @Override
    public void initView() {
        initPop();
        type = getIntent().getStringExtra("type");
        setData();
    }

    @Override
    public void initData() {
        getTeamList();
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.rb_personal_man, R.id.rb_personal_women, R.id.img_personal, R.id.cb_personal, R.id.tv_personal_register,
            R.id.tv_national, R.id.tv_job, R.id.tv_shenfen, R.id.tv_unit, R.id.tv_city, R.id.tv_card_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_personal_man:
                sex = 1;
                break;
            case R.id.rb_personal_women:
                sex = 2;
                break;
            case R.id.img_personal:
                MultiImageSelector.create().single().origin(new ArrayList<String>()).start(this, AppConfig.REQUEST_IMAGE);
                break;
            case R.id.cb_personal:
                break;
            case R.id.tv_personal_register:
                register();
                break;
            case R.id.tv_card_type:
                flag = 2;
                refrushData(cardList,tvCardType);
                break;
            case R.id.tv_unit:
                flag = 6;
                refrushData(unitList,tvUnit);
                break;
            case R.id.tv_national:
                flag = 1;
                refrushData(mingzuList,tvNational);
                break;
            case R.id.tv_shenfen:
                flag = 3;
                refrushData(shenfenList,tvShenfen);
                break;
            case R.id.tv_job:
                flag = 4;
                refrushData(jobList,tvJob);
                break;
            case R.id.tv_city:
                flag = 5;
                refrushData(cityList,tvCity);
                break;

        }
    }

    private void refrushData(List<MySelfModel> list,TextView view) {
        showList.clear();
        showList.addAll(list);
        lvAdapter.notifyDataSetChanged();

        popType.setWidth(view.getMeasuredWidth() + 30);
        if (showList.size() > 6) {
            popType.setHeight(500);
        } else {
            popType.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        popType.showAsDropDown(view, -12, 20);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            List<String> list = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            Glide.with(activity).load(list.get(0)).into(imgPersonal);
            updateAvatar(list);
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
                        teamList = list;
                        //所属机构
                        for (int i = 0; i < teamList.size(); i++) {
                            TeamListBean teamListBean = teamList.get(i);
                            MySelfModel model = new MySelfModel();
                            model.setSelected(false);
                            model.setName(teamListBean.getTeam_name());
                            model.setId(teamListBean.getId() + "");
                            unitList.add(model);
                        }
                    }
                });
    }

    private void setData() {
        if (type.equals("normal")) {
            llNengli.setVisibility(View.GONE);
            llZhengshu.setVisibility(View.GONE);
            setCenterTitle("志愿者注册");
            is_personnel = 0;
        } else {
            setCenterTitle("专业志愿者注册");
            is_personnel = 1;
        }

        //民族
        for (int i = 0; i < BaseApp.config.getNatinal_list().size(); i++) {
            ConfigBean.NatinalListBean natinalListBean = BaseApp.config.getNatinal_list().get(i);
            MySelfModel model = new MySelfModel();
            model.setSelected(false);
            model.setName(natinalListBean.getName());
            model.setId(natinalListBean.getId() + "");
            mingzuList.add(model);
        }

        //证件类型
        for (int i = 0; i < BaseApp.config.getId_type().size(); i++) {
            String natinalListBean = BaseApp.config.getId_type().get(i);
            MySelfModel model = new MySelfModel();
            model.setSelected(false);
            model.setName(natinalListBean);
            cardList.add(model);
        }

//政治面貌
        for (int i = 0; i < BaseApp.config.getPolital_status().size(); i++) {
            String natinalListBean = BaseApp.config.getPolital_status().get(i);
            MySelfModel model = new MySelfModel();
            model.setSelected(false);
            model.setName(natinalListBean);
            shenfenList.add(model);
        }

        //职业
        for (int i = 0; i < BaseApp.config.getJob_list().size(); i++) {
            ConfigBean.JobListBean natinalListBean = BaseApp.config.getJob_list().get(i);
            MySelfModel model = new MySelfModel();
            model.setSelected(false);
            model.setName(natinalListBean.getName());
            model.setId(natinalListBean.getId() + "");
            jobList.add(model);
        }

        //所在区域
        for (int i = 0; i < BaseApp.config.getCity_list().size(); i++) {
            ConfigBean.CityListBean natinalListBean = BaseApp.config.getCity_list().get(i);
            MySelfModel model = new MySelfModel();
            model.setSelected(false);
            model.setName(natinalListBean.getName());
            model.setId(natinalListBean.getId() + "");
            cityList.add(model);
        }
    }

    /**
     * 检查输入并输入
     */
    private void register() {
        String name = etPersonalName.getText().toString();
        if (isEmpty(name)) {
            showToastMsg("请输入姓名");
            return;
        }
        String mingzu = tvNational.getText().toString();
        if(TextUtils.isEmpty(mingzu)){
            showToastMsg("请选择您的民族");
            return;
        }

        //证件号码
        String no = etPersonalNo.getText().toString();
        if (isEmpty(no)) {
            showToastMsg("请输入证件号码");
            return;
        }

        //手机号码
        String phone = etPersonalPhone.getText().toString();
        if (isEmpty(phone)) {
            showToastMsg("请输入手机号码");
            return;
        }

        String shen = tvShenfen.getText().toString();

        if(TextUtils.isEmpty(shen)){
            showToastMsg("请选择政治面貌");
            return;
        }
        //工作单位
        String job = etPersonalJob.getText().toString();

        //通讯地址
        String address = etPersonalAddress.getText().toString();

        //电子邮箱
        String email = etPersonalEmail.getText().toString();

        //qq
        String qq = etPersonalQq.getText().toString();

        //微信
        String wx = etPersonalWx.getText().toString();


        //专业志愿者需要填写的信息

        //专业能力
        String power = etPersonalNengli.getText().toString();

        String cc = tvCity.getText().toString();
        if(TextUtils.isEmpty(cc)){
            showToastMsg("请选择所在区域");
            return;
        }

        String jigou = tvUnit.getText().toString();
        if(TextUtils.isEmpty(jigou)){
            showToastMsg("请选择所属机构");
            return;
        }

        //登录密码
        String pwd = etPersonalPwd.getText().toString();
        if (isEmpty(pwd)) {
            showToastMsg("请输入登录密码");
            return;
        }

        if (pwd.length() < 6) {
            ToastUtils.showToast("密码长度不得少于6位");
            return;
        }

        //确认登录密码
        String repwd = etPersonalPwd.getText().toString();
        if (isEmpty(repwd)) {
            showToastMsg("请确认登录密码");
            return;
        }

        if (!repwd.equals(pwd)) {
            showToastMsg("两次密码输入不一致");
            return;
        }

        if (!cbPersonal.isChecked()) {
            showToastMsg("请先同意《高密志愿者APP注册志愿者服务守则》");
            return;
        }

        Map<String, Object> map = new HashMap<>();

//                 证书
        map.put("user_nickname", name);
        map.put("id_no", no);
        map.put("user_pass", pwd);
        map.put("mobile", phone);
        map.put("id_type", cardtype);
        map.put("is_personnel", is_personnel);
        map.put("sex", sex);
        map.put("natinal", nationalId);
        map.put("polital_status", shenfen);
        if(!TextUtils.equals("null",job)&&!TextUtils.isEmpty(job)){
            map.put("worker_address", job);
        }

        if(!TextUtils.isEmpty(address)&&!TextUtils.equals("null",address)){
            map.put("address", address);
        }

        map.put("email", email);
        map.put("qq", qq);
        map.put("wechat", wx);
        map.put("special_skill", power);
        map.put("city_id", cityId);
        if(!TextUtils.isEmpty(jobid)&&!TextUtils.equals("null",jobid)){
            map.put("job", jobid);
        }
        map.put("team_id", unitid);
        if (is_personnel == 1) {
            if (isEmpty(picUrl)) {
                showToastMsg("请上传专业证书");
                return;
            } else {
                map.put("certificate", picUrl);
            }
        }

        for (Map.Entry<String, Object> me : map.entrySet()) {
            String key = me.getKey();
            Object value = me.getValue();
            LogUtils.e(key + "-----------" + value);
        }

        RetrofitClient.getInstance().createApi().personalRegister(map)
                .compose(RxUtils.<HttpResult<CommonBean>>io_main())
                .subscribe(new BaseObjObserver<CommonBean>(activity) {
                    @Override
                    protected void onHandleSuccess(CommonBean commonBean) {
                        showToast("申请成功，等待审核");
                        finish();
                    }
                });
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
                            picUrl = uploadBean.getData().getFile().getUrl();
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
                    tvNational.setText(model.getName());
                    nationalId = model.getName();
                } else if (flag == 2) {
                    tvCardType.setText(model.getName());
                    cardtype = model.getName();
                } else if (flag == 3) {
                    tvShenfen.setText(model.getName());
                    shenfen = model.getName();
                } else if (flag == 4) {
                    tvJob.setText(model.getName());
                    jobid = model.getId();
                } else if (flag == 5) {
                    tvCity.setText(model.getName());
                    cityId = model.getId();
                } else if (flag == 6) {
                    tvUnit.setText(model.getName());
                    unitid = model.getId();
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
