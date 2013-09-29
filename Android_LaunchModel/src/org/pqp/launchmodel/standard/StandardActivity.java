package org.pqp.launchmodel.standard;

import org.pqp.launchmodel.BaseActivity;
import org.pqp.launchmodel.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * 
 * @author pengqinping
 * 在activity中跳转一个Activity,标准模式下的生命周期：
 * 1.this.onPause-->other.Oncreate-->other.Onstart-->other.Resume-->this.onStop
 * 2.锁屏的生命周期：黑屏：onPause,到解锁:onResuem
 * 
 */
public class StandardActivity extends BaseActivity{
	
	
	private static final String TAG = StandardActivity.class.getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(TAG," onCreate ");
		setContentView(R.layout.activity_main);
		
	}

	@Override
	public void handlerBtn() {
		Intent intent = new Intent(this,StandardTargetActivity.class);
		startActivity(intent);
	}
	
	@Override
	protected void onDestroy() {
		
		Log.i(TAG," onDestroy ");
		
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		
		Log.i(TAG," onPause ");
		
		super.onPause();
	}

	@Override
	protected void onRestart() {
		Log.i(TAG," onRestart ");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.i(TAG," onResume ");
		super.onResume();
	}

	@Override
	protected void onStart() {
		Log.i(TAG," onStart ");
		super.onStart();
	}

	@Override
	protected void onStop() {
		Log.i(TAG," onStop ");
		super.onStop();
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		Log.i(TAG," onNewIntent ");
		super.onNewIntent(intent);
	}
	
	
	
	

}
