/*
 * 文  件  名:  ResultActivity.java
 * 创建日期:  2013-8-3/下午9:14:22
 * 版       权:  pengqinping@mail.com, All rights reserved
 * 作       者:  pengqinping
 * 座 右  铭:  想要看到璀璨的星空，就要忘记平趟的大地
 */
package org.pqp.notification;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * 
 * @author org.pengqinping / Never give up, adhere to in the end.
 * email: pengqinping@gmail.com
 */
public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		tv.setText("ResultActivity");
		setContentView(tv);
	}
	
}
