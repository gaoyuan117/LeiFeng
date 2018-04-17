package com.jzbwlkj.leifeng.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseFragment;
import com.jzbwlkj.leifeng.ui.adapter.AcManagementAdapter;
import com.jzbwlkj.leifeng.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/12.
 */

public class ProjectManagementFragment extends BaseFragment implements  BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private String type;
    private AcManagementAdapter adapter;
    private List<String> mList = new ArrayList<>();

    @Override
    public int getLayoutResId() {
        return R.layout.layout_tab;
    }

    @Override
    public void initView() {
        type = getArguments().getString("type");
        LogUtils.e("type：" + type);

        for (int i = 0; i < 10; i++) {
            mList.add("");
        }

        adapter = new AcManagementAdapter(R.layout.item_my_ac, mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {
        adapter.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        toActivity(ProjectManagementDetailsActivity.class);
    }
}
