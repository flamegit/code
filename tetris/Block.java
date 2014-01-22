package com.example.tetris2;

import java.util.Arrays;

public class Block {
	private int[][] mBlock;
	
	public Block(){
		mBlock=new int[TetrisView.XCOUNT][TetrisView.YCOUNT];
	}
	
	public int get(int x,int y){
		return mBlock[x][y];
	}
	
	public void set(int color,int x,int y){
		mBlock[x][y]=color;
	}
	
	private boolean isFull(int position){
		int value=1;
		for(int i=0;i<TetrisView.YCOUNT;i++){
			value&=mBlock[position][i];
			if(value==0)
				return false;
		}
		return true;
	}
	
	private void fallDown(int position){
		int[] tmp=mBlock[position];
		Arrays.fill(tmp, 0);
		for(int i=position;i>0;i--){
			mBlock[i]=mBlock[i-1];
		}
		mBlock[0]=tmp;
	}
	
	
	public void check(int position){
			for(int i=0;i<4;i++){
			if(isFull(position))
				fallDown(position);
			else{
				position--;
			}
		}	
	}

	public void clear() {
		// TODO Auto-generated method stub
		for(int i=0;i<TetrisView.XCOUNT;i++){
			Arrays.fill(mBlock[i], 0);
		}
		
	}
}
