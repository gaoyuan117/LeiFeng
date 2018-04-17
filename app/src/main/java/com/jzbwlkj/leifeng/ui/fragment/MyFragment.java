package com.jzbwlkj.leifeng.ui.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseFragment;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.activity.AboutActivity;
import com.jzbwlkj.leifeng.ui.activity.AcManagementActivity;
import com.jzbwlkj.leifeng.ui.activity.ChangePwdActivity;
import com.jzbwlkj.leifeng.ui.activity.LaunchEventActivity;
import com.jzbwlkj.leifeng.ui.activity.LeaveWordActivity;
import com.jzbwlkj.leifeng.ui.activity.MyAcActivity;
import com.jzbwlkj.leifeng.ui.activity.MyInfoActivity;
import com.jzbwlkj.leifeng.ui.activity.MyTeamActivity;
import com.jzbwlkj.leifeng.ui.activity.ProjectManagementActivity;
import com.jzbwlkj.leifeng.ui.activity.PublishProjectActivity;
import com.jzbwlkj.leifeng.ui.activity.SettingActivity;
import com.jzbwlkj.leifeng.ui.bean.UserInfoBean;
import com.jzbwlkj.leifeng.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/31.
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.img_my_avatar)
    ImageView imgMyAvatar;
    @BindView(R.id.tv_my_namer)
    TextView tvMyNamer;
    @BindView(R.id.tv_my_phone)
    TextView tvMyPhone;
    @BindView(R.id.tv_my_time)
    TextView tvMyTime;
    private UserInfoBean userBean;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.tv_my_info, R.id.tv_my_card, R.id.tv_my_team, R.id.tv_my_ac_management, R.id.tv_my_project_management, R.id.tv_my_project_leave_word, R.id.tv_my_activity, R.id.tv_my_pwd, R.id.tv_my_about, R.id.tv_my_setting, R.id.tv_my_launch_event, R.id.tv_my_publish_project})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_my_info://基本信息
                toActivity(MyInfoActivity.class);
                break;
            case R.id.tv_my_card://名片

                break;
            case R.id.tv_my_launch_event://发布活动
                toActivity(LaunchEventActivity.class);
                break;
            case R.id.tv_my_publish_project://发布项目
                toActivity(PublishProjectActivity.class);
                break;
            case R.id.tv_my_ac_management://活动管理
                toActivity(AcManagementActivity.class);
                break;
            case R.id.tv_my_project_management://项目管理
                toActivity(ProjectManagementActivity.class);
                break;
            case R.id.tv_my_project_leave_word://留言管理
                toActivity(LeaveWordActivity.class);
                break;
            case R.id.tv_my_team://我的队伍
                toActivity(MyTeamActivity.class);
                break;
            case R.id.tv_my_activity://我的活动
                toActivity(MyAcActivity.class);
                break;
            case R.id.tv_my_pwd://修改密码
                toActivity(ChangePwdActivity.class);
                break;
            case R.id.tv_my_about://关于我们
                toActivity(AboutActivity.class);
                break;
            case R.id.tv_my_setting://设置
                toActivity(SettingActivity.class);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getUserInfo();
    }

    //获取用户信息
    private void getUserInfo() {
        if (TextUtils.isEmpty(BaseApp.token)) return;
        RetrofitClient.getInstance().createApi().getUserInfo(BaseApp.token)
                .compose(RxUtils.<HttpResult<UserInfoBean>>io_main())
                .subscribe(new BaseObjObserver<UserInfoBean>(activity) {
                    @Override
                    protected void onHandleSuccess(UserInfoBean bean) {
                        userBean = bean;
                        setUserData();
                    }
                });
    }

    private void setUserData() {
        Glide.with(activity).load(userBean.getAvatar()).error(R.mipmap.avatar_default).into(imgMyAvatar);
        tvMyNamer.setText(userBean.getUser_nickname());
        tvMyPhone.setText(userBean.getMobile());
        tvMyTime.setText(userBean.getService_hour() + "小时");

    }
}
