package com.jzbwlkj.leifeng.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.bean.CommitBean;
import com.jzbwlkj.leifeng.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyUserInfoActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText etPhone;
    private String key;

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_user_info;
    }

    @Override
    public void initView() {
        setCenterTitle("修改个人信息");

        key = getIntent().getStringExtra("key");
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }

    @OnClick(R.id.tv_button)
    public void onViewClicked() {
        update();
    }

    private void update() {
        String content = etPhone.getText().toString();
        if (TextUtils.isEmpty(content)) {
            ToastUtils.showToast("请输入修改信息");
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put(key, content);
        RetrofitClient.getInstance().createApi().upDatePerson(BaseApp.token, map)
                .compose(RxUtils.<HttpResult<CommitBean>>io_main())
                .subscribe(new BaseObjObserver<CommitBean>(this, "修改中") {
                    @Override
                    protected void onHandleSuccess(CommitBean commitBean) {
                        ToastUtils.showToast("修改成功");
                        finish();
                    }
                });
    }


}
