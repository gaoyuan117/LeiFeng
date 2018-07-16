package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.adapter.ProjectAdapter;
import com.jzbwlkj.leifeng.ui.bean.CommitBean;
import com.jzbwlkj.leifeng.ui.bean.JoinTeamListBean;
import com.jzbwlkj.leifeng.ui.bean.ProjectBean;
import com.jzbwlkj.leifeng.ui.bean.TeamInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    private ProjectAdapter adapter;
    private List<ProjectBean.DataBean> mList = new ArrayList<>();
    private int id;//点击的队伍id

    private int page = 1;
    private int all = 1;
    private String status = "8";//加入队伍的状态   1 没有加入  2  审核中  3 审核通过  4  审核拒绝
    private List<JoinTeamListBean.ListBean> okList = new ArrayList<>();
    private List<JoinTeamListBean.ListBean> IngList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_team;
    }

    @Override
    public void initView() {
        id = getIntent().getIntExtra("id", 0);
        setCenterTitle("");
        tvRightText.setText("退出");
        tvRightText.setVisibility(View.GONE);
        initAdapter();
    }

    @Override
    public void initData() {
        if (BaseApp.type == 1) {
            getNetData();
            getJoinTeam();
        } else {
            getTeamData();
        }

        getProJectList();
    }

    @Override
    public void configViews() {

    }


    @OnClick({R.id.tv_team_status,R.id.tv_right_text})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.tv_team_status:
                if (okList.size() >= 2) {
                    showToastMsg("您已经加入了2支队伍，不能再加入其他队伍了");
                    return;
                }
                if (IngList.size() > 0) {
                    showToastMsg("您当前已有加入队伍申请正在审核中，不能进行此操作");
                    return;
                }
                if (TextUtils.equals("-1", status)) {
                    joinTeam();
                } else if (TextUtils.equals("0", status)) {
                    showToastMsg("报名审核中，请耐心等待");
                } else if (TextUtils.equals("1", status)) {
                    showToastMsg("您已在当前队伍中");
                } else {
                    joinTeam();
                }
                break;

            case R.id.tv_right_text:
                goOut();
                break;
        }

    }

    /**
     * 获取网络数据
     */
    private void getNetData() {
        RetrofitClient.getInstance().createApi().getTeamInfop(String.valueOf(id), BaseApp.token)
                .compose(RxUtils.<HttpResult<TeamInfoBean>>io_main())
                .subscribe(new BaseObjObserver<TeamInfoBean>(activity, "队伍信息") {
                    @Override
                    protected void onHandleSuccess(TeamInfoBean teamInfoBeans) {
                        String teamName = teamInfoBeans.getTeam_name();
                        if (TextUtils.equals("null", teamName) || TextUtils.isEmpty(teamName)) {
                            teamName = "----";
                        }
                        if (TextUtils.isEmpty(BaseApp.token) || BaseApp.type == 2) {
                            tvTeamStatus.setVisibility(View.GONE);
                        } else {
                            tvTeamStatus.setVisibility(View.VISIBLE);
                            if (teamInfoBeans.getJoin_info() != null) {
                                status = teamInfoBeans.getJoin_info().getStatus() + "";
                            }
                            if (TextUtils.equals("0", status)) {
                                tvTeamStatus.setText("审核中");
                            } else if (TextUtils.equals("-1", status)) {
                                tvTeamStatus.setText("重新加入");
                            } else if (TextUtils.equals("1", status)) {
                                tvRightText.setVisibility(View.VISIBLE);
                                tvTeamStatus.setText("已加入");
                            } else {
                                tvTeamStatus.setText("我要加入");
                            }
                        }

                        tvTeamName.setText(teamName);
                        tvTeamNam.setText(teamName);
                        tvTeamUnit.setText(teamName);
                        String path = teamInfoBeans.getPic();
                        if (!TextUtils.isEmpty(path) && !TextUtils.equals("null", path)) {
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

    private void getTeamData() {
        RetrofitClient.getInstance().createApi().getTeamInfo(String.valueOf(id), BaseApp.token)
                .compose(RxUtils.<HttpResult<TeamInfoBean>>io_main())
                .subscribe(new BaseObjObserver<TeamInfoBean>(activity, "队伍信息") {
                    @Override
                    protected void onHandleSuccess(TeamInfoBean teamInfoBeans) {
                        String teamName = teamInfoBeans.getTeam_name();
                        if (TextUtils.equals("null", teamName) || TextUtils.isEmpty(teamName)) {
                            teamName = "----";
                        }
                        if (TextUtils.isEmpty(BaseApp.token) || BaseApp.type == 2) {
                            tvTeamStatus.setVisibility(View.GONE);
                        } else {
                            tvTeamStatus.setVisibility(View.VISIBLE);
                            if (teamInfoBeans.getJoin_info() != null) {
                                status = teamInfoBeans.getJoin_info().getStatus() + "";
                            }
                            if (TextUtils.equals("0", status)) {
                                tvTeamStatus.setText("审核中");
                            } else if (TextUtils.equals("-1", status)) {
                                tvTeamStatus.setText("重新加入");
                            } else if (TextUtils.equals("1", status)) {
                                tvTeamStatus.setText("已加入");
                            } else {
                                tvTeamStatus.setText("我要加入");
                            }
                        }

                        tvTeamName.setText(teamName);
                        tvTeamNam.setText(teamName);
                        tvTeamUnit.setText(teamName);
                        String path = teamInfoBeans.getPic();
                        if (!TextUtils.isEmpty(path) && !TextUtils.equals("null", path)) {
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

    private void setWeb(String content) {
        String linkCss = "<style type=\"text/css\"> " +
                "img {" +
                "width:100%;" +
                "height:auto;" +
                "}" +
                "</style>";

        String html = "<html><header>" + linkCss + "</header>" + content + "</body></html>";
        webTeamDetail.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
    }

    /**
     * 初始化数据适配器
     */
    private void initAdapter() {
        adapter = new ProjectAdapter(R.layout.item_my_ac, mList, "0", this);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (page < all) {
                    page++;
                    getNetData();
                } else {
                    showToastMsg("没有更多数据了");
                }

            }
        }, recyclerView);
        adapter.disableLoadMoreIfNotFullPage();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                ProjectBean.DataBean dataBean= mList.get(position);
//                Intent intent = new Intent(ProjectRecruitActivity.this,AcDetailActivity.class);
//                intent.putExtra("id",dataBean.getId());
//                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);
    }

    /**
     * 获取当前队伍的活动列表
     */
    private void getProJectList() {
        RetrofitClient.getInstance().createApi().projevtList("1", null, 1, null,
                null, null, String.valueOf(id), null)
                .compose(RxUtils.<HttpResult<ProjectBean>>io_main())
                .subscribe(new BaseObjObserver<ProjectBean>(this, "活动列表") {
                    @Override
                    protected void onHandleSuccess(ProjectBean projectBean) {
                        if(projectBean.getData() == null||projectBean.getData().size()<=0){
                            return;
                        }
                        mList.addAll(projectBean.getData());
                        adapter.notifyDataSetChanged();
                    }
                });

    }

    /**
     * 加入队伍
     */
    private void joinTeam() {
        RetrofitClient.getInstance().createApi().joinTeam(String.valueOf(id), BaseApp.token)
                .compose(RxUtils.<HttpResult<CommitBean>>io_main())
                .subscribe(new BaseObjObserver<CommitBean>(this, "加入队伍") {
                    @Override
                    protected void onHandleSuccess(CommitBean commitBean) {
                        showToastMsg("报名成功，后台人员将会加快审核，请耐心等待");
                        tvTeamStatus.setText("审核中");
                        status = "2";
                    }
                });
    }

    /**
     * 获取加入的队伍，看一下有没有有正在审核的或者已经有两个了，不允许再加入
     */
    private void getJoinTeam() {
        RetrofitClient.getInstance().createApi().joinTeamList(BaseApp.token)
                .compose(RxUtils.<HttpResult<List<JoinTeamListBean>>>io_main())
                .subscribe(new BaseObjObserver<List<JoinTeamListBean>>(getActivity()) {
                    @Override
                    protected void onHandleSuccess(List<JoinTeamListBean> joinTeamListBeans) {
                        if(joinTeamListBeans == null){
                            return;
                        }
                        okList.addAll(recycleList(joinTeamListBeans, "已通过"));
                        IngList.addAll(recycleList(joinTeamListBeans, "审核中"));
                    }
                });
    }

    /**
     * 遍历相关集合
     */
    private List<JoinTeamListBean.ListBean> recycleList(List<JoinTeamListBean> projectBeans, String type) {
        List<JoinTeamListBean.ListBean> listBeans = new ArrayList<>();
        for (JoinTeamListBean projectBean : projectBeans) {
            if (TextUtils.equals(type, projectBean.getStatus_text())) {
                listBeans.addAll(projectBean.getList());
            }
        }

        return listBeans;
    }

    /**
     * 退出队伍操作
     */
    private void goOut(){
        RetrofitClient.getInstance().createApi().outTeam(BaseApp.token,String.valueOf(id))
                .compose(RxUtils.<HttpResult<String>>io_main())
                .subscribe(new BaseObjObserver<String>(this,"退出队伍") {
                    @Override
                    protected void onHandleSuccess(String s) {
                        showToastMsg("成功退出当前队伍");
                        finish();
                    }
                });
    }
}
