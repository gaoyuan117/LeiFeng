package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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
import com.jzbwlkj.leifeng.utils.CommonApi;
import com.jzbwlkj.leifeng.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyTeamPwdActivity extends BaseActivity {
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
    @BindView(R.id.et_old_pwd)
    EditText etOldPwd;
    @BindView(R.id.et_new_pwd)
    EditText etNewPwd;
    @BindView(R.id.img_eye)
    ImageView imgEye;
    @BindView(R.id.et_yzm)
    EditText etYzm;
    @BindView(R.id.tv_yzm)
    TextView tvYzm;
    @BindView(R.id.tv_certain)
    TextView tvCertain;
    private String yzm;

    private boolean isOpen = false;
    private String oldPwd;
    private String newPwd;
    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_team_pwd;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.tv_yzm, R.id.tv_certain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_yzm:
                yzm =CommonApi.getNum();
                tvYzm.setText(yzm);
                break;
            case R.id.tv_certain:
                postData();
                break;
            case R.id.img_eye://显示密码
                isOpen = !isOpen;
                if (isOpen) {
                    imgEye.setImageResource(R.mipmap.yanjingkai);
                    etNewPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    imgEye.setImageResource(R.mipmap.yanjingbi);
                    etNewPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
        }
    }

    /**
     * 提交修改数据
     */
    private void postData(){
        oldPwd = etOldPwd.getText().toString();
        newPwd = etNewPwd.getText().toString();

        if (TextUtils.isEmpty(oldPwd)) {
            ToastUtils.showToast(getResources().getString(R.string.please_input_old_pwd));
            return;
        }
        if (TextUtils.isEmpty(newPwd)) {
            ToastUtils.showToast(getResources().getString(R.string.please_input_new_pwd));
            return;
        }
        if (TextUtils.isEmpty(etYzm.getText().toString())) {
            ToastUtils.showToast(getResources().getString(R.string.please_input_yzm));
            return;
        }
        if (!etYzm.getText().toString().equals(yzm.replaceAll(" ", ""))) {
            ToastUtils.showToast("验证码错误");
            return;
        }

        if (newPwd.length() < 6) {
            ToastUtils.showToast("新密码长度不得少于6位");
            return;
        }
        RetrofitClient.getInstance().createApi().changeTeamPwd(BaseApp.token,oldPwd,newPwd)
                .compose(RxUtils.<HttpResult<CommonBean>>io_main())
                .subscribe(new BaseObjObserver<CommonBean>(this,"修改队伍密码") {
                    @Override
                    protected void onHandleSuccess(CommonBean commonBean) {
                        showToastMsg("密码修改成功");
                        finish();
                    }
                });
    }
}
