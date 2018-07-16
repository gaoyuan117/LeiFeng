package com.jzbwlkj.leifeng.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseFragment;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.CommonBean;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.activity.LoginActivity;
import com.jzbwlkj.leifeng.ui.activity.TeamActivity;
import com.jzbwlkj.leifeng.ui.activity.VoluntaryCardActivity;
import com.jzbwlkj.leifeng.ui.adapter.RankAdapter;
import com.jzbwlkj.leifeng.ui.bean.RankBean;
import com.jzbwlkj.leifeng.utils.ToastUtils;
import com.jzbwlkj.leifeng.view.OnDyClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/10.
 */

public class RankFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    SwipeMenuRecyclerView recyclerView;
    @BindView(R.id.sr_layout)
    SwipeRefreshLayout srLayout;
    private LinearLayoutManager layoutManager;
    private RankAdapter adapter;
    private int page = 1;
    private List<RankBean.RankUserBean> list = new ArrayList<>();
    @Override
    public int getLayoutResId() {

        return R.layout.fragment_rank;
    }

    @Override
    public void initView() {
        initAdapter();
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
        RetrofitClient.getInstance().createApi().rankListP(null,String.valueOf(page))
                .compose(RxUtils.<HttpResult<RankBean>>io_main())
                .subscribe(new BaseObjObserver<RankBean>(getActivity(), "排行榜单") {
                    @Override
                    protected void onHandleSuccess(RankBean rankBean) {
                        if(rankBean == null){
                            return;
                        }
                        if(rankBean.getRank_user().size()>0){
                            list.addAll(rankBean.getRank_user());
                            recyclerView.loadMoreFinish(false,true);
                        }else{
                            if(page == 1){
                                recyclerView.loadMoreFinish(true,false);
                            }else{
                                recyclerView.loadMoreFinish(false,false);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void initAdapter(){
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.loadMoreFinish(false, true);
        srLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
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
        recyclerView.useDefaultLoadMore();
        recyclerView.setLoadMoreListener(new SwipeMenuRecyclerView.LoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                getNetData();
            }
        });
        adapter = new RankAdapter( list,getActivity());
        adapter.setOnDyCLickListener(new OnDyClickListener() {
            @Override
            public void onClick(View v, int operate) {
                if(noLogin()){
                    toActivity(LoginActivity.class);
                    return;
                }
                RankBean.RankUserBean userBean = list.get(operate);
                Intent intent = new Intent(getActivity(), VoluntaryCardActivity.class);
                intent.putExtra("id",userBean.getId()+"");
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

}
