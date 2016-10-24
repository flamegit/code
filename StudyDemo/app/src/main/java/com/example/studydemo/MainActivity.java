package com.example.studydemo;



import android.app.Activity;
import android.app.DialogFragment;

import android.app.Fragment;
import android.os.Bundle;

public class MainActivity extends Activity implements MenuFragment.GameListener {
	

	//GameService mService;
	Fragment mMenuFragemt;
	Fragment mContentFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		setContentView(R.layout.activity_main);
//		TextSwitcher score=(TextSwitcher)findViewById(R.id.score);
//		GridImageView board=(GridImageView) findViewById(R.id.board);
//		ProgressBar bar =(ProgressBar) findViewById(R.id.progressbar);
//		GameView gameView=new GameView(bar,score,board);
//		board.setService(mService);
//		mService=new GameService(gameView);
		if(savedInstanceState==null){
			if(mMenuFragemt==null){
				mMenuFragemt=MenuFragment.newInstance();
			}
			getFragmentManager().beginTransaction().add(android.R.id.content,mMenuFragemt)
					.commit();
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		//mBoard.setService(mService);
		//mService.start();
		//hander.sendMessageDelayed(Message.obtain(), 1000);
	}

	private void showDialog(){
		/*AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setMessage("Game Failed");
       // alertBuilder.setCancelable(false);
        AlertDialog dialog = alertBuilder.create();
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();*/
		DialogFragment dialog=new FailDialogFragment();
		dialog.show(getFragmentManager(), "fail");
		dialog.setCancelable(false);
	}

	@Override
	public void onSetting() {

	}

	@Override
	public void onStartGame(int resume) {
		mContentFragment=ContentFragment.newInstance(resume);


      this.getFragmentManager() .beginTransaction().replace(android.R.id.content,mContentFragment)
			   .addToBackStack(null).commit();

	}

	@Override
	public void onRestartGame() {
		if(mContentFragment==null){
			mContentFragment=ContentFragment.newInstance(GameService.RUNING);
		}
		this.getFragmentManager() .beginTransaction().replace(android.R.id.content,mContentFragment)
				.addToBackStack(null).commit();


	}

}
