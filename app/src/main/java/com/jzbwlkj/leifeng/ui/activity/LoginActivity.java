package com.jzbwlkj.leifeng.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.bean.LoginBean;
import com.jzbwlkj.leifeng.ui.bean.TeamLoginBean;
import com.jzbwlkj.leifeng.utils.SharedPreferencesUtil;

import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_person)
    TextView tvPerson;
    @BindView(R.id.view_person)
    View viewPerson;
    @BindView(R.id.ll_person)
    LinearLayout llPerson;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.view_unit)
    View viewUnit;
    @BindView(R.id.ll_unit)
    LinearLayout llUnit;
    @BindView(R.id.iv_unit)
    ImageView ivUnit;
    @BindView(R.id.et_login_unit)
    EditText etLoginUnit;
    @BindView(R.id.img_pwd_unit)
    ImageView imgPwdUnit;
    @BindView(R.id.et_login_pwd_unit)
    EditText etLoginPwdUnit;
    @BindView(R.id.tv_login_forget_pwd_unit)
    TextView tvLoginForgetPwdUnit;
    @BindView(R.id.bt_login_unit)
    TextView btLoginUnit;
    @BindView(R.id.ll_date_unit)
    LinearLayout llDateUnit;
    @BindView(R.id.img_user)
    ImageView imgUser;
    @BindView(R.id.et_login_user)
    EditText etLoginUser;
    @BindView(R.id.img_pwd)
    ImageView imgPwd;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.tv_login_forget_pwd)
    TextView tvLoginForgetPwd;
    @BindView(R.id.bt_login)
    TextView btLogin;
    @BindView(R.id.ll_date_person)
    LinearLayout llDatePerson;
    private Animation indoor, outdoor;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        initAnim();
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.tv_login_forget_pwd, R.id.bt_login, R.id.tv_login_forget_pwd_unit, R.id.bt_login_unit,
            R.id.ll_person, R.id.ll_unit, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login_forget_pwd://个人忘记密码
                Intent intent = new Intent(this, ForgetPwdActivity.class);
                intent.putExtra("flag", "0");
                startActivity(intent);
                break;

            case R.id.tv_login_forget_pwd_unit://组织忘记密码
                Intent intent2 = new Intent(this, ForgetPwdActivity.class);
                intent2.putExtra("flag", "1");
                startActivity(intent2);
                break;
            case R.id.bt_login:
                login();
                break;

            case R.id.bt_login_unit:
                loginUnit();
                break;

            case R.id.ll_unit:
                llDateUnit.startAnimation(outdoor);
                llDatePerson.startAnimation(indoor);
                viewPerson.setVisibility(View.GONE);
                viewUnit.setVisibility(View.VISIBLE);
                tvPerson.setTextColor(getResources().getColor(R.color.text_black));
                tvUnit.setTextColor(getResources().getColor(R.color.global));
                outdoor.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        llDatePerson.setVisibility(View.VISIBLE);
                        llDateUnit.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        llDatePerson.setVisibility(View.GONE);
                        llDateUnit.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                break;

            case R.id.ll_person:
                llDateUnit.startAnimation(indoor);
                llDatePerson.startAnimation(outdoor);
                viewUnit.setVisibility(View.GONE);
                viewPerson.setVisibility(View.VISIBLE);
                tvPerson.setTextColor(getResources().getColor(R.color.global));
                tvUnit.setTextColor(getResources().getColor(R.color.text_black));
                outdoor.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        llDatePerson.setVisibility(View.VISIBLE);
                        llDateUnit.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        llDateUnit.setVisibility(View.GONE);
                        llDatePerson.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    /**
     * 个人登录
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
                        SharedPreferencesUtil.getInstance().putString("personId", phone);
                        SharedPreferencesUtil.getInstance().putString("token", bean.getToken());
                        SharedPreferencesUtil.getInstance().putString("phone", bean.getMobile());
                        SharedPreferencesUtil.getInstance().putInt("type", 1);
                        BaseApp.type = 1;
                        BaseApp.token = bean.getToken();
                        toActivity(MainActivity.class);
                        showToastMsg("登录成功");
                        finish();
                    }
                });
    }

    /**
     * 组织登录
     */
    private void loginUnit() {
        final String acc = etLoginUnit.getText().toString();
        String pwd = etLoginPwdUnit.getText().toString();

        if (TextUtils.isEmpty(acc)) {
            showToastMsg("请输入组织账号");
            return;
        }

        if (TextUtils.isEmpty(pwd)) {
            showToastMsg(getResources().getString(R.string.please_input_pwd));
            return;
        }

        RetrofitClient.getInstance().createApi().loginUnit(acc, pwd)
                .compose(RxUtils.<HttpResult<TeamLoginBean>>io_main())
                .subscribe(new BaseObjObserver<TeamLoginBean>(activity) {
                    @Override
                    protected void onHandleSuccess(TeamLoginBean bean) {
                        String phone = bean.getContact_mobile();
                        Set<String> strings = new HashSet<>();
                        strings.add(phone);
                        //对于队伍用不到定点推送
                        JPushInterface.setAliasAndTags(activity, phone, strings, new TagAliasCallback() {
                            @Override
                            public void gotResult(int i, String s, Set<String> set) {

                            }
                        });

                        SharedPreferencesUtil.getInstance().putString("token", bean.getTeam_token());
                        SharedPreferencesUtil.getInstance().putInt("type", 2);
                        SharedPreferencesUtil.getInstance().putInt("team_id", bean.getTeam_id());
                        SharedPreferencesUtil.getInstance().putString("phone", bean.getContact_mobile());
                        BaseApp.token = bean.getTeam_token();
                        BaseApp.type = 2;
                        BaseApp.team_id = bean.getTeam_id();
                        toActivity(MainActivity.class);
                        showToastMsg("登录成功");
                        finish();
                    }
                });
    }

    private void initAnim() {
        indoor = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.login_in);
        outdoor = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.login_out);

    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if(keyCode == KeyEvent.KEYCODE_BACK){
//            Intent intent = new Intent(this,MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
