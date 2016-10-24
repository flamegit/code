package com.example.inmobi_native;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AppListActivity extends AppCompatActivity {

    RecyclerView.Adapter mAdapter;
    List<ApplicationInfo> mAppList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAppList = new ArrayList<>();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        if (fab == null) return;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(view.getContext(), LoaderCustom.class);
                startActivityForResult(intent, 0);
            }
        });

        mAdapter = new RecyclerView.Adapter<ViewHolder>() {
            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                PackageManager packageManager = getPackageManager();
                final String packageName = mAppList.get(position).packageName;
                Drawable icon = mAppList.get(position).loadIcon(packageManager);
                holder.icon.setImageDrawable(icon);
                holder.icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_LAUNCHER);
                        intent.setPackage(packageName);
                        List<ResolveInfo> infos = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_ALL);
                        Intent intent1=new Intent();
                        intent1.putExtra("targetComponent",infos.get(0).activityInfo.name);
                        intent1.putExtra("targetPackageName", packageName);
                        Log.d("flame",packageName+":"+infos.get(0).activityInfo.name);
                        intent1.setAction("com.broadcast.mdroid.initboxprocessP00");
                        sendBroadcast(intent1);
                    }
                });
                holder.title.setText(mAppList.get(position).loadLabel(packageManager));
            }

            @Override
            public int getItemCount() {
                return mAppList.size();
            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.app_item, parent, false));
            }
        };
        list.setAdapter(mAdapter);
        list.setLayoutManager(new GridLayoutManager(this, 3));


    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView icon;
        public TextView title;

        ViewHolder(View view) {
            super(view);
            icon = (ImageView) view.findViewById(R.id.icon);
            title = (TextView) view.findViewById(R.id.title);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            ApplicationInfo info = (ApplicationInfo) data.getParcelableExtra("application");
            mAppList.add(info);
            mAdapter.notifyDataSetChanged();
        }
    }
}
