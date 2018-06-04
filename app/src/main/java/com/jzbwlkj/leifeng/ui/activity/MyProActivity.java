package com.jzbwlkj.leifeng.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.base.ViewPagerAdapter;
import com.jzbwlkj.leifeng.ui.fragment.MyAcFragment;
import com.jzbwlkj.leifeng.ui.fragment.MyProFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyProActivity extends BaseActivity implements TabLayout.OnTabSelectedListener{

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private ViewPagerAdapter mAdapter;
    private List<String> mTitles = new ArrayList<>();
    private List<Fragment> mList = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_my_team;
    }

    @Override
    public void initView() {
        setCenterTitle("已报名活动");
    }

    @Override
    public void initData() {

    }


    @Override
    public void configViews() {
        setTabViewPager();
    }

    /**
     * 设置Tablayout
     */
    private void setTabViewPager() {
        for (int i = 0; i < 3; i++) {
            MyProFragment f = new MyProFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", i + "");
            f.setArguments(bundle);
            mList.add(f);
        }

        mTitles.add("已通过");
        mTitles.add("审核中");
        mTitles.add("未通过");

        mAdapter = new ViewPagerAdapter(this.getSupportFragmentManager(), this, mList, mTitles);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabTextColors(ContextCompat.getColor(getActivity(), R.color.text_black), ContextCompat.getColor(getActivity(), R.color.global));
        mTabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(getActivity(), R.color.global));
        mTabLayout.setOnTabSelectedListener(this);

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            if (mTabLayout.getTabAt(i) == tab) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
