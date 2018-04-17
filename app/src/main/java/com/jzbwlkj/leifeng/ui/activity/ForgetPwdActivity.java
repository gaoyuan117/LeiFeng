package com.jzbwlkj.leifeng.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.CommonBean;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.utils.CommonApi;
import com.jzbwlkj.leifeng.utils.CountDownUtils;
import com.jzbwlkj.leifeng.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPwdActivity extends BaseActivity implements CountDownUtils.CountdownListener {

    @BindView(R.id.et_phone)
    EditText etPhone;
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
    private boolean isOpen;
    private CountDownUtils countDown;
    private String phone;
    private String password;
    private String yzm;

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    public void initView() {
        setCenterTitle("忘记密码");
        countDown = new CountDownUtils(tvYzm, "%s秒", 60);
        countDown.setCountdownListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.img_eye, R.id.tv_yzm, R.id.tv_certain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_eye:
                isOpen = !isOpen;
                if (isOpen) {
                    imgEye.setImageResource(R.mipmap.yanjingkai);
                    etNewPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    imgEye.setImageResource(R.mipmap.yanjingbi);
                    etNewPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                break;
            case R.id.tv_yzm:
                phone = etPhone.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    ToastUtils.showToast(getResources().getString(R.string.please_input_phone));
                    return;
                }
                CommonApi.sendsms(this, "forgotten", phone);
                countDown.start();
                break;
            case R.id.tv_certain:
                forgetpwd();
                break;
        }
    }

    @Override
    public void onStartCount() {

    }

    @Override
    public void onFinishCount() {
        if (tvYzm == null) {
            return;
        }
        tvYzm.setEnabled(true);
        tvYzm.setText("重新获取");
    }

    @Override
    public void onUpdateCount(int currentRemainingSeconds) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDown.isRunning()) {
            countDown.stop();
        }
    }

    /**
     * 修改密码
     */
    private void forgetpwd() {
        phone = etPhone.getText().toString();
        password = etNewPwd.getText().toString();
        yzm = etYzm.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showToast(getResources().getString(R.string.please_input_phone));
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showToast(getResources().getString(R.string.please_input_pwd));
            return;
        }
        if (TextUtils.isEmpty(yzm)) {
            ToastUtils.showToast(getResources().getString(R.string.please_input_yzm));
            return;
        }

        if (password.length() < 6) {
            ToastUtils.showToast("密码长度最少6位");
            return;
        }


        RetrofitClient.getInstance().createApi().forgetpwd(phone, password, yzm)
                .compose(RxUtils.<HttpResult<CommonBean>>io_main())
                .subscribe(new BaseObjObserver<CommonBean>(this, "修改中") {
                    @Override
                    protected void onHandleSuccess(CommonBean commonBean) {
                        ToastUtils.showToast("修改成功");
                        finish();
                    }
                });
    }
}
