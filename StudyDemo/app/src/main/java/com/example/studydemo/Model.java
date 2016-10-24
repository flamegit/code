package com.example.studydemo;

import android.graphics.Bitmap;

import java.util.List;

/**
 * Created by Administrator on 2016/5/3.
 */
public class Model {

    public final static int ROW=15;
    public final static int COLUMN=8;
    private Bitmap[] mBitmaps;
    private int[] mBoard;
    private int mSelected;
    private int mScore;
    private int mTime;
    private int mCount;
    private List<Integer> mLine ;

    public void init(){

    }

    public Bitmap getItem(int i) {
        return mBitmaps[mBoard[i]];
    }

    public void linked(){
        mCount++;
        mSelected=-1;
    }



}
