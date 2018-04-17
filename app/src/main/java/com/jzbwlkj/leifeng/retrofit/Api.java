package com.jzbwlkj.leifeng.retrofit;

import com.jzbwlkj.leifeng.ui.bean.ConfigBean;
import com.jzbwlkj.leifeng.ui.bean.HomeBean;
import com.jzbwlkj.leifeng.ui.bean.LoginBean;
import com.jzbwlkj.leifeng.ui.bean.NewsBean;
import com.jzbwlkj.leifeng.ui.bean.TeamListBean;
import com.jzbwlkj.leifeng.ui.bean.UserInfoBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by admin on 2017/3/27.
 */

public interface Api {

    //配置信息
    @FormUrlEncoded
    @POST("/api/public/getConfig")
    Observable<HttpResult<ConfigBean>> getConfig(@Field("mobile") String mobile);

    //文件上传
    @FormUrlEncoded
    @POST("/api/file/upload")
    Observable<HttpResult<ConfigBean>> upload(@Field("mobile") String mobile);

    //首页信息
    @FormUrlEncoded
    @POST("/api/index/index")
    Observable<HttpResult<HomeBean>> homeData(@Field("mobile") String mobile);

    //获取服务队列表
    @FormUrlEncoded
    @POST("/api/team/getTeamList")
    Observable<HttpResult<List<TeamListBean>>> getTeamList(@Field("mobile") String mobile);

    //新闻列表
    @FormUrlEncoded
    @POST("/api/news/index")
    Observable<HttpResult<NewsBean>> newsIndex(@Field("mobile") String mobile);

    //个人注册
    @FormUrlEncoded
    @POST("/api/public/register")
    Observable<HttpResult<CommonBean>> personalRegister(@FieldMap Map<String, Object> map);

    //发送短信
    @FormUrlEncoded
    @POST("/api/public/sendsms")
    Observable<HttpResult<CommonBean>> sendsms(@Field("mobile") String mobile, @Field("type") String type);

    //忘记密码
    @FormUrlEncoded
    @POST("/api/public/forgetpwd")
    Observable<HttpResult<CommonBean>> forgetpwd(@Field("mobile") String mobile, @Field("password") String password, @Field("code") String code);

    //登录
    @FormUrlEncoded
    @POST("/api/public/login")
    Observable<HttpResult<LoginBean>> login(@Field("id_no") String mobile, @Field("user_pass") String user_pass);

    //登录
    @FormUrlEncoded
    @POST("/api/user/getUserInfo")
    Observable<HttpResult<UserInfoBean>> getUserInfo(@Field("token") String token);

    //登录
    @FormUrlEncoded
    @POST("http://mw.yigouweb.com/mapi/index.php")
    Observable<HttpResult<CommonBean>> test(@Field("token") String token);

}