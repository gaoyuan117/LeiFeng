package com.jzbwlkj.leifeng.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.ui.adapter.RankAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 排行榜
 */
public class RankActivity extends BaseActivity {

    @BindView(R.id.tv_rank_personal)
    TextView tvRankPersonal;
    @BindView(R.id.tv_rank_team)
    TextView tvRankTeam;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<String> personalList = new ArrayList<>();
    private List<String> teamlList = new ArrayList<>();
    private List<String> mList = new ArrayList<>();
    private RankAdapter adapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_rank;
    }

    @Override
    public void initView() {
        setCenterTitle("排行榜");
        personalList.add("");
        personalList.add("");
        personalList.add("");
        personalList.add("");
        personalList.add("");
        personalList.add("");
        personalList.add("");


        teamlList.add("1");
        teamlList.add("1");
        teamlList.add("1");
        teamlList.add("1");
        teamlList.add("1");


        adapter = new RankAdapter(R.layout.item_rank, mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.tv_rank_personal, R.id.tv_rank_team})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_rank_personal:
                tvRankPersonal.setBackgroundResource(R.mipmap.rank_left_red_bg);
                tvRankTeam.setBackgroundResource(R.mipmap.rank_right_white_bg);
                mList.clear();
                mList.addAll(personalList);
                adapter.notifyDataSetChanged();
                break;
            case R.id.tv_rank_team:
                tvRankPersonal.setBackgroundResource(R.mipmap.rank_left_white_bg);
                tvRankTeam.setBackgroundResource(R.mipmap.rank_right_red_bg);
                mList.clear();
                mList.addAll(teamlList);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
