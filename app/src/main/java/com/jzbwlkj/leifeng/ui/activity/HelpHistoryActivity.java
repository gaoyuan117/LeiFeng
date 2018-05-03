package com.jzbwlkj.leifeng.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.adapter.HelpHistoryAdapter;
import com.jzbwlkj.leifeng.ui.bean.HelpListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpHistoryActivity extends BaseActivity {

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
    private HelpHistoryAdapter adapter;
    private List<HelpListBean> mList = new ArrayList<>();

    private int page = 0;

    private View footView;
    private TextView tvAdd;

    @Override
    public int getLayoutId() {
        return R.layout.activity_help_history;
    }

    @Override
    public void initView() {
        setCenterTitle("历史留言");
        initFootView();
        initAdapter();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getNetData(false, true, false);
            }
        });
        adapter.addFooterView(footView);
    }

    @Override
    public void initData() {
        getNetData(true, false, false);
    }

    @Override
    public void configViews() {

    }

    /**
     * 初始化脚布局
     */
    private void initFootView(){
        footView = LayoutInflater.from(this).inflate(R.layout.foot_layout,null);
        tvAdd = footView.findViewById(R.id.tv_public_liuyan);
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpHistoryActivity.this, CommitHelpActivity.class);
                startActivityForResult(intent, 100);
            }
        });
    }

    /**
     * 获取网络数据,不知道是不是需要加上分页，现在先加上
     */
    private void getNetData(boolean isfirst, boolean isrefresh, boolean isLoadMore) {
        if (isfirst || isrefresh) {
            mList.clear();
            page = 1;
        }

        if (isLoadMore) {
            page++;
        }
        RetrofitClient.getInstance().createApi().helpList("")
                .compose(RxUtils.<HttpResult<List<HelpListBean>>>io_main())
                .subscribe(new BaseObjObserver<List<HelpListBean>>(this, refresh) {
                    @Override
                    protected void onHandleSuccess(List<HelpListBean> helpList) {

                        if (helpList.size() > 0) {
                            mList.addAll(helpList);
                        } else {
                            return;
                        }

                        adapter.notifyDataSetChanged();

                    }
                });
    }

    /**
     * 初始化适配器，recycleView
     */
    private void initAdapter() {
        adapter = new HelpHistoryAdapter(R.layout.item_help_history, mList);
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

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == 100) {
            getNetData(false, true, false);
        }
    }

}
