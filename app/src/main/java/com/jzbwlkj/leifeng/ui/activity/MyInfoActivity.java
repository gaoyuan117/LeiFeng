package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.bean.UserInfoBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyInfoActivity extends BaseActivity {

    @BindView(R.id.img_my_info_avatar)
    CircleImageView imgMyInfoAvatar;
    @BindView(R.id.ll_my_info_avatar)
    LinearLayout llMyInfoAvatar;
    @BindView(R.id.tv_my_info_name)
    TextView tvMyInfoName;
    @BindView(R.id.tv_my_info_sex)
    TextView tvMyInfoSex;
    @BindView(R.id.tv_my_info_minzu)
    TextView tvMyInfoMinzu;
    @BindView(R.id.tv_my_info_no)
    TextView tvMyInfoNo;
    @BindView(R.id.tv_my_info_phone)
    TextView tvMyInfoPhone;
    @BindView(R.id.tv_my_info_zzmm)
    TextView tvMyInfoZzmm;
    @BindView(R.id.tv_my_info_zgxl)
    TextView tvMyInfoZgxl;
    @BindView(R.id.tv_my_info_job)
    TextView tvMyInfoJob;
    @BindView(R.id.tv_my_info_address)
    TextView tvMyInfoAddress;
    @BindView(R.id.tv_my_info_email)
    TextView tvMyInfoEmail;
    @BindView(R.id.tv_my_info_qq)
    TextView tvMyInfoQq;
    @BindView(R.id.tv_my_info_wx)
    TextView tvMyInfoWx;
    @BindView(R.id.tv_my_info_area)
    TextView tvMyInfoArea;
    @BindView(R.id.tv_my_info_address_detail)
    TextView tvMyInfoAddressDetail;
    @BindView(R.id.tv_my_info_ssjg)
    TextView tvMyInfoSsjg;
    private UserInfoBean bean;


    @Override
    public int getLayoutId() {
        return R.layout.activity_my_info;
    }

    @Override
    public void initView() {
        setCenterTitle("个人资料");
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.ll_my_info_avatar, R.id.tv_my_info_phone, R.id.tv_my_info_area})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_info_avatar:
                break;
            case R.id.tv_my_info_phone:
                break;
            case R.id.tv_my_info_area:
                break;
        }
    }

    @Override
    protected void onResume() {
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

                        setUserData(bean);
                    }
                });
    }

    private void setUserData(UserInfoBean bean) {
        Glide.with(activity).load(bean.getAvatar()).error(R.mipmap.avatar_default).into(imgMyInfoAvatar);
        tvMyInfoEmail.setText(bean.getEmail());
        tvMyInfoName.setText(bean.getUser_nickname());
        tvMyInfoMinzu.setText(bean.getNatinal());
        tvMyInfoNo.setText(bean.getId_no());
        tvMyInfoPhone.setText(bean.getMobile());
        tvMyInfoZzmm.setText(bean.getPolital_status() + "");//政治面貌
        tvMyInfoZgxl.setText(bean.getEducation() + "");//学历
        tvMyInfoJob.setText(bean.getJob());//职业
        tvMyInfoAddress.setText(bean.getAddress());//通讯地址
        tvMyInfoEmail.setText(bean.getEmail());//邮箱
        tvMyInfoQq.setText(bean.getQq());//qq
        tvMyInfoWx.setText(bean.getWechat());//微信
        tvMyInfoArea.setText("所在区域");
        tvMyInfoAddressDetail.setText("详细地址");
        tvMyInfoSsjg.setText("所属机构");


        if (bean.getSex() == 1) {
            tvMyInfoSex.setText("男");
        } else if (bean.getSex() == 2) {
            tvMyInfoSex.setText("女");
        } else {
            tvMyInfoSex.setText("保密");
        }
    }
}
