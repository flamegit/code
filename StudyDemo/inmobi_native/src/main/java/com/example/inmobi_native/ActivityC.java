package com.example.inmobi_native;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ActivityC extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        Button button= (Button) findViewById(R.id.button);
        button.setText("ActivityC");

    }

    public void click(View view){
        Intent intent=new Intent(this, ActivityB.class);

        startActivity(intent);
    }
}
