package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.ui.adapter.LeaveWordAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 留言审核
 */
public class LeaveWordActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private List<String> mList = new ArrayList<>();
    private LeaveWordAdapter adapter;
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
        setCenterTitle("留言审核");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        footView = View.inflate(activity, R.layout.foot_registered_staff, null);
        cbAll = footView.findViewById(R.id.cb_register_staff_all);
        tvSubmit = footView.findViewById(R.id.tv_register_staff_submit);

        adapter = new LeaveWordAdapter(R.layout.item_leave_word, mList);
        adapter.addFooterView(footView);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(rvDivider(1));
    }

    @Override
    public void initData() {
        adapter.setOnItemChildClickListener(this);
        tvSubmit.setOnClickListener(this);
    }

    @Override
    public void configViews() {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter helper, View view, int i) {
        ImageView imgAgree = (ImageView) helper.getViewByPosition(recyclerView, i, R.id.img_leave_word_agree);
        ImageView imgRefuse = (ImageView) helper.getViewByPosition(recyclerView, i, R.id.img_leave_word_refuse);
        switch (view.getId()) {
            case R.id.img_leave_word_agree:
                imgAgree.setImageResource(R.mipmap.dui_green);
                imgRefuse.setImageResource(R.mipmap.close_gray);

                map.put(i, true);
                break;
            case R.id.img_leave_word_refuse:
                imgAgree.setImageResource(R.mipmap.dui_gray);
                imgRefuse.setImageResource(R.mipmap.close_red);

                map.put(i, false);
                break;
        }
    }

    @Override
    public void onClick(View v) {

    }
}
