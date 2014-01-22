package com.example.tetris2;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class TetrisView extends android.view.View implements OnTouchListener {

	private Shape mCurrShape;
	private Shape mNextShape;
	private Block mBlock;
	private View mContainer;
	private ShapeView mShapeView;

	public final static Bitmap[] TILES = new Bitmap[4];
	public final static int CUBESIZE = 25;
	public final static int XCOUNT = 20;
	public final static int YCOUNT = 10;

	public final static int READY = 0;
	public final static int RUNNING = 1;
	public final static int PAUSE = 2;
	public final static int FINISH = 3;

	private final static int UP = 0;
	private final static int DOWN = 1;
	private final static int LEFT = 2;
	private final static int RIGHT = 3;

	public void setDependentViews(View container, ShapeView shapeView) {
		mContainer = container;
		mContainer.setOnTouchListener(this);
		mShapeView = shapeView;
	}

	private int state = READY;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				this.removeMessages(0);
				updateView();
				break;
			}
		}
	};

	public TetrisView(Context context) {
		super(context);
	}

	public void init() {
		mBlock = new Block();
		Resources res = this.getContext().getResources();
		loadTile(res.getDrawable(R.drawable.greenstar), 1);
		loadTile(res.getDrawable(R.drawable.redstar), 2);
		loadTile(res.getDrawable(R.drawable.yellowstar), 3);
	}

	private void initGame() {
		mShapeView.changeShape(mNextShape);
		mBlock.clear();
		mCurrShape = Shape.getInstance();
		mNextShape = Shape.getInstance();
	}

	public void loadTile(Drawable drawable, int key) {
		Bitmap bitmap = Bitmap.createBitmap(CUBESIZE, CUBESIZE,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, CUBESIZE, CUBESIZE);
		drawable.draw(canvas);
		TILES[key] = bitmap;
	}

	public TetrisView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void move(int direction) {
		if (mCurrShape.isConflict(mBlock, direction))
			return;
		switch (direction) {
		case 0:
			mCurrShape.changeDiretion();
			break;
		case 1:
			mCurrShape.down();
			break;
		case 2:
			mCurrShape.left();
			break;
		case 3:
			mCurrShape.right();
			break;
		}
	}

	public void setMode(int newMode) {
		state = newMode;
		switch (newMode) {
		case READY:
			initGame();
			break;
		case RUNNING:
			updateView();
			break;
		}
	}

	public void updateView() {
		if (state != RUNNING)
			return;
		if (mCurrShape.isConflict(mBlock, DOWN)) {
			mCurrShape.fillShape(mBlock);
			mCurrShape.recyle();
			mCurrShape = mNextShape;
			mNextShape = Shape.getInstance();
			//mShapeView.changeShape(mCurrShape);
		} else {
			move(DOWN);
		}
		invalidate();
		handler.sendMessageDelayed(handler.obtainMessage(0), 1000);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent msg) {
		move(keyCode - 19);
		return super.onKeyDown(keyCode, msg);
	}

	// @Override
	// protected void onSizeChanged(int w, int h, int oldw, int oldh) {
	// mXTileCount = w / CUBESIZE;
	// mYTileCount = h / CUBESIZE;
	//
	// mXOffSet = (w - CUBESIZE * mXTileCount) / 2;
	// mYOffSet = (h - CUBESIZE * mYTileCount) / 2;
	// mTileGrid = new int[mXTileCount][mYTileCount];
	// }
	private void drawBlock(Canvas canvas) {
		int colorIndex = 0;
		for (int i = 0; i < XCOUNT; i++)
			for (int j = 0; j < YCOUNT; j++) {
				if ((colorIndex = mBlock.get(i, j)) > 0)
					canvas.drawBitmap(TILES[colorIndex], j * CUBESIZE, i
							* CUBESIZE, null);
			}
	}

	private void drawLine(Canvas canvas, Paint paint) {
		for (int i = 0; i < XCOUNT + 1; i++)
			canvas.drawLine(0, i * CUBESIZE, YCOUNT * CUBESIZE, i * CUBESIZE,
					paint);
		for (int j = 0; j < YCOUNT + 1; j++)
			canvas.drawLine(j * CUBESIZE, 0, j * CUBESIZE, XCOUNT * CUBESIZE,
					paint);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		// Paint mPaint=new Paint();
		// mPaint.setColor(Color.GRAY);
		// Bitmap bitmap=Bitmap.createBitmap(this.getWidth(), this.getHeight(),
		// Bitmap.Config.ARGB_8888);
		// bitmap.s
		Paint paint = new Paint();
		paint.setColor(android.graphics.Color.WHITE);
		drawLine(canvas, paint);
		drawBlock(canvas);
		mCurrShape.draw(canvas);
		super.onDraw(canvas);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		float x = event.getX() / getWidth();
		float y = event.getY() / getHeight();

		// Direction will be [0,1,2,3] depending on quadrant
		int direction = 0;
		direction = (x > y) ? 2 : 0;
		direction |= (x > 1 - y) ? 1 : 0;

		// Direction is same as the quadrant which was clicked
		// mSnakeView.moveSnake(direction);

		move(direction);
		return false;
	}

}
