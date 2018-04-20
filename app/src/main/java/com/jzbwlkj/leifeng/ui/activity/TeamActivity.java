package com.jzbwlkj.leifeng.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.adapter.MyTeamAdapter;
import com.jzbwlkj.leifeng.ui.bean.TeamInfoBean;
import com.jzbwlkj.leifeng.ui.bean.TeamListBean;

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
    @BindView(R.id.tv_team_nam)
    TextView tvTeamNam;
    @BindView(R.id.tv_team_unit)
    TextView tvTeamUnit;
    @BindView(R.id.tv_team_linkman)
    TextView tvTeamLinkman;
    @BindView(R.id.tv_team_phone)
    TextView tvTeamPhone;
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
    private int id;//点击的队伍id

    @Override
    public int getLayoutId() {
        return R.layout.activity_team;
    }

    @Override
    public void initView() {
        id = getIntent().getIntExtra("id", 0);
        setCenterTitle("");
        //setRightTitle("分享");


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
        getNetData();
    }

    @Override
    public void configViews() {

    }


    @OnClick(R.id.tv_team_status)
    public void onViewClicked() {
    }

    /**
     * 获取网络数据
     */
    private void getNetData() {
        RetrofitClient.getInstance().createApi().getTeamInfo(String.valueOf(id))
                .compose(RxUtils.<HttpResult<TeamInfoBean>>io_main())
                .subscribe(new BaseObjObserver<TeamInfoBean>(activity, "队伍信息") {
                    @Override
                    protected void onHandleSuccess(TeamInfoBean teamInfoBeans) {
                        String teamName = teamInfoBeans.getTeam_name();
                        if(TextUtils.equals("null",teamName)||TextUtils.isEmpty(teamName)){
                            teamName = "----";
                        }
                        tvTeamName.setText(teamName);
                        tvTeamNam.setText(teamName);
                        tvTeamUnit.setText(teamName);
                        String path = teamInfoBeans.getPic();
                        if(!TextUtils.isEmpty(path)&&!TextUtils.equals("null",path)){
                            Glide.with(TeamActivity.this).load(path).error(R.mipmap.avatar_default).into(imgTeamAvatar);
                        }
                        tvTeamLinkman.setText(teamInfoBeans.getContact());
                        tvTeamPhone.setText(teamInfoBeans.getContact_mobile());
                        tvTeamHead.setText(teamInfoBeans.getManager());
                        tvTeamLinkphone.setText(teamInfoBeans.getManager_mobile());
                        setWeb(teamInfoBeans.getDesc());
                    }
                });
    }

    private void setWeb(String content){
        String linkCss = "<style type=\"text/css\"> " +
                "img {" +
                "width:100%;" +
                "height:auto;" +
                "}" +
                "</style>";

        String html = "<html><header>" + linkCss + "</header>" + content + "</body></html>";
        webTeamDetail.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
    }
}
