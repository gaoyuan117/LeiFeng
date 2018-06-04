package com.jzbwlkj.leifeng.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.jzbwlkj.leifeng.ui.adapter.LeaveWordAdapter;
import com.jzbwlkj.leifeng.ui.adapter.TrainingAdapter;
import com.jzbwlkj.leifeng.ui.bean.AuditListBean;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 留言审核
 */
public class LeaveWordActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener, View.OnClickListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;

    private List<AuditListBean> mList = new ArrayList<>();
    private List<AuditListBean> resultList = new ArrayList<>();//储存处理结果的集合
    private LeaveWordAdapter adapter;
    private View footView;
    private CheckBox cbAll;
    private TextView tvSubmit;

    private View addComment;
    private TextView tvCancel;
    private TextView tvDiaTitle;
    private TextView tvSend;
    private EditText etContent;
    private Dialog addCommenDialog;

    private AuditListBean bean;//这是当前点击的对象

    @Override
    public int getLayoutId() {
        return R.layout.activity_help_history;
    }

    @Override
    public void initView() {
        setCenterTitle("留言审核");
        initDialog();
        footView = View.inflate(activity, R.layout.foot_registered_staff, null);
        cbAll = footView.findViewById(R.id.cb_register_staff_all);
        tvSubmit = footView.findViewById(R.id.tv_register_staff_submit);
        initAdapter();
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mList.clear();
                getNetData();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        cbAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setcheck(isChecked);
                adapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 全选和取消全选的处理
     *
     * @param check
     */
    private void setcheck(boolean check) {
        int dd = 0;
        if (check) {
            dd = 1;
        } else {
            dd = 0;
        }

        for (AuditListBean bean : mList) {
            bean.setStatus(dd);
        }
        resultList.clear();
        if (check) {
            resultList.addAll(mList);
        }
    }

    @Override
    public void initData() {
        adapter.setOnItemChildClickListener(this);
        tvSubmit.setOnClickListener(this);
        getNetData();
    }

    @Override
    public void configViews() {

    }

    /**
     * 一个一个的点击是的处理方法
     *
     * @param helper
     * @param view
     * @param i
     */
    @Override
    public void onItemChildClick(BaseQuickAdapter helper, View view, int i) {
        bean = mList.get(i);
        switch (view.getId()) {
            case R.id.img_leave_word_agree:
                bean.setStatus(1);
                bean.setJujue("同意");
                resultList.add(bean);
                adapter.notifyDataSetChanged();
                break;
            case R.id.img_leave_word_refuse:
                addCommenDialog.show();
                break;
        }


    }

    @Override
    public void onClick(View v) {
        if (resultList.size() > 0) {
            postResult(resultList);
        } else {
            showToastMsg("您还没进行审核操作");
        }
    }

    /**
     * 获取留言列表，这里也是需要
     */
    private void getNetData() {
        RetrofitClient.getInstance().createApi().getAuditList(BaseApp.token)
                .compose(RxUtils.<HttpResult<List<AuditListBean>>>io_main())
                .subscribe(new BaseObjObserver<List<AuditListBean>>(this, "留言列表") {
                    @Override
                    protected void onHandleSuccess(List<AuditListBean> liuYanBeans) {
                        if(liuYanBeans == null){
                            return;
                        }
                        if (liuYanBeans.size() > 0) {
                            mList.addAll(liuYanBeans);
                        } else {
                            footView.setVisibility(View.INVISIBLE);
                            showToastMsg("暂无相关数据");
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
    }

    /**
     * 上传的参数都是String类型的字符串，不过要以数组格式"[\"id\":id,\"status\":1],[]"这样来拼接
     */
    private void postResult(List<AuditListBean> list) {
        String ss = "";
        for (int i = 0; i < list.size(); i++) {
            AuditListBean bean = list.get(i);
            if (i == 0) {
                ss = ss + bean.getId() + "|" + bean.getStatus()+"|"+bean.getJujue();
            } else {
                ss = ss + "," + bean.getId() + "|" + bean.getStatus()+"|"+bean.getJujue();
            }
        }

        RetrofitClient.getInstance().createApi().postAuditList(BaseApp.token, ss)
                .compose(RxUtils.<HttpResult<CommonBean>>io_main())
                .subscribe(new BaseObjObserver<CommonBean>(this, "提交评价") {
                    @Override
                    protected void onHandleSuccess(CommonBean commonBean) {
                        showToastMsg("审核提交成功");
                        finish();//结束当前界面，或者清空提交审核的结果结合resultList，重新获取数据，刷新当前界面，因为这是没有审核的列表，社和通过的不在这里展示
                    }
                });
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        adapter = new LeaveWordAdapter(R.layout.item_leave_word, mList, this);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getNetData();
            }
        }, recyclerView);
        adapter.disableLoadMoreIfNotFullPage();

        adapter.addFooterView(footView);

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.addItemDecoration(rvDivider(1));
        recyclerView.setAdapter(adapter);
    }

    /**
     * 初始化输入弹窗
     */

    private void initDialog() {
        addComment = LayoutInflater.from(this).inflate(R.layout.dialog_add_comment, null);
        tvCancel = addComment.findViewById(R.id.tv_cancel);
        tvDiaTitle = addComment.findViewById(R.id.tv_dia_title);
        tvDiaTitle.setText("留言审核");
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
                    bean.setJujue(ss);
                    bean.setStatus(-1);
                    resultList.add(bean);
                    adapter.notifyDataSetChanged();
                    etContent.setText("");
                    addCommenDialog.dismiss();
                    postResult(resultList);
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


}
