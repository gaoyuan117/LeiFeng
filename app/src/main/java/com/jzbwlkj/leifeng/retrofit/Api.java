package com.jzbwlkj.leifeng.retrofit;

import com.jzbwlkj.leifeng.ui.bean.ChatListBean;
import com.jzbwlkj.leifeng.ui.bean.ChatListDeticalBean;
import com.jzbwlkj.leifeng.ui.bean.CommitBean;
import com.jzbwlkj.leifeng.ui.bean.ConfigBean;
import com.jzbwlkj.leifeng.ui.bean.HelpListBean;
import com.jzbwlkj.leifeng.ui.bean.HomeBean;
import com.jzbwlkj.leifeng.ui.bean.LoginBean;
import com.jzbwlkj.leifeng.ui.bean.NewsBean;
import com.jzbwlkj.leifeng.ui.bean.ProjectBean;
import com.jzbwlkj.leifeng.ui.bean.ProjectDetialBean;
import com.jzbwlkj.leifeng.ui.bean.TeamInfoBean;
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

    //获取队伍信息,2018.4.20  现在是在加入队伍的队伍详情使用，但是返回数据并不正确，不确定是否使用正确
    @FormUrlEncoded
    @POST("/api/team/getTeamInfo")
    Observable<HttpResult<TeamInfoBean>> getTeamInfo(@Field("id") String team_id);

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

    //信息列表
    @FormUrlEncoded
    @POST("/api/message/getMessageList")
    Observable<HttpResult<ChatListBean>> chatlist(@Field("token") String token,@Field("page") int page);

    //信息详情
    @FormUrlEncoded
    @POST("/api/message/getMessageInfo")
    Observable<HttpResult<ChatListDeticalBean>> chatlistDetical(@Field("id") String id);

    //发布求助信息
    @FormUrlEncoded
    @POST("/api/help/addHelp")
    Observable<HttpResult<CommitBean>> commitHelp(@Field("fullname") String name,@Field("mobile") String phone,@Field("address") String address,@Field("content") String content,@Field("is_anonymous") String anonymous);

    //求助列表
    @FormUrlEncoded
    @POST("/api/help/getHelpList")
    Observable<HttpResult<List<HelpListBean>>> helpList(@Field("") String name);

    //1活动，0项目列表,通用接口参数非必传
    @FormUrlEncoded
    @POST("/api/activity/getList")
    Observable<HttpResult<List<ProjectBean>>> projevtList(@Field("type") String type, @Field("status") String status, @Field("page") int page,
                                                          @Field("city_id") String city_id, @Field("service_type") String service_type,
                                                          @Field("keyword") String keyword, @Field("team_id") String team_id);

    //活动详情
    @FormUrlEncoded
    @POST("/api/activity/getInfo")
    Observable<HttpResult<ProjectDetialBean>> projectDetial(@Field("token") String token, @Field("id") String id);

    //申请加入活动
    @FormUrlEncoded
    @POST("/api/activity/joinActivity")
    Observable<HttpResult<CommitBean>> joinProject(@Field("token") String token,@Field("activity_id") String activity_id);
}