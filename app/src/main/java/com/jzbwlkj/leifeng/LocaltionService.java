package com.jzbwlkj.leifeng;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.IBinder;

import com.jzbwlkj.leifeng.ui.BaseMapfragment;
import com.zhy.http.okhttp.OkHttpUtils;

public class LocaltionService extends Service implements SensorEventListener {
    private SensorManager mSensorManager;
    private double lastX = 0.0;
    private Runnable mRunnable;
    private Handler mHandler;

    public LocaltionService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mHandler = new Handler(getMainLooper());
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//获取传感器管理服务
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_UI);
        mRunnable = new Runnable() {
            @Override
            public void run() {
                mHandler.postDelayed(mRunnable, 5000);
            }
        };
        mHandler.post(mRunnable);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mHandler.removeCallbacks(mRunnable);
        OkHttpUtils.getInstance().cancelTag("sendLocationToServer");
        OkHttpUtils.getInstance().cancelTag("getMissionDetail");
        super.onDestroy();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        double x = event.values[0];
        if (Math.abs(x - lastX) > 1.0) {
            BaseMapfragment.getInstance().setmCurrent((int) x);
        }
        lastX = x;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
