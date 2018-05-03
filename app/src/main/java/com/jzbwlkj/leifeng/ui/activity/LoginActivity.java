package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.bean.LoginBean;
import com.jzbwlkj.leifeng.utils.SharedPreferencesUtil;

import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import io.reactivex.functions.Consumer;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_login_user)
    EditText etLoginUser;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.tv_login_forget_pwd)
    TextView tvLoginForgetPwd;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        setCenterTitle("登录");
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.tv_login_forget_pwd, R.id.bt_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login_forget_pwd:
                toActivity(ForgetPwdActivity.class);
                break;
            case R.id.bt_login:
                login();
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
        final String phone = etLoginUser.getText().toString();
        String pwd = etLoginPwd.getText().toString();

        if (TextUtils.isEmpty(phone)) {
            showToastMsg(getResources().getString(R.string.please_input_phone));
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            showToastMsg(getResources().getString(R.string.please_input_pwd));
            return;
        }

        RetrofitClient.getInstance().createApi().login(phone, pwd)
                .compose(RxUtils.<HttpResult<LoginBean>>io_main())
                .subscribe(new BaseObjObserver<LoginBean>(activity) {
                    @Override
                    protected void onHandleSuccess(LoginBean bean) {
                        Set<String> strings = new HashSet<>();
                        strings.add(phone);
                        JPushInterface.setAliasAndTags(activity, phone, strings, new TagAliasCallback() {
                            @Override
                            public void gotResult(int i, String s, Set<String> set) {

                            }
                        });

                        SharedPreferencesUtil.getInstance().putString("token", bean.getToken());
                        SharedPreferencesUtil.getInstance().putString("phone", bean.getMobile());
                        BaseApp.token = bean.getToken();
                        toActivity(MainActivity.class);
                        showToastMsg("登录成功");
                        finish();
                    }
                });
    }


}
