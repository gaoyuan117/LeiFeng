package com.jzbwlkj.leifeng.ui.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.utils.DistanceUtil;
import com.jzbwlkj.leifeng.BaseApp;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.base.BaseActivity;
import com.jzbwlkj.leifeng.retrofit.BaseObjObserver;
import com.jzbwlkj.leifeng.retrofit.CommonBean;
import com.jzbwlkj.leifeng.retrofit.HttpResult;
import com.jzbwlkj.leifeng.retrofit.RetrofitClient;
import com.jzbwlkj.leifeng.retrofit.RxUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignActivity extends BaseActivity {

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
    @BindView(R.id.baiduMap)
    MapView baiduMap;
    @BindView(R.id.tv_qiandao)
    TextView tvQiandao;
    @BindView(R.id.tv_jieshu)
    TextView tvJieshu;

    private BaiduMap mBaiduMap;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private LatLng latLng;
    private int flag ;
    private int distence;//签到范围
    private long signEndTime;//签到截止时间
    private int id;
    private double lat;
    private double lng;
    /**
     * 签到的时候需要活动地点的经纬度，和签到范围，至于用户的位置是否在签到范围，需要计算，就是看是在后台计算还是在手机端计算
     *
     * @return
     */
    @Override
    public int getLayoutId() {
        return R.layout.fragment_sign;
    }

    @Override
    public void initData() {
        id = getIntent().getIntExtra("id",0);
        String ss = getIntent().getStringExtra("dis");
        if(!TextUtils.isEmpty(ss)){
            distence = Integer.parseInt(ss);
        }
        lat = getIntent().getDoubleExtra("lat",0);
        lng = getIntent().getDoubleExtra("lng",0);

    }

    @Override
    public void initView() {
        centerTitleTv.setText("签到");
        mBaiduMap = baiduMap.getMap();
        mLocationClient = new LocationClient(getApplicationContext());
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);
        initBaiDuMap();
        mLocationClient.start();
    }


    @Override
    public void configViews() {

    }


    @OnClick({R.id.iv_back, R.id.tv_qiandao, R.id.tv_jieshu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_qiandao:
//                BaseApp.signStatus = 1;
                double dd = getjuli(new LatLng(lat,lng),latLng);
                if(distence>dd){
                    showToastMsg("您当前还不在签到范围");
                    return;
                }
                if(System.currentTimeMillis()/1000>signEndTime){
                    showToastMsg("您已过了签到时间");
                    return;
                }
                flag = 1;
                sign(latLng);
                break;
            case R.id.tv_jieshu:
//                BaseApp.signStatus = 2;
                flag = 2;
                sign(latLng);
                break;
        }
    }

    /**
     * 配置定位SDK参数
     */
    public void initBaiDuMap() {
        LocationClientOption option = new LocationClientOption();

        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //可选，设置定位模式，默认高精度
        //LocationMode.Hight_Accuracy：高精度；
        //LocationMode. Battery_Saving：低功耗；
        //LocationMode. Device_Sensors：仅使用设备；

        option.setCoorType("bd09ll");
        //可选，设置返回经纬度坐标类型，默认gcj02
        //gcj02：国测局坐标；
        //bd09ll：百度经纬度坐标；
        //bd09：百度墨卡托坐标；
        //海外地区定位，无需设置坐标类型，统一返回wgs84类型坐标

        option.setScanSpan(0);//每间隔20s定位一次
        //可选，设置发起定位请求的间隔，int类型，单位ms
        //如果设置为0，则代表单次定位，即仅定位一次，默认为0
        //如果设置非0，需设置1000ms以上才有效

        option.setOpenGps(true);
        //可选，设置是否使用gps，默认false
        //使用高精度和仅用设备两种定位模式的，参数必须设置为true

        option.setLocationNotify(true);
        //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false

        option.setIgnoreKillProcess(false);
        //可选，定位SDK内部是一个service，并放到了独立进程。
        //设置是否在stop的时候杀死这个进程，默认（建议）不杀死，即setIgnoreKillProcess(true)

        option.SetIgnoreCacheException(false);
        //可选，设置是否收集Crash信息，默认收集，即参数为false

        option.setWifiCacheTimeOut(5 * 60 * 1000);
        //可选，7.2版本新增能力
        //如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位

        option.setEnableSimulateGps(false);
        //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false

        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true

        mLocationClient.setLocOption(option);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取经纬度相关（常用）的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            double latitude = location.getLatitude();    //获取纬度信息
            double longitude = location.getLongitude();    //获取经度信息
            float radius = location.getRadius();    //获取定位精度，默认值为0.0f
            //获取经纬度坐标类型，以LocationClientOption中设置过的坐标类型为准
            String coorType = location.getCoorType();
            //获取定位类型、定位错误返回码，具体信息可参照类参考中BDLocation类中的说明
            int errorCode = location.getLocType();
            BaseApp.address = location.getAddrStr();
            Log.i("sun", BaseApp.address + "==" + 1);
            latLng = new LatLng(latitude, longitude);
            drawPoint(latLng);
            drawEnd(latLng);
        }
    }

    /**
     * 画出用户的当前位置
     */
    private void drawPoint(LatLng location) {
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.weizhi);
        OverlayOptions option = new MarkerOptions()
                .position(location)
                .icon(bitmap);
        Marker marker = (Marker) mBaiduMap.addOverlay(option);
        marker.setTitle("起点");
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(location);
        mBaiduMap.setMapStatus(u);
    }

    /**
     * 画出目的地，闪烁效果  ，画出签到范围
     */
    private void drawEnd(LatLng end) {
//        ArrayList<BitmapDescriptor> giflist = new ArrayList<BitmapDescriptor>();
//        BitmapDescriptor bitmap2 = BitmapDescriptorFactory
//                .fromResource(R.mipmap.kac_xiao);
//        BitmapDescriptor bitmap1 = BitmapDescriptorFactory
//                .fromResource(R.mipmap.kac);
//        BitmapDescriptor bitmap3 = BitmapDescriptorFactory
//                .fromResource(R.mipmap.kac_da);
//        giflist.add(bitmap1);
//        giflist.add(bitmap2);
//        giflist.add(bitmap3);
//
//        /**
//         * OverlayOptions ooD = new MarkerOptions().position(pt).icons(giflist)
//         .zIndex(0).period(10);
//         */
//        OverlayOptions option2 = new MarkerOptions()
//                .position(end)
//                .icons(giflist)
//                .zIndex(0)
//                .period(20);
//        Marker marker2 = (Marker) mBaiduMap.addOverlay(option2);
//        marker2.setTitle("目的地");
//        MapStatusUpdate u2 = MapStatusUpdateFactory.newLatLng(end);
//        mBaiduMap.setMapStatus(u2);


//        OverlayOptions ooCircle = new CircleOptions().fillColor(0x66f4c6c9)
//                .center(end).stroke(new Stroke(0, 0x66f4c6c9))
//                .radius(300);
//        mBaiduMap.addOverlay(ooCircle);
    }

    private void sign(LatLng ll){
        RetrofitClient.getInstance().createApi().signProject(BaseApp.token,String.valueOf(id),String.valueOf(ll.latitude),String.valueOf(ll.longitude))
                .compose(RxUtils.<HttpResult<CommonBean>>io_main())
                .subscribe(new BaseObjObserver<CommonBean>(this,"签到") {
                    @Override
                    protected void onHandleSuccess(CommonBean commonBean) {
                        if(flag == 1){
                            showToastMsg("签到成功");
                        }else{
                            showToastMsg("签退成功");
                        }
                        finish();
                    }
                });


    }

    /**
     * 需要传活动得位置和用户当前的位置的点
     * @param point1 活动位置
     * @param point2  用户位置
     * @return  返回两者之间的距离  类型为double
     */
    private double getjuli(LatLng point1,LatLng point2){
        return DistanceUtil. getDistance(point1, point1);
    }
}
