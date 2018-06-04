package com.jzbwlkj.leifeng.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.utils.AppManager;
import com.jzbwlkj.leifeng.utils.CleanMessageUtil;
import com.jzbwlkj.leifeng.utils.DownloadUtil;
import com.jzbwlkj.leifeng.utils.SharedPreferencesUtil;
import com.jzbwlkj.leifeng.utils.ToastUtils;
import com.jzbwlkj.leifeng.view.ArrowDownloadButton;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_set_version)
    TextView tvSetVersion;
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
    @BindView(R.id.tv_set_clear)
    TextView tvSetClear;
    @BindView(R.id.tv_set_update)
    TextView tvSetUpdate;
    @BindView(R.id.tv_button)
    TextView tvButton;

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
                        update(SettingActivity.this, "leifeng");
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        setCenterTitle("系统设置");
        checkVersion();
        initDialog();
    }

    @Override
    public void initData() {
        getCache();
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.tv_set_clear, R.id.tv_set_update, R.id.tv_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_set_clear:
                CleanMessageUtil.clearAllCache(getApplicationContext());
                getCache();
                break;
            case R.id.tv_set_update:
                if (newVersioncode > oldVersioncode) {
                    llNo.setVisibility(View.GONE);
                    llUpdate.setVisibility(View.VISIBLE);
                    tvOldVersion.setText("当前版本：" + oldVersionName);
                    tvNewVersion.setText("新版本：" + newVersionname);
                    //            tvNewInfo.setText("更新信息：" + apkInfo);

                } else {
                    llUpdate.setVisibility(View.GONE);
                    llNo.setVisibility(View.VISIBLE);
                }
                upDialog.show();
                break;
            case R.id.tv_button:
//                AppManager.getAppManager().finishActivity(MainActivity.class);
                SharedPreferencesUtil.getInstance().removeAll();
//                Intent intent = new Intent(this, LoginActivity.class);
//                startActivity(intent);
//                finish();
                Intent intent = new Intent(this,MainActivity.class);
                intent.putExtra(MainActivity.TAG_EXIT, true);
                startActivity(intent);
                finish();
                break;
        }
    }

    private void getCache() {
        try {
            String huancun = CleanMessageUtil.getTotalCacheSize(getApplicationContext());
            tvSetClear.setText(huancun);
        } catch (Exception e) {
            tvSetClear.setText("0 k");
        }
    }

    //////////////////////////////////////

    private void checkVersion() {
        //获取本机版本信息
        try {
            PackageInfo pkg;
            pkg = getPackageManager().getPackageInfo(getPackageName(), 0);
            oldVersioncode = pkg.versionCode;
            oldVersionName = pkg.versionName;
            tvSetVersion.setText(oldVersionName);
            newVersioncode = Integer.parseInt(BaseApp.config.getApp_version());
            downPath = BaseApp.config.getApp_url_android();
    //        downPath = "http:\\/\\/www.solomoslm.com\\/public\\/suoluomen.apk";
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
        upDialog.getWindow().setWindowAnimations(R.style.wx_dialog);
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
                    if(TextUtils.isEmpty(downPath)){
                        showToastMsg("文件资源获取失败");
                    }else{
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

}
