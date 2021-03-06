package com.jzbwlkj.leifeng.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jzbwlkj.leifeng.AppConfig;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.CommonBean;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.bean.CodeBean;
import com.jzbwlkj.leifeng.view.OnDyClickListener;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;


/**
 * Created by gaoyuan on 2018/1/7.
 */

public class CommonApi {

    private Toast toast;

    /**
     * 获取验证码
     */
    public static void sendsms(Context context, String type, String phone,String tt) {

        Log.i("sun","tt=="+tt);
        RetrofitClient.getInstance().createApi().sendsms(phone, type,tt)
                .compose(RxUtils.<HttpResult<CodeBean>>io_main())
                .subscribe(new BaseObjObserver<CodeBean>(context) {
                    @Override
                    protected void onHandleSuccess(CodeBean codeBean) {

                    }
                });
    }

    /**
     * 是否登录
     *
     * @param context
     * @return
     */
//    public static boolean isLogin(Context context) {
//        if (TextUtils.isEmpty(BaseApp.token)) {
//            showLoginDialog(context);
//            return false;
//        }
//        return true;
//    }

    /**
     * 提示登录对话框
     *
     * @param context
     */
//    public static void showLoginDialog(final Context context) {
//        if (dialog != null && dialog.isShowing()) {
//            dialog.dismiss();
//        }
//
//        View view = View.inflate(context, R.layout.dialog_login, null);
//        TextView tvMessage = (TextView) view.findViewById(R.id.tv_message);
//        tvMessage.setText("您还没有登录");
//        dialog = new Dialog(context, R.style.wx_dialog);
//        dialog.setContentView(view);
//        dialog.show();
//
//        view.findViewById(R.id.tv_yes).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                context.startActivity(new Intent(context, LoginActivity.class).putExtra("type", "login"));
//                dialog.dismiss();
//            }
//        });
//
//        view.findViewById(R.id.tv_no).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//    }
    public static void commonDialog(final Context context, String msg, String text, final OnDyClickListener listener) {

        View view = View.inflate(context, R.layout.dialog_common, null);
        TextView tvMessage = (TextView) view.findViewById(R.id.tv_message);
        tvMessage.setText(msg);
        TextView tv = (TextView) view.findViewById(R.id.tv_yes);
        tv.setText(text);
        final Dialog dialog = new Dialog(context, R.style.wx_dialog);
        dialog.setContentView(view);
        dialog.show();

        view.findViewById(R.id.tv_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                listener.onClick(v, 0);
            }
        });

        final Dialog finalDialog = dialog;
        view.findViewById(R.id.tv_no).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalDialog.dismiss();
            }
        });

    }


    /**
     * 分享
     */
    public static void share(final Context context, String name, PlatformActionListener listener) {
        OnekeyShare oks = new OnekeyShare();
        oks.setSilent(true);
        oks.disableSSOWhenAuthorize();
        if (!TextUtils.isEmpty(name)) {
            oks.setPlatform(name);
        }
        oks.setTitle(context.getString(R.string.app_name));
        oks.setText(BaseApp.config.getShare_desc());
        oks.setImageUrl("http://leifeng.jzbwlkj.com/logo.png");

        oks.setUrl(BaseApp.config.getApp_url_android());
        oks.setSiteUrl(BaseApp.config.getApp_url_android());
        oks.setTitleUrl(BaseApp.config.getApp_url_android());

        oks.setSite(context.getString(R.string.app_name));
        if (listener != null)
            oks.setCallback(listener);
        oks.show(context);
    }

    //打开浏览器
    public static void toBrowser(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }

    /**
     * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
     * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
     *
     * @param anchorView  呼出window的view
     * @param contentView window的内容布局
     * @return window显示的左上角的xOff, yOff坐标
     */
    public static int[] calculatePopWindowPos(final View anchorView, final View contentView) {
        final int windowPos[] = new int[2];
        final int anchorLoc[] = new int[2];
        // 获取锚点View在屏幕上的左上角坐标位置
        anchorView.getLocationOnScreen(anchorLoc);
        final int anchorHeight = anchorView.getHeight();
        // 获取屏幕的高宽
        final int screenHeight = ScreenUtils.getScreenHeight(anchorView.getContext());
        final int screenWidth = ScreenUtils.getScreenWidth(anchorView.getContext());
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        // 计算contentView的高宽
        final int windowHeight = contentView.getMeasuredHeight();
        final int windowWidth = contentView.getMeasuredWidth();
        // 判断需要向上弹出还是向下弹出显示
        final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
        if (isNeedShowUp) {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] - windowHeight;
        } else {
            windowPos[0] = screenWidth - windowWidth;
            windowPos[1] = anchorLoc[1] + anchorHeight;
        }
        return windowPos;
    }

    /**
     * TabLayout设置下划线长度
     *
     * @param tabs
     * @param leftDip
     * @param rightDip
     */
    public static void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        tabStrip.setAccessible(true);
        LinearLayout llTab = null;
        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());
        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }
    }

    public static void glideUtils(Context context, ImageView imageView, String url) {
        if (TextUtils.isEmpty(url)) return;

        if (!url.contains("upload")) {
            url = AppConfig.BASE_URL + "/upload/" + url;
        }
        Glide.with(context).load(url).error(R.mipmap.logo).into(imageView);
    }

    public static void glideUtils(Context context, ImageView imageView, String url, int errorImg) {
        if (TextUtils.isEmpty(url)) return;

        if (!url.contains("upload")) {
            url = AppConfig.BASE_URL + "/upload/" + url;
        } else {
            url = AppConfig.BASE_URL + url;
        }
        Glide.with(BaseApp.getsInstance()).load(url).error(errorImg).into(imageView);
    }

    /**
     * 设置页面的透明度
     *
     * @param bgAlpha 1表示不透明
     */
    public static void setBackgroundAlpha(Activity activity, float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        if (bgAlpha == 1) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上半透明效果无效的bug
        }
        activity.getWindow().setAttributes(lp);
    }

    /**
     * 打开拨号盘
     *
     * @param context
     * @param phone
     */
    public static void takePhone(Context context, String phone) {
        Uri uri = Uri.parse("tel:" + phone);
        Intent i = new Intent(Intent.ACTION_DIAL, uri);
        context.startActivity(i);
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            int versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }

        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return "版本号：" + versionName;
    }

    /**
     * 获取随机验证码
     *
     * @return
     */
    public static String getNum() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 4; i++) {

            Random rand = new Random();
            int num = rand.nextInt(10);

            if (i == 3) {
                sb.append(num);
            } else {
                sb.append(num + " ");
            }
        }

        LogUtils.e("随机验证码：" + sb.toString().replaceAll(" ", ""));
        return sb.toString();
    }

    /**
     * 调用系统相机拍一张照片
     *
     * @return 图片地址
     */
    public static String getPhotoByCamera(Activity activity) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "avatar.png");
        Uri imageUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        activity.startActivityForResult(intent, AppConfig.REQUEST_CAMERA);
        return file.getAbsolutePath();
    }

    public static boolean isEmpty(List<?> list) {
        if (list == null || list.size() == 0)
            return true;
        else
            return false;
    }

    /**
     * 毫秒换成00:00:00
     *
     * @param finishTime
     * @return
     */
    public static String getCountTimeByLong(long finishTime) {
        int totalTime = (int) (finishTime / 1000);//秒
        int hour = 0, minute = 0, second = 0;

        if (3600 <= totalTime) {
            hour = totalTime / 3600;
            totalTime = totalTime - 3600 * hour;
        }
        if (60 <= totalTime) {
            minute = totalTime / 60;
            totalTime = totalTime - 60 * minute;
        }
        if (0 <= totalTime) {
            second = totalTime;
        }
        StringBuilder sb = new StringBuilder();

        if (hour < 10) {
            sb.append("0").append(hour).append("：");
        } else {
            sb.append(hour).append("：");
        }
        if (minute < 10) {
            sb.append("0").append(minute).append("：");
        } else {
            sb.append(minute).append("：");
        }
        if (second < 10) {
            sb.append("0").append(second);
        } else {
            sb.append(second);
        }
        return sb.toString();

    }


    /**
     * 设置轮播图
     *
     * @param ltPath
     */
    public static void setBanner(Banner banner, List<String> ltPath) {
//        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
//        banner.setImageLoader(new MyImageLoader());
//        banner.setImages(list);
//        banner.setBannerAnimation(Transformer.Default);
//        banner.isAutoPlay(true);
//        banner.setViewPagerIsScroll(true);
//        banner.setDelayTime(3000);
//        banner.setIndicatorGravity(BannerConfig.CENTER);
//        banner.start();

        if (banner.getTag() == null) {
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)//BannerConfig.CIRCLE_INDICATOR.setIndicatorGravity(BannerConfig.CENTER)
                    .setImageLoader(new MyImageLoader())//设置图片加载器
                    .setBannerAnimation(Transformer.DepthPage); //设置banner动画效果
            banner.setImages(ltPath)
                    //                .setBannerTitles(titles)//设置标题集合（当banner样式有显示title时）
                    .isAutoPlay(true)//设置自动轮播，默认为true
                    .setDelayTime(4000)//设置轮播时间
                    .start();  //banner设置方法全部调用完毕时最后调用
            banner.setTag(1);
        } else {
            if (ltPath.size() > 0) {
                banner.update(ltPath);
            }
        }
    }

}
