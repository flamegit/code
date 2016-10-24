package com.example.test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver3 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d("MyReceiver","onReceive3");
        try{int i=1/0;}catch(Exception e){e.printStackTrace();}
        String name=context.getResources().getString(R.string.app_name);

        Log.d("MyReceiver",name);
    }
}
