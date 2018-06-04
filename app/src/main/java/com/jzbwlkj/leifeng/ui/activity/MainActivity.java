package com.jzbwlkj.leifeng.ui.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.Text;
import com.baidu.mapapi.model.LatLng;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.saomiao.CaptureActivity;
import com.jzbwlkj.leifeng.ui.adapter.ListViewAdapter;
import com.jzbwlkj.leifeng.ui.adapter.QiandaoAdapter;
import com.jzbwlkj.leifeng.ui.bean.JoinProjectBean;
import com.jzbwlkj.leifeng.ui.bean.MySelfModel;
import com.jzbwlkj.leifeng.ui.bean.QianDaoModel;
import com.jzbwlkj.leifeng.ui.fragment.HomeFragment;
import com.jzbwlkj.leifeng.ui.fragment.MyFragment;
import com.jzbwlkj.leifeng.ui.fragment.NewsFragment;
import com.jzbwlkj.leifeng.utils.AppManager;
import com.jzbwlkj.leifeng.utils.CommonApi;
import com.jzbwlkj.leifeng.utils.DownloadUtil;
import com.jzbwlkj.leifeng.utils.SharedPreferencesUtil;
import com.jzbwlkj.leifeng.utils.ToastUtils;
import com.jzbwlkj.leifeng.view.ArrowDownloadButton;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnClickListener {

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

    private View dialogView;
    private TextView tvTt;
    private TextView tvClose;
    private LinearLayout llNo;
    private ArrowDownloadButton adbButton;
    private TextView tvOldVersion;
    private TextView tvNewVersion;
    private TextView tvNewInfo;
    private TextView tvNo;
    private TextView tvOk;
    private LinearLayout llUpdate;
    private Dialog upDialog;

    private int oldVersioncode;
    private String oldVersionName;
    private int newVersioncode;//新版本号
    private String newVersionname;//新版本名
    private String downPath;//新版本下载地址
    private String apkInfo;//新版本描述
    private String size;//新版本大小
    private String isQinag = "0";
    public static final String TAG_EXIT = "exit";

    private int r = 1;
    private int i = 0;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 88:
                    if (i >= 100) {
                        i = 100;
                        handler.removeCallbacksAndMessages(null);
                        adbButton.reset();
                        upDialog.dismiss();
                        update(MainActivity.this, "leifeng");
                    } else {
                        adbButton.startAnimating();
                        adbButton.setProgress((float) i);
                    }
                    break;
                default:
                    break;
            }
        }

        ;
    };
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
    private Drawable saomiao;
    private Drawable qiandao;

    private View viewType;
    private ListView lvContent;
    private Dialog popType;
    private QiandaoAdapter lvAdapter;
    private List<QianDaoModel> showList = new ArrayList<>();

    private String acId;//活动id

    @Override
    public int getLayoutId() {
        qiandao = getResources().getDrawable(R.mipmap.qiandao);
        saomiao = getResources().getDrawable(R.mipmap.saoyisao);
        qiandao.setBounds(0, 0, qiandao.getMinimumWidth(), qiandao.getMinimumHeight());
        saomiao.setBounds(0, 0, saomiao.getMinimumWidth(), saomiao.getMinimumHeight());
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!noLogin()) {
            if (BaseApp.type == 1) {
                showList.clear();
                getList();
            }
        }
    }

    @Override
    public void initView() {
        initDialog();
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
            } else {
                checkVersion();
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
//                if (noLogin()) {
//                    toActivity(LoginActivity.class);
//                    return;
//                }

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
                popType.show();
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
        tvPhone.setText(BaseApp.config.getService_tel());
        tvLocation.setText(BaseApp.config.getService_address());
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

    ///////////////////////////版本更新相关////////////////////////////////
    private void checkVersion() {
        //获取本机版本信息
        try {
            PackageInfo pkg;
            pkg = getPackageManager().getPackageInfo(getPackageName(), 0);
            oldVersioncode = pkg.versionCode;
            oldVersionName = pkg.versionName;
            newVersioncode = Integer.parseInt(BaseApp.config.getApp_version());
            downPath = BaseApp.config.getApp_url_android();
            //           downPath = "http:\\/\\/www.solomoslm.com\\/public\\/suoluomen.apk";
            if (newVersioncode > oldVersioncode) {
                llNo.setVisibility(View.GONE);
                llUpdate.setVisibility(View.VISIBLE);
                tvOldVersion.setText("当前版本：" + oldVersionName);
                tvNewVersion.setText("新版本：" + newVersionname);
                //            tvNewInfo.setText("更新信息：" + apkInfo);
                upDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initDialog() {
        dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_download, null);
        tvTt = (TextView) dialogView.findViewById(R.id.tv_tt);
        tvClose = (TextView) dialogView.findViewById(R.id.tv_close);
        llNo = (LinearLayout) dialogView.findViewById(R.id.ll_no);
        tvOldVersion = (TextView) dialogView.findViewById(R.id.tv_old_version);
        tvNewVersion = (TextView) dialogView.findViewById(R.id.tv_new_version);
        tvNewInfo = (TextView) dialogView.findViewById(R.id.tv_new_info);
        tvNo = (TextView) dialogView.findViewById(R.id.tv_no);
        llUpdate = (LinearLayout) dialogView.findViewById(R.id.ll_update);
        tvOk = (TextView) dialogView.findViewById(R.id.tv_ok);
        adbButton = (ArrowDownloadButton) dialogView.findViewById(R.id.adb_button);
        tvClose.setOnClickListener(this);
        tvNo.setOnClickListener(this);
        tvOk.setOnClickListener(this);

        upDialog = new Dialog(this, R.style.setpicture_dailog_style);
        upDialog.setContentView(dialogView);
        upDialog.setCanceledOnTouchOutside(false);

/////////////////////////////////////////////////////////////////////////////////
        viewType = LayoutInflater.from(this).inflate(R.layout.pop_list, null);
        lvContent = viewType.findViewById(R.id.lv_content);
        lvAdapter = new QiandaoAdapter(showList, this);
        lvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                QianDaoModel model = showList.get(position);
                acId = model.getId();
                if (TextUtils.isEmpty(acId)) {
                    showToastMsg("请选择将要进行签到的活动或项目");
                    return;
                }
                Intent in = new Intent(MainActivity.this, SignActivity.class);
                in.putExtra("id", acId);
                in.putExtra("dis", model.getPid());
                in.putExtra("lat", model.getLat());
                in.putExtra("lng", model.getLng());
                startActivity(in);
                popType.dismiss();
            }
        });
        lvContent.setAdapter(lvAdapter);
        popType = new Dialog(this, R.style.wx_dialog);
        popType.setContentView(viewType);
        popType.setCanceledOnTouchOutside(false);

        ViewGroup.LayoutParams layoutParams = viewType.getLayoutParams();
        layoutParams.width = getResources().getDisplayMetrics().widthPixels;
        viewType.setLayoutParams(layoutParams);
        popType.getWindow().setGravity(Gravity.BOTTOM);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_close:
                i = 0;
                upDialog.dismiss();
                break;
            case R.id.tv_no:
                if (r == 1) {
                    if (TextUtils.equals("0", isQinag)) {
                        i = 0;
                        upDialog.dismiss();
                    } else {
                        ToastUtils.showToast("此次更新您必须更新到新版本，请谅解");
                    }
                } else {
                    ToastUtils.showToast("当前正在更新中，请耐心等待.....");
                }

                break;
            case R.id.tv_ok:
                if (r == 1) {
                    r++;
                    if (TextUtils.isEmpty(downPath)) {
                        showToastMsg("文件资源获取失败");
                    } else {
                        dowm(downPath);
                    }
                } else {
                    ToastUtils.showToast("当前正在更新中，请耐心等待.....");
                }

                break;
        }
    }

    private void dowm(String url) {
        Log.i("sun", "url==" + url);
        DownloadUtil.get().download(url, "leifeng", new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showToast("下载完成");
                    }
                });

            }

            @Override
            public void onDownloading(int progress) {
                i = progress;
                handler.sendEmptyMessage(88);
            }

            @Override
            public void onDownloadFailed() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showToast("下载失败");
                    }
                });
            }
        });
    }

    /**
     * 安装应用
     */
    public void update(Context ctx, String name) {
        Log.i("sun", "安装==" + 1);
        File file = DownloadUtil.file;
        Uri apkUri;
        if (Build.VERSION.SDK_INT >= 24) {
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致 参数3 共享的文件
            apkUri = FileProvider.getUriForFile(ctx, "com.jzbwlkj.leifeng.fileprovider", file);
        } else {
            apkUri = Uri.fromFile(file);
        }
        Log.i("sun", "安装==" + 2 + "==路径==" + file.getPath());
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Log.i("sun", "安装==" + 4);
        // 由于没有在Activity环境下启动Activity,设置下面的标签
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.i("sun", "安装==" + 5);
        //添加这一句表示对目标应用临时授权该Uri所代表的文件
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        Log.i("sun", "安装==" + 6 + "==uri==" + apkUri);
        intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        Log.i("sun", "安装==" + 7);
        ctx.startActivity(intent);
        Log.i("sun", "安装==" + 8);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        checkVersion();
    }

    /**
     * 获取参加的活动列表
     */
    private void getList() {
        RetrofitClient.getInstance().createApi().userProListp(BaseApp.token)
                .compose(RxUtils.<HttpResult<List<JoinProjectBean>>>io_main())
                .subscribe(new BaseObjObserver<List<JoinProjectBean>>(getActivity()) {
                    @Override
                    protected void onHandleSuccess(List<JoinProjectBean> projectBeans) {
                        if (projectBeans == null || projectBeans.size() > 0) return;
                        for (JoinProjectBean bean : projectBeans) {
                            for (JoinProjectBean.ListBean listBean : bean.getList()) {
                                QianDaoModel model = new QianDaoModel();
                                model.setId(listBean.getActivity_id() + "");
                                model.setName(listBean.getActivity_info().getTitle());
                                model.setPid(listBean.getActivity_info().getSign_scope() + "");
                                model.setLat(Double.parseDouble(listBean.getActivity_info().getLat()));
                                model.setLng(Double.parseDouble(listBean.getActivity_info().getLng()));
                                showList.add(model);
                            }
                        }

                        lvAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent != null) {
            boolean isExit = intent.getBooleanExtra(TAG_EXIT, false);
            if (isExit) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                MainActivity.this.finish();
            }
        }

    }
}
