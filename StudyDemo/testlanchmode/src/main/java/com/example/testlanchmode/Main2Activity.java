package com.example.testlanchmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
