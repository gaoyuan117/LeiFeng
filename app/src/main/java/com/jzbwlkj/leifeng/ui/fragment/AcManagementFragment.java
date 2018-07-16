package com.jzbwlkj.leifeng.ui.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.jzbwlkj.leifeng.ui.activity.AcManagementDetailsActivity;
import com.jzbwlkj.leifeng.ui.adapter.AcManagementAdapter;
import com.jzbwlkj.leifeng.ui.adapter.ProjectAdapter;
import com.jzbwlkj.leifeng.ui.bean.ProjectBean;
import com.jzbwlkj.leifeng.utils.LogUtils;
import com.jzbwlkj.leifeng.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/12.
 */

public class AcManagementFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private String type;
    private List<ProjectBean.DataBean> mList = new ArrayList<>();
    private ProjectAdapter adapter;

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
    }


    /**
     * 获取网络数据,这是对于队伍来说的，team_id不为null，此时应该为token，待确定
     */
    private void getNetData(){
        RetrofitClient.getInstance().createApi().projevtList("1",type,page,null,null,null, BaseApp.team_id+"",null)
                .compose(RxUtils.<HttpResult<ProjectBean>>io_main())
                .subscribe(new BaseObjObserver<ProjectBean>(getActivity(),refresh) {
                    @Override
                    protected void onHandleSuccess(ProjectBean projectBean) {
                        if(projectBean == null){
                            return;
                        }
                        all = projectBean.getTotal();
                        ll = projectBean.getPer_page();
                        if(projectBean.getData() != null&&projectBean.getData().size()>0){
                            mList.addAll(projectBean.getData());
                        }else{
                  //          ToastUtils.showToast("暂无相关数据");
                        }
                        adapter.notifyDataSetChanged();

                    }
                });
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        adapter = new ProjectAdapter(R.layout.item_my_ac, mList, "0",getActivity());
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
                ProjectBean.DataBean dataBean= mList.get(position);
                Intent intent = new Intent(getActivity(),AcManagementDetailsActivity.class);
                intent.putExtra("id",dataBean.getId());
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);
    }
}
