package com.jzbwlkj.leifeng.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.adapter.ChatListAdapter;
import com.jzbwlkj.leifeng.ui.adapter.TrainingAdapter;
import com.jzbwlkj.leifeng.ui.bean.ChatListBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 志愿培训
 */
public class TrainingActivity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

//    private List<String> mList = new ArrayList<>();
    private TrainingAdapter adapter;

//    private ChatListAdapter adapter;
    private List<ChatListBean.AllListBean> mList = new ArrayList<>();

    private int pageCount = 0;
    @Override
    public int getLayoutId() {
        return R.layout.activity_help_history;
    }

    @Override
    public void initView() {
        setCenterTitle("志愿培训");
//        adapter = new TrainingAdapter(R.layout.item_home, mList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
//        recyclerView.setAdapter(adapter);
        initAdapter();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageCount = 1;
                mList.clear();
                getNetData(false,true,false);
            }
        });
    }

    @Override
    public void initData() {
        getNetData(true,false,false);
    }

    @Override
    public void configViews() {

    }


    /**
     * 处理网络请求
     */
    private void getNetData(final boolean isfirst, final boolean isrefresh, final boolean isloadMore) {
        if(isfirst||isrefresh){
            pageCount = 1;
            mList.clear();
        }

        if(isloadMore){
            pageCount++;
        }
        RetrofitClient.getInstance().createApi().chatlist(BaseApp.token,pageCount,1)
                .compose(RxUtils.<HttpResult<ChatListBean>>io_main())
                .subscribe(new BaseObjObserver<ChatListBean>(this, refresh) {
                    @Override
                    protected void onHandleSuccess(ChatListBean chatListBean) {
                        if (chatListBean == null) {
                            return;
                        }
                        List<ChatListBean.AllListBean> listBeans = chatListBean.getAll_list();
                        if (listBeans.size() > 0) {
                            mList.addAll(listBeans);
                            adapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if(isfirst||isrefresh){
                            //展示空数据view
                        }
                        if(isloadMore){
                            adapter.setEnableLoadMore(false);
                        }
                    }
                });
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        adapter = new TrainingAdapter(R.layout.item_home, mList,this);
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
                ChatListBean.AllListBean allListBean= mList.get(position);
                Intent intent = new Intent(TrainingActivity.this,TrainingDetailsActivity.class);
                intent.putExtra("id",allListBean.getId());
                intent.putExtra("title",allListBean.getTitle());
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);
    }
}
