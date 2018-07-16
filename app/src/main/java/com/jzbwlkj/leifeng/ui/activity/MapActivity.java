package com.jzbwlkj.leifeng.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.jzbwlkj.leifeng.R;
import com.jzbwlkj.leifeng.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapActivity extends Activity {

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
    @BindView(R.id.mapView)
    MapView mapView;
    private double lat = 0;
    private double lng = 0;

    private View view;
    private TextView tvMessage;
    private TextView tvCancel;
    private TextView tvOk;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        initDialog();
        centerTitleTv.setText("百度地图");
        LatLng ll = new LatLng(36.38933628916225,119.76170429907167);
        drawPoint(ll);
        mapView.getMap().setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                lat = latLng.latitude;
                lng = latLng.longitude;
                if(lat == 0||lng == 0){
                    ToastUtils.showToast("您选取的位置失效，请重新选择");
                    return;
                }
                dialog.show();
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                String name =  mapPoi.getName();
                Log.i("sun","位置 == "+name);
                lat = mapPoi.getPosition().latitude;
                lng = mapPoi.getPosition().longitude;
 //               Log.i("sun","位置 == "+lat+"=="+lng);
                if(lat != 0&&lng != 0){
                    dialog.show();
                }
                return false;
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    /**
     * 初始化弹框
     */
    private void initDialog(){
        view = LayoutInflater.from(this).inflate(R.layout.dialog_common,null);
        tvMessage = view.findViewById(R.id.tv_message);
        tvCancel = view.findViewById(R.id.tv_no);
        tvOk = view.findViewById(R.id.tv_yes);
        tvMessage.setText("您是否确认当前位置为活动地点");
        tvOk.setText("确定");
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("lat",lat);
                intent.putExtra("lng",lng);
                setResult(100,intent);
                dialog.dismiss();
                finish();
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog = new Dialog(this,R.style.wx_dialog);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
    }


    //119.74254999999994
   // 纬度:36.37617
    /**
     * 画出用户的当前位置
     */
    private void drawPoint(LatLng location) {
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.weizhi);
        OverlayOptions option = new MarkerOptions()
                .position(location)
                .icon(bitmap);
        Marker marker = (Marker) mapView.getMap().addOverlay(option);
        marker.setTitle("起点");
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(location);
        mapView.getMap().setMapStatus(u);
    }
}
