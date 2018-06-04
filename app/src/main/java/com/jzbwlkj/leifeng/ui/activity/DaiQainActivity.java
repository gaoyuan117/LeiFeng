package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.CommonBean;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.adapter.AcManagementDetailsAdapter;
import com.jzbwlkj.leifeng.ui.adapter.DaiQianAdapter;
import com.jzbwlkj.leifeng.ui.adapter.ZhiYuanZheAdapter;
import com.jzbwlkj.leifeng.ui.bean.JoinProjectUserBean;
import com.jzbwlkj.leifeng.ui.bean.UserBean;
import com.jzbwlkj.leifeng.utils.FormatUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DaiQainActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.exit_layout)
    LinearLayout exitLayout;
    @BindView(R.id.tv_left_title)
    TextView tvLeftTitle;
    @BindView(R.id.center_title_tv)
    TextView centerTitleTv;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.iv_right2)
    ImageView ivRight2;
    @BindView(R.id.img_right)
    ImageView imgRight;
    @BindView(R.id.title_linLayout)
    LinearLayout titleLinLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    private List<JoinProjectUserBean> mList = new ArrayList<>();
    private DaiQianAdapter adapter;
    private int id;

    @Override
    public int getLayoutId() {
        id = getIntent().getIntExtra("id", 0);
        return R.layout.activity_manager_user;
    }

    @Override
    public void initView() {
        centerTitleTv.setText("队员代签");
        initAadapter();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh.setRefreshing(false);
                    }
                }, 1000);
                getNetData();
            }
        });
    }

    @Override
    public void initData() {
        getNetData();
    }

    @Override
    public void configViews() {

    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    /**
     * 获取网络数据
     */
    private void getNetData() {
        RetrofitClient.getInstance().createApi().userList(null, String.valueOf(id))
                .compose(RxUtils.<HttpResult<List<JoinProjectUserBean>>>io_main())
                .subscribe(new BaseObjObserver<List<JoinProjectUserBean>>(this, "已加入") {
                    @Override
                    protected void onHandleSuccess(List<JoinProjectUserBean> joinProjectBeans) {
                        if (joinProjectBeans.size() > 0) {
                            mList.addAll(joinProjectBeans);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    /**
     * 初始化adapter
     */
    private void initAadapter() {
        adapter = new DaiQianAdapter(R.layout.item_qiandao, mList, this);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getNetData();
            }
        }, recyclerView);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                JoinProjectUserBean bean = mList.get(position);

                String tt = FormatUtils.formatTime(System.currentTimeMillis());
                switch (view.getId()) {
                    case R.id.tv_qiandao:
                        if(bean.getStatus() == 1){
                            showToastMsg("当前队员以完成签到");
                            return;
                        }
                        postData(String.valueOf(id), tt, null, String.valueOf(bean.getId()));
                        break;
                    case R.id.tv_qiantui:
                        if(bean.getStatus() == 2){
                            showToastMsg("当前队员以完成签退");
                            return;
                        }
                        postData(String.valueOf(id), null, tt, String.valueOf(bean.getId()));
                        break;
                }

            }
        });
        adapter.disableLoadMoreIfNotFullPage();
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);
    }

    /**
     * 提交审核结果1 已审核 -1 已拒绝
     */
    private void postData(String id, String start, String end, String uid) {
        RetrofitClient.getInstance().createApi().daiqian(BaseApp.token, id, uid, start, end)
                .compose(RxUtils.<HttpResult<CommonBean>>io_main())
                .subscribe(new BaseObjObserver<CommonBean>(getActivity(), "代签") {
                    @Override
                    protected void onHandleSuccess(CommonBean commonBean) {
                        showToastMsg("当前队员代签已完成");
                        mList.clear();
                        getNetData();
                    }
                });
    }

}
