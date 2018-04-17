package com.jzbwlkj.leifeng.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.webkit.WebView;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.ui.adapter.MyTeamAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class TeamActivity extends BaseActivity {


    @BindView(R.id.img_team_avatar)
    CircleImageView imgTeamAvatar;
    @BindView(R.id.tv_team_name)
    TextView tvTeamName;
    @BindView(R.id.tv_team_unit)
    TextView tvTeamUnit;
    @BindView(R.id.tv_team_linkman)
    TextView tvTeamLinkman;
    @BindView(R.id.tv_team_head)
    TextView tvTeamHead;
    @BindView(R.id.tv_team_linkphone)
    TextView tvTeamLinkphone;
    @BindView(R.id.web_team_detail)
    WebView webTeamDetail;
    @BindView(R.id.rv_team)
    RecyclerView recyclerView;
    @BindView(R.id.tv_team_status)
    TextView tvTeamStatus;

    private MyTeamAdapter adapter;
    private List<String> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_team;
    }

    @Override
    public void initView() {
        setCenterTitle("");
        setRightTitle("分享");


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

    }


    @OnClick(R.id.tv_team_status)
    public void onViewClicked() {
    }
}
