package com.jzbwlkj.leifeng.retrofit;

import com.jzbwlkj.leifeng.ui.bean.AuditListBean;
import com.jzbwlkj.leifeng.ui.bean.ChatListBean;
import com.jzbwlkj.leifeng.ui.bean.ChatListDeticalBean;
import com.jzbwlkj.leifeng.ui.bean.CodeBean;
import com.jzbwlkj.leifeng.ui.bean.CommitBean;
import com.jzbwlkj.leifeng.ui.bean.ConfigBean;
import com.jzbwlkj.leifeng.ui.bean.HelpListBean;
import com.jzbwlkj.leifeng.ui.bean.HomeBean;
import com.jzbwlkj.leifeng.ui.bean.JoinProjectBean;
import com.jzbwlkj.leifeng.ui.bean.JoinProjectUserBean;
import com.jzbwlkj.leifeng.ui.bean.JoinTeamListBean;
import com.jzbwlkj.leifeng.ui.bean.LiuYanBean;
import com.jzbwlkj.leifeng.ui.bean.LoginBean;
import com.jzbwlkj.leifeng.ui.bean.MainNewsBean;
import com.jzbwlkj.leifeng.ui.bean.MainUserBean;
import com.jzbwlkj.leifeng.ui.bean.MallBean;
import com.jzbwlkj.leifeng.ui.bean.NewsBean;
import com.jzbwlkj.leifeng.ui.bean.ProjectBean;
import com.jzbwlkj.leifeng.ui.bean.ProjectDetialBean;
import com.jzbwlkj.leifeng.ui.bean.RankBean;
import com.jzbwlkj.leifeng.ui.bean.StudyBean;
import com.jzbwlkj.leifeng.ui.bean.TeamInfoBean;
import com.jzbwlkj.leifeng.ui.bean.TeamListBean;
import com.jzbwlkj.leifeng.ui.bean.TeamLoginBean;
import com.jzbwlkj.leifeng.ui.bean.TeamStatusBean;
import com.jzbwlkj.leifeng.ui.bean.UserBean;
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
    Observable<HttpResult<HomeBean>> homeData(@Field("city_id") String city_id);

    //获取服务队列表
    @FormUrlEncoded
    @POST("/api/team/getTeamList")
    Observable<HttpResult<List<TeamListBean>>> getTeamList(@Field("mobile") String mobile);

    //获取队伍信息
    @FormUrlEncoded
    @POST("/api/team/getTeamInfo")
    Observable<HttpResult<TeamInfoBean>> getTeamInfo(@Field("id") String team_id, @Field("team_token") String token);

    //获取队伍信息
    @FormUrlEncoded
    @POST("/api/team/getTeamInfo")
    Observable<HttpResult<TeamInfoBean>> getTeamInfop(@Field("id") String team_id, @Field("token") String token);

    //用户申请加入队伍
    @FormUrlEncoded
    @POST("/api/team/joinTeam")
    Observable<HttpResult<CommitBean>> joinTeam(@Field("team_id") String team_id, @Field("token") String token);

    //用户加入的队伍列表
    @FormUrlEncoded
    @POST("/api/team/getUserTeam")
    Observable<HttpResult<List<JoinTeamListBean>>> joinTeamList(@Field("token") String token);

    //新闻列表
    @FormUrlEncoded
    @POST("/api/news/index")
    Observable<HttpResult<NewsBean>> newsIndex(@Field("mobile") String mobile);

    //学习园地  使用帮助  type            文章类型 0 志愿新闻 1 使用帮助 2学习园地
    @FormUrlEncoded
    @POST("/api/news/getNewsList")
    Observable<HttpResult<List<StudyBean>>> studyGarden(@Field("type") String type);

    //个人注册
    @FormUrlEncoded
    @POST("/api/public/register")
    Observable<HttpResult<CommonBean>> personalRegister(@FieldMap Map<String, Object> map);

    //队伍注册
    @FormUrlEncoded
    @POST("/api/team/register")
    Observable<HttpResult<String>> teamRegister(@FieldMap Map<String, Object> map);

    //发送短信
    @FormUrlEncoded
    @POST("/api/public/sendsms")
    Observable<HttpResult<CodeBean>> sendsms(@Field("mobile") String mobile, @Field("type") String type);

    //忘记密码   0 为志愿者，1为队伍
    @FormUrlEncoded
    @POST("/api/public/forgetpwd")
    Observable<HttpResult<String>> forgetpwd(@Field("mobile") String mobile, @Field("password") String password,
                                                 @Field("code") String code,@Field("user_type") String user_type);

    //登录
    @FormUrlEncoded
    @POST("/api/public/login")
    Observable<HttpResult<LoginBean>> login(@Field("id_no") String mobile, @Field("user_pass") String user_pass);

    //队伍登录
    @FormUrlEncoded
    @POST("/api/team/teamLogin")
    Observable<HttpResult<TeamLoginBean>> loginUnit(@Field("username") String username, @Field("password") String password);


    //志愿名片
    @FormUrlEncoded
    @POST("/api/user/getUserInfo")
    Observable<HttpResult<UserInfoBean>> getUserInfo(@Field("token") String token,@Field("uid") String id);

    //信息列表
    @FormUrlEncoded
    @POST("/api/message/getMessageList")
    Observable<HttpResult<ChatListBean>> chatlist(@Field("token") String token, @Field("page") int page, @Field("type") int type);


    //信息列表
    @FormUrlEncoded
    @POST("/api/message/getMessageList")
    Observable<HttpResult<ChatListBean>> chatlist2(@Field("team_token") String token, @Field("page") int page, @Field("type") int type);

    //信息详情
    @FormUrlEncoded
    @POST("/api/message/getMessageInfo")
    Observable<HttpResult<ChatListDeticalBean>> chatlistDetical(@Field("id") String id, @Field("token") String token);

    //发布求助信息
    @FormUrlEncoded
    @POST("/api/help/addHelp")
    Observable<HttpResult<CommitBean>> commitHelp(@Field("fullname") String name, @Field("mobile") String phone, @Field("address") String address, @Field("content") String content, @Field("is_anonymous") String anonymous);

    //求助列表
    @FormUrlEncoded
    @POST("/api/help/getHelpList")
    Observable<HttpResult<List<HelpListBean>>> helpList(@Field("") String name);

    //1活动，0项目列表,通用接口参数非必传
    @FormUrlEncoded
    @POST("/api/activity/getList")
    Observable<HttpResult<ProjectBean>> projevtList(@Field("type") String type, @Field("status") String status, @Field("page") int page,
                                                    @Field("city_id") String city_id, @Field("service_type") String service_type,
                                                    @Field("keyword") String keyword, @Field("team_id") String team_id, @Field("order_type") String sort);

    //活动详情
    @FormUrlEncoded
    @POST("/api/activity/getInfo")
    Observable<HttpResult<ProjectDetialBean>> projectDetial(@Field("token") String token, @Field("id") String id);

    //活动详情
    @FormUrlEncoded
    @POST("/api/activity/getInfo")
    Observable<HttpResult<ProjectDetialBean>> projectDetialT(@Field("team_token") String token, @Field("id") String id);

    //申请加入活动
    @FormUrlEncoded
    @POST("/api/activity/joinActivity")
    Observable<HttpResult<CommitBean>> joinProject(@Field("token") String token, @Field("activity_id") String activity_id);

    //取消加入活动
    @FormUrlEncoded
    @POST("/api/activity/cancelActivity")
    Observable<HttpResult<CommitBean>> cancelProject(@Field("token") String token, @Field("activity_id") String activity_id);

    //点赞
    @FormUrlEncoded
    @POST("/api/activity/setPraise")
    Observable<HttpResult<CommitBean>> dianzan(@Field("token") String token, @Field("activity_id") String activity_id);

    //留言
    @FormUrlEncoded
    @POST("/api/activity/addMessage")
    Observable<HttpResult<CommitBean>> liuyan(@Field("token") String token, @Field("activity_id") String activity_id, @Field("content") String content, @Field("pid") String pid);

    //留言列表
    @FormUrlEncoded
    @POST("/api/activity/getMessageList")
    Observable<HttpResult<List<LiuYanBean>>> liuyanList(@Field("activity_id") String activity_id);

    //获得用户参加的活动列表
    @FormUrlEncoded
    @POST("/api/activity/getUserJoinList")
    Observable<HttpResult<List<JoinProjectBean>>> userProList(@Field("token") String token,@Field("type") int type);

    //获得用户参加的活动列表
    @FormUrlEncoded
    @POST("/api/activity/getUserJoinList")
    Observable<HttpResult<List<MainUserBean>>> userProListp(@Field("token") String token);

    //获得参加活动的人员列表
    @FormUrlEncoded
    @POST("/api/activity/getJoinMemberList")
    Observable<HttpResult<List<JoinProjectUserBean>>> userList(@Field("token") String token, @Field("activity_id") String activity_id);


    //获得参加活动的人员列表
    @FormUrlEncoded
    @POST("/api/activity/getJoinMemberList")
    Observable<HttpResult<List<JoinProjectUserBean>>> userListT(@Field("team_token") String token, @Field("activity_id") String activity_id);

    //提交审核结果
    @FormUrlEncoded
    @POST("/api/activity/auditUser")
    Observable<HttpResult<CommitBean>> postAudit(@Field("team_token") String token, @Field("audit_arr") String audit_arr);


    //留言待审核列表
    @FormUrlEncoded
    @POST("/api/activity/getAuditMessageList")
    Observable<HttpResult<List<AuditListBean>>> getAuditList(@Field("team_token") String token);

    //审核留言待审核列表
    @FormUrlEncoded
    @POST("/api/activity/auditAcitivtyMessage")
    Observable<HttpResult<CommonBean>> postAuditList(@Field("team_token") String token, @Field("audit_arr") String audit_arr);


    //报名培训   参数2表示培训项目的id
    @FormUrlEncoded
    @POST("/api/message/applyPeixun")
    Observable<HttpResult<CommitBean>> joinTraining(@Field("token") String token, @Field("id") String id);

    //爱心义仓
    @FormUrlEncoded
    @POST("/api/goods/index")
    Observable<HttpResult<List<MallBean>>> mallList(@Field("") String token);

    //修改密码
    @FormUrlEncoded
    @POST("/api/user/editPassword")
    Observable<HttpResult<CommonBean>> modifyPwd(@Field("token") String token, @Field("oldpwd") String oldpwd, @Field("newpwd") String newpwd);

    //修改个人信息
    @FormUrlEncoded
    @POST("/api/user/updateUserInfo")
    Observable<HttpResult<CommitBean>> upDatePerson(@Field("token") String token, @Field("avatar") String picPath, @Field("user_nickname") String nickname,
                                                    @Field("sex") String sex, @Field("natinal") String natinal, @Field("id_no") String id_no,
                                                    @Field("city_id") String city_id);

    //修改个人手机号
    @FormUrlEncoded
    @POST("/api/user/updateMobile")
    Observable<HttpResult<CommonBean>> modifyPhone(@Field("token") String token, @Field("mobile") String mobile, @Field("code") String code);

    //排行榜单
    @FormUrlEncoded
    @POST("/api/rank/index")
    Observable<HttpResult<RankBean>> rankList(@Field("token") String token);

    //发布,更新活动项目
    @FormUrlEncoded
    @POST("/api/Activity/saveActivity")
    Observable<HttpResult<CommonBean>> publicProject(@FieldMap Map<String, Object> map);

    //签到，签退
    @FormUrlEncoded
    @POST("/api/user/sign")
    Observable<HttpResult<CommonBean>> signProject(@Field("token") String token,@Field("activity_id") String activity_id,@Field("lng") String lng,@Field("lat") String lat);

    //获取队伍的会员列表
    @FormUrlEncoded
    @POST("/api/team/getMemberList")
    Observable<HttpResult<List<UserBean>>> getMember(@Field("team_token") String token,@Field("status") String status);


    //获得主管地址列表
    @FormUrlEncoded
    @POST("/api/public/getAreaList")
    Observable<HttpResult<UserBean>> getZhuguanList(@Field("pid") String pid);

    //获得主管地址列表/api/news/getNewsInfo
    @FormUrlEncoded
    @POST("/api/team/editTeamInfo")
    Observable<HttpResult<CommonBean>> modifyTeam(@FieldMap Map<String, Object> map);


    //新闻详情  /api/team/updateMobile
    @FormUrlEncoded
    @POST("/api/news/getNewsInfo")
    Observable<HttpResult<MainNewsBean>> getNewsInfo(@Field("id") String id);

    //修改队伍联系人手机号码
    @FormUrlEncoded
    @POST("/api/team/updateMobile")
    Observable<HttpResult<CommonBean>> modifyLink(@Field("team_token") String token,@Field("mobile") String mobile,@Field("code") String code);

    //提交志愿者审核结果
    @FormUrlEncoded
    @POST("/api/team/auditUser")
    Observable<HttpResult<String>> volunteersaudit(@Field("team_token") String token,@Field("status") String status,@Field("note") String note,@Field("id") String id);

    //修改队伍登录密码
    @FormUrlEncoded
    @POST("/api/team/editPassword")
    Observable<HttpResult<CommonBean>> changeTeamPwd(@Field("team_token") String token,@Field("oldpwd") String oldpwd,@Field("newpwd") String newpwd);

    //代签
    @FormUrlEncoded
    @POST("/api/team/signForUser")
    Observable<HttpResult<CommonBean>> daiqian(@Field("team_token") String token,@Field("activity_id") String activity_id,
                                               @Field("uid") String uid,@Field("time_s") String time_s,@Field("time_e") String time_e);

}