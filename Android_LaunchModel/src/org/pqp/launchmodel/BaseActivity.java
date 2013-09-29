package org.pqp.launchmodel;

import org.pqp.launchmodel.singleinstant.SingleInstantActivity;
import org.pqp.launchmodel.singletask.SingleTaskActivity;
import org.pqp.launchmodel.singletop.SingleTopActivity;
import org.pqp.launchmodel.standard.StandardActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
/**
 * 
 * @author <font color='#0f767334' size='4' bold=true>org.pengqinping</font> [Never give up, adhere to in the end.]<br>
 * @time [2013-9-29:ÏÂÎç10:32:48.]<br>
 * @description <br>
 */
public class BaseActivity extends Activity implements OnClickListener {

	private EditText etText;

	private Button btnClick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		etText = (EditText) findViewById(R.id.tv_test);
		btnClick = (Button) findViewById(R.id.btn_click);
		findViewById(R.id.tvStandard).setOnClickListener(this);
		findViewById(R.id.tvSingleTop).setOnClickListener(this);
		findViewById(R.id.tvSingleTask).setOnClickListener(this);
		findViewById(R.id.tvSingleInstant).setOnClickListener(this);
		btnClick.setOnClickListener(this);
		((TextView) findViewById(R.id.tvMsg)).setText("this.addr:"
				+ this.toString() + ",taskId : " + this.getTaskId());
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tvStandard:
			Intent standardIntent = new Intent(this, StandardActivity.class);
			startActivity(standardIntent);
			break;
		case R.id.tvSingleTop:
			Intent singleTopIntent = new Intent(this, SingleTopActivity.class);
			startActivity(singleTopIntent);
			break;
		case R.id.tvSingleTask:
			Intent singleTaskIntent = new Intent(this, SingleTaskActivity.class);
			startActivity(singleTaskIntent);
			break;
		case R.id.tvSingleInstant:
			Intent singleInstantIntent = new Intent(this,
					SingleInstantActivity.class);
			startActivity(singleInstantIntent);
			break;
		case R.id.btn_click:
			handlerBtn();
			break;
		default:
			break;
		}
	}

	public EditText getEditText() {

		return etText;
	}

	public Button getButton() {
		return btnClick;
	}

	public void handlerBtn() {

	}
}
