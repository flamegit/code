package com.example.test;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import java.io.File;

public class ActivityB extends Activity {

    static final String TAG="ActivityB";
    private static final String path= "http://img0.imgtn.bdimg.com/it/u=2545179197,2573899739&fm=21&gp=0.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        //load();
        Log.d(TAG, getDir("bin", Context.MODE_PRIVATE).getAbsolutePath()); ///data/data/com.example.myapp/app_bin
        Log.d(TAG,getFilesDir().getAbsolutePath());                        ///data/data/com.example.myapp/files
        Log.d(TAG,getCacheDir().getAbsolutePath());                        ///data/data/com.example.myapp/cache
        Log.d(TAG,getObbDir().getAbsolutePath());                          ///storage/sdcard0/Android/obb/


        Log.d(TAG,getExternalCacheDir().getAbsolutePath());               ///storage/sdcard1/Android/data/com.example.myapp/cache
        Log.d(TAG,getExternalFilesDir("apk").getAbsolutePath());             ///storage/sdcard1/Android/data/com.example.myapp/files


        Log.d(TAG, Environment.getExternalStorageState());                //mounted

        Log.d(TAG, Environment.getExternalStorageDirectory().getAbsolutePath());           ///storage/sdcard0
        Log.d(TAG, Environment.getDataDirectory().getAbsolutePath());                      ///data
        Log.d(TAG, Environment.getDownloadCacheDirectory().getAbsolutePath());             ///cache
        Log.d(TAG, Environment.getExternalStoragePublicDirectory("apk").getAbsolutePath());   ///storage/sdcard0
        Log.d(TAG, Environment.getRootDirectory().getAbsolutePath());
    }


    public void load(){
        DownloadManager downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(path);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        request.setDestinationInExternalPublicDir("apk","temlates");
       // request.setDestinationInExternalFilesDir(this,)
        long reference = downloadManager.enqueue(request);
    }
}
