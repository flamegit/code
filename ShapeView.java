package com.example.tetris2;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class ShapeView extends View {

	private Shape mShape;
	public ShapeView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public ShapeView(Context context, AttributeSet attrs){
		super(context,attrs);
	}
	
	public void changeShape(Shape shape){
		mShape=shape;
		invalidate();
	}
	
	protected void onDraw(Canvas canvas){
		if(mShape==null)
			return;
		mShape.draw(canvas);
	}

}
