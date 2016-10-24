package com.flame.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2016/9/21.
 */
public class costumView extends LinearLayout {
    @TargetApi(21)
    public costumView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            @TargetApi(21)
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                Log.d("fff","dddd ");
                insets.getStableInsetTop();
                return insets;
            }
        });

   }


}
