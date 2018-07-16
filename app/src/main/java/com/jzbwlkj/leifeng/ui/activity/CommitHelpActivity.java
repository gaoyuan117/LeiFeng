package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.bean.CommitBean;
import com.jzbwlkj.leifeng.ui.bean.ConfigBean;
import com.jzbwlkj.leifeng.utils.CommonApi;
import com.jzbwlkj.leifeng.utils.StringCheckUtil;
import com.jzbwlkj.leifeng.view.OnDyClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CommitHelpActivity extends BaseActivity {

    @BindView(R.id.et_commit_help_name)
    EditText etCommitHelpName;
    @BindView(R.id.et_commit_help_phone)
    EditText etCommitHelpPhone;
    @BindView(R.id.et_commit_help_adress)
    EditText etCommitHelpAdress;
    @BindView(R.id.et_commit_help_comment)
    EditText etCommitHelpComment;
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
    @BindView(R.id.iv_content)
    ImageView ivContent;
    @BindView(R.id.cb_commit_help)
    CheckBox cbCommitHelp;
    @BindView(R.id.tv_commit)
    TextView tvCommit;

    private String name;
    private String address;
    private String phone;
    private String content;
    private String niming;//是否匿名   0 匿名  1  不匿名  默认不匿名

    @Override
    public int getLayoutId() {
        return R.layout.activity_commit_help;
    }

    @Override
    public void initView() {
        setCenterTitle("求助留言");
        initLinstener();
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.cb_commit_help, R.id.tv_commit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_commit_help:
                break;
            case R.id.tv_commit:
                if(checkCondition()){
                    CommonApi.commonDialog(this, "确认发布留言吗?", "确定", new OnDyClickListener() {
                        @Override
                        public void onClick(View v, int operate) {
                            postHelp();
                        }
                    });
                }

                break;
        }
    }

    /**
     * 处理留言内容和图片的说展示关系
     */
    private void initLinstener(){
        etCommitHelpComment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    String ss = etCommitHelpComment.getText().toString();
                    if(ss.length()>0){
                        ivContent.setVisibility(View.GONE);
                    }else{
                        ivContent.setVisibility(View.VISIBLE);
                    }

                }else{
                    String ss = etCommitHelpComment.getText().toString();
                    if(ss.length()>0){
                        ivContent.setVisibility(View.GONE);
                    }else{
                        ivContent.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        etCommitHelpComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ss = etCommitHelpComment.getText().toString();
                if(ss.length()>0){
                    ivContent.setVisibility(View.GONE);
                }else{
                    ivContent.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    /**
     * 获取网络数据
     */
    private void postHelp() {
        RetrofitClient.getInstance().createApi().commitHelp(name,phone,address,content,niming)
                .compose(RxUtils.<HttpResult<CommitBean>>io_main())
                .subscribe(new BaseObjObserver<CommitBean>(this,"提交帮助留言"){
                    @Override
                    protected void onHandleSuccess(CommitBean commitBean) {

                        showToastMsg("提交成功");
                        setResult(100);
                        finish();
                    }
    });
    }

    private boolean checkCondition() {
        name = etCommitHelpName.getText().toString();
        phone = etCommitHelpPhone.getText().toString();
        address = etCommitHelpAdress.getText().toString();
        content = etCommitHelpComment.getText().toString();

        if(cbCommitHelp.isChecked()){
            niming = "1";
        }else{
            niming = "0";
        }

        if(TextUtils.isEmpty(name)){
            showToastMsg("您还没有填写您的姓名");
            return false;
        }else if(TextUtils.isEmpty(address)){
            showToastMsg("您还没有填写您的地址信息");
            return false;
        }else if(TextUtils.isEmpty(phone)){
            showToastMsg("您还没有填写您的联系方式");
            return false;
        }else if(!StringCheckUtil.isMobileNO(phone)){
            showToastMsg("请正确填写您的手机号码");
            return false;
        }else if(TextUtils.isEmpty(content)){
            showToastMsg("您还没有填写您的留言内容");
            return false;
        }else {
            return true;
        }
    }

}
