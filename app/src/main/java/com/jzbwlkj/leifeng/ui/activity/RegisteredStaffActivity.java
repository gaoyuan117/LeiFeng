package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.ui.adapter.RegisteredStaffAdapter;
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

    private List<String> mList = new ArrayList<>();
    private RegisteredStaffAdapter adapter;
    private Map<Integer, Boolean> map = new HashMap<>();
    private View footView;
    private CheckBox cbAll;
    private TextView tvSubmit;


    @Override
    public int getLayoutId() {
        return R.layout.activity_help_history;
    }

    @Override
    public void initView() {
        setCenterTitle("已报名人员");
        mList.add("");
        mList.add("");
        mList.add("1");
        mList.add("1");
        mList.add("");
        mList.add("1");

        footView = View.inflate(activity, R.layout.foot_registered_staff, null);
        cbAll = footView.findViewById(R.id.cb_register_staff_all);
        tvSubmit = footView.findViewById(R.id.tv_register_staff_submit);

        adapter = new RegisteredStaffAdapter(R.layout.item_registered_staff, mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void initData() {
    }

    @Override
    public void configViews() {
        adapter.setOnItemChildClickListener(this);
        adapter.addFooterView(footView);
        tvSubmit.setOnClickListener(this);
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter helper, View view, int i) {
        TextView tvAgree = (TextView) helper.getViewByPosition(recyclerView, i, R.id.tv_register_staff_agree);
        TextView tvRefuse = (TextView) helper.getViewByPosition(recyclerView, i, R.id.tv_register_staff_refuse);
        switch (view.getId()) {
            case R.id.tv_register_staff_agree:
                tvAgree.setBackgroundResource(R.drawable.shape_global);
                tvRefuse.setBackgroundResource(R.drawable.shape_gray);
                tvAgree.setTextColor(getResources().getColor(R.color.global));
                tvRefuse.setTextColor(getResources().getColor(R.color.gray));
                map.put(i, true);
                break;
            case R.id.tv_register_staff_refuse:
                tvAgree.setBackgroundResource(R.drawable.shape_gray);
                tvRefuse.setBackgroundResource(R.drawable.shape_global);
                tvAgree.setTextColor(getResources().getColor(R.color.gray));
                tvRefuse.setTextColor(getResources().getColor(R.color.global));
                map.put(i, false);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register_staff_submit://提交
                for (int key : map.keySet()) {
                    LogUtils.e("key=" + key + "and value=" + map.get(key));
                }
                break;
        }
    }
}
