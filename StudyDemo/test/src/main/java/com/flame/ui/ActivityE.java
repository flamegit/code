package com.flame.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.SwipeDismissBehavior;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;


import com.example.test.R;

public class ActivityE extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_e);
        ViewPager viewPager=(ViewPager)findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewAdapter());
        Button button=new Button(this);
        button.setText("helllo");
        ViewPager.LayoutParams params=new ViewPager.LayoutParams();
        params.isDecor=true;
        params.gravity= Gravity.BOTTOM;
        params.height=ViewGroup.MarginLayoutParams.WRAP_CONTENT;
        button.setLayoutParams(params);
        viewPager.addView(button);
    }
}
