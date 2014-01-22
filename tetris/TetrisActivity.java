package com.example.tetris2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class TetrisActivity extends Activity {

	private TetrisView tetrisView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tetrisView = (TetrisView) this.findViewById(R.id.view);
		// tetrisView.setFocusable(true);
		tetrisView.setDependentViews(this.findViewById(R.id.container),
				(ShapeView) this.findViewById(R.id.nextview));
		tetrisView.setMode(TetrisView.READY);
		
	}
	

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		tetrisView.setMode(TetrisView.RUNNING);
	}


	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onStop();
		tetrisView.setMode(TetrisView.PAUSE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
