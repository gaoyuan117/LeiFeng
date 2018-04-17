package com.jzbwlkj.leifeng.ui.activity;

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
import com.jzbwlkj.leifeng.ui.adapter.FilterPopAdapter;
import com.jzbwlkj.leifeng.ui.adapter.MyTeamAdapter;
import com.jzbwlkj.leifeng.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ProjectRecruitActivity extends BaseActivity {

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

    private List<String> mList = new ArrayList<>();
    private MyTeamAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_join_team;
    }

    @Override
    public void initView() {
        setCenterTitle("项目招募");
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


        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");

        adapter = new MyTeamAdapter(R.layout.item_my_ac, mList, "0");
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                toActivity(TeamActivity.class);
            }
        });
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

}
