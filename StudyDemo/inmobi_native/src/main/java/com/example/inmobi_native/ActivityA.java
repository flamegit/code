package com.example.inmobi_native;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        Button button= (Button) findViewById(R.id.button);
        button.setText("ActivityA");

//        IntentFilter intentFilter=new IntentFilter();
//        intentFilter.setPriority(100);
//        intentFilter.addAction("com.fxlts.receiver");
//        this.registerReceiver(new MyReceiver(),intentFilter);
//
//        IntentFilter intentFilter2=new IntentFilter();
//        intentFilter2.addAction("com.fxlts.receiver");
//        this.registerReceiver(new Receiver2(),intentFilter2);

    }

    public void click(View view){
//        Intent intent=new Intent();
//        intent.setAction("com.fxlts.receiver");
//        this.sendOrderedBroadcast(intent,null);
        Intent intent=new Intent(this, ActivityB.class);
        startActivity(intent);

    }
}
