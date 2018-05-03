package com.jzbwlkj.leifeng.ui.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.ui.fragment.HomeFragment;
import com.jzbwlkj.leifeng.ui.fragment.MyFragment;
import com.jzbwlkj.leifeng.ui.fragment.NewsFragment;
import com.jzbwlkj.leifeng.utils.AppManager;
import com.jzbwlkj.leifeng.utils.CommonApi;
import com.jzbwlkj.leifeng.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rg_main_navigation)
    RadioGroup mRadiogroup;
    @BindView(R.id.ll_main_replace)
    LinearLayout llMainReplace;
    @BindView(R.id.rb_main_home)
    RadioButton rbMainHome;
    @BindView(R.id.rb_main_news)
    RadioButton rbMainNews;
    @BindView(R.id.rb_main_help)
    RadioButton rbMainHelp;
    @BindView(R.id.rb_main_me)
    RadioButton rbMainMe;
    @BindView(R.id.tv_main_sign)
    TextView tvMainSign;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE
    };

    private List<Fragment> mList;
    private List<RadioButton> mRadioButtos;
    private int count, cuuret, target;//RadioButton的个数,当前位置，目标位置
    private long firstTime = 0;
    private HomeFragment homeFragment;
    private NewsFragment newsFragment;
    private MyFragment myFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        getQuanxian();
        mList = new ArrayList<>();
        mRadioButtos = new ArrayList<>();
        homeFragment = new HomeFragment();
        newsFragment = new NewsFragment();
        myFragment = new MyFragment();
        mList.add(homeFragment);
        mList.add(newsFragment);
        mList.add(myFragment);
        count = mRadiogroup.getChildCount();
        for (int i = 0; i < count; i++) {
            mRadioButtos.add((RadioButton) mRadiogroup.getChildAt(i));
        }
        showFirstFragment();
    }

    private void getQuanxian() {
        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(this,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }else{
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.rb_main_home, R.id.rb_main_news, R.id.rb_main_help, R.id.tv_main_sign, R.id.rb_main_me})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.rb_main_home:
                target = 0;
                chooseFragment();
                break;
            case R.id.rb_main_news:
                target = 1;
                chooseFragment();
                break;
            case R.id.rb_main_help:
                if (noLogin()) {
                    toActivity(LoginActivity.class);
                    return;
                }

                if (cuuret == 2) {
                    mRadioButtos.get(4).setChecked(true);
                } else {
                    mRadioButtos.get(cuuret).setChecked(true);
                }

                mRadioButtos.get(3).setChecked(false);
                String phone = SharedPreferencesUtil.getInstance().getString("phone");
                helpDialog(phone, BaseApp.address);

                break;
            case R.id.tv_main_sign:
                if (noLogin()) {
                    toActivity(LoginActivity.class);
                    return;
                }
                showToastMsg("签到");
                break;
            case R.id.rb_main_me:
                if (noLogin()) {
                    toActivity(LoginActivity.class);
                    mRadioButtos.get(cuuret).setChecked(true);
                    mRadioButtos.get(3).setChecked(false);
                    return;
                }
                target = 2;
                chooseFragment();
                break;
        }
    }

    /**
     * 判断显示Fragment
     */
    private void chooseFragment() {
        if (cuuret == target) return;

        Fragment currentFragment = mList.get(cuuret);
        Fragment targetFragment = mList.get(target);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (targetFragment.isAdded()) {
            fragmentTransaction.hide(currentFragment).show(targetFragment);
        } else {
            fragmentTransaction.hide(currentFragment).add(R.id.ll_main_replace, targetFragment);
        }
        fragmentTransaction.commit();
        cuuret = target;
    }


    /**
     * 显示第一个fragment
     */
    private void showFirstFragment() {
        mRadioButtos.get(0).setChecked(true);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.ll_main_replace, mList.get(0));
        transaction.commit();
    }

    /**
     * 需要帮助
     */
    private void helpDialog(final String phone, String location) {
        final Dialog dialog = new Dialog(this, R.style.wx_dialog);
        View view = View.inflate(this, R.layout.dialog_help, null);
        dialog.setContentView(view);
        dialog.show();

        final TextView tvPhone = view.findViewById(R.id.tv_help_phone);
        TextView tvLocation = view.findViewById(R.id.tv_help_location);
        tvPhone.setText(phone);
        tvLocation.setText(location);
        tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonApi.takePhone(MainActivity.this, phone);
            }
        });

        tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //求助历史
        view.findViewById(R.id.tv_help_history).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(HelpHistoryActivity.class);
                dialog.dismiss();
            }
        });
        //发布求助留言
        view.findViewById(R.id.tv_help_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(CommitHelpActivity.class);
                dialog.dismiss();
            }
        });

    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {//如果两次按键时间间隔大于2秒，则不退出
                    Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;//更新firstTime
                    return true;
                } else {//两次按键小于2秒时，退出应用
//                    LoginActivity.loginActivity.finish();
                    AppManager.getAppManager().finishAllActivity();
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }


}
