package com.jzbwlkj.leifeng.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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
import com.jzbwlkj.leifeng.ui.activity.ManagerUserActivity;
import com.jzbwlkj.leifeng.ui.activity.ModifyTeamPwdActivity;
import com.jzbwlkj.leifeng.ui.activity.MyAcActivity;
import com.jzbwlkj.leifeng.ui.activity.MyInfoActivity;
import com.jzbwlkj.leifeng.ui.activity.MyProActivity;
import com.jzbwlkj.leifeng.ui.activity.MyTeamActivity;
import com.jzbwlkj.leifeng.ui.activity.ProjectManagementActivity;
import com.jzbwlkj.leifeng.ui.activity.PublishProjectActivity;
import com.jzbwlkj.leifeng.ui.activity.SettingActivity;
import com.jzbwlkj.leifeng.ui.activity.TeamInfoActivity;
import com.jzbwlkj.leifeng.ui.activity.VoluntaryCardActivity;
import com.jzbwlkj.leifeng.ui.bean.TeamInfoBean;
import com.jzbwlkj.leifeng.ui.bean.UserInfoBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2018/3/31.
 */

public class MyFragment extends BaseFragment {
    @BindView(R.id.img_my_avatar)
    CircleImageView imgMyAvatar;
    @BindView(R.id.tv_my_namer)
    TextView tvMyNamer;
    @BindView(R.id.tv_my_phone)
    TextView tvMyPhone;
    @BindView(R.id.tv_my_time)
    TextView tvMyTime;
    @BindView(R.id.layout)
    LinearLayout layout;
    @BindView(R.id.tv_my_info)
    TextView tvMyInfo;
    @BindView(R.id.tv_my_card)
    TextView tvMyCard;
    @BindView(R.id.tv_my_launch_event)
    TextView tvMyLaunchEvent;
    @BindView(R.id.tv_my_publish_project)
    TextView tvMyPublishProject;
    @BindView(R.id.tv_my_ac_management)
    TextView tvMyAcManagement;
    @BindView(R.id.tv_my_project_management)
    TextView tvMyProjectManagement;
    @BindView(R.id.tv_manager_user)
    TextView tvManagerUser;
    @BindView(R.id.tv_my_project_leave_word)
    TextView tvMyProjectLeaveWord;
    @BindView(R.id.tv_my_team)
    TextView tvMyTeam;
    @BindView(R.id.tv_my_activity)
    TextView tvMyActivity;
    @BindView(R.id.tv_my_project)
    TextView tvMyProject;
    @BindView(R.id.tv_my_pwd)
    TextView tvMyPwd;
    @BindView(R.id.tv_my_about)
    TextView tvMyAbout;
    @BindView(R.id.tv_my_setting)
    TextView tvMySetting;
    Unbinder unbinder;

    private UserInfoBean userBean;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {
        initVisible();
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.tv_my_info, R.id.tv_my_card, R.id.tv_my_team, R.id.tv_my_ac_management, R.id.tv_my_project_management,
            R.id.tv_my_project_leave_word, R.id.tv_my_activity, R.id.tv_my_pwd, R.id.tv_my_about, R.id.tv_my_setting,
            R.id.tv_my_launch_event, R.id.tv_my_publish_project, R.id.tv_my_project, R.id.tv_manager_user})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_my_info://基本信息
                if (BaseApp.type == 2) {
                    Intent intent = new Intent(getActivity(), TeamInfoActivity.class);
                    startActivityForResult(intent, 100);
                } else {
                    Intent intent = new Intent(getActivity(), MyInfoActivity.class);
                    startActivityForResult(intent, 100);
                }
                break;
            case R.id.tv_my_card://名片
                toActivity(VoluntaryCardActivity.class);
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
            case R.id.tv_my_project://我的项目
                toActivity(MyProActivity.class);
                break;
            case R.id.tv_my_pwd://修改密码
                if (BaseApp.type == 1) {
                    toActivity(ChangePwdActivity.class);
                } else {
                    toActivity(ModifyTeamPwdActivity.class);
                }

                break;
            case R.id.tv_my_about://关于我们
                toActivity(AboutActivity.class);
                break;
            case R.id.tv_my_setting://设置
                toActivity(SettingActivity.class);
                break;
            case R.id.tv_manager_user:
                toActivity(ManagerUserActivity.class);
                break;
        }
    }

    /**
     * 判断队伍还是个人账户，区别展现
     */
    private void initVisible() {
        if (BaseApp.type == 1) {
            tvMyLaunchEvent.setVisibility(View.GONE);
            tvMyPublishProject.setVisibility(View.GONE);
            tvMyAcManagement.setVisibility(View.GONE);
            tvMyProjectManagement.setVisibility(View.GONE);
            tvMyProjectLeaveWord.setVisibility(View.GONE);
            tvManagerUser.setVisibility(View.GONE);
        } else {
            tvMyCard.setVisibility(View.GONE);
            tvMyTeam.setVisibility(View.GONE);
            tvMyActivity.setVisibility(View.GONE);
            tvMyProject.setVisibility(View.GONE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (BaseApp.type == 1) {
            getUserInfo();
        } else {
            getTeamInfo();
        }
    }

    //获取用户信息
    private void getUserInfo() {
        if (TextUtils.isEmpty(BaseApp.token)) return;
        RetrofitClient.getInstance().createApi().getUserInfo(BaseApp.token, null)
                .compose(RxUtils.<HttpResult<UserInfoBean>>io_main())
                .subscribe(new BaseObjObserver<UserInfoBean>(activity) {
                    @Override
                    protected void onHandleSuccess(UserInfoBean bean) {
                        userBean = bean;
                        setUserData();
                    }
                });
    }

    /**
     * 获取队伍信息
     */
    private void getTeamInfo() {
        if (TextUtils.isEmpty(BaseApp.token)) return;
        RetrofitClient.getInstance().createApi().getTeamInfo(null, BaseApp.token)
                .compose(RxUtils.<HttpResult<TeamInfoBean>>io_main())
                .subscribe(new BaseObjObserver<TeamInfoBean>(activity) {
                    @Override
                    protected void onHandleSuccess(TeamInfoBean bean) {
                        Glide.with(activity).load(bean.getPic()).error(R.mipmap.avatar_default).into(imgMyAvatar);
                        tvMyNamer.setText(bean.getTeam_name());
                        tvMyPhone.setText(bean.getManager_mobile());
                        tvMyTime.setText(bean.getService_hour() + "小时");
                    }
                });
    }


    private void setUserData() {
        Glide.with(activity).load(userBean.getAvatar()).error(R.mipmap.avatar_default).into(imgMyAvatar);
        tvMyNamer.setText(userBean.getUser_nickname());
        tvMyPhone.setText(userBean.getMobile());
        tvMyTime.setText(userBean.getService_hour() + "小时");

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 100) {
            if (BaseApp.type == 1) {
                getUserInfo();
            } else {
                getTeamInfo();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }

}
