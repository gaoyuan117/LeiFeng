package com.jzbwlkj.leifeng.ui.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.BaseApp;
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
import com.jzbwlkj.leifeng.ui.activity.MainNewsActivity;
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
import com.jzbwlkj.leifeng.utils.SharedPreferencesUtil;
import com.jzbwlkj.leifeng.utils.ToastUtils;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/31.
 */

public class HomeFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.tv_home_location)
    TextView tvHomeLocation;
    @BindView(R.id.img_home_title)
    ImageView imgHomeTitle;
    @BindView(R.id.iv_chat_status)
    ImageView ivChatStatus;
    @BindView(R.id.img_home_chat)
    RelativeLayout imgHomeChat;
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
    TextView tvHomeJoinTeam;
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
    @BindView(R.id.scrollView)
    NestedScrollView scrollView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    Unbinder unbinder;
    private List<HomeBean.NewsRecommendListBean> mList = new ArrayList<>();
    private List<String> bannerList = new ArrayList<>();

    private HomeAdapter adapter;

    private View infoView;
    private TextView web;
    private CheckBox cbXieYi;
    private TextView tvButton;
    private Dialog infoDialog;
    private String id;//城市id
    private int flag = -1;//0 队伍注册   1  个人非专业   2  个人专业

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        scrollView.scrollTo(0, 0);
        initDialog();
        adapter = new HomeAdapter(getActivity(), R.layout.item_home, mList);
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
        id = SharedPreferencesUtil.getInstance().getString("city_id");
        String name = SharedPreferencesUtil.getInstance().getString("city_name");
        if (TextUtils.isEmpty(name)) {
            name = "高密市";
        }
        tvHomeLocation.setText(name);
        homeData(id);
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
                Intent address = new Intent(getActivity(), SelectAreaActivity.class);
                startActivityForResult(address, 100);
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
                flag = 0;
                web.setText(BaseApp.config.getZhuceshouze());
                infoDialog.show();
                break;
            case R.id.tv_home_personal_register://个人注册
                if (noLogin()) {
                    personalRegisterDialog();
                } else {
                    ToastUtils.showToast("您当前已注册账号");
                }
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
                flag = 1;
                web.setText(BaseApp.config.getZhuceshouze());
                infoDialog.show();
                dialog.dismiss();
            }
        });

        view.findViewById(R.id.tv_professional).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//专业志愿者注册
                flag = 2;
                web.setText(BaseApp.config.getZhuceshouze());
                infoDialog.show();
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
        HomeBean.NewsRecommendListBean bean = mList.get(i);
        Intent intent = new Intent(getActivity(), MainNewsActivity.class);
        intent.putExtra("id", bean.getId());
        startActivity(intent);
    }


    private void homeData(String id) {
        RetrofitClient.getInstance().createApi().homeData(id)
                .compose(RxUtils.<HttpResult<HomeBean>>io_main())
                .subscribe(new BaseObjObserver<HomeBean>(activity, refresh) {
                    @Override
                    protected void onHandleSuccess(HomeBean homeBean) {

                        tvHomeZuzhiNum.setText("志愿组织:" + homeBean.getTeam_count() + "个");
                        tvHomeZhiyuanzheNum.setText("志愿者:" + homeBean.getUser_count() + "个");
                        tvHomeDangyuanNum.setText("党员:" + homeBean.getUser_polital_count() + "个");

                        if (homeBean.getIndex_ad1() != null) {
                            bannerList.clear();
                            for (HomeBean.IndexAd1Bean bean : homeBean.getIndex_ad1()) {
                                bannerList.add(bean.getImage());
                            }
                            CommonApi.setBanner(banner, bannerList);
                        }

                        if (homeBean.getNew_message_num() > 0) {
                            ivChatStatus.setVisibility(View.VISIBLE);
                        } else {
                            ivChatStatus.setVisibility(View.GONE);
                        }

                        if (isEmpty(homeBean.getNews_recommend_list())) {
                            return;
                        }

                        mList.clear();
                        for (HomeBean.NewsRecommendListBean bean : homeBean.getNews_recommend_list()) {
                            mList.add(bean);
                        }
                        adapter.notifyDataSetChanged();

                    }
                });
    }

    @Override
    public void onRefresh() {
        homeData(id);
    }


    /**
     * 初始化说明弹窗
     */
    private void initDialog() {
        infoView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_reason, null);
        web = infoView.findViewById(R.id.web);
        cbXieYi = infoView.findViewById(R.id.cb_xieyi);
        tvButton = infoView.findViewById(R.id.tv_sure);
        tvButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cbXieYi.isChecked()) {
                    ToastUtils.showToast("请您先同意志愿者服务守则，再进行下一步");
                    return;
                }
                if (flag == 0) {
                    toActivity(RegisterTeamActivity.class);
                } else if (flag == 1) {
                    toPersonalRegisterActivity("normal");
                } else if (flag == 2) {
                    toPersonalRegisterActivity("professional");
                }
                infoDialog.dismiss();
            }
        });
        infoDialog = new Dialog(getActivity(), R.style.wx_dialog);
        infoDialog.setContentView(infoView);
        infoDialog.setCanceledOnTouchOutside(false);

        ViewGroup.LayoutParams layoutParams = infoView.getLayoutParams();
        layoutParams.height = getResources().getDisplayMetrics().widthPixels;
        infoView.setLayoutParams(layoutParams);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 100) {//选择地址
            id = data.getStringExtra("id");
            homeData(id);
            tvHomeLocation.setText(data.getStringExtra("name"));
        }
    }
}
