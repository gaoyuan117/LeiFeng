package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.adapter.UserDatialAdapter;
import com.jzbwlkj.leifeng.ui.bean.UserSignBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetialActivity extends BaseActivity {

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
    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    private String id;
    private List<UserSignBean.DataBean> list = new ArrayList<>();
    private UserDatialAdapter adapter;
    private int page = 1;
    private int all = 1;
    private String path;
    private String name;

    @Override
    public int getLayoutId() {
        path = getIntent().getStringExtra("path");
        name = getIntent().getStringExtra("name");
        return R.layout.activity_user_detial;
    }

    @Override
    public void initView() {
        centerTitleTv.setText("服务详情");
        id = getIntent().getStringExtra("id");
        tvName.setText(name);
        if (TextUtils.isEmpty(path) || TextUtils.equals("null", path)) {
            path = "xxx";
        }
        Glide.with(this).load(path).error(R.mipmap.avatar_default).into(ivHead);
        intiAdapter();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                list.clear();
                getNetData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh.setRefreshing(false);
                    }
                }, 1000);
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

    /**
     * 获取网络数据
     */
    private void getNetData() {
        RetrofitClient.getInstance().createApi().getServiceDetical(id, page)
                .compose(RxUtils.<HttpResult<UserSignBean>>io_main())
                .subscribe(new BaseObjObserver<UserSignBean>(this, "签到详情") {
                    @Override
                    protected void onHandleSuccess(UserSignBean signBean) {
                        all = signBean.getTotal();
                        List<UserSignBean.DataBean> lt = signBean.getData();
                        if(page == 1){
                            if (lt != null && lt.size() > 0) {
                                refresh.setVisibility(View.VISIBLE);
                                tvNodata.setVisibility(View.GONE);
                                list.addAll(lt);
                            }else{
                                refresh.setVisibility(View.GONE);
                                tvNodata.setVisibility(View.VISIBLE);
                            }

                        }else{
                            if (lt != null && lt.size() > 0) {
                                refresh.setVisibility(View.VISIBLE);
                                tvNodata.setVisibility(View.GONE);
                                list.addAll(lt);
                            }
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    private void intiAdapter() {
        adapter = new UserDatialAdapter(R.layout.item_user_service_detical, list, this);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < all) {
                    page++;
                    getNetData();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            adapter.loadMoreEnd();
                        }
                    }, 3000);
                }
            }
        }, recyclerView);
//        adapter.disableLoadMoreIfNotFullPage();
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
