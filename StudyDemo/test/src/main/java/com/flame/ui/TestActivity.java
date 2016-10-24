package com.flame.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.test.R;

import rx.functions.Action1;

public class TestActivity extends AppCompatActivity {

    RecyclerViewAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            //window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        RxBus.getDefault().toObservable(RxBus.ItemEvent.class).subscribe(new Action1<RxBus.ItemEvent>() {
            @Override
            public void call(RxBus.ItemEvent itemEvent) {
                Log.d("fxlts","onclick");
            }
        });

        setSupportActionBar(toolbar);
        TabLayout mTabLayout = (TabLayout)findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.select_tab1));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.select_tab2));
        mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.select_tab3));

        mAdapter=new RecyclerViewAdapter();

        final RecyclerView recyclerView=(RecyclerView)findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new ItemDecoration() );

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!recyclerView.canScrollVertically(1)){
                    mAdapter.showFooter();
                }
            }
        });
        ItemTouchHelper touchHelper=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP,ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                mAdapter.onItemMove(viewHolder.getAdapterPosition(),
//                        target.getAdapterPosition());
                return true;
            }
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                mAdapter.remove(viewHolder.getAdapterPosition());
            }

        });
        recyclerView.setAdapter(mAdapter);
        touchHelper.attachToRecyclerView(recyclerView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(),ActivityE.class));
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
