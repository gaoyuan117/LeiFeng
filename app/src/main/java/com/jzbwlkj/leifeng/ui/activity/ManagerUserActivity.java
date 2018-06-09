package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.ui.adapter.TabFragmentAdapter;
import com.jzbwlkj.leifeng.ui.fragment.UserAlreadyFragment;
import com.jzbwlkj.leifeng.ui.fragment.UserDaiShenFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManagerUserActivity extends BaseActivity {


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
    @BindView(R.id.tab_manager)
    TabLayout tabManager;
    @BindView(R.id.vpmanager)
    ViewPager vpmanager;
    private List<Fragment> list = new ArrayList<>();
    private UserAlreadyFragment alreadyFragment;
    private UserDaiShenFragment daiShenFragment;
    private TabFragmentAdapter adapter;
    private List<String> lt_str = new ArrayList<>();
    @Override
    public int getLayoutId() {
        return R.layout.activity_manager_user;
    }

    @Override
    public void initView() {
        centerTitleTv.setText("志愿者管理");

    }

    @Override
    public void initData() {
        initTab();
        vpmanager.setCurrentItem(0);
    }

    @Override
    public void configViews() {

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    private void initTab() {
        lt_str.add("待审核");
        lt_str.add("已审核");

        daiShenFragment = new UserDaiShenFragment();
        alreadyFragment = new UserAlreadyFragment();

        list.add(daiShenFragment);
        list.add(alreadyFragment);

//        tabManager.setTabMode(TabLayout.GRAVITY_FILL);
        adapter = new TabFragmentAdapter(getSupportFragmentManager(), list, lt_str);
        vpmanager.setAdapter(adapter);//给ViewPager设置适配器
        vpmanager.setOffscreenPageLimit(2);
//        vpmanager.setPageTransformer(true, new ScaleInTransformer());
        tabManager.setupWithViewPager(vpmanager);//将TabLayout和ViewPager关联起来。
        tabManager.setTabsFromPagerAdapter(adapter);//给Tab
//        vpmanager.setCurrentItem(flag);
    }
}
