package com.jzbwlkj.leifeng.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.adapter.FilterPopAdapter;
import com.jzbwlkj.leifeng.ui.adapter.JoinTeamAdapter;
import com.jzbwlkj.leifeng.ui.bean.TeamListBean;
import com.jzbwlkj.leifeng.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class JoinTeamActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

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

    private List<CheckBox> cbList = new ArrayList<>();
    private List<ImageView> imgList = new ArrayList<>();
    private List<String> areaList = new ArrayList<>();
    private List<String> typeList = new ArrayList<>();
    private List<String> filterList = new ArrayList<>();
    private int area, type = -1, filter = -1;
    private ListPopupWindow window;

    private List<TeamListBean> mList = new ArrayList<>();
    private JoinTeamAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_join_team;
    }

    @Override
    public void initView() {
        setCenterTitle("队伍");
        imgList.add(imgArea);
        imgList.add(imgType);
        imgList.add(imgFiltrate);

        cbList.add(cbArea);
        cbList.add(cbType);
        cbList.add(cbFiltrate);

        areaList.add("高密市");
        areaList.add("高密市");
        areaList.add("高密市");
        areaList.add("高密市");

        typeList.add("赛会服务");
        typeList.add("赛会服务");
        typeList.add("赛会服务");

        filterList.add("距离最近");
        filterList.add("最新发布");

        adapter = new JoinTeamAdapter(R.layout.item_join_team, mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(rvDivider(1));
    }

    @Override
    public void initData() {
        getTeamList();
    }

    @Override
    public void configViews() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                TeamListBean teamListBean = mList.get(i);
                Intent info = new Intent( JoinTeamActivity.this, TeamActivity.class);
                info.putExtra("id",teamListBean.getId());
                startActivity(info);
            }
        });

        refresh.setOnRefreshListener(this);
    }

    @OnClick({R.id.cb_area, R.id.ll_area, R.id.cb_type, R.id.ll_type, R.id.cb_filtrate, R.id.ll_filtrate})
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

    private void showPop(final int p, List<String> list) {
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
                    area = position;
                    adapter.setSelect(area);
                } else if (p == 1) {
                    type = position;
                    adapter.setSelect(type);
                } else if (p == 2) {
                    filter = position;
                    adapter.setSelect(filter);
                }
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


    private void getTeamList(){
        RetrofitClient.getInstance().createApi().getTeamList("")
                .compose(RxUtils.<HttpResult<List<TeamListBean>>>io_main())
                .subscribe(new BaseObjObserver<List<TeamListBean>>(activity,refresh) {
                    @Override
                    protected void onHandleSuccess(List<TeamListBean> list) {
                        if (isEmpty(list)) return;
                        mList.clear();
                        mList.addAll(list);
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onRefresh() {
        getTeamList();
    }
}
