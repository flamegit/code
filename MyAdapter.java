package com.example.listsildedel;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {

	private List<String> arrays = null;
	private Context mContext;
	private Button curDel_btn;
	private float x,ux;
	public MyAdapter(Context mContext, List<String> arrays) {
		this.mContext = mContext;
		this.arrays = arrays;
	}

	public int getCount() {
		return this.arrays.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.item, null);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
			viewHolder.btnDel = (Button) view.findViewById(R.id.del);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		
		view.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				final ViewHolder holder = (ViewHolder) v.getTag();
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					v.setBackgroundResource(R.drawable.mm_listitem_pressed);
					x = event.getX();
					if (curDel_btn != null) {
						curDel_btn.setVisibility(View.GONE);
					}
					
				} else if (event.getAction() == MotionEvent.ACTION_UP) {
					v.setBackgroundResource(R.drawable.mm_listitem_simple);
					ux = event.getX();
					if (holder.btnDel != null) {
						if (Math.abs(x - ux) > 20) {
							holder.btnDel.setVisibility(View.VISIBLE);
							curDel_btn = holder.btnDel;
						}
					}
				} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
					v.setBackgroundResource(R.drawable.mm_listitem_pressed);
				} else {
					v.setBackgroundResource(R.drawable.mm_listitem_simple);
				}
				return true;
			}
		});
		viewHolder.tvTitle.setText(this.arrays.get(position));
		viewHolder.btnDel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if(curDel_btn!=null)
					curDel_btn.setVisibility(View.GONE);
				arrays.remove(position);
				notifyDataSetChanged();
			}
		});
		return view;
	}
	final static class ViewHolder {
		TextView tvTitle;
		Button btnDel;
	}
}