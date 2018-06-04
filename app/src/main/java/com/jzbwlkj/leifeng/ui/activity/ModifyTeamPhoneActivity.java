package com.jzbwlkj.leifeng.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.CommonBean;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.bean.CodeBean;
import com.jzbwlkj.leifeng.ui.bean.TeamInfoBean;
import com.jzbwlkj.leifeng.utils.StringCheckUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ModifyTeamPhoneActivity extends BaseActivity {

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
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.tv_button)
    TextView tvButton;
    private int type;
    private TeamInfoBean bean;
    private Map<String,Object> map = new HashMap<>();
    private String phoneCode;
    private int i = 60;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 88:
                    if (i > 0) {
                        i--;
                        tvGetCode.setText(i + "s");
                        handler.sendEmptyMessageDelayed(88, 1000);
                    } else {
                        handler.removeCallbacksAndMessages(null);
                        i = 60;
                        phoneCode = "-1";
                        tvGetCode.setText("重新获取");
                    }
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        type = getIntent().getIntExtra("type",0);
        bean = (TeamInfoBean) getIntent().getBundleExtra("bundle").getSerializable("bean");
        return R.layout.activity_modify_phone;
    }

    @Override
    public void initView() {
        centerTitleTv.setText("更换手机号码");
    }

    @Override
    public void initData() {
        map.put("team_token", BaseApp.token);
        map.put("team_name", bean.getTeam_name());
        map.put("pic", bean.getPic());
        map.put("contact", bean.getContact());
        map.put("manager", bean.getManager());
        map.put("manager_mobile",bean.getManager_mobile());
        map.put("desc",bean.getDesc());
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.tv_get_code, R.id.tv_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_get_code:
                String phone = etPhone.getText().toString();
                if (TextUtils.isEmpty(phone) || TextUtils.equals("null", phone)) {
                    showToastMsg("请先输入您要使用的手机号码");
                    return;
                } else if (!StringCheckUtil.isMobileNO(phone)) {
                    showToastMsg("请输入正确的手机号码");
                    return;
                } else {
                    if (i < 60 && i > 0) {
                        showToastMsg("验证码发送中，请勿重复点击");
                    } else {
                        getCode(phone);
                    }

                }

                break;
            case R.id.tv_button:
                String phone2 = etPhone.getText().toString();
                String code = etCode.getText().toString();
                if (TextUtils.isEmpty(phone2) || TextUtils.equals("null", phone2)) {
                    showToastMsg("请先输入您要使用的手机号码");
                    return;
                } else if (!StringCheckUtil.isMobileNO(phone2)) {
                    showToastMsg("请输入正确的手机号码");
                    return;
                } else if (TextUtils.isEmpty(code)) {
                    showToastMsg("请输入您获取到的验证码");
                    return;
                } else if (TextUtils.equals("-1", phoneCode)) {
                    showToastMsg("您的验证码已失效，请重新获取");
                    return;
                } else if (!TextUtils.equals(code, phoneCode)) {
                    showToastMsg("您输入的验证码不正确，请重新输入");
                    return;
                } else {
                    if(type == 98){
                        postData(phone2,code);
                    }else{
                        postManagerData(phone2);
                    }
                }
                break;
        }
    }

    /**
     * 修改联系人手机号  没有相关参数
     * @param phone
     */
    private void postData(String phone,String code) {
   //     map.put("manager_mobile",phone);
        RetrofitClient.getInstance().createApi().modifyLink(BaseApp.token,phone,code)
                .compose(RxUtils.<HttpResult<CommonBean>>io_main())
                .subscribe(new BaseObjObserver<CommonBean>(this, "修改联系人手机号") {
                    @Override
                    protected void onHandleSuccess(CommonBean commitBean) {
                        showToastMsg("联系人手机号更新成功");
                        setResult(100);
                        finish();
                    }
                });
    }

    /**
     * 修改负责人手机号
     * @param phone
     */
    private void postManagerData(String phone) {
        map.put("manager_mobile",phone);
        RetrofitClient.getInstance().createApi().modifyTeam(map)
                .compose(RxUtils.<HttpResult<CommonBean>>io_main())
                .subscribe(new BaseObjObserver<CommonBean>(this, "更新队伍信息") {
                    @Override
                    protected void onHandleSuccess(CommonBean commitBean) {
                        showToastMsg("组织信息更新成功");
                        setResult(100);
                        finish();
                    }
                });
    }

    private void getCode(String phone) {
        RetrofitClient.getInstance().createApi().sendsms(phone, "modifymobile")
                .compose(RxUtils.<HttpResult<CodeBean>>io_main())
                .subscribe(new BaseObjObserver<CodeBean>(this, "获取验证码") {
                    @Override
                    protected void onHandleSuccess(CodeBean codeBean) {
                        handler.sendEmptyMessage(88);
                        phoneCode = codeBean.getCode();
                        showToastMsg("验证码已发送，请注意查收");
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
