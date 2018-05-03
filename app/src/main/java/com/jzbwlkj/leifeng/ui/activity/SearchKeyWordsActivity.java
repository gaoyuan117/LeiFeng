package com.jzbwlkj.leifeng.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.dao.MySelfModelDao;
import com.jzbwlkj.leifeng.ui.adapter.ListViewAdapter;
import com.jzbwlkj.leifeng.ui.bean.MySelfModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchKeyWordsActivity extends BaseActivity {

    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tv_history_recoder)
    TextView tvHistoryRecoder;
    @BindView(R.id.lv_recoder)
    ListView lvRecoder;
    @BindView(R.id.tv_clear)
    TextView tvClear;
    private MySelfModelDao dao;
    private String flag ;
    private ListViewAdapter adapter;
    private List<MySelfModel> list = new ArrayList<>();

    @Override
    public int getLayoutId() {
        dao = BaseApp.daoSession.getMySelfModelDao();
        flag = getIntent().getStringExtra("flag");
        return R.layout.activity_search_key_words;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initAdapter();
    }

    @Override
    public void initData() {
        list.addAll(dao.queryBuilder().where(MySelfModelDao.Properties.Pid.eq(flag)).list());
        adapter.notifyDataSetChanged();
        if(list.size()>0){
            tvClear.setVisibility(View.VISIBLE);
        }else{
            tvClear.setVisibility(View.GONE);
        }
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.rl_back, R.id.tv_search, R.id.tv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_search:
                String ss = etContent.getText().toString();
                if(list.size()>0){
                    for (MySelfModel model : list) {
                        if (TextUtils.equals(ss, model.getName())) {
                            int num = model.getNum();
                            num++;
                            model.setNum(num);
                            dao.update(model);
                        } else {
                            if (!TextUtils.isEmpty(ss) && !TextUtils.equals("null", ss)) {
                                MySelfModel mySelfModel = new MySelfModel(null, ss, "0", 1, flag, false);
                                dao.insert(mySelfModel);
                            }
                        }
                    }
                }else{
                    if (!TextUtils.isEmpty(ss) && !TextUtils.equals("null", ss)) {
                        MySelfModel mySelfModel = new MySelfModel(null, ss, "0", 1, flag, false);
                        dao.insert(mySelfModel);
                    }
                }

                if (TextUtils.isEmpty(ss) || TextUtils.equals("null", ss)) {
                    showToastMsg("您还没有输入关键字内容");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("keyword", ss);
                setResult(100, intent);
                finish();
                break;
            case R.id.tv_clear:
                list.clear();
                adapter.notifyDataSetChanged();
                dao.deleteAll();
                break;
        }
    }

    private void initAdapter() {
        adapter = new ListViewAdapter(list, this);
        lvRecoder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MySelfModel model = list.get(position);
                int num = model.getNum();
                num++;
                model.setNum(num);
                dao.update(model);

                Intent intent = new Intent();
                intent.putExtra("keyword", model.getName());
                setResult(100, intent);
                finish();
            }
        });

        lvRecoder.setAdapter(adapter);
    }
}
