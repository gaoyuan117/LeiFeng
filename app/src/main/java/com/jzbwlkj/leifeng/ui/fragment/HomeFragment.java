package com.jzbwlkj.leifeng.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseFragment;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.activity.ActivitiesRecruitActivity;
import com.jzbwlkj.leifeng.ui.activity.ChatListActivity;
import com.jzbwlkj.leifeng.ui.activity.JoinTeamActivity;
import com.jzbwlkj.leifeng.ui.activity.LearningGardenActivity;
import com.jzbwlkj.leifeng.ui.activity.LoginActivity;
import com.jzbwlkj.leifeng.ui.activity.LoveShopActivity;
import com.jzbwlkj.leifeng.ui.activity.NewsDetalActivity;
import com.jzbwlkj.leifeng.ui.activity.ProjectRecruitActivity;
import com.jzbwlkj.leifeng.ui.activity.RankActivity;
import com.jzbwlkj.leifeng.ui.activity.RegisterPersonalActivity;
import com.jzbwlkj.leifeng.ui.activity.RegisterTeamActivity;
import com.jzbwlkj.leifeng.ui.activity.SelectAreaActivity;
import com.jzbwlkj.leifeng.ui.activity.TrainingActivity;
import com.jzbwlkj.leifeng.ui.activity.UsingHelpActivity;
import com.jzbwlkj.leifeng.ui.adapter.HomeAdapter;
import com.jzbwlkj.leifeng.ui.bean.HomeBean;
import com.jzbwlkj.leifeng.utils.CommonApi;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/31.
 */

public class HomeFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.tv_home_location)
    TextView tvHomeLocation;
    @BindView(R.id.img_home_title)
    ImageView imgHomeTitle;
    @BindView(R.id.img_home_chat)
    ImageView imgHomeChat;
    @BindView(R.id.tv_home_zuzhi_num)
    TextView tvHomeZuzhiNum;
    @BindView(R.id.tv_home_zhiyuanzhe_num)
    TextView tvHomeZhiyuanzheNum;
    @BindView(R.id.tv_home_dangyuan_num)
    TextView tvHomeDangyuanNum;
    @BindView(R.id.tv_home_team_register)
    TextView tvHomeTeamRegister;
    @BindView(R.id.tv_home_personal_register)
    TextView tvHomePersonalRegister;
    @BindView(R.id.tv_home_join_team)
    TextView tvHomeAddTeam;
    @BindView(R.id.tv_home_peixun)
    TextView tvHomePeixun;
    @BindView(R.id.tv_home_zhaomu)
    TextView tvHomeZhaomu;
    @BindView(R.id.tv_home_xiangmu_zhaomu)
    TextView tvHomeXiangmuZhaomu;
    @BindView(R.id.tv_home_love_shop)
    TextView tvHomeLoveShop;
    @BindView(R.id.tv_home_paihang)
    TextView tvHomePaihang;
    @BindView(R.id.tv_home_learning_garden)
    TextView tvHomeLearningGarden;
    @BindView(R.id.tv_home_use_help)
    TextView tvHomeUseHelp;
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.scrollView)
    ScrollView scrollView;

    private List<HomeBean.NewsRecommendListBean> mList = new ArrayList<>();
    private List<String> bannerList = new ArrayList<>();

    private HomeAdapter adapter;


    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        scrollView.scrollTo(0, 0);

        adapter = new HomeAdapter(R.layout.item_home, mList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setFocusable(false);
    }

    @Override
    public void initDatas() {
        homeData();
    }

    @Override
    public void configViews() {
        adapter.setOnItemClickListener(this);
        refresh.setOnRefreshListener(this);
    }

    @OnClick({R.id.tv_home_location, R.id.img_home_chat, R.id.tv_home_team_register, R.id.tv_home_personal_register, R.id.tv_home_join_team, R.id.tv_home_peixun, R.id.tv_home_zhaomu, R.id.tv_home_xiangmu_zhaomu, R.id.tv_home_love_shop, R.id.tv_home_paihang, R.id.tv_home_learning_garden, R.id.tv_home_use_help})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_home_location://定位
                toActivity(SelectAreaActivity.class);
                break;
            case R.id.img_home_chat://系统消息
                if (noLogin()) {
                    toActivity(LoginActivity.class);
                    return;
                }
                toActivity(ChatListActivity.class);
                break;
            case R.id.tv_home_team_register://队伍注册
                if (noLogin()) {
                    toActivity(LoginActivity.class);
                    return;
                }
                toActivity(RegisterTeamActivity.class);
                break;
            case R.id.tv_home_personal_register://个人注册
                personalRegisterDialog();
                break;
            case R.id.tv_home_join_team://加入队伍
                if (noLogin()) {
                    toActivity(LoginActivity.class);
                    return;
                }
                toActivity(JoinTeamActivity.class);
                break;
            case R.id.tv_home_peixun://志愿培训
                if (noLogin()) {
                    toActivity(LoginActivity.class);
                    return;
                }
                toActivity(TrainingActivity.class);
                break;
            case R.id.tv_home_zhaomu://活动招募
                if (noLogin()) {
                    toActivity(LoginActivity.class);
                    return;
                }
                toActivity(ActivitiesRecruitActivity.class);
                break;
            case R.id.tv_home_xiangmu_zhaomu://项目招募
                if (noLogin()) {
                    toActivity(LoginActivity.class);
                    return;
                }
                toActivity(ProjectRecruitActivity.class);
                break;
            case R.id.tv_home_love_shop://爱心义仓

                toActivity(LoveShopActivity.class);
                break;
            case R.id.tv_home_paihang://排行榜

                toActivity(RankActivity.class);
                break;
            case R.id.tv_home_learning_garden://学习园地
                toActivity(LearningGardenActivity.class);
                break;
            case R.id.tv_home_use_help://使用帮助
                toActivity(UsingHelpActivity.class);
                break;
        }
    }

    private void personalRegisterDialog() {
        final Dialog dialog = new Dialog(activity, R.style.wx_dialog);
        View view = View.inflate(activity, R.layout.dialog_personal_register, null);
        dialog.setContentView(view);
        dialog.show();
        view.findViewById(R.id.tv_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//志愿者注册
                toPersonalRegisterActivity("normal");
                dialog.dismiss();
            }
        });

        view.findViewById(R.id.tv_professional).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//专业志愿者注册
                toPersonalRegisterActivity("professional");
                dialog.dismiss();
            }
        });
    }

    private void toPersonalRegisterActivity(String type) {
        Intent intent = new Intent(activity, RegisterPersonalActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    @Override
    public void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        NewsDetalActivity.toActivity(activity, mList.get(i).getTitle(), "", 0);
    }


    private void homeData() {
        RetrofitClient.getInstance().createApi().homeData("")
                .compose(RxUtils.<HttpResult<HomeBean>>io_main())
                .subscribe(new BaseObjObserver<HomeBean>(activity, refresh) {
                    @Override
                    protected void onHandleSuccess(HomeBean homeBean) {

                        tvHomeZuzhiNum.setText("志愿组织:" + homeBean.getTeam_count() + "个");
                        tvHomeZhiyuanzheNum.setText("志愿者:" + homeBean.getUser_count() + "个");
                        tvHomeDangyuanNum.setText("党员:" + homeBean.getUser_polital_count() + "个");

                        if (homeBean.getIndex_ad1() != null) {
                            bannerList.clear();
                            bannerList.add(homeBean.getIndex_ad1().getImage());
                            CommonApi.setBanner(banner, bannerList);
                        }

                        if (isEmpty(homeBean.getNews_recommend_list())) {
                            return;
                        }

                        mList.clear();
                        mList.addAll(homeBean.getNews_recommend_list());
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onRefresh() {
        homeData();
    }
}
