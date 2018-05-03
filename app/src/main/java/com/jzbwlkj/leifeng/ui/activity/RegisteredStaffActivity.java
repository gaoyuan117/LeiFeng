package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.adapter.RegisteredStaffAdapter;
import com.jzbwlkj.leifeng.ui.bean.CommitBean;
import com.jzbwlkj.leifeng.ui.bean.JoinProjectUserBean;
import com.jzbwlkj.leifeng.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 已报名人员
 */
public class RegisteredStaffActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private List<JoinProjectUserBean> mList = new ArrayList<>();
    private RegisteredStaffAdapter adapter;
    private Map<Integer, JoinProjectUserBean> map = new HashMap<>();
    private View footView;
    private CheckBox cbAll;
    private TextView tvSubmit;

    private int id;

    @Override
    public int getLayoutId() {
        id = getIntent().getIntExtra("id", 0);
        return R.layout.activity_help_history;
    }

    @Override
    public void initView() {
        setCenterTitle("已报名人员");

        footView = View.inflate(activity, R.layout.foot_registered_staff, null);
        cbAll = footView.findViewById(R.id.cb_register_staff_all);
        tvSubmit = footView.findViewById(R.id.tv_register_staff_submit);

        cbAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    for (JoinProjectUserBean userBean:mList){
                        boolean ff = (userBean.getStatus() == -1&&userBean.getMyStatus() == 2);
                        if(userBean.getStatus() == 0||ff){
                            userBean.setStatus(1);
                            userBean.setMyStatus(1);
                            map.put(userBean.getId(),userBean);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }else{
                    for (JoinProjectUserBean userBean:mList){
                        if(userBean.getMyStatus() == 1){
                            userBean.setStatus(0);
                            userBean.setMyStatus(0);
                            map.clear();
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });

        adapter = new RegisteredStaffAdapter(R.layout.item_registered_staff, mList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                getPeople();
            }
        });

    }

    @Override
    public void initData() {
        getPeople();
    }

    @Override
    public void configViews() {
        adapter.setOnItemChildClickListener(this);
        adapter.addFooterView(footView);
        tvSubmit.setOnClickListener(this);
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter helper, View view, int i) {
        JoinProjectUserBean userBean = mList.get(i);
        switch (view.getId()) {
            case R.id.tv_register_staff_agree:
                userBean.setStatus(1);
                userBean.setMyStatus(1);
                map.put(userBean.getId(), userBean);
                break;
            case R.id.tv_register_staff_refuse:
                userBean.setStatus(-1);
                userBean.setMyStatus(2);
                map.put(userBean.getId(), userBean);
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register_staff_submit://提交
                if (map.size() <= 0) {
                    showToastMsg("您还没有审核队员");
                    return;
                }
                int i = 0;
                String kk = "[";
                for (int key : map.keySet()) {
                    JoinProjectUserBean userBean = map.get(key);
                    if(i==0){
                        kk = kk+"{\"id\":" + userBean.getId() + ",\"status\":" + userBean.getStatus() + ",\"reason\":null}";
                    }else{
                        kk = kk+","+"{\"id\":" + userBean.getId() + ",\"status\":" + userBean.getStatus() + ",\"reason\":null}";
                    }
                    i++;
                }
                postData(kk+"]");
                break;
        }
    }

    /**
     * 获取已加入人数
     */
    private void getPeople() {
        RetrofitClient.getInstance().createApi().userList(BaseApp.token, String.valueOf(id))
                .compose(RxUtils.<HttpResult<List<JoinProjectUserBean>>>io_main())
                .subscribe(new BaseObjObserver<List<JoinProjectUserBean>>(this, "已加入") {
                    @Override
                    protected void onHandleSuccess(List<JoinProjectUserBean> joinProjectBeans) {
                        if (joinProjectBeans.size() > 0) {
                            mList.addAll(joinProjectBeans);
                            adapter.notifyDataSetChanged();
                        } else {
                            showToastMsg("暂无相关数据");
                        }
                    }
                });
    }

    /**
     * 提交相关数据
     */
    private void postData(String ss) {
        RetrofitClient.getInstance().createApi().postAudit(BaseApp.token, ss)
                .compose(RxUtils.<HttpResult<CommitBean>>io_main())
                .subscribe(new BaseObjObserver<CommitBean>(this, "提交数据") {
                    @Override
                    protected void onHandleSuccess(CommitBean commitBean) {
                        showToastMsg("提交成功");
                        finish();
                    }
                });
    }
}
