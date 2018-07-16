package com.jzbwlkj.leifeng.ui.fragment;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.AppConfig;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseFragment;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.activity.NewsDetalActivity;
import com.jzbwlkj.leifeng.ui.activity.ShowBannerActivity;
import com.jzbwlkj.leifeng.ui.adapter.NewsAdapter;
import com.jzbwlkj.leifeng.ui.bean.HomeBean;
import com.jzbwlkj.leifeng.ui.bean.NewsBean;
import com.jzbwlkj.leifeng.ui.bean.TeamListBean;
import com.jzbwlkj.leifeng.utils.CommonApi;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/31.
 */

public class NewsFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private View headView;
    private Banner banner;
    private List<NewsBean.NewsListBean> mList = new ArrayList<>();
    private NewsAdapter adapter;
    private List<String> listPath = new ArrayList<>();

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_news;
    }

    @Override
    public void initView() {
        headView = View.inflate(activity, R.layout.head_banner, null);
        banner = headView.findViewById(R.id.banner);
        adapter = new NewsAdapter(R.layout.item_news, mList,getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
        adapter.addHeaderView(headView);
        recyclerView.addItemDecoration(rvDivider(1));

    }

    @Override
    public void initDatas() {
        newsList();
    }

    @Override
    public void configViews() {
        adapter.setOnItemClickListener(this);
        refresh.setOnRefreshListener(this);
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        NewsDetalActivity.toActivity(activity, mList.get(i).getTitle(), mList.get(i).getContent(),mList.get(i).getAdd_time());
    }

    private void newsList() {
        RetrofitClient.getInstance().createApi().newsIndex("")
                .compose(RxUtils.<HttpResult<NewsBean>>io_main())
                .subscribe(new BaseObjObserver<NewsBean>(activity, refresh) {
                    @Override
                    protected void onHandleSuccess(final NewsBean newsBean) {
                        if (newsBean.getAd_info() != null) {
                            List<String> list = new ArrayList<>();
                            for (NewsBean.AdInfoBean bean:newsBean.getAd_info()){
                                String path = bean.getImage();
                                if(!path.contains("http")){
                                    if (!path.contains("upload")) {
                                        path = AppConfig.BASE_URL + "/upload/" + path;
                                    }
                                }

                                list.add(path);
                            }

                            CommonApi.setBanner(banner, list);
                            banner.setOnBannerClickListener(new OnBannerClickListener() {
                                @Override
                                public void OnBannerClick(int position) {
                                    NewsBean.AdInfoBean bean = newsBean.getAd_info().get(position-1);
                                    Intent intent = new Intent(getActivity(), ShowBannerActivity.class);
                                    intent.putExtra("flag",bean.getType());
                                    intent.putExtra("url",bean.getUrl());
                                    startActivity(intent);
                                }
                            });
                        }

                        if (isEmpty(newsBean.getNews_list())) return;

                        mList.clear();
                        mList.addAll(newsBean.getNews_list());
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onRefresh() {
        newsList();
    }
}
