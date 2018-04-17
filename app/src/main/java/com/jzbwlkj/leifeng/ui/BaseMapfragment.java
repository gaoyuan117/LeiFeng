package com.jzbwlkj.leifeng.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.overlayutil.OverlayManager;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.jzbwlkj.leifeng.AppConfig;
import com.jzbwlkj.leifeng.R;

import java.util.ArrayList;


/**
 * @Project_Name :DuDaohang
 * @package_Name :com.daohang.dudaohang
 * @AUTHOR :xzwzz@vip.qq.com
 * @DATE :2018/3/8  20:00
 */
public class BaseMapfragment extends Fragment implements OnGetRoutePlanResultListener {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private View rootView;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private static BaseMapfragment instance;

    private BitmapDescriptor trashFull = BitmapDescriptorFactory.fromResource(R.mipmap.trash_full);
    private BitmapDescriptor trashEmpty = BitmapDescriptorFactory.fromResource(R.mipmap.trash_empty);

    private ArrayList<Marker> fullMarkers, emptyMarkers;

    private MapListener mMapListener;

    private RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用
    private RouteLine route = null;
    private DrivingRouteResult nowResultdrive = null;
    private int nodeIndex = -1; // 节点索引,供浏览节点时使用
    private boolean hasShownDialogue = false;
    private OverlayManager routeOverlay = null;

    private boolean useDefaultIcon = false;

    public static BaseMapfragment getInstance() {
        if (instance == null) {
            instance = new BaseMapfragment();
        }
        return instance;
    }

    @SuppressLint("ValidFragment")
    private BaseMapfragment() {

    }

    @Override
    public View onCreateView(LayoutInflater var1, ViewGroup var2, Bundle var3) {
        rootView = var1.inflate(R.layout.fragment_map, var2, false);

        initView();
        initData();
        setlistener();

        return rootView;
    }

    private void setlistener() {
    }

    private void initView() {
        this.mMapView = rootView.findViewById(R.id.bmapView);
        this.mBaiduMap = mMapView.getMap();
    }

    private void initData() {
        fullMarkers = new ArrayList<>();
        emptyMarkers = new ArrayList<>();

        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);

        openLocation();

    }

    private void openLocation() {
        MyLocationConfiguration.LocationMode mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;//定位罗盘态
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory.fromResource(R.mipmap.car);

        MyLocationConfiguration config = new MyLocationConfiguration(mCurrentMode, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfiguration(config);

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 定位初始化
        mLocationClient = new LocationClient(getActivity().getApplicationContext());
        mLocationClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setOpenGps(true); // 打开gps
        option.setIgnoreKillProcess(false);
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        option.setEnableSimulateGps(false);

        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }

    //添加可倒垃圾桶位置
    private void addFullTrash(Double lat, Double lng) {
        LatLng llA = new LatLng(lat, lng);
        MarkerOptions ooA = new MarkerOptions().position(llA).icon(trashFull)
                .zIndex(9).draggable(true);
        // 掉下动画
        ooA.animateType(MarkerOptions.MarkerAnimateType.drop);
        Marker marker = (Marker) (mBaiduMap.addOverlay(ooA));
        fullMarkers.add(marker);
    }

    //添加空垃圾桶位置
    private void addEmpryTrash(Double lat, Double lng) {
        LatLng llA = new LatLng(lat, lng);
        MarkerOptions ooA = new MarkerOptions().position(llA).icon(trashEmpty)
                .zIndex(9).draggable(true);
        // 掉下动画
        ooA.animateType(MarkerOptions.MarkerAnimateType.drop);
        Marker marker = (Marker) (mBaiduMap.addOverlay(ooA));
        emptyMarkers.add(marker);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.mMapView.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.mMapView.onDestroy();
    }

    public void setMapListener(MapListener listener) {
        this.mMapListener = listener;
    }

    //------------------------------------------------定位-------------------------------------------------
    private double mCurrentLat = 0.0;
    private double mCurrentLon = 0.0;
    private float mCurrentAccracy;
    boolean isFirstLoc = true; // 是否首次定位
    private Double lastX = 0.0;
    private MyLocationData locData;
    private float mCurrentDirection;

    public void setmCurrent(float mCurrentDirection) {
        this.mCurrentDirection = mCurrentDirection;
        locData = new MyLocationData.Builder()
                .accuracy(mCurrentAccracy)
                // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(mCurrentDirection).latitude(mCurrentLat)
                .longitude(mCurrentLon).build();
        mBaiduMap.setMyLocationData(locData);
    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {
//        if (routeOverlay != null) {
//            routeOverlay.removeFromMap();
//        }
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(getActivity(), "抱歉，未找到结果", Toast.LENGTH_SHORT).show();
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            result.getSuggestAddrInfo();
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            nodeIndex = -1;
            if (result.getRouteLines().size() >= 1) {
//                nowResultdrive = result;
//                if (!hasShownDialogue) {
//                    MyTransitDlg myTransitDlg = new MyTransitDlg(MainActivity.this,
//                            result.getRouteLines(),
//                            RouteLineAdapter.Type.DRIVING_ROUTE);
//                    myTransitDlg.setOnDismissListener(dialog -> hasShownDialogue = false);
//                    myTransitDlg.setOnItemInDlgClickLinster(position -> {
//                        route = nowResultdrive.getRouteLines().get(position);
//                        DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);
//                        mBaiduMap.setOnMarkerClickListener(overlay);
//                        routeOverlay = overlay;
//                        overlay.setData(nowResultdrive.getRouteLines().get(position));
//                        overlay.addToMap();
//                        overlay.zoomToSpan();
//                    });
//                    myTransitDlg.show();
//                    hasShownDialogue = true;
//                }
//            } else if (result.getRouteLines().size() == 1) {
                route = result.getRouteLines().get(0);
                DrivingRouteOverlay overlay = new MyDrivingRouteOverlay(mBaiduMap);
                routeOverlay = overlay;
                mBaiduMap.setOnMarkerClickListener(overlay);
                overlay.setData(result.getRouteLines().get(0));
                overlay.addToMap();
                overlay.zoomToSpan();
            }
        }
    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null || mMapView == null) {
                return;
            }
            mCurrentLat = location.getLatitude();
            mCurrentLon = location.getLongitude();
            AppConfig.mCurrentLng = mCurrentLon;
            AppConfig.mCurrentLat = mCurrentLat;
            mCurrentAccracy = location.getRadius();
            locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(mCurrentDirection).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            mBaiduMap.setMyLocationData(locData);
            if (isFirstLoc) {
                isFirstLoc = false;
                LatLng ll = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatus.Builder builder = new MapStatus.Builder();
                builder.target(ll).zoom(18.0f);
                mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
            }
        }

        public void onReceivePoi(BDLocation poiLocation) {
        }
    }

    public interface MapListener {
//        void toNavigation(TaskBean.TrashBean bean);
    }

    //对历史记录进行规划
//    private void planTheRoute(List<TaskDetailBean> list) {
//        if (routeOverlay != null) {
//            routeOverlay.removeFromMap();
//        }
//
//        for (int i = 0; i < list.size(); i++) {
//            try {
//                LatLng star = new LatLng(list.get(i).getLat(), list.get(i).getLng());
//                LatLng end = new LatLng(list.get(i + 1).getLat(), list.get(i + 1).getLng());
//                PlanNode stNode = PlanNode.withLocation(star);
//                PlanNode enNode = PlanNode.withLocation(end);
//
//                mSearch.drivingSearch((new DrivingRoutePlanOption())
//                        .from(stNode).to(enNode));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    // 定制RouteOverly
    private class MyDrivingRouteOverlay extends DrivingRouteOverlay {

        public MyDrivingRouteOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public BitmapDescriptor getStartMarker() {
            if (true) {
                return BitmapDescriptorFactory.fromResource(R.mipmap.trash_empty);
            }
            return null;
        }

        @Override
        public BitmapDescriptor getTerminalMarker() {
            if (true) {
                return BitmapDescriptorFactory.fromResource(R.mipmap.trash_empty);
            }
            return null;
        }
    }

}
