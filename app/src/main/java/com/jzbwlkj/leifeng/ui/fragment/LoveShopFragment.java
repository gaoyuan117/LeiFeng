package com.jzbwlkj.leifeng.ui.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseFragment;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.activity.AcDetailActivity;
import com.jzbwlkj.leifeng.ui.adapter.JoinProjectAdapter;
import com.jzbwlkj.leifeng.ui.adapter.LoveShopAdapter;
import com.jzbwlkj.leifeng.ui.bean.JoinProjectBean;
import com.jzbwlkj.leifeng.ui.bean.MallBean;
import com.jzbwlkj.leifeng.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/8.
 */

public class LoveShopFragment extends BaseFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    //0 生活  1  学习  2 党务
    private String type;
    private List<MallBean.ListBean> mList = new ArrayList<>();
    private LoveShopAdapter adapter;
    private int page = 1;
    private int all = 1;
    @Override
    public int getLayoutResId() {
        type = getArguments().getString("type");
        return R.layout.layout_tab;
    }

    @Override
    public void initView() {
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                getNetData();
            }
        });
        initAdapter();
        getNetData();
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void configViews() {

    }

    private void getNetData(){
        RetrofitClient.getInstance().createApi().mallList(null)
                .compose(RxUtils.<HttpResult<List<MallBean>>>io_main())
                .subscribe(new BaseObjObserver<List<MallBean>>(getActivity(),refresh) {
                    @Override
                    protected void onHandleSuccess(List<MallBean> mallBeans) {
                        if(mallBeans.size()<=0){
                            ToastUtils.showToast("暂时没有相关数据");
                        }else{
                            for (MallBean mallBean:mallBeans){
                                if(TextUtils.equals("0",type)&&TextUtils.equals("生活用品",mallBean.getType_name())){
                                    mList.addAll(mallBean.getList());
                                }else if(TextUtils.equals("1",type)&&TextUtils.equals("学习用品",mallBean.getType_name())){
                                    mList.addAll(mallBean.getList());
                                }else if(TextUtils.equals("2",type)&&TextUtils.equals("党务用品",mallBean.getType_name())){
                                    mList.addAll(mallBean.getList());
                                }
                            }

                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void initAdapter(){
        adapter = new LoveShopAdapter(R.layout.item_learning_garden, mList,getActivity());
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
//                JoinProjectBean.ListBean.ActivityInfoBean dataBean= mList.get(position).getActivity_info();
//                Intent intent = new Intent(getActivity(),AcDetailActivity.class);
//                intent.putExtra("id",dataBean.getId());
//                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);
    }

}
