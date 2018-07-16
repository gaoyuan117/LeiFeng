package com.jzbwlkj.leifeng.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseFragment;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.activity.LearningGardenActivity;
import com.jzbwlkj.leifeng.ui.activity.NewsDetalActivity;
import com.jzbwlkj.leifeng.ui.adapter.LearningGardenAdapter;
import com.jzbwlkj.leifeng.ui.bean.StudyBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZhengCeFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    // private List<String> mList = new ArrayList<>();
    private LearningGardenAdapter adapter;

    private List<StudyBean> mList = new ArrayList<>();
    private int pageCount = 0;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_xin_wen;
    }

    @Override
    public void initView() {
        initAdapter();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageCount = 1;
                mList.clear();
                getNetData(false, true, false);
            }
        });
    }

    @Override
    public void initDatas() {
        getNetData(true,false,false);
    }

    @Override
    public void configViews() {

    }

    /**
     * 处理网络请求
     */
    private void getNetData(final boolean isfirst, final boolean isrefresh, final boolean isloadMore) {
        if (isfirst || isrefresh) {
            pageCount = 1;
            mList.clear();
        }

        if (isloadMore) {
            pageCount++;
        }
        RetrofitClient.getInstance().createApi().studyGarden("3")
                .compose(RxUtils.<HttpResult<List<StudyBean>>>io_main())
                .subscribe(new BaseObjObserver<List<StudyBean>>(getActivity(), refresh) {
                    @Override
                    protected void onHandleSuccess(List<StudyBean> chatListBeans) {

                        if (chatListBeans.size() > 0) {
                            mList.addAll(chatListBeans);
                            adapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (isfirst || isrefresh) {
                            //展示空数据view
                        }
                        if (isloadMore) {
                            adapter.setEnableLoadMore(false);
                        }
                    }
                });
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        adapter = new LearningGardenAdapter(R.layout.item_news, mList, getActivity());
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getNetData(false, false, true);
            }
        }, recyclerView);
        adapter.disableLoadMoreIfNotFullPage();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                StudyBean allListBean = mList.get(position);
                Intent intent = new Intent(getActivity(), NewsDetalActivity.class);
                intent.putExtra("id", allListBean.getId());
                intent.putExtra("title", allListBean.getTitle());
                intent.putExtra("content", allListBean.getContent());
                intent.putExtra("time", allListBean.getAdd_time());
                intent.putExtra("flag",1);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);
    }

}
