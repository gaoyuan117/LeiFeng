package com.jzbwlkj.leifeng.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jzbwlkj.leifeng.AppConfig;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.bean.CommitBean;
import com.jzbwlkj.leifeng.ui.bean.ConfigBean;
import com.jzbwlkj.leifeng.ui.bean.UploadBean;
import com.jzbwlkj.leifeng.ui.bean.UserInfoBean;
import com.jzbwlkj.leifeng.utils.LogUtils;
import com.jzbwlkj.leifeng.utils.RoundCornesTransFormation;
import com.jzbwlkj.leifeng.utils.ToastUtils;
import com.jzbwlkj.leifeng.view.OnDyClickListener;
import com.jzbwlkj.leifeng.view.PhoneCameraUtil;
import com.jzbwlkj.leifeng.view.WinCameraDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyInfoActivity extends BaseActivity {

    @BindView(R.id.img_my_info_avatar)
    CircleImageView imgMyInfoAvatar;
    @BindView(R.id.ll_my_info_avatar)
    LinearLayout llMyInfoAvatar;
    @BindView(R.id.tv_my_info_name)
    TextView tvMyInfoName;
    @BindView(R.id.tv_my_info_sex)
    TextView tvMyInfoSex;
    @BindView(R.id.tv_my_info_minzu)
    TextView tvMyInfoMinzu;
    @BindView(R.id.tv_my_info_no)
    TextView tvMyInfoNo;
    @BindView(R.id.tv_my_info_phone)
    TextView tvMyInfoPhone;
    @BindView(R.id.tv_my_info_zzmm)
    TextView tvMyInfoZzmm;
    @BindView(R.id.tv_my_info_zgxl)
    TextView tvMyInfoZgxl;
    @BindView(R.id.tv_my_info_job)
    TextView tvMyInfoJob;
    @BindView(R.id.tv_my_info_address)
    TextView tvMyInfoAddress;
    @BindView(R.id.tv_my_info_email)
    TextView tvMyInfoEmail;
    @BindView(R.id.tv_my_info_qq)
    TextView tvMyInfoQq;
    @BindView(R.id.tv_my_info_wx)
    TextView tvMyInfoWx;
    @BindView(R.id.tv_my_info_area)
    TextView tvMyInfoArea;
    @BindView(R.id.tv_my_info_address_detail)
    TextView tvMyInfoAddressDetail;
    @BindView(R.id.tv_my_info_ssjg)
    TextView tvMyInfoSsjg;
    private UserInfoBean bean2;

    private String city_id;
    private String picUrl;

    private WinCameraDialog cameraDialog;
    private PhoneCameraUtil cameraUtil;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_info;
    }

    @Override
    public void initView() {
        setCenterTitle("个人资料");
    }

    @Override
    public void initData() {

    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.ll_my_info_avatar, R.id.tv_my_info_phone, R.id.tv_my_info_area})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_info_avatar:
                winSelectPic();
                break;
            case R.id.tv_my_info_phone:
                Intent intent = new Intent(this,ModifyPhoneActivity.class);
                startActivityForResult(intent,100);
                break;
            case R.id.tv_my_info_area:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserInfo();
    }

    //获取用户信息
    private void getUserInfo() {
        if (TextUtils.isEmpty(BaseApp.token)) return;
        RetrofitClient.getInstance().createApi().getUserInfo(BaseApp.token)
                .compose(RxUtils.<HttpResult<UserInfoBean>>io_main())
                .subscribe(new BaseObjObserver<UserInfoBean>(activity) {
                    @Override
                    protected void onHandleSuccess(UserInfoBean bean) {
                        bean2 = bean;
                        setUserData(bean);
                    }
                });
    }

    private void setUserData(UserInfoBean bean) {
        Glide.with(activity).load(bean.getAvatar()).error(R.mipmap.avatar_default).into(imgMyInfoAvatar);
        tvMyInfoEmail.setText(bean.getEmail());
        tvMyInfoName.setText(bean.getUser_nickname());
        for (ConfigBean.NatinalListBean natinalListBean : BaseApp.config.getNatinal_list()) {
            if (natinalListBean.getId() == bean.getNatinal()) {
                tvMyInfoMinzu.setText(natinalListBean.getName());
            }
        }

        tvMyInfoNo.setText(bean.getId_no());
        tvMyInfoPhone.setText(bean.getMobile());
        tvMyInfoZzmm.setText(bean.getPolital_status_text());//政治面貌
        tvMyInfoZgxl.setText(bean.getEducation_text());//学历
        tvMyInfoJob.setText(bean.getJob_text());//职业
        tvMyInfoAddress.setText(bean.getAddress());//通讯地址
        tvMyInfoEmail.setText(bean.getEmail());//邮箱
        tvMyInfoQq.setText(bean.getQq());//qq
        tvMyInfoWx.setText(bean.getWechat());//微信
        tvMyInfoArea.setText(bean.getCity_text());
        tvMyInfoAddressDetail.setText(bean.getWorker_address());
        tvMyInfoSsjg.setText("所属机构");
        tvMyInfoSex.setText(bean.getSex_text());
    }

    private void winSelectPic() {
        if (cameraUtil == null) {
            cameraUtil = new PhoneCameraUtil(this, this);
        }
        if (cameraDialog == null) {
            cameraDialog = new WinCameraDialog(getActivity());
            cameraDialog.setOnOperateListener(new OnDyClickListener() {
                @Override
                public void onClick(View v, int operate) {
                    Log.i("sun", "标志==" + operate);
                    if (operate == 1) {
                        cameraUtil.getImageCameraPermission();
                        //                      cameraUtil.getImageCamera();
                    } else if (operate == 2) {
                        cameraUtil.getImagePicture();
                    }
                }
            });
        }
        cameraDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == getActivity().RESULT_OK && requestCode == PhoneCameraUtil.PICK_FROM_CAMERA) {
            try {
                String picUrl2 = "";
                if (data != null)
                    PhoneCameraUtil.imageCaptureUri = data.getData();
                Log.i("sun", "结果==" + PhoneCameraUtil.imageCaptureUri);
                if (PhoneCameraUtil.imageCaptureUri != null) {
                    if (Build.VERSION.SDK_INT >= 24) {
                        String ss = Environment.getExternalStorageDirectory().getPath();
                        picUrl2 = PhoneCameraUtil.imageCaptureUri.getPath().replace("download", ss);
                    } else {
                        picUrl2 = PhoneCameraUtil.imageCaptureUri.getPath();
                    }
                    Log.i("sun", "结果==" + picUrl2);
                    Glide.with(this).load(picUrl2).error(R.mipmap.avatar_default).into(imgMyInfoAvatar);
                    postHead(picUrl2);
                } else {
                    ToastUtils.showToast("路径获取失败");
                }
            } catch (Exception e) {
                Toast.makeText(getActivity(), "获取失败", Toast.LENGTH_SHORT).show();
                Log.i("sun", "拍照异常==" + e.toString());
                e.printStackTrace();
            }
        } else if (resultCode == getActivity().RESULT_OK && requestCode == PhoneCameraUtil.PICK_FROM_FILE) {
            try {
                if (data != null) {
                    PhoneCameraUtil.imageCaptureUri = data.getData();
                    String imgFileUrl = cameraUtil.getPath(PhoneCameraUtil.imageCaptureUri);
                    if (!TextUtils.isEmpty(imgFileUrl)) {
                        PhoneCameraUtil.imageCaptureUri = null;
                        Log.i("sun", "路经==" + imgFileUrl);
                        Glide.with(this).load(imgFileUrl).error(R.mipmap.avatar_default).into(imgMyInfoAvatar);
                        postHead(imgFileUrl);
                    } else {
                        ToastUtils.showToast("图片路径获取失败");
                    }

                }
            } catch (Exception e) {
                Toast.makeText(getActivity(), "获取失败", Toast.LENGTH_SHORT).show();
                Log.i("sun", "相册异常==" + e.toString());
                e.printStackTrace();
            }
        }else if(requestCode == 100&&requestCode == 100){
            getUserInfo();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 更新用户个人信息
     */
    private void upData(String path, String city_id) {
        RetrofitClient.getInstance().createApi().upDatePerson(BaseApp.token, path, bean2.getUser_nickname(),
                bean2.getSex() + "", bean2.getNatinal() + "", bean2.getId_no(), city_id)
                .compose(RxUtils.<HttpResult<CommitBean>>io_main())
                .subscribe(new BaseObjObserver<CommitBean>(this, "更新个人信息") {
                    @Override
                    protected void onHandleSuccess(CommitBean commitBean) {
                        showToastMsg("个人信息更新成功");
                        setResult(100);
                        getUserInfo();
                    }
                });
    }

    /**
     * 上传头像
     */
    private void postHead(String head) {
        String url = AppConfig.BASE_URL + "/api/file/upload";
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        File f = new File(head);
        if (f != null) {
            builder.addFormDataPart("file", f.getName(), RequestBody.create(MediaType.parse("image/*; charset=utf-8"), f));
        }
        MultipartBody requestBody = builder.build();
        //构建请求
        Request request = new Request.Builder()
                .url(url)//地址
                .post(requestBody)//添加请求体
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("sun", "失败==" + e);
                System.out.println("上传失败:e.getLocalizedMessage() = " + e.getLocalizedMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String result = response.body().string();
                Log.i("sun", "上传返回result==" + result);

                UploadBean uploadBean = new Gson().fromJson(result, UploadBean.class);
                try {
                    if (uploadBean.getCode() == 200) {
                        picUrl = uploadBean.getData().getFile().getUrl();
                        upData(picUrl, bean2.getCity_id() + "");
                    }
                }catch (Exception e){
                    Log.i("sun","异常 == "+e);
                }

            }
        });
    }

}
