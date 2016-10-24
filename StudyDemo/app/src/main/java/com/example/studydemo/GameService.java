package com.example.studydemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;

public class GameService {
	public static final  int BEGIN=1;
	public static final  int RUNING=2;
	public static final  int PAUSE=3;
	public static final  int COMPLETE=4;
	public static final  int FAILED=5;

    private int mCount=0;
	private int mTime=0;
	private Timer mTimer;
	private ProgressBar mBar;
	private GridImageView mBoardView;
	private TextSwitcher mScore;
	private int[] mBoard;
	private int mSelected;
	private Bitmap[] mBitmaps;
	private Gamelogic mLogic;
	private List<Integer> mLine = null;
	private int mState;
	private static GameService instanc;

	public static GameService getInstanc(){
		if(instanc==null){
			synchronized (GameService.class){
				if(instanc==null){
					instanc=new GameService();
				}
			}
		}
		return instanc;
	}

	private GameService(){
	}

	public  void bindView(ProgressBar bar,GridImageView boardView,TextSwitcher score){
		mBar=bar;
		mBoardView=boardView;
		mScore=score;
	}

	public void changeState(int state){
		mState=state;
		process();
	}

	public void process(){
         switch (mState){
			 case BEGIN:
                  init(10,15,8);
				  resume();
				  mState=RUNING;
				  break;
			 case RUNING:

			 case PAUSE:

			 case COMPLETE:

			 case FAILED:
		 }
	}

    private void resume(){
		TimerTask task=new TimerTask() {
			@Override
			public void run() {
				if(mTime==30)
					cancel();
				else{
					mTime++;
					mBar.setProgress(mTime*10);
				}
			}
		};
		mTimer=new Timer();
		mTimer.schedule(task,10,1000);
	}

	public void init(int num){



		//mBitmaps=loadImages(ITEMSIZE);
		//mLogic=new Gamelogic(10,row,column);
		mBoard=mLogic.fillBoard();
	}

	private void clear(int i,int j){
		mBoard[i]=0;
		mBoard[j]=0;
	}

	public void startGame(){

	}

    public int caculateScore(int num){
		return num*100;
	}

    public void setState(int state){
		mState=state;
	}

	public void pause(){
		mTimer.cancel();
	}
	public void stop(){
	}



	public void onSelected(int row, int column){

		int position = num(row, column);
		if (getItem(position) == null) {
			return ;
		}
		if (mSelected == -1) {
			mSelected = num(row, column);
		} else {
			mLine = mLogic.isLinked(mSelected, num(row, column));
			if (mLine != null) {
				mCount++;
                mScore.setText(""+mCount*100);
				mSelected = -1;
				mBoardView.postDelayed(new Runnable() {
					@Override
					public void run() {
						if (mLine != null) {
							clear(mLine.get(0), mLine.get(mLine.size() - 1));
							mLine = null;
						}
						mBoardView.invalidate();
					}
				},100);
			} else {
				mSelected = num(row, column);
			}
		}
		mBoardView.invalidate();
	}

	public void drawBoard(Canvas canvas,Paint paint){
		for (int i = 0; i < COLUMN * ROW; i++) {
			if (getItem(i) != null) {
				canvas.drawBitmap(getItem(i), pointX(i), pointY(i), null);
			}
		}
		if (mSelected != -1) {
			float left = pointX(mSelected);
			float top = pointY(mSelected);
			canvas.drawRect(left + 5, top + 5, left + ITEMSIZE - 5, top
					+ ITEMSIZE - 5, paint);
		}
	}

	public  void drawLine(Canvas c,Paint paint) {
		if (mLine == null)
			return;
		List<Float> points = new ArrayList<Float>();
		for (int i = 0; i < mLine.size(); i++) {
			points.add(pointX(mLine.get(i))+ ITEMSIZE / 2);
			points.add(pointY(mLine.get(i))+ ITEMSIZE / 2);
			points.add(pointX(mLine.get(i))+ ITEMSIZE / 2);
			points.add(pointY(mLine.get(i))+ ITEMSIZE / 2);
		}
		for (int i = 2; i < points.size() - 2;) {
			c.drawLine(points.get(i++), points.get(i++), points.get(i++),
					points.get(i++), paint);
		}
	}
	private Bitmap getItem(int i) {
		return mBitmaps[mBoard[i]];
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
	private float pointX(int p) {
		int c = column(p);
		return mOffSetX + c * ITEMSIZE;
	}
	private float pointY(int p) {
		int r = row(p);
		return mOffSetY + r * ITEMSIZE;
	}


	private  Bitmap[] loadImages(int size) {
		int[] bitmapIds = {R.mipmap.a, R.mipmap.a, R.mipmap.b,
				R.mipmap.c, R.mipmap.d, R.mipmap.e, R.mipmap.f,
				R.mipmap.g, R.mipmap.h, R.mipmap.i, R.mipmap.j};
		Bitmap[] bitmaps = new Bitmap[bitmapIds.length];
		bitmaps[0] = null;
		for (int i = 1; i < bitmapIds.length; i++) {
			Drawable drawable =mBoardView.getResources().getDrawable(bitmapIds[i]);
			drawable.setBounds(1, 1, size - 1, size - 1);
			Bitmap b = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
			Canvas c = new Canvas(b);
			drawable.draw(c);
			bitmaps[i] = b;
		}
		return bitmaps;
	}


}
