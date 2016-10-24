package com.example.test;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//import com.wilddog.client.ChildEventListener;
//import com.wilddog.client.DataSnapshot;
//import com.wilddog.client.ValueEventListener;
//import com.wilddog.client.Wilddog;
//import com.wilddog.client.WilddogError;

import dalvik.system.PathClassLoader;

public class ActivityA extends Activity {

    private static final String AppId="https://flame.wilddogio.com/";
    ViewPager mPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        mPager=(ViewPager)findViewById(R.id.pager);

        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                 Log.d("TAG",""+mPager.getChildCount());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

//        Wilddog.setAndroidContext(this);
//        Wilddog ref = new Wilddog(AppId);
//        ref.setValue(30);

//        ref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//            }
//
//            @Override
//            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//            }
//
//            @Override
//            public void onChildRemoved(DataSnapshot dataSnapshot) {
//            }
//
//            @Override
//            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//            }
//
//            @Override
//            public void onCancelled(WilddogError wilddogError) {
//            }
//        });
//        ref.addValueEventListener(
//                new ValueEventListener(){
//                    public void onDataChange(DataSnapshot snapshot){
//                        System.out.println(snapshot.getValue()); //打印结果 "hello world!!!"
//                    }
//
//                    public void onCancelled(WilddogError error){
//                        if(error != null){
//                            System.out.println(error.getCode());
//                        }
//                    }
//                });

        //Log.d("ActivityA",getApplicationInfo().sourceDir+":"+getApplicationInfo().dataDir);

        //Log.d("ActivityA", getApplicationInfo().sharedLibraryFiles.toString()+"");
//        button.setPivotX(0);
//        button.setPivotY(10000);
//        button.setRotationY(30);
//       // button.setTranslationY(-100);
//        button.post(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("ActivityA",  button.getMeasuredHeight()+"");
//            }
//        });


    }

    public void click(View view){
        mPager.setCurrentItem(8);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
