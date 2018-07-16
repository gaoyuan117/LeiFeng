package com.jzbwlkj.leifeng.ui.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jzbwlkj.leifeng.AppConfig;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.adapter.ListViewAdapter;
import com.jzbwlkj.leifeng.ui.bean.MySelfModel;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.Call;

public class BecomeZhuanActivity extends BaseActivity {

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
    @BindView(R.id.tv_xueli)
    TextView tvXueli;
    @BindView(R.id.ll_xueli)
    LinearLayout llXueli;
    @BindView(R.id.et_personal_nengli)
    EditText etPersonalNengli;
    @BindView(R.id.ll_nengli)
    LinearLayout llNengli;
    @BindView(R.id.img_personal)
    ImageView imgPersonal;
    @BindView(R.id.ll_zhengshu)
    LinearLayout llZhengshu;
    @BindView(R.id.tv_button)
    TextView tvButton;
    private String picUrl;
    private List<MySelfModel> xueliList = new ArrayList<>();
    private View viewType;
    private ListView lvContent;
    private PopupWindow popType;
    private ListViewAdapter lvAdapter;
    private String xueliId;

    private Map<String,Object> map = new HashMap<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_become_zhuan;
    }

    @Override
    public void initView() {
        centerTitleTv.setText("申请专业志愿者");
        initPop();
    }

    @Override
    public void initData() {
        //学历
        for (int i = 0; i < BaseApp.config.getEducation().size(); i++) {
            String ss = BaseApp.config.getEducation().get(i);
            MySelfModel model = new MySelfModel();
            model.setSelected(false);
            model.setName(ss);
            model.setId(i + "");
            xueliList.add(model);
        }
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.tv_xueli, R.id.img_personal,R.id.tv_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_xueli:
                lvAdapter.notifyDataSetChanged();
                popType.setWidth(tvXueli.getMeasuredWidth() + 30);
                if (xueliList.size() > 6) {
                    popType.setHeight(500);
                } else {
                    popType.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                }
                popType.showAsDropDown(tvXueli, -12, 20);
                break;
            case R.id.img_personal:
                MultiImageSelector.create().single().origin(new ArrayList<String>()).start(this, AppConfig.REQUEST_IMAGE);
                break;
            case R.id.tv_button:
                if(TextUtils.isEmpty(xueliId)){
                    showToastMsg("请选择您的学历水平");
                    return;
                }
                String jineng = etPersonalNengli.getText().toString();
                if(TextUtils.isEmpty(jineng)){
                    showToastMsg("请输入您的专业技能");
                    return;
                }

                if(TextUtils.isEmpty(picUrl)){
                    showToastMsg("专业证书获取失败，请重新上传");
                    return;
                }
                map.put("education",xueliId);
                map.put("token",BaseApp.token);
                map.put("certificate",picUrl);
                map.put("special_skill",jineng);
                postData();
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
        lvAdapter = new ListViewAdapter(xueliList, this);
        lvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MySelfModel model = xueliList.get(position);
                for (MySelfModel model1 : xueliList) {
                    if (TextUtils.equals(model.getName(), model1.getName())) {
                        model1.setSelected(true);
                    } else {
                        model1.setSelected(false);
                    }
                }
                lvAdapter.notifyDataSetChanged();
                popType.dismiss();

                tvXueli.setText(model.getName());
                xueliId = model.getId();

            }
        });
        lvContent.setAdapter(lvAdapter);
        popType = new PopupWindow(this);
        popType.setFocusable(true);
        popType.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景防止出现黑色边框
        popType.setFocusable(true);
        popType.setContentView(viewType);
    }

    /**
     * 提交数据
     */
    private void postData(){
        RetrofitClient.getInstance().createApi().becomeZhuan(map)
                .compose(RxUtils.<HttpResult<String>>io_main())
                .subscribe(new BaseObjObserver<String>(this) {
                    @Override
                    protected void onHandleSuccess(String s) {
                        showToastMsg("专业志愿者申请已提交，请耐心等待");
                        finish();
                    }
                });
    }

}
