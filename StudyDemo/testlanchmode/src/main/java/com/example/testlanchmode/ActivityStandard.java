package com.example.testlanchmode;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityStandard extends AppCompatActivity {

    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button= (Button) findViewById(R.id.button);
        mTextView=(TextView)findViewById(R.id.text);
        mTextView.setText("飞流直下三千尺，\n 疑是银河落九天。\n 我欲乘风破浪，\n踏浪票房。");
        mTextView.setLineSpacing(10,1);
        Paint.FontMetrics fontMetrics=mTextView.getPaint().getFontMetrics();
        Log.d("font",fontMetrics.top+":"+fontMetrics.bottom);
        Log.d("font",fontMetrics.descent+":"+fontMetrics.ascent+":"+fontMetrics.leading);

        mTextView.getDrawingCache();
        button.setText("ActivityStandard");


        Log.d("tag",getCacheDir().toString());
    }

    public void click1(View view){
//        Intent intent=new Intent(this,ActivityStandard.class);
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        startActivity(intent);
        Rect r=new Rect();
        mTextView.getLineBounds(0,r);
        Log.d("rect",r.top+":"+r.bottom);
        mTextView.getLineBounds(1,r);
        Log.d("rect",r.top+":"+r.bottom);
        mTextView.getLineBounds(2,r);
        Log.d("rect",r.top+":"+r.bottom);
        mTextView.getLineBounds(3,r);
        Log.d("rect",r.top+":"+r.bottom);
    }
    public void click2(View view){
        Intent intent=new Intent(this,ActivityStandard2.class);
       // Log.d("tag",this.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY).activityInfo.name+" ");

        startActivity(intent);
    }
    public void click3(View view){
        Intent intent=new Intent(this,ActivitySingleTask.class);
        startActivity(intent);
    }
    public void click4(View view){
        Intent intent=new Intent(this,ActivitySingleInstance.class);
        startActivity(intent);
    }


    @Override
    protected void onPause() {
        Log.d("LaunchMode","onPause ActivityStandard");
        try{int i=1/0;}catch(Exception e){e.printStackTrace();}
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.d("LaunchMode","onDestroy ActivityStandard");
        try{int i=1/0;}catch(Exception e){e.printStackTrace();}
        super.onDestroy();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Log.d("LaunchMode","onConfigurationChanged");
    }
}
