package com.jzbwlkj.leifeng.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.jzbwlkj.leifeng.R;

/**
 * Created by lj on 2017/6/11.
 * 启动相机或者相册，
 */

public class WinCameraDialog {

    private Dialog dialog;
    //    private AlertDialog dialog;
    private OnDyClickListener listener;
    private Context context;
    public WinCameraDialog(Context context){
        this.context=context;
        initDialog();
    }

    public void setOnOperateListener(OnDyClickListener operateListener) {
        this.listener = operateListener;
    }

    public void show(){
        if (dialog==null){
            initDialog();
        }
        if (dialog.isShowing()){
            dialog.dismiss();
        }else{
            dialog.show();
        }
    }
    private void initDialog(){
        View win= LayoutInflater.from(context).inflate(R.layout.win_camera_dialog,null);
        dialog = new Dialog(context, R.style.setpicture_dailog_style);
        dialog.setCancelable(true);
        dialog.setContentView(win);
        dialog.setCanceledOnTouchOutside(true);
        win.findViewById(R.id.camera_open).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (listener!=null){
                    listener.onClick(view,1);
                }
            }
        });
        win.findViewById(R.id.camera_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (listener!=null){
                    listener.onClick(view,2);
                }
            }
        });
        win.findViewById(R.id.camera_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                if (listener!=null){
                    listener.onClick(view,4);
                }
            }
        });
    }
}
