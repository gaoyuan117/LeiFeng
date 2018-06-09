package com.jzbwlkj.leifeng.ui.fragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseFragment;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.activity.ManagerUserActivity;
import com.jzbwlkj.leifeng.ui.activity.UserDetialActivity;
import com.jzbwlkj.leifeng.ui.adapter.ZhiYuanZheAdapter;
import com.jzbwlkj.leifeng.ui.bean.UserBean;
import com.jzbwlkj.leifeng.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserAlreadyFragment extends BaseFragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.tv_nodata)
    TextView tvNodata;
    Unbinder unbinder;

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
    public int getLayoutResId() {
        return R.layout.fragment_user_already;
    }

    @Override
    public void initView() {
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
    public void initDatas() {
        getNetData();
    }

    @Override
    public void configViews() {

    }

    /**
     * 获取网络数据
     */
    private void getNetData() {
        RetrofitClient.getInstance().createApi().getMember(BaseApp.token, "1")
                .compose(RxUtils.<HttpResult<List<UserBean>>>io_main())
                .subscribe(new BaseObjObserver<List<UserBean>>(getActivity(), "队员列表") {
                    @Override
                    protected void onHandleSuccess(List<UserBean> rankBeans) {
                        if(rankBeans!= null&&rankBeans.size()>0){
                            refresh.setVisibility(View.VISIBLE);
                            tvNodata.setVisibility(View.GONE);
                            list.addAll(rankBeans);
                            adapter.notifyDataSetChanged();
                        }else{
                            refresh.setVisibility(View.GONE);
                            tvNodata.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

    /**
     * 初始化adapter
     */
    private void initAadapter() {
        adapter = new ZhiYuanZheAdapter(R.layout.item_zhiyuanzhe, list, getActivity(),2);
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

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                UserBean bean = list.get(position);
                Intent intent = new Intent(getActivity(),UserDetialActivity.class);
                intent.putExtra("name",bean.getUser_nickname());
                intent.putExtra("path",bean.getAvatar());
                intent.putExtra("id",bean.getId()+"");
                startActivity(intent);
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
                        ToastUtils.showToast(commonBean);
                        list.clear();
                        getNetData();
                    }
                });
    }

    private void initDialog() {
        addComment = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_comment, null);
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
                    ToastUtils.showToast("请输入您的拒绝原因");
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
        addCommenDialog = new Dialog(getActivity(), R.style.wx_dialog);
        addCommenDialog.setContentView(addComment);
        addCommenDialog.setCanceledOnTouchOutside(false);
    }
}
