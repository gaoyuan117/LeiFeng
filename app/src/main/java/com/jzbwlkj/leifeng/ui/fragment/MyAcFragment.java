package com.jzbwlkj.leifeng.ui.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseFragment;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.activity.AcDetailActivity;
import com.jzbwlkj.leifeng.ui.activity.ActivitiesRecruitActivity;
import com.jzbwlkj.leifeng.ui.adapter.JoinProjectAdapter;
import com.jzbwlkj.leifeng.ui.adapter.MyTeamAdapter;
import com.jzbwlkj.leifeng.ui.adapter.ProjectAdapter;
import com.jzbwlkj.leifeng.ui.bean.CommitBean;
import com.jzbwlkj.leifeng.ui.bean.JoinProjectBean;
import com.jzbwlkj.leifeng.ui.bean.ProjectBean;
import com.jzbwlkj.leifeng.utils.CommonApi;
import com.jzbwlkj.leifeng.utils.LogUtils;
import com.jzbwlkj.leifeng.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MyAcFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private String type;
    private List<JoinProjectBean.ListBean> mList = new ArrayList<>();
    private JoinProjectAdapter adapter;

    private int page = 1;
    private int all = 1;
    private int ll = 0;//每页的数据长度

    @Override
    public int getLayoutResId() {
        return R.layout.layout_tab;
    }

    @Override
    public void initView() {
        type = getArguments().getString("type");
        LogUtils.e("type：" + type);
        initAdapter();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mList.clear();
                getNetData();
            }
        });
    }

    @Override
    public void initDatas() {
        getNetData();
    }

    @Override
    public void configViews() {
        adapter.setOnItemChildClickListener(this);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        CommonApi.commonDialog(activity, "确认重新申请吗?", "确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    /**
     * 获取网络数据
     */
    private void getNetData() {
        RetrofitClient.getInstance().createApi().userProList(BaseApp.token,1)
                .compose(RxUtils.<HttpResult<List<JoinProjectBean>>>io_main())
                .subscribe(new BaseObjObserver<List<JoinProjectBean>>(getActivity(), refresh) {
                    @Override
                    protected void onHandleSuccess(List<JoinProjectBean> projectBeans) {

                        if (TextUtils.equals("0", type)) {//已通过
                            mList.clear();
                            mList.addAll(recycleList(projectBeans, "已通过"));
                            if (mList.size() <= 0) {
                                ToastUtils.showToast("暂无相关数据");
                            }

                        } else if (TextUtils.equals("1", type)) {//审核中
                            mList.clear();
                            mList.addAll(recycleList(projectBeans, "审核中"));
                            if (mList.size() <= 0) {
                                ToastUtils.showToast("暂无相关数据");
                            }
                        } else if (TextUtils.equals("2", type)) {//未通过
                            mList.clear();
                            mList.addAll(recycleList(projectBeans, "未通过"));
                            if (mList.size() <= 0) {
                                ToastUtils.showToast("暂无相关数据");
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 遍历相关集合
     */
    private List<JoinProjectBean.ListBean> recycleList(List<JoinProjectBean> projectBeans, String type) {

        List<JoinProjectBean.ListBean> listBeans = new ArrayList<>();
        if(projectBeans  != null&&projectBeans.size()>0){
            if (projectBeans.size() > 0) {
                for (JoinProjectBean projectBean : projectBeans) {
                    if (TextUtils.equals(type, projectBean.getStatus_text())) {
                        listBeans.addAll(projectBean.getList());
                    }
                }
            }
        }
        return listBeans;
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        adapter = new JoinProjectAdapter(R.layout.item_my_ac, mList, type, getActivity());
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < all) {
                    page++;
                    getNetData();
                } else {
                    ToastUtils.showToast("没有更多数据了");
                }
            }
        }, recyclerView);
        adapter.disableLoadMoreIfNotFullPage();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                JoinProjectBean.ListBean.ActivityInfoBean dataBean = mList.get(position).getActivity_info();
                Intent intent = new Intent(getActivity(), AcDetailActivity.class);
                intent.putExtra("id", dataBean.getId());
                startActivity(intent);
            }
        });

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                //这里是报名未通过的活动进行重新申请
                joinAc(mList.get(position).getId());
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);
    }

    /**
     * 报名参加活动
     */
    private void joinAc(int id) {
        RetrofitClient.getInstance().createApi().joinProject(BaseApp.token, String.valueOf(id))
                .compose(RxUtils.<HttpResult<CommitBean>>io_main())
                .subscribe(new BaseObjObserver<CommitBean>(getActivity(), "报名参加活动") {
                    @Override
                    protected void onHandleSuccess(CommitBean commitBean) {
                        ToastUtils.showToast("您已报名成功，请等待审核");
                        page = 1;
                        mList.clear();
                        getNetData();
                    }
                });
    }
}
