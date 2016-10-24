package com.example.test;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by Administrator on 2016/8/4.
 */
public class ContentLoader extends AsyncTaskLoader<List<String>> {

    public ContentLoader(Context context) {
        super(context);
    }

    @Override
    public List<String> loadInBackground() {
        return null;
    }
}
