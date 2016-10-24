package com.example.testlanchmode;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/7/26.
 */
  public class CustomView extends View {

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                int actionIndex = MotionEventCompat.getActionIndex(event);
                int pointId = MotionEventCompat.getPointerId(event, actionIndex);
                int pointIndex=MotionEventCompat.findPointerIndex(event, pointId);
                Log.d("Event",actionIndex+":"+pointId+":"+pointIndex);
                break;
            case MotionEvent.ACTION_UP:
                Log.d("Event"," "+MotionEventCompat.findPointerIndex(event, 0));

                break;

        }


        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }
}
