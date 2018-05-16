package com.jzbwlkj.leifeng.ui.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.adapter.AcDetailAdapter;
import com.jzbwlkj.leifeng.ui.bean.CommitBean;
import com.jzbwlkj.leifeng.ui.bean.ProjectDetialBean;
import com.jzbwlkj.leifeng.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AcDetailActivity extends BaseActivity {

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
    WebView tvAcDetail;
    @BindView(R.id.tv_ac_demand)
    WebView tvAcDemand;
    @BindView(R.id.tv_ac_comment_num)
    TextView tvAcCommentNum;
    @BindView(R.id.tv_no_ping)
    TextView tvNoPing;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.tv_liuyan)
    TextView tvLiuyan;
    @BindView(R.id.tv_dianzan)
    TextView tvDianzan;
    @BindView(R.id.tv_baoming)
    TextView tvBaoming;
    @BindView(R.id.ll_baoming)
    LinearLayout llBaoming;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.tv_cancel_post)
    TextView tvCancelPost;
    @BindView(R.id.ll_ing)
    LinearLayout llIng;

    private List<ProjectDetialBean.MessageListBean> mList = new ArrayList<>();
    private AcDetailAdapter adapter;
    private int id;
    private String pid = null;
    private boolean zan = false;//是否点赞
    private int joinStatus = 0;

    private View addComment;
    private TextView tvCancel;
    private TextView tvSend;
    private EditText etContent;
    private Dialog addCommenDialog;

    private int already = 0;//以招募人数
    private int all = 0;//共招募人数
    private long endtime;//报名截止时间

    @Override
    public int getLayoutId() {
        id = getIntent().getIntExtra("id", 0);
        return R.layout.activity_ac_detail;
    }

    @Override
    public void initView() {
        setCenterTitle("招募详情");
        initDialog();
        adapter = new AcDetailAdapter(R.layout.item_ac_comment, mList, this);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ProjectDetialBean.MessageListBean liuYanBean = mList.get(position);
                pid = String.valueOf(liuYanBean.getId());
                if (!addCommenDialog.isShowing()) {
                    addCommenDialog.show();
                }
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        getNetData();
    }

    @Override
    public void configViews() {

    }


    private void getNetData() {
        RetrofitClient.getInstance().createApi().projectDetial(BaseApp.token, String.valueOf(id))
                .compose(RxUtils.<HttpResult<ProjectDetialBean>>io_main())
                .subscribe(new BaseObjObserver<ProjectDetialBean>(this, "活动详情") {
                    @Override
                    protected void onHandleSuccess(ProjectDetialBean projectDetialBean) {
                        if (projectDetialBean == null) {
                            return;
                        }
                        String path = projectDetialBean.getPic();
                        if (!TextUtils.isEmpty(path) && !TextUtils.equals("null", path)) {
                            Glide.with(AcDetailActivity.this).load(path).error(R.mipmap.logo).into(imgAcDetail);
                        } else {
                            Glide.with(AcDetailActivity.this).load("xxx").error(R.mipmap.logo).into(imgAcDetail);
                        }
                        endtime = (long) projectDetialBean.getJoin_time_e();
                        tvAcTime.setText(projectDetialBean.getPraise_num() + "");
                        tvAcTitle.setText(projectDetialBean.getTitle());
                        tvAcName.setText(projectDetialBean.getTitle());
                        tvAcType.setText(projectDetialBean.getService_type_text());
                        tvAcBaomingTime.setText(projectDetialBean.getJoin_time_s_text());
                        tvAcJieziTime.setText(projectDetialBean.getJoin_time_e_text());
                        tvAcStartTime.setText(projectDetialBean.getStart_time_text());
                        tvAcEndTime.setText(projectDetialBean.getEnd_time_text());
                        tvAcOneTime.setText(projectDetialBean.getService_hour() + "小时");
                        tvAcAllnum.setText(projectDetialBean.getService_num() + "人");
                        tvAcAddress.setText(projectDetialBean.getAddress());
                        tvAcRange.setText(projectDetialBean.getSign_scope() + "米");
                        tvAcUnit.setText(projectDetialBean.getTeam_name());
                        tvAcLinkman.setText(projectDetialBean.getContact());
                        tvAcLinkphone.setText(projectDetialBean.getContact_mobile());
                        tvAcEmail.setText(projectDetialBean.getEmail());
                        setweb(tvAcDetail, projectDetialBean.getContent());
                        setweb(tvAcDemand, projectDetialBean.getRequirement());
                        all = projectDetialBean.getService_num();
                        already = projectDetialBean.getPraise_num();
                        int dian = projectDetialBean.getIs_praise();
                        ProjectDetialBean.JoinInfoBean beandd = projectDetialBean.getJoin_info();
                        if (beandd == null) {
                            joinStatus = 2;
                        } else {
                            joinStatus = beandd.getStatus();
                        }

                        if (dian == 0) {
                            zan = false;
                        } else {
                            zan = true;
                        }
                        switch (joinStatus) {
                            case 0:
                                llBaoming.setVisibility(View.GONE);
                                llIng.setVisibility(View.VISIBLE);
                                tvStatus.setText("审核中");
                                break;

                            case -1:
                                llBaoming.setVisibility(View.VISIBLE);
                                llIng.setVisibility(View.GONE);
                                tvBaoming.setText("重新报名");
                                break;

                            case 1:
                                llBaoming.setVisibility(View.VISIBLE);
                                llIng.setVisibility(View.GONE);
                                tvBaoming.setText("已报名");
                                break;

                            default:
                                llBaoming.setVisibility(View.VISIBLE);
                                llIng.setVisibility(View.GONE);
                                tvBaoming.setText("我要报名");
                                break;
                        }
//                        if (joinStatus == 0) {
//
//                        } else if (joinStatus == -1) {
//
//                        } else if (joinStatus == 1) {
//
//                        } else {
//
//                        }
                        initZan();

                        if (projectDetialBean.getMessage_list().size() > 0) {
                            mList.clear();
                            mList.addAll(projectDetialBean.getMessage_list());
                            recyclerView.setVisibility(View.VISIBLE);
                            tvNoPing.setVisibility(View.GONE);
                            tvAcCommentNum.setText("共" + mList.size() + "条留言");
                            adapter.notifyDataSetChanged();
                        } else {
                            recyclerView.setVisibility(View.GONE);
                            tvNoPing.setVisibility(View.VISIBLE);
                            tvAcCommentNum.setText("暂无留言");
                        }
                    }
                });

    }

    /**
     * 对webview赋值
     *
     * @param web     需要复制的webview
     * @param content 内容
     */
    private void setweb(WebView web, String content) {
        String linkCss = "<style type=\"text/css\"> " +
                "img {" +
                "width:100%;" +
                "height:auto;" +
                "}" +
                "</style>";

        String html = "<html><header>" + linkCss + "</header>" + content + "</body></html>";
        web.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
    }

    /**
     * 报名参加活动
     */
    private void joinAc() {
        RetrofitClient.getInstance().createApi().joinProject(BaseApp.token, String.valueOf(id))
                .compose(RxUtils.<HttpResult<CommitBean>>io_main())
                .subscribe(new BaseObjObserver<CommitBean>(this, "报名参加活动") {
                    @Override
                    protected void onHandleSuccess(CommitBean commitBean) {
                        ToastUtils.showToast("您已报名成功，请等待审核");
                        joinStatus = 2;
                        tvBaoming.setText("报名审核中");
                    }
                });
    }

    /**
     * 添加留言,对留言进行回复
     */
    private void liuyan(String content) {
        RetrofitClient.getInstance().createApi().liuyan(BaseApp.token, String.valueOf(id), content, pid)
                .compose(RxUtils.<HttpResult<CommitBean>>io_main())
                .subscribe(new BaseObjObserver<CommitBean>(this, "留言") {
                    @Override
                    protected void onHandleSuccess(CommitBean commitBean) {
                        if (TextUtils.isEmpty(pid)) {
                            ToastUtils.showToast("留言添加成功");
                        } else {
                            ToastUtils.showToast("添加评价成功");
                        }
                        pid = null;
                        addCommenDialog.dismiss();
                        etContent.setText("");
                        getNetData();
                    }
                });
    }

    /**
     * 点赞
     */
    private void dianzan() {
        RetrofitClient.getInstance().createApi().dianzan(BaseApp.token, String.valueOf(id))
                .compose(RxUtils.<HttpResult<CommitBean>>io_main())
                .subscribe(new BaseObjObserver<CommitBean>(this, "点赞") {
                    @Override
                    protected void onHandleSuccess(CommitBean commitBean) {
                        zan = !zan;
                        String ss;
                        if (zan) {
                            ss = "点赞成功";
                        } else {
                            ss = "取消点赞成功";
                        }
                        ToastUtils.showToast(ss);
                        getNetData();
                    }
                });
    }

    /**
     * 处理点赞相关状态
     */
    private void initZan() {
        if (zan) {
            tvDianzan.setText("取消点赞");
        } else {
            tvDianzan.setText("点赞");
        }
    }

    @OnClick({R.id.tv_liuyan, R.id.tv_dianzan, R.id.tv_baoming, R.id.tv_cancel_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_liuyan:
                if (!addCommenDialog.isShowing()) {
                    addCommenDialog.show();
                }
                break;
            case R.id.tv_dianzan:
                dianzan();
                break;
            case R.id.tv_baoming:
                if (already >= all) {
                    showToastMsg("当前活动已满员，谢谢");
                } else if (System.currentTimeMillis() / 1000 > endtime) {
                    showToastMsg("当前时间报名已截至，谢谢");
                } else if (joinStatus == 0) {
                    showToastMsg("报名审核中");
                } else if (joinStatus == -1) {
                    joinAc();
                } else if (joinStatus == 1) {
                    showToastMsg("您已报名成功，请勿重复申请");
                } else {
                    joinAc();
                }
                break;

            case R.id.tv_cancel_post:
                cancel();
                break;
        }
    }


    /**
     * 初始化留言的dialog
     */
    private void initDialog() {
        addComment = LayoutInflater.from(this).inflate(R.layout.dialog_add_comment, null);
        tvCancel = addComment.findViewById(R.id.tv_cancel);
        tvSend = addComment.findViewById(R.id.tv_send);
        etContent = addComment.findViewById(R.id.et_content);
        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ss = etContent.getText().toString();
                if (TextUtils.isEmpty(ss)) {
                    showToastMsg("请您先输入您的留言内容");
                } else {
                    liuyan(ss);
                }
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCommenDialog.dismiss();
            }
        });
        addCommenDialog = new Dialog(this, R.style.wx_dialog);
        addCommenDialog.setContentView(addComment);
        addCommenDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * 取消报名
     */
    private void cancel() {
        RetrofitClient.getInstance().createApi().cancelProject(BaseApp.token, String.valueOf(id))
                .compose(RxUtils.<HttpResult<CommitBean>>io_main())
                .subscribe(new BaseObjObserver<CommitBean>(this, "取消报名") {
                    @Override
                    protected void onHandleSuccess(CommitBean commitBean) {
                        showToastMsg("取消报名成功");
                        finish();
                    }
                });

    }
}
