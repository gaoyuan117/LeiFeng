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
import com.jzbwlkj.leifeng.ui.fragment.XinWenFragment;
import com.jzbwlkj.leifeng.ui.fragment.ZhengCeFragment;
import com.jzbwlkj.leifeng.ui.fragment.ZhiDuFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LearningGardenActivity extends BaseActivity {
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
    @BindView(R.id.vp_study)
    ViewPager vpStudy;
    @BindView(R.id.tab)
    TabLayout tab;

    private List<Fragment> list = new ArrayList<>();
    private ZhiDuFragment zhiDuFragment;
    private ZhengCeFragment zhengCeFragment;
    private XinWenFragment xinWenFragment;
    private TabFragmentAdapter adapter;
    private List<String> lt_str = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_study;
    }

    @Override
    public void initView() {
        setCenterTitle("学习园地");
        initTab();
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }

    private void initTab() {
        lt_str.add("党建制度");
        lt_str.add("最新政策");
        lt_str.add("党建新闻");

        zhiDuFragment = new ZhiDuFragment();
        zhengCeFragment = new ZhengCeFragment();
        xinWenFragment = new XinWenFragment();
        list.add(zhiDuFragment);
        list.add(zhengCeFragment);
        list.add(xinWenFragment);

        adapter = new TabFragmentAdapter(getSupportFragmentManager(), list, lt_str);
        vpStudy.setAdapter(adapter);//给ViewPager设置适配器
        vpStudy.setOffscreenPageLimit(3);
//        vpmanager.setPageTransformer(true, new ScaleInTransformer());
        tab.setupWithViewPager(vpStudy);//将TabLayout和ViewPager关联起来。
        tab.setTabsFromPagerAdapter(adapter);//给Tab
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

}
