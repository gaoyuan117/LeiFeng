package com.jzbwlkj.leifeng.ui.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;
import com.jzbwlkj.leifeng.ui.bean.UserInfoBean;
import com.jzbwlkj.leifeng.utils.FormatUtils;
import com.jzbwlkj.leifeng.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VoluntaryCardActivity extends BaseActivity {

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
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_person_id)
    TextView tvPersonId;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_time_hour)
    TextView tvTimeHour;
    @BindView(R.id.tv_shenfen)
    TextView tvShenfen;
    @BindView(R.id.tv_grade)
    TextView tvGrade;
    @BindView(R.id.tv_register_time)
    TextView tvRegisterTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_creat)
    TextView tvCreat;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    @BindView(R.id.iv_qrcode)
    ImageView ivQrcode;
    private String id;//这个是从首页中的个人排行中传过来的

    @Override
    public int getLayoutId() {
        id = getIntent().getStringExtra("id");
        return R.layout.activity_voluntary_card;
    }

    @Override
    public void initView() {
        centerTitleTv.setText("志愿名片");
    }

    @Override
    public void initData() {
        getUserInfo();
    }

    @Override
    public void configViews() {

    }

    @OnClick({R.id.iv_back, R.id.tv_creat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_creat:
                int  ss = (int) ScreenUtils.dpToPx(80f);
                final Bitmap qq = creatCode(ss,ss,BaseApp.config.getApp_url_android());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ivQrcode.setImageBitmap(qq);
                    }
                },300);
                break;
        }
    }

    //获取用户信息
    private void getUserInfo() {
        if (TextUtils.isEmpty(BaseApp.token)) return;
        if(BaseApp.type == 1){
            RetrofitClient.getInstance().createApi().getUserInfo(BaseApp.token, id)
                    .compose(RxUtils.<HttpResult<UserInfoBean>>io_main())
                    .subscribe(new BaseObjObserver<UserInfoBean>(activity) {
                        @Override
                        protected void onHandleSuccess(UserInfoBean bean) {
                            setUserData(bean);
                        }
                    });
        }else{
            RetrofitClient.getInstance().createApi().getUserInfoT(BaseApp.token, id)
                    .compose(RxUtils.<HttpResult<UserInfoBean>>io_main())
                    .subscribe(new BaseObjObserver<UserInfoBean>(activity) {
                        @Override
                        protected void onHandleSuccess(UserInfoBean bean) {
                            setUserData(bean);
                        }
                    });
        }

    }

    private void setUserData(UserInfoBean bean) {
        Glide.with(this).load(bean.getAvatar()).error(R.mipmap.avatar_default).into(ivHead);
        tvName.setText(bean.getUser_nickname());
        tvNumber.setText(bean.getUser_login());
        tvPersonId.setText(bean.getId_no());
        tvPhone.setText(bean.getMobile());
        tvShenfen.setText(bean.getPolital_status_text());
        tvGrade.setText(bean.getLevel() + "级");
        tvTimeHour.setText(bean.getService_hour() + "小时");
        tvRegisterTime.setText(FormatUtils.formatTime(bean.getCreate_time()));
        tvAddress.setText(bean.getCity_text());
    }

    /**
     * 生成二维码
     */
    private Bitmap creatCode(int width, int height, String str) {
        Bitmap bitmap = null;
        BitMatrix result = null;
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            result = multiFormatWriter.encode(str, BarcodeFormat.QR_CODE, width, height);
            int[] pixels = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (result.get(j, i)) {
                        pixels[i * width + j] = 0x00000000;
                    } else {
                        pixels[i * width + j] = 0xffffffff;
                    }
                }
            }
            bitmap = Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException iae) { // ?
            return null;
        }
        return bitmap;
    }

}
