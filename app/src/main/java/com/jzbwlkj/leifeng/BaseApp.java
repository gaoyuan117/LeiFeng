package com.jzbwlkj.leifeng;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.jzbwlkj.leifeng.dao.DaoMaster;
import com.jzbwlkj.leifeng.dao.DaoSession;
import com.jzbwlkj.leifeng.ui.bean.ConfigBean;
import com.jzbwlkj.leifeng.ui.bean.UserInfoBean;
import com.jzbwlkj.leifeng.utils.SharedPreferencesUtil;

import org.greenrobot.greendao.AbstractDaoMaster;

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
    public static int type = 0;//  1 代表个人用户   2代表队伍账号
    public static int team_id;//队伍登录时候，当前队伍的id
    public static int quanxian = 0;
    public static ConfigBean config;
    public static UserInfoBean userBean;
    public static String address;
    public static DaoSession daoSession;
//    public static int signStatus = 2;//签到状态  1为已签到， 2为未签到

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
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "searchRecoder.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
    }

    public static BaseApp getsInstance() {
        return sInstance;
    }

}
