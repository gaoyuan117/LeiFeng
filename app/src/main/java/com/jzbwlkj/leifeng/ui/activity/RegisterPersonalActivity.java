package com.jzbwlkj.leifeng.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
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
import com.jzbwlkj.leifeng.ui.bean.ConfigBean;
import com.jzbwlkj.leifeng.ui.bean.TeamListBean;
import com.jzbwlkj.leifeng.ui.bean.UploadBean;
import com.jzbwlkj.leifeng.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.Call;

import static com.jzbwlkj.leifeng.utils.ToastUtils.showToast;

public class RegisterPersonalActivity extends BaseActivity {

    @BindView(R.id.et_personal_name)
    EditText etPersonalName;
    @BindView(R.id.rb_personal_man)
    RadioButton rbPersonalMan;
    @BindView(R.id.rb_personal_women)
    RadioButton rbPersonalWomen;
    @BindView(R.id.sp_personal_national)
    Spinner spPersonalNational;
    @BindView(R.id.sp_personal_card)
    Spinner spPersonalCard;
    @BindView(R.id.et_personal_no)
    EditText etPersonalNo;
    @BindView(R.id.et_personal_phone)
    EditText etPersonalPhone;
    @BindView(R.id.sp_personal_political)
    Spinner spPersonalPolitical;
    @BindView(R.id.et_personal_job)
    EditText etPersonalJob;
    @BindView(R.id.sp_personal_professional)
    Spinner spPersonalProfessional;
    @BindView(R.id.et_personal_address)
    EditText etPersonalAddress;
    @BindView(R.id.et_personal_email)
    EditText etPersonalEmail;
    @BindView(R.id.et_personal_qq)
    EditText etPersonalQq;
    @BindView(R.id.et_personal_wx)
    EditText etPersonalWx;
    @BindView(R.id.sp_personal_area)
    Spinner spPersonalArea;
    @BindView(R.id.sp_personal_institutions)
    Spinner spPersonalInstitutions;
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_register_personal;
    }

    @Override
    public void initView() {
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

    @OnClick({R.id.rb_personal_man, R.id.rb_personal_women, R.id.img_personal, R.id.cb_personal, R.id.tv_personal_register})
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
        }
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
                        List<String> team = new ArrayList<>();
                        for (int i = 0; i < teamList.size(); i++) {
                            team.add(teamList.get(i).getTeam_name());
                        }
                        spPersonalInstitutions.setAdapter(new ArrayAdapter<>(activity, android.R.layout.simple_spinner_item, team));
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
        List<String> nationalList = new ArrayList<>();
        for (int i = 0; i < BaseApp.config.getNatinal_list().size(); i++) {
            nationalList.add(BaseApp.config.getNatinal_list().get(i).getName());
        }
        spPersonalNational.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nationalList));

        //证件类型
        spPersonalCard.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, BaseApp.config.getId_type()));

        //政治面貌
        spPersonalPolitical.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, BaseApp.config.getPolital_status()));

        //职业
        List<String> jobList = new ArrayList<>();
        for (int i = 0; i < BaseApp.config.getJob_list().size(); i++) {
            jobList.add(BaseApp.config.getJob_list().get(i).getName());
        }
        spPersonalProfessional.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, jobList));

        //所在区域
        List<String> arealList = new ArrayList<>();
        for (int i = 0; i < BaseApp.config.getCity_list().size(); i++) {
            arealList.add(BaseApp.config.getCity_list().get(i).getName());
        }
        spPersonalArea.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arealList));
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

        //登录密码
        String pwd = etPersonalPwd.getText().toString();
        if (isEmpty(pwd)) {
            showToastMsg("请输入登录密码");
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
        map.put("id_type", (String) spPersonalCard.getSelectedItem());
        map.put("is_personnel", is_personnel);
        map.put("sex", sex);
        map.put("natinal", spPersonalNational.getSelectedItem());
        map.put("polital_status", spPersonalPolitical.getSelectedItem());
        map.put("worker_address", job);
        map.put("address", address);
        map.put("email", email);
        map.put("qq", qq);
        map.put("wechat", wx);
        map.put("special_skill", power);

        String area = (String) spPersonalArea.getSelectedItem();
        LogUtils.e("地区：" + area);
        for (int i = 0; i < BaseApp.config.getCity_list().size(); i++) {
            if (area.equals(BaseApp.config.getCity_list().get(i).getName())) {
                map.put("city_id", BaseApp.config.getCity_list().get(i).getId());
            }
        }

        String j = (String) spPersonalProfessional.getSelectedItem();
        LogUtils.e("职业：" + j);
        for (int i = 0; i < BaseApp.config.getJob_list().size(); i++) {
            if (j.equals(BaseApp.config.getJob_list().get(i).getName())) {
                map.put("job", BaseApp.config.getJob_list().get(i).getId());
            }
        }

        String team = (String) spPersonalInstitutions.getSelectedItem();
        LogUtils.e("机构：" + team);
        for (int i = 0; i < teamList.size(); i++) {
            if (team.equals(teamList.get(i).getTeam_name())) {
                map.put("team_id", teamList.get(i).getId());
            }
        }

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
}
