package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.CommonBean;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.adapter.AcManagementDetailsAdapter;
import com.jzbwlkj.leifeng.ui.adapter.DaiQianAdapter;
import com.jzbwlkj.leifeng.ui.adapter.ZhiYuanZheAdapter;
import com.jzbwlkj.leifeng.ui.bean.JoinProjectUserBean;
import com.jzbwlkj.leifeng.ui.bean.UserBean;
import com.jzbwlkj.leifeng.utils.FormatUtils;
import com.jzbwlkj.leifeng.view.CustomDatePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DaiQainActivity extends BaseActivity {

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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    private List<JoinProjectUserBean> mList = new ArrayList<>();
    private DaiQianAdapter adapter;
    private int id;

    private String now;
    private int type = 0;//1  签到时间   2  签退时间
    private JoinProjectUserBean userBean;//时间选择器返回的时间
    private CustomDatePicker customDatePicker1;
    private TextView tvStart;
    private TextView tvEnd;
    private TextView tvDaiqain;
    private String tt;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    tvStart.setText("签到时间："+tt);
                    break;

                case 2:
                    tvEnd.setText("签退时间："+tt);
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        id = getIntent().getIntExtra("id", 0);
        return R.layout.activity_daiqian;
    }

    @Override
    public void initView() {
        centerTitleTv.setText("队员代签");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        long aa = System.currentTimeMillis() - (7 * 24 * 60 * 60 * 1000);
        now = sdf.format(new Date(aa));
        initTimeDialog();
        initAadapter();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh.setRefreshing(false);
                    }
                }, 1000);
                getNetData();
            }
        });
    }

    @Override
    public void initData() {
        getNetData();
    }

    @Override
    public void configViews() {

    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    /**
     * 获取网络数据
     */
    private void getNetData() {
        RetrofitClient.getInstance().createApi().userListT(BaseApp.token, String.valueOf(id))
                .compose(RxUtils.<HttpResult<List<JoinProjectUserBean>>>io_main())
                .subscribe(new BaseObjObserver<List<JoinProjectUserBean>>(this, "已加入") {
                    @Override
                    protected void onHandleSuccess(List<JoinProjectUserBean> joinProjectBeans) {
                        if (joinProjectBeans.size() > 0) {
                            mList.addAll(joinProjectBeans);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    /**
     * 初始化adapter
     */
    private void initAadapter() {
        adapter = new DaiQianAdapter(R.layout.item_qiandao, mList, this);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getNetData();
            }
        }, recyclerView);

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                tvStart = view.findViewById(R.id.tv_qiandao);
                tvEnd = view.findViewById(R.id.tv_qiantui);
                userBean = mList.get(position);
                JoinProjectUserBean.SignInfoBean bean = userBean.getSign_info();
                switch (view.getId()) {
                    case R.id.tv_qiandao:
                        type = 1;
                        customDatePicker1.show(now);
                        break;
                    case R.id.tv_qiantui:
                        type = 2;
                        customDatePicker1.show(now);
                        break;

                    case R.id.tv_daiqian:
                        JoinProjectUserBean.SignInfoBean signInfoBean =userBean.getSign_info();
                        if(signInfoBean == null){
                            showToastMsg("您还没有选择签到，签退时间");
                            return;
                        }
                        String ss = signInfoBean.getData_s();
                        String ee = signInfoBean.getData_e();
                        postData(String.valueOf(id), ss, ee, String.valueOf(userBean.getUid()));
                        break;
                }

            }
        });
        adapter.disableLoadMoreIfNotFullPage();
        recyclerView.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);
    }

    /**
     * 提交审核结果1 已审核 -1 已拒绝
     */
    private void postData(String id, String start, String end, String uid) {
        Log.i("sun","activity_id=="+id+"==uid=="+uid+"==ss=="+start+"==ee=="+end);
        RetrofitClient.getInstance().createApi().daiqian(BaseApp.token, id, uid, start, end)
                .compose(RxUtils.<HttpResult<CommonBean>>io_main())
                .subscribe(new BaseObjObserver<CommonBean>(getActivity(), "代签") {
                    @Override
                    protected void onHandleSuccess(CommonBean commonBean) {
                        showToastMsg("当前队员代签已完成");
                        mList.clear();
                        getNetData();
                    }
                });
    }


    /**
     * 初始化时间选择器
     */
    private void initTimeDialog() {

        customDatePicker1 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tt = time;
                JoinProjectUserBean.SignInfoBean bean = userBean.getSign_info();
                if(bean == null){
                    bean = new JoinProjectUserBean.SignInfoBean();
                }
                if(type == 1){
                    bean.setData_s(time);
                }else if(type == 2){
                    bean.setData_e(time);
                }
                userBean.setSign_info(bean);
                handler.sendEmptyMessage(type);
  //              adapter.notifyDataSetChanged();
            }
        }, now, "3000-12-31 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker1.showSpecificTime(true); // 不显示时和分
        customDatePicker1.setIsLoop(false); // 不允许循环滚动
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
