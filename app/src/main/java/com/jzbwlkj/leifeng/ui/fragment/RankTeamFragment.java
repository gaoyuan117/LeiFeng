package com.jzbwlkj.leifeng.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseFragment;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.activity.TeamActivity;
import com.jzbwlkj.leifeng.ui.adapter.RankAdapter;
import com.jzbwlkj.leifeng.ui.adapter.RankTeamAdapter;
import com.jzbwlkj.leifeng.ui.bean.RankBean;
import com.jzbwlkj.leifeng.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class RankTeamFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.sr_layout)
    SwipeRefreshLayout srLayout;
    private RankTeamAdapter adapter;
    private List<RankBean.RankTeamBean> list = new ArrayList<>();
    @Override
    public int getLayoutResId() {
        return R.layout.fragment_rank;
    }

    @Override
    public void initView() {
        initAdapter();
        srLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                getNetData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srLayout.setRefreshing(false);
                    }
                },1000);
            }
        });
    }

    @Override
    public void initDatas() {
        getNetData();
    }

    @Override
    public void configViews() {

    }

    /**
     * 获取网络数据
     */
    private void getNetData() {
        RetrofitClient.getInstance().createApi().rankList(null)
                .compose(RxUtils.<HttpResult<RankBean>>io_main())
                .subscribe(new BaseObjObserver<RankBean>(getActivity(), "排行榜单") {
                    @Override
                    protected void onHandleSuccess(RankBean rankBean) {
                        if(rankBean == null){
                            return;
                        }
                        if(rankBean.getRank_user().size()>0){
                            list.addAll(rankBean.getRank_team());
                            adapter.notifyDataSetChanged();
                        }else{
                            ToastUtils.showToast("暂无相关数据");
                            adapter.getEmptyView();
                        }
                    }
                });
    }

    private void initAdapter(){
        adapter = new RankTeamAdapter(R.layout.item_rank, list,getActivity());
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getNetData();
            }
        }, recyclerView);
        adapter.disableLoadMoreIfNotFullPage();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RankBean.RankTeamBean teamBean = list.get(position);
                Intent intent = new Intent(getActivity(), TeamActivity.class);
                intent.putExtra("id",teamBean.getId());
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);
    }
}
