package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.base.ViewPagerAdapter;
import com.jzbwlkj.leifeng.ui.adapter.RankAdapter;
import com.jzbwlkj.leifeng.ui.fragment.RankFragment;
import com.jzbwlkj.leifeng.ui.fragment.RankTeamFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 排行榜
 */
public class RankActivity extends BaseActivity {


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
    @BindView(R.id.tv_rank_personal)
    TextView tvRankPersonal;
    @BindView(R.id.tv_rank_team)
    TextView tvRankTeam;
    @BindView(R.id.vp_rank)
    ViewPager vpRank;
    private ViewPagerAdapter mAdapter;
    private List<Fragment> mList = new ArrayList<>();
    private RankFragment rankFragment;
    private RankTeamFragment rankTeamFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_rank;
    }

    @Override
    public void initView() {
        setCenterTitle("排行榜");

    }

    @Override
    public void initData() {
        rankFragment = new RankFragment();
        rankTeamFragment = new RankTeamFragment();
        mList.add(rankFragment);
        mList.add(rankTeamFragment);
        initVp();
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.tv_rank_personal, R.id.tv_rank_team})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_rank_personal:
                vpRank.setCurrentItem(0);
                break;
            case R.id.tv_rank_team:
                vpRank.setCurrentItem(1);
                break;
        }
    }

    private void initVp(){
        mAdapter = new ViewPagerAdapter(this.getSupportFragmentManager(), this, mList);
        vpRank.setAdapter(mAdapter);
        vpRank.setOffscreenPageLimit(2);
        vpRank.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0){
                    tvRankPersonal.setBackgroundResource(R.mipmap.rank_left_red_bg);
                    tvRankTeam.setBackgroundResource(R.mipmap.rank_right_white_bg);
                }else{
                    tvRankPersonal.setBackgroundResource(R.mipmap.rank_left_white_bg);
                    tvRankTeam.setBackgroundResource(R.mipmap.rank_right_red_bg);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


}
