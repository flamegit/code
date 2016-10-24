package com.example.testlanchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivitySingleTask extends AppCompatActivity {

    public ActivitySingleTask(){
        super();
        try{int i=1/0;}catch(Exception e){e.printStackTrace();}
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button= (Button) findViewById(R.id.button);
        button.setText("ActivitySingleTask");
    }

    public void click1(View view){
        Intent intent=new Intent(this,ActivityStandard.class);
        startActivity(intent);
    }
    public void click2(View view){
        Intent intent=new Intent(this,ActivityTop.class);
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
    protected void onNewIntent(Intent intent) {
        try{int i=1/0;}catch(Exception e){e.printStackTrace();}
        Log.d("LaunchMode","onNewIntent ActivitySingleTask");
    }

    @Override
    protected void onPause() {
        Log.d("LaunchMode","onPause ActivitySingleTask");
        try{int i=1/0;}catch(Exception e){e.printStackTrace();}
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.d("LaunchMode","onDestroy ActivitySingleTask");
        try{int i=1/0;}catch(Exception e){e.printStackTrace();}
        super.onDestroy();

    }
}
