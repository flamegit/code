package com.example.studydemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

class GridImageView extends ViewGroup {
    private GameService mService = null;
    private Paint mBgPaint;
    private Paint mBoardPaint;
    private int ITEMSIZE;
    private int mOffSetX;
    private int mOffSetY;
    private int ROW;
    private int COLUMN;

    public GridImageView(Context context) {
        super(context);
    }

    public GridImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.GridImageView);
        a.recycle();
        mBgPaint = new Paint();
        mBgPaint.setColor(Color.BLACK);
        mBoardPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBoardPaint.setStyle(Paint.Style.STROKE);
        mBoardPaint.setStrokeWidth(10);
        mBoardPaint.setColor(Color.BLUE);
    }

    public void init(int num){
        for(int i=0;i<num;i++){
            addView(new ImageView(getContext()),i);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).layout(pointX(i),pointY(i),pointX(i)+ITEMSIZE,pointY(i)+ITEMSIZE);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        ITEMSIZE=Math.min(w/Model.COLUMN,h/Model.ROW);
        mOffSetX=(w-COLUMN*ITEMSIZE)/2;
        mOffSetY=(h-ROW*ITEMSIZE)/2;
        if (mService != null) {
            //mService.init(10, 15, 8);
        }
    }

    public void setService(GameService service) {
        mService = service;
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            return true;
        } else if (action == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            int column = (x - mOffSetX) / ITEMSIZE;
            int row = (y - mOffSetY) / ITEMSIZE;
            mService.onSelected(row, column);
            return true;
        }
        return true;
    }

    private int row(int num) {
        return num / COLUMN;
    }
    private int column(int num) {
        return num % COLUMN;
    }
    private int num(int r, int c) {
        return r * COLUMN + c;
    }
    private int pointX(int p) {
        int c = column(p);
        return mOffSetX + c * ITEMSIZE;
    }
    private int pointY(int p) {
        int r = row(p);
        return mOffSetY + r * ITEMSIZE;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getWidth(), getHeight(), mBgPaint);
        if (mService != null) {
            mService.drawBoard(canvas, mBoardPaint);
            mService.drawLine(canvas, mBoardPaint);
        }
    }
}
