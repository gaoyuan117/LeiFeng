package com.jzbwlkj.leifeng.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lj on 2017/6/9.
 * 获取手机相册图片14343
 */

public class PhoneCameraUtil {
    public static final int PICK_FROM_CAMERA = 1;
    public static final int CROP_FROM_CAMERA = 2;
    public static final int PICK_FROM_FILE = 3;//REQUEST_CODE_CAMERA
    public static Uri imageCaptureUri;
    private static final int MEDIA_TYPE_IMAGE = 1;

    private Activity mActivity;
    private Context mContext;
    public PhoneCameraUtil(Context context, Activity activity){
        this.mActivity=activity;
        this.mContext=context;
    }
    public void getImageCamera(){
        try {
            imageCaptureUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
            Log.i("sun","不为空=="+imageCaptureUri);
            if (TextUtils.isEmpty(imageCaptureUri.getPath())) {
                Log.i("sun","1");
                File mediaFile = new File(
                        getFileAddress(5,"leifeng", mActivity),"img" + System.currentTimeMillis() + ".jpg");
                if (!mediaFile.mkdirs()) {
                    mediaFile.createNewFile();
                }
                Log.i("sun","aaa=="+ Build.VERSION.SDK_INT);
                if (Build.VERSION.SDK_INT >= 24) {
                    Log.i("sun","hhh");
                    //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致 参数3 共享的文件
                    imageCaptureUri = FileProvider.getUriForFile(mContext, "com.jzbwlkj.leifeng.fileprovider", mediaFile);
                } else {
                    imageCaptureUri = Uri.fromFile(mediaFile);
                }
                imageCaptureUri = Uri.fromFile(mediaFile);
            }
            // 最后一个参数是文件夹的名称，可以随便起
//            File file=new File(Environment.getExternalStorageDirectory(),"ShangDuHui");
//            if(!file.exists()){
//                file.mkdir();
//            }
//            //这里将时间作为不同照片的名称
//            File output=new File(file,System.currentTimeMillis()+".jpg");
//
//            /**
//             * 如果该文件夹已经存在，则删除它，否则创建一个
//             */
//            try {
//                if (output.exists()) {
//                    output.delete();
//                }
//                output.createNewFile();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            /**
//             * 隐式打开拍照的Activity，并且传入CROP_PHOTO常量作为拍照结束后回调的标志
//             * 将文件转化为uri
//             */
//            imageCaptureUri = Uri.fromFile(output);
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageCaptureUri);
            mActivity.startActivityForResult(intent, PICK_FROM_CAMERA);
//            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取权限 Permission
     */
    public void getImageCameraPermission(){
        //判断版本
        if (Build.VERSION.SDK_INT >= 23) {
            //检查权限是否被授予：
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA);
            int hasExternalPermission = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                //申请权限
                if(hasExternalPermission != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(mActivity,new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 12);
                } else{
                    ActivityCompat.requestPermissions(mActivity,new String[]{Manifest.permission.CAMERA}, 10);
                }
            }else{
                if(hasExternalPermission != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(mActivity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 11);
                }else{
                    //权限已经被授予
                    Log.i("sun","调相机");
                    getImageCamera();
                }
            }
        } else {
            getImageCamera();
        }
    }
    //从图库获取图片
    public void getImagePicture() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(mActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(mActivity,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
            } else {
                openPictureFiles();
            }
        }else{
            openPictureFiles();
        }
    }
    //去手机相册
    private void openPictureFiles(){
        // pick from file
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivity.startActivityForResult(
                Intent.createChooser(intent, "Complete action using"),
                PICK_FROM_FILE);
          /*Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image*//**//**//**//*");

        } else {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        startActivityForResult(intent, PICK_FROM_FILE);*/
        //打开选择图片的界面
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType("image/*");//相片类型
//        mActivity.startActivityForResult(intent, PICK_FROM_FILE);
    }
    public String getSelectImagePath(Uri uri){
        imageCaptureUri = uri;
        if (imageCaptureUri != null) {
            String imgPath=getPath(imageCaptureUri);
             /* Bitmap bit = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        userimg.setImageBitmap(bit);*/
            PhoneCameraUtil.imageCaptureUri = null;
            return imgPath;
        }
        return "";
    }
    public void getFile(Bitmap bitmap){
//                 File file = null;
//                if (bitmap != null) {
//                    Bitmap bitmap = bitmap;
//                    FileUtils.mkDir(FileUtils.SDCARD_PATH + "/HelpLove/");
//                    FileOutputStream fos = new FileOutputStream(
//                            FileUtils.SDCARD_PATH + "/HelpLove/img.jpg");
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//                    fos.close();
//                    file = new File(FileUtils.SDCARD_PATH
//                            + "/HelpLove/img.jpg");
//                    if (!TextUtils.isEmpty(imgPath1String)) {
//                        imgPath1String = "update_x";
//                    }
//                    files.put("img1", file);
//                }
    }
    public String getDocumentId(Uri uri) {
        String res = null;
        try {
            Class<?> c = Class.forName("android.provider.DocumentsContract");
            Method get = c.getMethod("getDocumentId", Uri.class);
            res = (String) get.invoke(c, uri);
        } catch (Exception ignored) {
            ignored.getMessage();
        }
        return res;
    }

    @SuppressLint("NewApi")
    public String getPath(final Uri uri) {
        //
        // final boolean mIsKitKat = Build.VERSION.SDK_INT >=
        // Build.VERSION_CODES.KITKAT;
        //
        // if (mIsKitKat
        // && DocumentsContract.isDocumentUri(MainAixinActivity.this, uri)) {
        if (isExternalStorageDocument(uri)) {
            final String docId = getDocumentId(uri);
            final String[] split = docId.split(":");
            final String type = split[0];
            if ("primary".equalsIgnoreCase(type)) {
                return Environment.getExternalStorageDirectory() + "/"
                        + split[1];
            }
            // TODO handle non-primary volumes
        } // MediaProvider
        else if (isMediaDocument(uri)) {
            final String docId = getDocumentId(uri);
            final String[] split = docId.split(":");
            final String type = split[0];
            Uri contentUri = null;
            if ("image".equals(type)) {
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(type)) {
                contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(type)) {
                contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            }
            final String selection = "_id=?";
            final String[] selectionArgs = new String[] { split[1] };
            return getDataColumn(mContext, contentUri,
                    selection, selectionArgs);
        } else if (isDownloadsDocument(uri)) {

            final String id = getDocumentId(uri);
            final Uri contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"),
                    Long.valueOf(id));

            return getDataColumn(mContext, contentUri, null,
                    null);
        } else if (isDownloadsDocument(uri)) {

            final String id = getDocumentId(uri);
            final Uri contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"),
                    Long.valueOf(id));

            return getDataColumn(mContext, contentUri, null,
                    null);
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {

            if (isGooglePhotosUri(uri))
                return uri.getLastPathSegment();

            return getDataColumn(mContext, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(mContext, uri, null, null);
        } else {
            return getRealPathFromUriMinusKitKat(mContext,
                    uri);
        }

        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context
     *            The context.
     * @param uri
     *            The Uri to query.
     * @param selection
     *            (Optional) Filter used in the query.
     * @param selectionArgs
     *            (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public String getDataColumn(Context context, Uri uri, String selection,
                                String[] selectionArgs) {
        Cursor cursor = null;
        String[] projection = { MediaStore.Images.Media.DATA,
                MediaStore.Images.Media.ORIENTATION };
        try {
            cursor = context.getContentResolver().query(uri, projection,
                    selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
                return cursor.getString(columnIndex);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private String getRealPathFromUriMinusKitKat(Context context, Uri uri) {
        Cursor cursor = null;
        try {

            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(uri, filePathColumn,
                    null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String picturePath = cursor.getString(columnIndex);

                return picturePath;
            }

        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri
     *            The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public boolean isExternalStorageDocument(Uri uri) {
        //com.android.externalstorage.documents
        return "com.jzbwlkj.leifeng.fileprovider".equals(uri
                .getAuthority());
    }

    /**
     * @param uri
     *            The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public boolean isDownloadsDocument(Uri uri) {
        //com.android.providers.downloads.documents
        return "com.jzbwlkj.leifeng.fileprovider".equals(uri
                .getAuthority());
    }

    /**
     * @param uri
     *            The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri
                .getAuthority());
    }

    /**
     * @param uri
     *            The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    public boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri
                .getAuthority());
    }
    public boolean isPicturePath(String filePath){
        if (TextUtils.isEmpty(filePath)){
            return false;
        }else if (filePath.endsWith(".png")){
            return true;
        }else if (filePath.endsWith(".jpg")){
            return true;
        }else if (filePath.endsWith(".jpeg")){
            return true;
        }
        return false;
    }
    /**
     * 加载本地路径图片
     *
     * @param strPicPath
     * @return
     * @throws Exception
     */
    public Bitmap getDeal(String strPicPath) throws Exception {
        Bitmap bitmap = null;
        Bitmap rBitmap = null;
        FileInputStream fs = null;
        try {
            BitmapFactory.Options bfOptions = new BitmapFactory.Options();
            bfOptions.inDither = false;
            bfOptions.inPurgeable = true;

            bfOptions.inInputShareable = true;
            bfOptions.inTempStorage = new byte[12 * 1024];

            File file = new File(strPicPath);

            if (file != null && file.exists()) {

                fs = new FileInputStream(file);
                if (fs != null)
                    rBitmap = BitmapFactory.decodeFileDescriptor(fs.getFD(),
                            null, bfOptions);

                if (rBitmap != null) {
                    int iheight = 320;
                    int iwidth = 480;

                    if (rBitmap.getHeight() > 768) {
                        iheight = 320;
                    }

                    if (rBitmap.getWidth() > 1024) {
                        iwidth = 480;
                    }

                    bitmap = reduce(rBitmap, iwidth, iheight, true);
                    bitmap = compressBmpFromBmp(bitmap);
//                    FileUtils.mkDir(FileUtils.SDCARD_PATH + "/HelpLove/");
//                    FileOutputStream fos = new FileOutputStream(
//                            FileUtils.SDCARD_PATH + "/HelpLove/img.jpg");

//                    bitmap.compress(CompressFormat.JPEG, 100, fos);
//                    fos.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fs != null)
                fs.close();
            if (rBitmap != null) {
                if (!rBitmap.isRecycled()) {
                    rBitmap = null;

                }
            }
        }
        return bitmap;
    }

    public Bitmap compressBmpFromBmp(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 90;
        image.compress(Bitmap.CompressFormat.JPEG, options, baos);
        while (baos.toByteArray().length / 1024 > 200) {
            if (options - 10 > 0)
                options -= 10;
            else {
                break;
            }
            baos.reset();
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);
        return bitmap;
    }

    /**
     * 压缩图片
     *
     * @param bitmap
     *            源图片
     * @param width
     *            想要的宽度
     * @param height
     *            想要的高度
     * @param isAdjust
     *            是否自动调整尺寸, true图片就不会拉伸，false严格按照你的尺寸压缩
     * @return Bitmap
     */
    public Bitmap reduce(Bitmap bitmap, int width, int height, boolean isAdjust) {
        // 如果想要的宽度和高度都比源图片小，就不压缩了，直接返回原图
        if (bitmap.getWidth() < width && bitmap.getHeight() < height) {
            return bitmap;
        }
        // 根据想要的尺寸精确计算压缩比例, 方法详解：public BigDecimal divide(BigDecimal divisor,
        // int scale, int roundingMode);
        // scale表示要保留的小数位, roundingMode表示如何处理多余的小数位，BigDecimal.ROUND_DOWN表示自动舍弃
        float sx = new BigDecimal(width).divide(
                new BigDecimal(bitmap.getWidth()), 4, BigDecimal.ROUND_DOWN)
                .floatValue();
        float sy = new BigDecimal(height).divide(
                new BigDecimal(bitmap.getHeight()), 4, BigDecimal.ROUND_DOWN)
                .floatValue();
        if (isAdjust) {// 如果想自动调整比例，不至于图片会拉伸
            sx = (sx < sy ? sx : sy);
            sy = sx;// 哪个比例小一点，就用哪个比例
        }
        Matrix matrix = new Matrix();
        matrix.postScale(sx, sy);// 调用api中的方法进行压缩，就大功告成了
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
    }

    /**
     * Create a file Uri for saving an image or video
     *
     * @throws IOException
     */
    private Uri getOutputMediaFileUri(int type) throws IOException {
        Uri pp;
        File file = getOutputMediaFile(type);
        if (Build.VERSION.SDK_INT >= 24) {
            Log.i("sun","hhh");
            //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致 参数3 共享的文件
            pp = FileProvider.getUriForFile(mContext, "com.jzbwlkj.leifeng.fileprovider",file );
        } else {
            pp = Uri.fromFile(file);
        }
        Log.i("sun","路径=="+pp);
        return pp;
    }

    public String getFileAddress(int state, String appName, Context mContext) {
        String Address = "";
        if (GetSDState()) {
            Address = Environment.getExternalStorageDirectory().getPath() + "/"
                    + appName + "/";
        } else {
            Address = mContext.getCacheDir().getAbsolutePath() + "/" + appName
                    + "/";
        }
        switch (state) {
            case 1:
                Address = Address + "cache1/";
                break;
            case 2:
                Address = Address + "video/";
                break;
            case 3:
                Address = Address + "voice/";
                break;
            case 4:
                Address = Address + "file/";
                break;
            case 5:
                Address = Address + "photos/";
                break;
            default:
                Address = Address + "cache/";
                break;
        }
        File baseFile = new File(Address);
        if (!baseFile.exists()) {
            baseFile.mkdirs();
        }
        return Address;
    }

    // 返回是否有SD卡
    public boolean GetSDState() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    /**
     * Create a File for saving an image or video
     *
     * @throws IOException
     */
    @SuppressLint("SimpleDateFormat")
    private File getOutputMediaFile(int type) throws IOException {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaFile = null;
        if (GetSDState()) {// ----判断是否有SD卡
            File mediaStorageDir = new File(
                    Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                    "MyCameraApp");
            // This location works best if you want the created images to be
            // shared
            // between applications and persist after your app has been
            // uninstalled.
            if (mediaStorageDir != null) {
                if (!mediaStorageDir.exists()) {
                    if (!mediaStorageDir.mkdirs()) {
                        // Log.d("MyCameraApp", "failed to create directory");
                        return null;
                    }
                }
            }
            // Create a media file name
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss")
                    .format(new Date());
            if (type == MEDIA_TYPE_IMAGE) {
                mediaFile = new File(mediaStorageDir.getPath() + File.separator
                        + "IMG_" + timeStamp + ".jpg");
            }
        } else {
            mediaFile = new File(getFileAddress(0, "camera", mContext), "userface"
                    + System.currentTimeMillis() + ".jpg");
        }

        return mediaFile;
    }

}
