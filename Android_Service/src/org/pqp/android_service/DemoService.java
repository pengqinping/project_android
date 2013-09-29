/*
 * 文  件  名:  DemoService.java
 * 创建日期:  2013-8-17/下午9:02:04
 * 版       权:  pengqinping@mail.com, All rights reserved
 * 作       者:  pengqinping
 * 座 右  铭:  想要看到璀璨的星空，就要忘记平趟的大地
 */
package org.pqp.android_service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
/**
 * 
 * @author <font color='#0f767334' size='4' bold=true>org.pengqinping</font> [Never give up, adhere to in the end.]<br>
 * @time [2013-9-29:下午9:31:11.]<br>
 * @description Service的使用<br>
 */
public class DemoService extends Service {

	@Override
	public IBinder onBind(Intent arg0) {
		Log.i("---","onBind.");
		return null;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}
	
	
	
}
