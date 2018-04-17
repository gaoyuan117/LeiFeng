package com.jzbwlkj.leifeng.ui.activity;

import android.view.View;
import android.widget.EditText;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.utils.CommonApi;

import butterknife.BindView;
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_commit_help;
    }

    @Override
    public void initView() {
        setCenterTitle("求助留言");
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
                CommonApi.commonDialog(this, "确认发布留言吗?", "确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToastMsg("发布成功");
                        finish();
                    }
                });
                break;
        }
    }
}
