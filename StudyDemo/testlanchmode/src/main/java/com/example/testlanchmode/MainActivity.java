package com.example.testlanchmode;

import android.content.Intent;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button= (Button) findViewById(R.id.button);
        ImageView imageView=(ImageView)findViewById(R.id.image);
        button.setText("MainActivity");
        TextView content=(TextView)findViewById(R.id.text);
        content.setText("hello this is my house welcome !");
        content.buildDrawingCache(true);
        imageView.setImageBitmap(content.getDrawingCache());
       // Process.myUid();
      //  Log.d("tag",this.getApplicationInfo().dataDir+":"+ Process.myUid());
        button.post(new Runnable() {
            @Override
            public void run() {
                button.getMeasuredHeight();
            }
        });

    }



    public void click1(View view){
        Intent intent=new Intent(this,ActivityStandard.class);
        Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();
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
