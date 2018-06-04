package com.jzbwlkj.leifeng.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.ui.adapter.AreaAdapter;
import com.jzbwlkj.leifeng.ui.adapter.ChatListAdapter;
import com.jzbwlkj.leifeng.ui.bean.ChatListBean;
import com.jzbwlkj.leifeng.ui.bean.ConfigBean;
import com.jzbwlkj.leifeng.ui.bean.MySelfModel;
import com.jzbwlkj.leifeng.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 地区选择
 */
public class SelectAreaActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private AreaAdapter adapter;
    private List<MySelfModel> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_help_history;
    }

    @Override
    public void initView() {
        setCenterTitle("地区选择");
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh.setRefreshing(false);
                    }
                },1000);
            }
        });
        initAdapter();
    }

    @Override
    public void initData() {
        for (ConfigBean.CityListBean cityListBean: BaseApp.config.getCity_list()){
            MySelfModel model = new MySelfModel();
            model.setName(cityListBean.getName());
            model.setPid(model.getPid());
            model.setId(model.getId());
            model.setSelected(false);
            mList.add(model);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void configViews() {

    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        adapter = new AreaAdapter(R.layout.item_select_area, mList,this);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

            }
        }, recyclerView);
        adapter.disableLoadMoreIfNotFullPage();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                MySelfModel allListBean= mList.get(position);
                Intent intent = new Intent();
                intent.putExtra("name",allListBean.getName());
                intent.putExtra("id",allListBean.getId());
                SharedPreferencesUtil.getInstance().putString("city_id",allListBean.getId());
                SharedPreferencesUtil.getInstance().putString("city_name",allListBean.getName());
                setResult(100,intent);
                finish();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);
    }
}
