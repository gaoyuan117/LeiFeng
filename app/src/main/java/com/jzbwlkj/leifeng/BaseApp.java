package com.jzbwlkj.leifeng;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.jzbwlkj.leifeng.ui.bean.ConfigBean;
import com.jzbwlkj.leifeng.ui.bean.UserInfoBean;
import com.jzbwlkj.leifeng.utils.SharedPreferencesUtil;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by gaoyuan on 2018/1/7.
 */

public class BaseApp extends MultiDexApplication {

    private static BaseApp sInstance;
    public static String token;
    public static ConfigBean config;
    public static UserInfoBean userBean;

    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        sInstance = this;

        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);

        SharedPreferencesUtil.init(this, "data", MODE_PRIVATE);


        String phone = SharedPreferencesUtil.getInstance().getString("phone");
        if (!TextUtils.isEmpty(phone)) {
            Set<String> strings = new HashSet<>();
            strings.add(phone);
            JPushInterface.setAliasAndTags(this, phone, strings, new TagAliasCallback() {
                @Override
                public void gotResult(int i, String s, Set<String> set) {

                }
            });
        }
    }

    public static BaseApp getsInstance() {
        return sInstance;
    }


}
