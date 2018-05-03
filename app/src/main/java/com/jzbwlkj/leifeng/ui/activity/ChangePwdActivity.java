package com.jzbwlkj.leifeng.ui.activity;

import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import butterknife.OnClick;

public class ChangePwdActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
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

    private String yzm;
    private boolean isOpen = false;
    private String phone;
    private String oldPwd;
    private String newPwd;


    @Override
    public int getLayoutId() {
        return R.layout.activity_change_pwd;
    }

    @Override
    public void initView() {
        setCenterTitle("修改密码");
        yzm = CommonApi.getNum();
        tvYzm.setText(yzm);
    }

    @Override
    public void initData() {
//        etPhone.setText(BaseApp.phone);

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.img_eye, R.id.tv_yzm, R.id.tv_certain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
            case R.id.tv_yzm://更换验证码
                yzm = CommonApi.getNum();
                tvYzm.setText(yzm);
                break;
            case R.id.tv_certain:
                modifypwd();
                break;
        }
    }

    /**
     * 修改密码
     */
    private void modifypwd() {
        phone = etPhone.getText().toString();
        oldPwd = etOldPwd.getText().toString();
        newPwd = etNewPwd.getText().toString();

//        if (TextUtils.isEmpty(phone)) {
//            ToastUtils.showToast(getResources().getString(R.string.please_input_phone));
//            return;
//        }
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

        RetrofitClient.getInstance().createApi().modifyPwd(BaseApp.token, oldPwd, newPwd)
                .compose(RxUtils.<HttpResult<CommonBean>>io_main())
                .subscribe(new BaseObjObserver<CommonBean>(activity, "修改中") {
                    @Override
                    protected void onHandleSuccess(CommonBean commonBean) {
                //        EventBus.getDefault().post("change_pwd");
                        showToastMsg("密码修改成功");
                        finish();
                    }
                });
    }
}
