package com.jzbwlkj.leifeng.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.adapter.FilterPopAdapter;
import com.jzbwlkj.leifeng.ui.adapter.ProjectAdapter;
import com.jzbwlkj.leifeng.ui.bean.ConfigBean;
import com.jzbwlkj.leifeng.ui.bean.MySelfModel;
import com.jzbwlkj.leifeng.ui.bean.ProjectBean;
import com.jzbwlkj.leifeng.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivitiesRecruitActivity extends BaseActivity {

    @BindView(R.id.cb_area)
    CheckBox cbArea;
    @BindView(R.id.img_area)
    ImageView imgArea;
    @BindView(R.id.cb_type)
    CheckBox cbType;
    @BindView(R.id.img_type)
    ImageView imgType;
    @BindView(R.id.img_filtrate)
    ImageView imgFiltrate;
    @BindView(R.id.cb_filtrate)
    CheckBox cbFiltrate;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.layout)
    LinearLayout layout;
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
    @BindView(R.id.ll_area)
    LinearLayout llArea;
    @BindView(R.id.ll_type)
    LinearLayout llType;
    @BindView(R.id.ll_filtrate)
    LinearLayout llFiltrate;
    @BindView(R.id.ll_condition)
    LinearLayout llCondition;

    private List<CheckBox> cbList = new ArrayList<>();
    private List<ImageView> imgList = new ArrayList<>();
    private List<MySelfModel> areaList = new ArrayList<>();
    private List<MySelfModel> typeList = new ArrayList<>();
    private List<MySelfModel> filterList = new ArrayList<>();
    private int area, type = -1, filter = -1;
    private ListPopupWindow window;

    private List<ProjectBean.DataBean> mList = new ArrayList<>();
    private ProjectAdapter adapter;

    private String city_id = null;
    private String service_type = null;
    private String keyword = null;

    private int page = 1;
    private int all = 1;
    private int ll = 0;//每页的数据长度

    private String sortType;
    @Override
    public int getLayoutId() {
        return R.layout.activity_join_team;
    }

    @Override
    public void initView() {
        setCenterTitle("活动招募");
        imgRight.setVisibility(View.VISIBLE);
        imgRight.setImageDrawable(getResources().getDrawable(R.mipmap.sousuo));
        llCondition.setVisibility(View.VISIBLE);
        imgList.add(imgArea);
        imgList.add(imgType);
        imgList.add(imgFiltrate);

        cbList.add(cbArea);
        cbList.add(cbType);
        cbList.add(cbFiltrate);

        for (int i=0;i< BaseApp.config.getCity_list().size();i++){
            MySelfModel model = new MySelfModel();
            ConfigBean.CityListBean cityListBean = BaseApp.config.getCity_list().get(i);
            model.setName(cityListBean.getName());
            model.setId(cityListBean.getId()+"");
            model.setSelected(false);
            areaList.add(model);
        }

        for (int i=0;i< BaseApp.config.getService_type().size();i++){
            MySelfModel model = new MySelfModel();
            String cityListBean = BaseApp.config.getService_type().get(i);
            model.setName(cityListBean);
            model.setSelected(false);
            model.setId(i+"");
            typeList.add(model);
        }

        MySelfModel model = new MySelfModel();
        model.setName("距离最近");
        model.setPid("add_time");
        model.setSelected(false);
        filterList.add(model);

        MySelfModel model2 = new MySelfModel();
        model2.setName("最新发布");
        model.setPid("add_time");
        model2.setSelected(false);
        filterList.add(model2);

        initAdapter();

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                getNetData(city_id, service_type, keyword,sortType);
            }
        });
    }

    @Override
    public void initData() {
        getNetData(city_id, service_type, keyword,sortType);
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.cb_area, R.id.ll_area, R.id.cb_type, R.id.ll_type, R.id.cb_filtrate, R.id.ll_filtrate, R.id.img_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_area:
            case R.id.ll_area:
                changeType(0);
                showPop(0, areaList);
                break;
            case R.id.cb_type:
            case R.id.ll_type:
                changeType(1);
                showPop(1, typeList);
                break;
            case R.id.cb_filtrate:
            case R.id.ll_filtrate:
                changeType(2);
                showPop(2, filterList);
                break;
            case R.id.img_right:
                Intent intent = new Intent(this,SearchKeyWordsActivity.class);
                intent.putExtra("flag","1");//用来在数据库标记不同的搜索记录，省的建多张表
                startActivityForResult(intent,100);
                break;
        }
    }

    private void changeType(int p) {
        for (int i = 0; i < cbList.size(); i++) {
            if (i == p) {
                cbList.get(i).setChecked(true);
            } else {
                cbList.get(i).setChecked(false);
            }
        }

        for (int i = 0; i < imgList.size(); i++) {
            if (i == p) {
                imgList.get(i).setImageResource(R.mipmap.triangle_red);
            } else {
                imgList.get(i).setImageResource(R.mipmap.triangle_black);
            }
        }


    }

    private void showPop(final int p, List<MySelfModel> list) {
        if (window == null) {
            window = new ListPopupWindow(activity);
        } else {
            window.dismiss();
        }
        window.setAnchorView(layout);
        final FilterPopAdapter adapter = new FilterPopAdapter(activity, list);
        window.setAdapter(adapter);
        window.show();

        if (p == 0) {
            adapter.setSelect(area);
        } else if (p == 1) {
            if (type != -1)
                adapter.setSelect(type);
        } else if (p == 2) {
            if (filter != -1)
                adapter.setSelect(filter);
        }

        adapter.setOnClickInterface(new FilterPopAdapter.ClickInterface() {
            @Override
            public void click(int position) {
                LogUtils.e("p:" + p);
                if (p == 0) {
                    MySelfModel model = areaList.get(position);
                    city_id = model.getId();
                    area = position;
                    adapter.setSelect(area);
                } else if (p == 1) {
                    MySelfModel model = typeList.get(position);
                    service_type = model.getId();
                    type = position;
                    adapter.setSelect(type);
                } else if (p == 2) {
                    MySelfModel model = filterList.get(position);
                    sortType = model.getPid();
                    filter = position;
                    adapter.setSelect(filter);
                }
                page = 1;
                mList.clear();
                getNetData(city_id,service_type,keyword,sortType);
                window.dismiss();
            }
        });

        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                changeType(-1);
            }
        });

    }

    /**
     * 获取网络数据
     */
    private void getNetData(String city_id, String service_type, String keyword,String type) {
        RetrofitClient.getInstance().createApi().projevtList("1", null, page, city_id, service_type, keyword, null,type)
                .compose(RxUtils.<HttpResult<ProjectBean>>io_main())
                .subscribe(new BaseObjObserver<ProjectBean>(this, refresh) {
                    @Override
                    protected void onHandleSuccess(ProjectBean projectBean) {
                        if (projectBean == null) {
                            return;
                        }
                        all = projectBean.getTotal();
                        ll = projectBean.getPer_page();
                        if (projectBean.getData() != null&&projectBean.getData().size() > 0) {
                            mList.addAll(projectBean.getData());
                        } else {
                            showToastMsg("暂无相关数据");
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        adapter = new ProjectAdapter(R.layout.item_my_ac, mList, "0", this);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < all) {
                    page++;
                    getNetData(city_id, service_type, keyword,sortType);
                } else {
                    showToastMsg("没有更多数据了");
                }
            }
        }, recyclerView);
        adapter.disableLoadMoreIfNotFullPage();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ProjectBean.DataBean dataBean = mList.get(position);
                Intent intent = new Intent(ActivitiesRecruitActivity.this, AcDetailActivity.class);
                intent.putExtra("id", dataBean.getId());
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100&&resultCode == 100){
            keyword = data.getStringExtra("keyword");
            page = 1;
            mList.clear();
            getNetData(city_id,service_type,keyword,sortType);
        }
    }
}
