package com.jzbwlkj.leifeng.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
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
import com.jzbwlkj.leifeng.ui.adapter.ListViewAdapter;
import com.jzbwlkj.leifeng.ui.bean.CommitBean;
import com.jzbwlkj.leifeng.ui.bean.ConfigBean;
import com.jzbwlkj.leifeng.ui.bean.MySelfModel;
import com.jzbwlkj.leifeng.ui.bean.UploadBean;
import com.jzbwlkj.leifeng.ui.bean.UserInfoBean;
import com.jzbwlkj.leifeng.utils.ToastUtils;
import com.jzbwlkj.leifeng.view.OnDyClickListener;
import com.jzbwlkj.leifeng.view.PhoneCameraUtil;
import com.jzbwlkj.leifeng.view.WinCameraDialog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
    @BindView(R.id.tv_button)
    TextView tvButton;

    private View viewType;
    private ListView lvContent;
    private PopupWindow popType,popJob;

    private String unitid;//单位Id
    private ListViewAdapter lvAdapter;
    private List<MySelfModel> showList = new ArrayList<>();
    private List<MySelfModel> jobList = new ArrayList<>();
    private UserInfoBean bean2;

    private String city_id;
    private String picUrl;

    private WinCameraDialog cameraDialog;
    private PhoneCameraUtil cameraUtil;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 66:
                    upData(picUrl, bean2.getCity_id() + "");
                    break;

                case 88:
                    setResult(100);
                    getUserInfo();
                    break;
            }
        }
    };

    @Override
    public int getLayoutId() {
        initPop();
        return R.layout.activity_my_info;
    }

    @Override
    public void initView() {
        setCenterTitle("个人资料");
        tvRightText.setText("");
    }

    @Override
    public void initData() {
        if (BaseApp.config.getCity_list() != null && BaseApp.config.getCity_list().size() > 0) {
            for (ConfigBean.CityListBean cityListBean : BaseApp.config.getCity_list()) {
                MySelfModel model = new MySelfModel();
                model.setPid(cityListBean.getPid() + "");
                model.setId(cityListBean.getId() + "");
                model.setName(cityListBean.getName());
                model.setSelected(false);
                showList.add(model);
            }
            lvAdapter.notifyDataSetChanged();
        }
        getUserInfo();
    }


    @Override
    public void configViews() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        getUserInfo();
    }

    @OnClick({R.id.ll_my_info_avatar, R.id.tv_my_info_job, R.id.tv_my_info_phone, R.id.tv_my_info_area, R.id.tv_button, R.id.ll_my_info_address, R.id.ll_my_info_email, R.id.ll_my_info_address_detail, R.id.ll_my_info_qq, R.id.ll_my_info_wx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_my_info_avatar:
                winSelectPic();
                break;
            case R.id.tv_my_info_phone:
                Intent intent = new Intent(this, ModifyPhoneActivity.class);
                startActivityForResult(intent, 100);
                break;
            case R.id.ll_my_info_address_detail:
                startActivity(new Intent(this, ModifyUserInfoActivity.class).putExtra("key", "worker_address"));
                break;
            case R.id.ll_my_info_wx:
                startActivity(new Intent(this, ModifyUserInfoActivity.class).putExtra("key", "wechat"));
                break;
            case R.id.ll_my_info_address:
                startActivity(new Intent(this, ModifyUserInfoActivity.class).putExtra("key", "address"));
                break;
            case R.id.ll_my_info_qq:
                startActivity(new Intent(this, ModifyUserInfoActivity.class).putExtra("key", "qq"));
                break;
            case R.id.ll_my_info_email:
                startActivity(new Intent(this, ModifyUserInfoActivity.class).putExtra("key", "email"));
                break;
            case R.id.tv_my_info_area:
                popType.setWidth(tvMyInfoArea.getMeasuredWidth() + 100);
                if (showList.size() > 6) {
                    popType.setHeight(500);
                } else {
                    popType.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
                }
                popType.showAsDropDown(tvMyInfoArea, -12, 40);
                break;
            case R.id.tv_my_info_job:
                initPop2();
                break;
            case R.id.tv_button:
                Intent become = new Intent(this, BecomeZhuanActivity.class);
                startActivity(become);
                break;
        }
    }


    //获取用户信息
    private void getUserInfo() {
        if (TextUtils.isEmpty(BaseApp.token)) return;
        RetrofitClient.getInstance().createApi().getUserInfo(BaseApp.token, null)
                .compose(RxUtils.<HttpResult<UserInfoBean>>io_main())
                .subscribe(new BaseObjObserver<UserInfoBean>(activity) {
                    @Override
                    protected void onHandleSuccess(UserInfoBean bean) {
                        handler.removeCallbacksAndMessages(null);
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
        // 0 :'党员', 1: '团员',2:'其他'
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
        if (bean.getIs_personnel() == 0) {//0代表志愿者   1  专业志愿者
            tvButton.setVisibility(View.VISIBLE);
        } else {
            tvButton.setVisibility(View.GONE);
        }
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
                    File file = new File(picUrl2);
                    Log.i("sun", "长度==" + file.length());
                    if (file.length() > 1024 * 1024) {
                        picUrl2 = saveBitmapToFile(file, picUrl2);
                    }
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
                    File file = new File(imgFileUrl);
                    Log.i("sun", "长度==" + file.length());
                    if (file.length() > (1024 * 1024)) {
                        imgFileUrl = saveBitmapToFile(file, imgFileUrl);
                    }
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
        } else if (requestCode == 100 && requestCode == 100) {
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
                        handler.sendEmptyMessage(88);
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
                        handler.sendEmptyMessage(66);
                    }
                } catch (Exception e) {
                    Log.i("sun", "异常 == " + e);
                }

            }
        });
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
     * 初始化popupwindow
     */
    private void initPop() {
        viewType = LayoutInflater.from(this).inflate(R.layout.pop_list, null);
        lvContent = viewType.findViewById(R.id.lv_content);
        lvAdapter = new ListViewAdapter(showList, this);
        lvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MySelfModel model = showList.get(position);
                for (MySelfModel model1 : showList) {
                    if (TextUtils.equals(model.getName(), model1.getName())) {
                        model1.setSelected(true);
                    } else {
                        model1.setSelected(false);
                    }
                }
                lvAdapter.notifyDataSetChanged();
                popType.dismiss();
                tvMyInfoArea.setText(model.getName());
                unitid = model.getId();
                upData(null, unitid);
            }
        });
        lvContent.setAdapter(lvAdapter);
        popType = new PopupWindow(this);
        popType.setFocusable(true);
        popType.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景防止出现黑色边框
        popType.setFocusable(true);
        popType.setContentView(viewType);
    }


    private void initPop2() {
        List<ConfigBean.JobListBean> job_list = BaseApp.config.getJob_list();
        jobList.clear();
        for (int i = 0; i < job_list.size(); i++) {
            MySelfModel model = new MySelfModel();
            ConfigBean.JobListBean jobListBean = job_list.get(i);
            model.setName(jobListBean.getName());
            model.setId(jobListBean.getId() + "");
            jobList.add(model);
        }
        View viewType = LayoutInflater.from(this).inflate(R.layout.pop_list, null);
        ListView lvContent = viewType.findViewById(R.id.lv_content);
        final ListViewAdapter lvAdapter = new ListViewAdapter(jobList, this);
        lvContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MySelfModel model = jobList.get(position);
                for (MySelfModel model1 : jobList) {
                    if (TextUtils.equals(model.getName(), model1.getName())) {
                        model1.setSelected(true);
                    } else {
                        model1.setSelected(false);
                    }
                }
                lvAdapter.notifyDataSetChanged();
                popJob.dismiss();
                tvMyInfoJob.setText(model.getName());
                unitid = model.getId();
                updateJob(unitid);
            }
        });
        lvContent.setAdapter(lvAdapter);
         popJob = new PopupWindow(this);
        popJob.setFocusable(true);
        popJob.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景防止出现黑色边框
        popJob.setFocusable(true);
        popJob.setContentView(viewType);

        popJob.setWidth(tvMyInfoJob.getMeasuredWidth() + 100);
        if (showList.size() > 6) {
            popJob.setHeight(500);
        } else {
            popJob.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        popJob.showAsDropDown(tvMyInfoJob, -12, 40);
    }

    private void updateJob(String city_id) {
        Map<String, String> map = new HashMap<>();
        map.put("job", city_id);
        RetrofitClient.getInstance().createApi().upDatePerson(BaseApp.token, map)
                .compose(RxUtils.<HttpResult<CommitBean>>io_main())
                .subscribe(new BaseObjObserver<CommitBean>(this, "更新个人信息") {
                    @Override
                    protected void onHandleSuccess(CommitBean commitBean) {
                        showToastMsg("个人信息更新成功");
                        getUserInfo();
                    }
                });
    }


}
