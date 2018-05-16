package com.jzbwlkj.leifeng.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
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
import com.jzbwlkj.leifeng.ui.adapter.ZhiYuanZheAdapter;
import com.jzbwlkj.leifeng.ui.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManagerUserActivity extends BaseActivity {

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

    private ZhiYuanZheAdapter adapter;
    private List<UserBean> list = new ArrayList<>();

    private View addComment;
    private TextView tvCancel;
    private TextView tvDiaTitle;
    private TextView tvSend;
    private EditText etContent;
    private Dialog addCommenDialog;

    private String uid;

    @Override
    public int getLayoutId() {
        return R.layout.activity_manager_user;
    }

    @Override
    public void initView() {
        centerTitleTv.setText("志愿者管理");
        initDialog();
        initAadapter();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
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

    /**
     * 获取网络数据
     */
    private void getNetData() {
        RetrofitClient.getInstance().createApi().getMember(BaseApp.token, "0")
                .compose(RxUtils.<HttpResult<List<UserBean>>>io_main())
                .subscribe(new BaseObjObserver<List<UserBean>>(getActivity(), "队员列表") {
                    @Override
                    protected void onHandleSuccess(List<UserBean> rankBeans) {
                        if(rankBeans!= null&&rankBeans.size()>0){
                            list.addAll(rankBeans);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 初始化adapter
     */
    private void initAadapter() {
        adapter = new ZhiYuanZheAdapter(R.layout.item_zhiyuanzhe, list, this);
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
                UserBean bean = list.get(position);
                uid = bean.getId()+"";
                switch (view.getId()) {
                    case R.id.tv_refuse:
                        addCommenDialog.show();
                        break;

                    case R.id.tv_agree:
                        postData(1, "同意",uid);
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
    private void postData(int status, String note,String id) {
        RetrofitClient.getInstance().createApi().volunteersaudit(BaseApp.token, String.valueOf(status), note,id)
                .compose(RxUtils.<HttpResult<String>>io_main())
                .subscribe(new BaseObjObserver<String >(getActivity(), "审核队员") {
                    @Override
                    protected void onHandleSuccess(String commonBean) {
                        showToastMsg(commonBean);
                        list.clear();
                        getNetData();
                    }
                });
    }

    private void initDialog() {
        addComment = LayoutInflater.from(this).inflate(R.layout.dialog_add_comment, null);
        tvCancel = addComment.findViewById(R.id.tv_cancel);
        tvDiaTitle = addComment.findViewById(R.id.tv_dia_title);
        tvDiaTitle.setText("志愿者审核");
        tvSend = addComment.findViewById(R.id.tv_send);
        etContent = addComment.findViewById(R.id.et_content);
        etContent.setHint("请输入您的拒绝原因");
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss = etContent.getText().toString();
                if (TextUtils.isEmpty(ss)) {
                    showToastMsg("请输入您的拒绝原因");
                } else {
                    etContent.setText("");
                    addCommenDialog.dismiss();
                    postData(-1, ss,uid);
                }
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etContent.setText("");
                addCommenDialog.dismiss();
            }
        });
        addCommenDialog = new Dialog(this, R.style.wx_dialog);
        addCommenDialog.setContentView(addComment);
        addCommenDialog.setCanceledOnTouchOutside(false);
    }


    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
