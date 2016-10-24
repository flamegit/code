package com.example.testlanchmode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivitySingleInstance extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button= (Button) findViewById(R.id.button);
        button.setText("ActivitySingleInstance");
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
        Log.d("LaunchMode","onNewIntent ActivitySingleInstance");
    }

    @Override
    protected void onPause() {
        Log.d("LaunchMode","onPause ActivitySingleInstance");
        try{int i=1/0;}catch(Exception e){e.printStackTrace();}
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        Log.d("LaunchMode","onDestroy ActivitySingleInstance");
        try{int i=1/0;}catch(Exception e){e.printStackTrace();}
        super.onDestroy();

    }
}
