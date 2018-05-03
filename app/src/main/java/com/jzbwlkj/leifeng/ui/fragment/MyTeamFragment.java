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
import com.jzbwlkj.leifeng.ui.activity.ProjectRecruitActivity;
import com.jzbwlkj.leifeng.ui.activity.TeamActivity;
import com.jzbwlkj.leifeng.ui.adapter.MyTeamAdapter;
import com.jzbwlkj.leifeng.ui.adapter.ProjectAdapter;
import com.jzbwlkj.leifeng.ui.bean.JoinProjectBean;
import com.jzbwlkj.leifeng.ui.bean.JoinTeamListBean;
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

public class MyTeamFragment extends BaseFragment implements BaseQuickAdapter.OnItemChildClickListener{

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private String type;
    private MyTeamAdapter adapter;
    private List<JoinTeamListBean.ListBean> mList = new ArrayList<>();

    private int page = 1;
    private int all = 1;
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
     * 获取数据
     */
    private void getNetData(){
        RetrofitClient.getInstance().createApi().joinTeamList(BaseApp.token)
                .compose(RxUtils.<HttpResult<List<JoinTeamListBean>>>io_main())
                .subscribe(new BaseObjObserver<List<JoinTeamListBean>>(getActivity(),refresh) {
                    @Override
                    protected void onHandleSuccess(List<JoinTeamListBean> joinTeamListBeans) {
                        if(TextUtils.equals("0",type)){//已通过
                            mList.clear();
                            mList.addAll(recycleList(joinTeamListBeans,"已通过"));
                            if(mList.size()<=0){
                                ToastUtils.showToast("暂无相关数据");
                            }

                        }else if(TextUtils.equals("1",type)){//审核中
                            mList.clear();
                            mList.addAll(recycleList(joinTeamListBeans,"审核中"));
                            if(mList.size()<=0){
                                ToastUtils.showToast("暂无相关数据");
                            }
                        }else if(TextUtils.equals("2",type)){//未通过
                            mList.clear();
                            mList.addAll(recycleList(joinTeamListBeans,"未通过"));
                            if(mList.size()<=0){
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
    private List<JoinTeamListBean.ListBean> recycleList(List<JoinTeamListBean> projectBeans, String type){
        List<JoinTeamListBean.ListBean> listBeans = new ArrayList<>();
        for (JoinTeamListBean projectBean:projectBeans){
            if(TextUtils.equals(type,projectBean.getStatus_text())){
                listBeans.addAll(projectBean.getList());
            };
        }

        return listBeans;
    }

    /**
     * 初始化adapter
     */
    private void initAdapter(){
        adapter = new MyTeamAdapter(R.layout.item_my_team, mList, type,getActivity());
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if(page<all){
                    page++;
                    getNetData();
                }else{
                    ToastUtils.showToast("没有更多数据了");
                }

            }
        }, recyclerView);
        adapter.disableLoadMoreIfNotFullPage();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                JoinTeamListBean.ListBean dataBean= mList.get(position);
                Intent intent = new Intent(getActivity(),TeamActivity.class);
                intent.putExtra("id",dataBean.getTeam_id());
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);
    }
}
