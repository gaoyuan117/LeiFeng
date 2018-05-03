package com.jzbwlkj.leifeng.ui.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
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
import com.jzbwlkj.leifeng.ui.adapter.ListViewAdapter;
import com.jzbwlkj.leifeng.ui.bean.ConfigBean;
import com.jzbwlkj.leifeng.ui.bean.MySelfModel;
import com.jzbwlkj.leifeng.ui.bean.UploadBean;
import com.jzbwlkj.leifeng.utils.LogUtils;
import com.jzbwlkj.leifeng.utils.RoundCornesTransFormation;
import com.jzbwlkj.leifeng.utils.StringCheckUtil;
import com.jzbwlkj.leifeng.utils.ToastUtils;
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

public class RegisterTeamActivity extends BaseActivity {

    @BindView(R.id.et_team_name)
    EditText etTeamName;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.et_team_linkman)
    EditText etTeamLinkman;
    @BindView(R.id.et_team_phone)
    EditText etTeamPhone;
    @BindView(R.id.et_team_head)
    EditText etTeamHead;
    @BindView(R.id.et_team_linkphone)
    EditText etTeamLinkphone;
    @BindView(R.id.et_team_jieshao)
    EditText etTeamJieshao;
    @BindView(R.id.img_team)
    ImageView imgTeam;
    @BindView(R.id.cb_team)
    CheckBox cbTeam;

    private List<String> list = new ArrayList<>();
    private String picUrl;//文件路径

    private Map<String, Object> map = new HashMap<>();

    private View viewType;
    private ListView lvContent;
    private PopupWindow popType;
    private int flag = 0;
    private String unitid;//单位Id
    private ListViewAdapter lvAdapter;
    private List<MySelfModel> showList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_register_team;
    }

    @Override
    public void initView() {
        setCenterTitle("队伍注册");

        initPop();
        showList.clear();
        for (ConfigBean.CityListBean cityListBean : BaseApp.config.getCity_list()) {
            MySelfModel model = new MySelfModel();
            model.setPid(cityListBean.getPid()+"");
            model.setId(cityListBean.getId()+"");
            model.setName(cityListBean.getName());
            model.setSelected(false);
            showList.add(model);
        }
        lvAdapter.notifyDataSetChanged();
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.img_team, R.id.tv_team_register, R.id.tv_unit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_team:
                MultiImageSelector.create().single().origin(new ArrayList<String>()).start(this, AppConfig.REQUEST_IMAGE);
                break;
            case R.id.tv_team_register:
                registerTeam();
                break;
            case R.id.tv_unit:
                popType.setWidth(tvUnit.getMeasuredWidth() + 30);
                if (showList.size() > 6) {
                    popType.setHeight(500);
                } else {
                    popType.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                }
                popType.showAsDropDown(tvUnit, -12, 20);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            List<String> list = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            Glide.with(activity).load(list.get(0)).bitmapTransform(new RoundCornesTransFormation(this,10,10))
                    .into(imgTeam);
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
                            picUrl = uploadBean.getData().getFile().getUrl();
                        }
                    }
                });
    }

    /**
     * 注册队伍
     */
    private void registerTeam() {
        String teamName = etTeamName.getText().toString();
        String linkMan = etTeamLinkman.getText().toString();
        String linkPhone = etTeamLinkphone.getText().toString();
        String manager = etTeamHead.getText().toString();
        String managerPhone = etTeamPhone.getText().toString();
        String desc = etTeamJieshao.getText().toString();
        if (TextUtils.isEmpty(teamName)) {
            ToastUtils.showToast("请输入队伍名");
            return;
        }
        if (TextUtils.isEmpty(linkMan)) {
            ToastUtils.showToast("请输入联系人");
            return;
        }
        if (TextUtils.isEmpty(linkPhone)) {
            ToastUtils.showToast("请输入联系人手机号");
            return;
        }
        if (!StringCheckUtil.isMobileNO(linkPhone)) {
            ToastUtils.showToast("请正确输入联系人手机号");
            return;
        }
        if (TextUtils.isEmpty(manager)) {
            ToastUtils.showToast("请输入负责人姓名");
            return;
        }
        if (TextUtils.isEmpty(managerPhone)) {
            ToastUtils.showToast("请输入负责人手机号");
            return;
        }
        if (!StringCheckUtil.isMobileNO(managerPhone)) {
            ToastUtils.showToast("请正确输入负责人手机号");
            return;
        }
        if (TextUtils.isEmpty(desc)) {
            ToastUtils.showToast("请输入队伍介绍");
            return;
        }
        if (TextUtils.isEmpty(unitid)) {
            ToastUtils.showToast("请选择主管单位");
            return;
        }

        if(!cbTeam.isChecked()){
            ToastUtils.showToast("请您先同意志愿者服务守则，再进行下一步");
            return;
        }

        map.put("token", BaseApp.token);
        map.put("team_name", teamName);
        map.put("pic", picUrl);
        map.put("parent_id", unitid);
        map.put("contact", linkMan);
        map.put("contact_mobile", linkPhone);
        map.put("manager", manager);
        map.put("manager_mobile", managerPhone);
        map.put("desc", desc);

        RetrofitClient.getInstance().createApi().teamRegister(map)
                .compose(RxUtils.<HttpResult<String>>io_main())
                .subscribe(new BaseObjObserver<String>(this, "队伍注册") {
                    @Override
                    protected void onHandleSuccess(String commonBean) {
                        showToastMsg("队伍注册提交成功，请等待审核");
                        finish();
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
                tvUnit.setText(model.getName());
                unitid = model.getId();
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
