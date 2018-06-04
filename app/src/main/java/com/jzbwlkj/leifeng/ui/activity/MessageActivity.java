package com.jzbwlkj.leifeng.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.utils.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;

/**
 * 展示极光推送自定义消息
 */
public class MessageActivity extends Activity {

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
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_content)
    TextView tvContent;

    private String title;
    private String content;
    private String head;
    private Intent intent;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
        intent = getIntent();
        bundle = intent.getExtras();
        title = intent.getStringExtra("biaoji");
        head = intent.getStringExtra("head");
        content = intent.getStringExtra("content");
        Log.i("sun", "标记==" + title + "==标题==" + head + "==内容==" + content);
        if (TextUtils.equals("通知", title)) {
            centerTitleTv.setText(title);
            //         receivingNotification(this,bundle);
        } else {

            centerTitleTv.setText("自定义消息");
        }
        tvHead.setText(head);
        tvContent.setText(content);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(AppManager.activityStack.empty()){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}
