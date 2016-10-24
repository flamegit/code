package com.flame.ui;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.animation.AnimatorCompatHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;


/**
 * Created by Administrator on 2016/10/18.
 */

public class ScrollBehavior extends CoordinatorLayout.Behavior<TabLayout> {

    private boolean isShow;
    public ScrollBehavior(Context context, AttributeSet attrs){
         super();
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, TabLayout child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
                || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, TabLayout child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        Log.d("fxlts",dyConsumed+":"+dyUnconsumed);
        if(dyConsumed<0 && !isShow){
            show(child);
        }
        else if(dyConsumed>0&& isShow){
            hide(child);
        }
    }

    public void hide(View view){
        isShow=false;
        ViewCompat.animate(view)
                .translationY(view.getHeight())
                .alpha(0f)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(null);
    }

    public void show(View view){
        isShow=true;
        ViewCompat.animate(view)
                .translationY(0)
                .alpha(1f)
                .setInterpolator(new AccelerateInterpolator())
                .setListener(null);
    }
}
