package com.jzbwlkj.leifeng.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.ui.adapter.AcDetailAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class AcDetailActivity extends BaseActivity {

    @BindView(R.id.img_ac_detail)
    ImageView imgAcDetail;
    @BindView(R.id.tv_ac_time)
    TextView tvAcTime;
    @BindView(R.id.tv_ac_title)
    TextView tvAcTitle;
    @BindView(R.id.tv_ac_name)
    TextView tvAcName;
    @BindView(R.id.tv_ac_type)
    TextView tvAcType;
    @BindView(R.id.tv_ac_baoming_time)
    TextView tvAcBaomingTime;
    @BindView(R.id.tv_ac_jiezi_time)
    TextView tvAcJieziTime;
    @BindView(R.id.tv_ac_start_time)
    TextView tvAcStartTime;
    @BindView(R.id.tv_ac_end_time)
    TextView tvAcEndTime;
    @BindView(R.id.tv_ac_one_time)
    TextView tvAcOneTime;
    @BindView(R.id.tv_ac_allnum)
    TextView tvAcAllnum;
    @BindView(R.id.tv_ac_address)
    TextView tvAcAddress;
    @BindView(R.id.tv_ac_range)
    TextView tvAcRange;
    @BindView(R.id.tv_ac_unit)
    TextView tvAcUnit;
    @BindView(R.id.tv_ac_linkman)
    TextView tvAcLinkman;
    @BindView(R.id.tv_ac_linkphone)
    TextView tvAcLinkphone;
    @BindView(R.id.tv_ac_email)
    TextView tvAcEmail;
    @BindView(R.id.tv_ac_detail)
    TextView tvAcDetail;
    @BindView(R.id.tv_ac_demand)
    TextView tvAcDemand;
    @BindView(R.id.tv_ac_comment_num)
    TextView tvAcCommentNum;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_ac_status)
    TextView tvAcStatus;
    @BindView(R.id.tv_ac_cancle)
    TextView tvAcCancle;

    private List<String> mList = new ArrayList<>();
    private AcDetailAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_ac_detail;
    }

    @Override
    public void initView() {
        setCenterTitle("招募详情");
        mList.add("");
        mList.add("");
        adapter = new AcDetailAdapter(R.layout.item_ac_comment, mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.tv_ac_status, R.id.tv_ac_cancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_ac_status:
                break;
            case R.id.tv_ac_cancle:
                break;
        }
    }
}
