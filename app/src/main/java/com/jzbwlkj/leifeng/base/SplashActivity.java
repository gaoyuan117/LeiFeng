package com.jzbwlkj.leifeng.base;


import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.jzbwlkj.leifeng.AppConfig;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.CommonBean;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.activity.LoginActivity;
import com.jzbwlkj.leifeng.ui.activity.MainActivity;
import com.jzbwlkj.leifeng.ui.bean.ConfigBean;
import com.jzbwlkj.leifeng.ui.bean.UserInfoBean;
import com.jzbwlkj.leifeng.utils.SharedPreferencesUtil;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.observers.BlockingBaseObserver;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SplashActivity extends BaseActivity {

    private OkHttpClient client = new OkHttpClient();
    private String token;
    private int type;
    private int team_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
        getConfig();
        token = SharedPreferencesUtil.getInstance().getString("token");
        type = SharedPreferencesUtil.getInstance().getInt("type");
        if (type == 2) {
            team_id = SharedPreferencesUtil.getInstance().getInt("team_id");
        }
        Observable.timer(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        BaseApp.token = token;
                        BaseApp.type = type;
                        BaseApp.team_id = team_id;
                        toActivity(MainActivity.class);
                        finish();
                    }
                });
    }


    @Override
    public void initData() {
        getdata();
    }

    @Override
    public void configViews() {

    }

    /**
     * 获取配置信息
     */
    private void getConfig() {
        RetrofitClient.getInstance().createApi().getConfig("")
                .compose(RxUtils.<HttpResult<ConfigBean>>io_main())
                .subscribe(new BaseObjObserver<ConfigBean>(activity) {
                    @Override
                    protected void onHandleSuccess(ConfigBean configBean) {
                        BaseApp.config = configBean;
                    }
                });
    }

    private void getdata() {

        FormBody.Builder builder = new FormBody.Builder();
//
//        try {
//            builder.add("mobile", "");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        FormBody body = builder.build();
        Request request = new Request.Builder().url(AppConfig.BASE_URL + "/api/public/getConfig").post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jieguo = response.body().string();
                SharedPreferences sharedPreferences = getSharedPreferences("sun", Context.MODE_PRIVATE); //私有数据
                SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
                editor.putString("config", jieguo);
                editor.commit();
            }
        });
    }


}
