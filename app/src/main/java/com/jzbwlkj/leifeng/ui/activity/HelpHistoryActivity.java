package com.jzbwlkj.leifeng.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.ui.adapter.HelpHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HelpHistoryActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private HelpHistoryAdapter adapter;
    private List<String> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_help_history;
    }

    @Override
    public void initView() {
        setCenterTitle("历史留言");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        adapter = new HelpHistoryAdapter(R.layout.item_help_history, mList);
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }
}
