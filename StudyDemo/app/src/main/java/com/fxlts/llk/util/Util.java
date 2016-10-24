package com.fxlts.llk.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import com.example.studydemo.R;

import java.util.List;

/**
 * Created by Administrator on 2016/5/3.
 */
public class Util {



    public  static   Bitmap[] loadImages(int size, Context context) {
        int[] bitmapIds = {R.mipmap.a, R.mipmap.a, R.mipmap.b,
                R.mipmap.c, R.mipmap.d, R.mipmap.e, R.mipmap.f,
                R.mipmap.g, R.mipmap.h, R.mipmap.i, R.mipmap.j};
        Bitmap[] bitmaps = new Bitmap[bitmapIds.length];
        bitmaps[0] = null;
        for (int i = 1; i < bitmapIds.length; i++) {
            Drawable drawable =context.getResources().getDrawable(bitmapIds[i]);
            drawable.setBounds(1, 1, size - 1, size - 1);
            Bitmap b = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            Canvas c = new Canvas(b);
            drawable.draw(c);
            bitmaps[i] = b;
        }
        return bitmaps;
    }
}
