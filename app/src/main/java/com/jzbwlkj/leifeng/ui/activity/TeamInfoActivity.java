package com.jzbwlkj.leifeng.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
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
import com.jzbwlkj.leifeng.retrofit.CommonBean;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.bean.CommitBean;
import com.jzbwlkj.leifeng.ui.bean.MySelfModel;
import com.jzbwlkj.leifeng.ui.bean.TeamInfoBean;
import com.jzbwlkj.leifeng.ui.bean.TeamListBean;
import com.jzbwlkj.leifeng.ui.bean.UploadBean;
import com.jzbwlkj.leifeng.utils.ToastUtils;
import com.jzbwlkj.leifeng.view.OnDyClickListener;
import com.jzbwlkj.leifeng.view.PhoneCameraUtil;
import com.jzbwlkj.leifeng.view.WinCameraDialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TeamInfoActivity extends BaseActivity {

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
    @BindView(R.id.team_pic)
    CircleImageView teamPic;
    @BindView(R.id.fl_head)
    FrameLayout flHead;
    @BindView(R.id.team_name)
    TextView teamName;
    @BindView(R.id.unit_name)
    TextView unitName;
    @BindView(R.id.link_name)
    TextView linkName;
    @BindView(R.id.link_phone)
    TextView linkPhone;
    @BindView(R.id.fl_link_phone)
    FrameLayout flLinkPhone;
    @BindView(R.id.manager_name)
    TextView managerName;
    @BindView(R.id.manager_phone)
    TextView managerPhone;
    @BindView(R.id.fl_manager_phone)
    FrameLayout flManagerPhone;
    @BindView(R.id.team_jieshao)
    TextView teamJieshao;
    @BindView(R.id.fl_team_info)
    FrameLayout flTeamInfo;

    private WinCameraDialog cameraDialog;
    private PhoneCameraUtil cameraUtil;
    private int parentId;//上级单位的ID
    private String picUrl;
    private TeamInfoBean teambean;
    private Map<String, Object> map = new HashMap<>();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 66://修改头像资料
                    modifyTeamInfo();
                    break;
                case 88:
                    setResult(100);
                    getTeamInfo();
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        return R.layout.activity_team_info;
    }

    @Override
    public void initView() {
        centerTitleTv.setText("队伍资料");
    }

    @Override
    public void initData() {
        getTeamInfo();
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.fl_head, R.id.fl_link_phone, R.id.fl_manager_phone, R.id.fl_team_info})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.fl_head:
                winSelectPic();
                break;
            case R.id.fl_link_phone:
                jumpActivity(98);
                break;
            case R.id.fl_manager_phone:
                jumpActivity(99);
                break;
            case R.id.fl_team_info:
                Intent team = new Intent(this, TeamActivity.class);
                team.putExtra("id", BaseApp.team_id);
                startActivity(team);
                break;
        }
    }

    /**
     * 跳转到修改手机号的界面，根据type来决定是修改联系人电话，还是负责人电话
     *
     * @param type
     */
    private void jumpActivity(int type) {
        Intent intent = new Intent(this, ModifyTeamPhoneActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("bean",teambean);
        intent.putExtra("bundle",bundle);
        intent.putExtra("type", type);
        startActivityForResult(intent, type);
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
                    File file = new File(picUrl2);
                    Log.i("sun", "长度==" + file.length());
                    if (file.length() > 1024 * 1024) {
                        picUrl2 = saveBitmapToFile(file, picUrl2);
                    }
                    Glide.with(this).load(picUrl2).error(R.mipmap.avatar_default).into(teamPic);
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
                    File file = new File(imgFileUrl);
                    Log.i("sun", "长度==" + file.length());
                    if (file.length() > (1024 * 1024)) {
                        imgFileUrl = saveBitmapToFile(file, imgFileUrl);
                    }
                    if (!TextUtils.isEmpty(imgFileUrl)) {
                        PhoneCameraUtil.imageCaptureUri = null;
                        Log.i("sun", "路经==" + imgFileUrl);
                        Glide.with(this).load(imgFileUrl).error(R.mipmap.avatar_default).into(teamPic);
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
        } else if (requestCode == 98 && requestCode == 100) {//联系人电话
            getTeamInfo();

        } else if (requestCode == 99 && requestCode == 100) {//管理员电话
            getTeamInfo();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * 压缩图片
     *
     * @param file    要压缩的文件
     * @param newpath 压缩后的保存路径
     * @return 返回压缩文件路径
     */
    public static String saveBitmapToFile(File file, String newpath) {
        try {
            // BitmapFactory options to downsize the image
            BitmapFactory.Options o = new BitmapFactory.Options();
            o.inJustDecodeBounds = true;
            o.inSampleSize = 6;
            // factor of downsizing the image

            FileInputStream inputStream = new FileInputStream(file);
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o);
            inputStream.close();

            // The new size we want to scale to
            final int REQUIRED_SIZE = 75;

            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE) {
                scale *= 2;
            }

            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            inputStream = new FileInputStream(file);

            Bitmap selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2);
            inputStream.close();

            // here i override the original image file
//            file.createNewFile();
//
//
//            FileOutputStream outputStream = new FileOutputStream(file);
//
//            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100 , outputStream);

            File aa = new File(newpath);
            FileOutputStream outputStream = new FileOutputStream(aa);

            //choose another format if PNG doesn't suit you

            selectedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);


            String filepath = aa.getAbsolutePath();
            Log.e("getAbsolutePath", aa.getAbsolutePath());

            return filepath;
        } catch (Exception e) {
            return null;
        }
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
                        map.put("pic",picUrl);
                        handler.sendEmptyMessage(66);
                    }
                } catch (Exception e) {
                    Log.i("sun", "异常 == " + e);
                }

            }
        });
    }

    /**
     * 获取队伍信息
     */
    private void getTeamInfo() {
        if (TextUtils.isEmpty(BaseApp.token)) return;
        RetrofitClient.getInstance().createApi().getTeamInfo(BaseApp.team_id + "", BaseApp.token)
                .compose(RxUtils.<HttpResult<TeamInfoBean>>io_main())
                .subscribe(new BaseObjObserver<TeamInfoBean>(activity) {
                    @Override
                    protected void onHandleSuccess(TeamInfoBean bean) {
                        Glide.with(activity).load(bean.getPic()).error(R.mipmap.avatar_default).into(teamPic);
                        teamName.setText(bean.getTeam_name());
                        managerName.setText(bean.getManager());
                        managerPhone.setText(bean.getManager_mobile());
                        linkName.setText(bean.getContact());
                        parentId = bean.getParent_id();
                        linkPhone.setText(bean.getContact_mobile());
                        teambean = bean;
                        map.put("team_token", BaseApp.token);
                        map.put("team_name", bean.getTeam_name());
                        map.put("pic", bean.getPic());
                        map.put("contact", bean.getContact());
                        map.put("manager", bean.getManager());
                        map.put("manager_mobile",bean.getManager_mobile());
                        map.put("desc",bean.getDesc());
                        getTeamList();
                    }
                });
    }

    private void modifyTeamInfo() {
        RetrofitClient.getInstance().createApi().modifyTeam(map)
                .compose(RxUtils.<HttpResult<CommonBean>>io_main())
                .subscribe(new BaseObjObserver<CommonBean>(this, "更新队伍信息") {
                    @Override
                    protected void onHandleSuccess(CommonBean commitBean) {
                        showToastMsg("组织信息更新成功");
                        handler.sendEmptyMessage(88);
                    }
                });
    }

    /**
     * 获取队伍
     */
    private void getTeamList() {
        RetrofitClient.getInstance().createApi().getTeamList("")
                .compose(RxUtils.<HttpResult<List<TeamListBean>>>io_main())
                .subscribe(new BaseObjObserver<List<TeamListBean>>(activity) {
                    @Override
                    protected void onHandleSuccess(List<TeamListBean> list) {
                        if (isEmpty(list)) return;
                        //所属机构
                        for (int i = 0; i < list.size(); i++) {
                            TeamListBean teamListBean = list.get(i);
                            if(teamListBean.getId() == parentId){
                                unitName.setText(teamListBean.getTeam_name());
                            }
                        }
                    }
                });
    }

}
