package com.example.tetris2;
import java.util.LinkedList;
import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.KeyEvent;

public class Shape {
	
	public static final int[][][] SHAPES={
		{{1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,4},
		{0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,1,4,1}},
		{{0,0,1,1,0,0,1,1,0,0,0,0,0,0,0,0,2,2}}
		};
	
	private static LinkedList<Shape> cacheShape=new LinkedList<Shape>();
	private int[][] mShape;
	private int mType;
	private int mStyle;
	private int mX;
	private int mY;
	private int mWidth;
	private int mHeight;
	private int mColor;
	private Shape(){		
	}
	
	public int getValue(int index){
		
		return mShape[mStyle][index];
	}
	
	public int getXPosition(int index){
		return mX+(index>>2);
	}
	
	public int getYPosition(int index){
		return mY+(index&3);
	}
	
	public static Shape getInstance(){
		if(cacheShape.peek()==null)
		{
			new Shape().recyle();
			new Shape().recyle();
		}
		return cacheShape.poll();
	}
	
	private boolean isMeetBlock(Block block,int xOffSet,int yOffSet){
		for(int i=0;i<mWidth;i++)
			for(int j=0;j<4;j++){
				if((block.get(mY+j+yOffSet, mX+i+xOffSet)&mShape[mStyle][i*4+j])==1)
					return true;
			}
		return false;
	}
	public boolean isConflict(Block block,int direction){
		switch (direction) {
		case 0:
			return isMeetBlock(block,0,0);
		case 1:
			if(mY+4>=TetrisView.XCOUNT)
				return true;
			return isMeetBlock(block,0,1);
		case 2:
			if(mX<=0)
				return true;
			return isMeetBlock(block,-1,0);
		case 3:
			if(mX+mWidth>=TetrisView.YCOUNT)
				return true;
			return isMeetBlock(block,1,0);
		}	
		return false;
	}
	
	public void  fillShape(Block block){
		for(int i=0;i<mWidth;i++)
			for(int j=0;j<4;j++){
				if(mShape[mStyle][4*i+j]>0)
				    block.set(mColor,mY+j, mX+i);
			}
		block.check(mY+3);
	}
	
	public void draw(Canvas canvas){
		for(int i=0;i<16;i++)
		{
			if(getValue(i)>0){
				canvas.drawBitmap(TetrisView.TILES[mColor], getXPosition(i)*TetrisView.CUBESIZE, 
					getYPosition(i)*TetrisView.CUBESIZE, null);
			}
		}				
	}

	public void recyle() {
		// TODO Auto-generated method stub
		Random random =new Random();
		mType=random.nextInt(SHAPES.length);
		mColor=random.nextInt(3)+1;
		mShape=SHAPES[mType];
		mStyle=0;
		mX=2;
		mY=0;
		mWidth=mShape[mStyle][16];
		mHeight=mShape[mStyle][17];
		cacheShape.add(this);
	}
	
	public void changeDiretion(){
		mStyle=(mStyle+1)%mShape.length;
		mWidth=mShape[mStyle][16];
		mHeight=mShape[mStyle][17];
	}
	public void down() {
		// TODO Auto-generated method stub
		mY++;
		
	}
	public void left() {
		// TODO Auto-generated method stub
		mX--;
	}
	public void right() {
		// TODO Auto-generated method stub
		mX++;
	}
}
