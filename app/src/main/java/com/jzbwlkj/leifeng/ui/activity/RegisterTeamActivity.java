package com.jzbwlkj.leifeng.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.jzbwlkj.leifeng.AppConfig;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class RegisterTeamActivity extends BaseActivity {

    @BindView(R.id.et_team_name)
    EditText etTeamName;
    @BindView(R.id.sp_team_unit)
    Spinner spTeamUnit;
    @BindView(R.id.et_team_linkman)
    EditText etTeamLinkman;
    @BindView(R.id.et_team_phone)
    EditText etTeamPhone;
    @BindView(R.id.et_team_head)
    EditText etTeamHead;
    @BindView(R.id.et_team_linkphone)
    EditText etTeamLinkphone;
    @BindView(R.id.et_team_jieshao)
    EditText etTeamJieshao;
    @BindView(R.id.img_team)
    ImageView imgTeam;
    @BindView(R.id.cb_team)
    CheckBox cbTeam;

    private List<String> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_register_team;
    }

    @Override
    public void initView() {
        setCenterTitle("队伍注册");
        list.add("济南");
        list.add("临沂");
        list.add("临沂");
        list.add("临沂");
        list.add("临沂");
        list.add("临沂");
        list.add("临沂");
        list.add("上海");
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, list);
        //这里设置的是Spinner的样式 ， 输入 simple_之后会提示有4人，如果专属spinner的话应该是俩种，在特殊情况可自己定义样式
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spTeamUnit.setAdapter(adapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.img_team, R.id.tv_team_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_team:
                MultiImageSelector.create().single().origin(new ArrayList<String>()).start(this, AppConfig.REQUEST_IMAGE);
                break;
            case R.id.tv_team_register:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            List<String> list = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            Glide.with(activity).load(list.get(0)).into(imgTeam);
        }
    }
}
