/*
 * 文  件  名:  MainActivity.java
 * 创建日期:  2013-8-1/下午9:14:22
 * 版       权:  pengqinping@mail.com, All rights reserved
 * 作       者:  pengqinping
 * 座 右  铭:  想要看到璀璨的星空，就要忘记平趟的大地
 */
package org.pqp.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * 
 * @author <font color='#0f767334' size='4' bold=true>org.pengqinping</font> [Never give up, adhere to in the end.]<br>
 * @time [2013-9-29:下午9:29:31.]<br>
 * @description android notification (通知)<br>
 */
public class MainActivity extends Activity {

	private static final int NOTIFIFIXID = 1;

	private static final String ACTION_PALY_MUSIC = "org.pqp.notification.playMusic";

	private static final String ACTION_NEXT_MUSIC = "org.pqp.notification.playNext";

	private Button btnShowNotification;

	private Button btnShowNotifactionWithId;

	private Button btnShowNotifactionRemoteView;

	private Button btnNotificationNewBig;

	private Button btnNotificationNew;

	private Button btnNotificationUpdateMsg;

	private Button btnNotificationActivity;

	private int notifiId = NOTIFIFIXID + 1;

	private int numId = 0;

	private RemoteClickRecevice remoteClickRecevice;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnShowNotification = (Button) findViewById(R.id.btnShowNotifaction);
		btnShowNotifactionWithId = (Button) findViewById(R.id.btnShowNotifactionWithId);
		btnShowNotifactionRemoteView = (Button) findViewById(R.id.btnShowNotifactionRemoteView);
		btnNotificationNew = (Button) findViewById(R.id.btnNotificationNew);
		btnNotificationNewBig = (Button) findViewById(R.id.btnNotificationNewBig);
		btnNotificationUpdateMsg = (Button) findViewById(R.id.btnNotificationUpdateMsg);
		btnNotificationActivity = (Button) findViewById(R.id.btnNotificationActivity);

		btnShowNotification.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showNotifi(MainActivity.this);
			}
		});

		btnShowNotifactionWithId.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showNotifi(MainActivity.this, notifiId++);
			}
		});

		btnShowNotifactionRemoteView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showNotifiMyself(MainActivity.this);
			}
		});

		btnNotificationNew.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showNotifiNews(MainActivity.this);
			}
		});

		btnNotificationNewBig.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showNotifiBigViewStyle(MainActivity.this);
			}
		});

		btnNotificationUpdateMsg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showNotifiRule(MainActivity.this);
			}
		});

		btnNotificationActivity.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showNotifiGood(MainActivity.this);
			}
		});

		//实例化广播
		remoteClickRecevice = new RemoteClickRecevice();
		IntentFilter intentFileter = new IntentFilter();
		intentFileter.addAction(ACTION_PALY_MUSIC);
		intentFileter.addAction(ACTION_NEXT_MUSIC);
		registerReceiver(remoteClickRecevice, intentFileter);
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(remoteClickRecevice);
		super.onDestroy();
	}

	/**
	 * 
	 * @param context 传入context，这里可以是Service,BroadcaseRecevice,Activity.
	 */
	private void showNotifi(Context context, int id) {

		//获取NotificationManager对象，来管理通知消息的发送与取消
		NotificationManager notifiManger = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		//创建通知载体
		Notification notification = new Notification();

		//头上的图标
		notification.icon = R.drawable.ic_launcher;

		//添加振动式要添加权限，android.permission.VIBRATE；声音可以自定义格式，如果没有使用系统的默认通知声音，
		notification.defaults = Notification.DEFAULT_ALL;

		//自定义声音
		//要使用应用程序指定的声音，则传递一个Uri引用给sound属性。以下例子使用已保存在设备SD卡上的音频文件作为提示音：
		//notification.sound = Uri.parse("file:///sdcard/notification/ringer.mp3");
		//在下面的例子里，音频文件从内部MediaStore类的ContentProvider中获取：
		//注意：如果defaults属性包含了“DEFAULT_SOUND”，则缺省提示音将覆盖sound 属性里指定的声音。
		//如果期望在用户响应通知或取消通知前，声音一直持续循环播放，可以把 “FLAG_INSISTENT” 加入flags属性中。
		//notification.sound = Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6");

		//自定义闪光
		/*notification.ledARGB = 0xff00ff00; 

		notification.ledOnMS = 500; 

		notification.ledOffMS = 3000; 

		notification.flags |= Notification.FLAG_SHOW_LIGHTS;*/

		//自定义振动
		long[] vibrate = { 0, 100, 200, 300, 400, 500, 600, 700 };

		notification.vibrate = vibrate;

		//在flags属性中增加此标志，则将通知放入通知窗口的“正在运行”（Ongoing）组中。表示应用程序正在运行——进程仍在后台运行，即使应用程序不可见（比如播放音乐或接听电话）。
		//notification.flags |= Notification.FLAG_ONGOING_EVENT; 

		//“FLAG_NO_CLEAR”标志在flags属性中增加此标志，表示通知不允许被“清除通知”按钮清除。这在期望通知保持运行时特别有用。
		//notification.flags |= Notification.FLAG_NO_CLEAR;

		notification.tickerText = "最新消息，明天大幅度降温";

		//点击跳转的Intent
		PendingIntent pendingItent = PendingIntent.getActivity(context, 0,
				new Intent(this, MainActivity.class), 0);

		notification.setLatestEventInfo(context, "天气通知,通知的id:" + id,
				java.lang.System.nanoTime() + ":根据国家气象局发布的天气明天会有降温",
				pendingItent);

		//id,是通知的标示，如果id,不同就会发送
		notifiManger.notify(id, notification);
	}

	//固定的id,点击多次在通知栏之后有一条。
	private void showNotifi(Context context) {
		showNotifi(context, NOTIFIFIXID);
	}

	private void showNotifiMyself(Context context) {
		//获取NotificationManager对象，来管理通知消息的发送与取消
		NotificationManager notifiManger = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		//创建通知载体
		Notification notification = new Notification();

		RemoteViews remoteView = new RemoteViews(context.getPackageName(),
				R.layout.noti_myself);

		//RemoteView的点击事件,在3.0之前支持的视图很少，listview集合都不支持。
		PendingIntent palyPendingIntent = PendingIntent.getBroadcast(context,
				0, new Intent(ACTION_PALY_MUSIC), 0);
		remoteView.setOnClickPendingIntent(R.id.palyMusic, palyPendingIntent);

		PendingIntent nextPendingIntent = PendingIntent.getBroadcast(context,
				0, new Intent(ACTION_NEXT_MUSIC), 0);
		remoteView.setOnClickPendingIntent(R.id.nextMusic, nextPendingIntent);

		notification.contentView = remoteView;

		//点击跳转的Intent
		PendingIntent pendingItent = PendingIntent.getActivity(context, 0,
				new Intent(this, ResultActivity.class), 0);

		notification.contentIntent = pendingItent;

		//自定义视图必须设置的属性,要不然不会出现Notification
		//头上的图标
		notification.icon = R.drawable.ic_launcher;

		notification.tickerText = "最新消息，明天大幅度降温";

		notifiManger.notify(NOTIFIFIXID, notification);

	}

	/**
	 * 参考网址
	 * http://developer.android.com/guide/topics/ui/notifiers/notifications.html#NotificationUI
	 * http://www.cnblogs.com/tianjian/archive/2012/12/31/2840862.html
	 * @author Administrator / Never give up, adhere to in the end.
	 * email: pengqinping@gmail.com
	 * @param context
	 */
	private void showNotifiNews(Context context) {
		//获取NotificationManager对象，来管理通知消息的发送与取消
		NotificationManager notifiManger = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);

		//创建通知载体,在3.0之后不直接去new Notificationd对象 NotificationCompat.Builder
		Builder notifiBuilder = new NotificationCompat.Builder(this);

		//必须设置的3个属性
		notifiBuilder.setSmallIcon(R.drawable.app_window);
		notifiBuilder.setContentTitle("消息提醒");
		notifiBuilder.setContentText("您的电脑出问题了");

		//如果没有设置大图标，系统会默认取应用的图标。
		notifiBuilder.setContentInfo("123:" + numId);
		notifiBuilder.setNumber(++numId);
		notifiBuilder.setLargeIcon(BitmapFactory.decodeResource(
				context.getResources(), R.drawable.app_window));
		notifiBuilder.setLights(0xff00ff00, 300, 1000);
		notifiBuilder.setVibrate(new long[] { 0, 100, 200, 300, 400 });

		//new 一个堆栈对象
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(ResultActivity.class);
		Intent intent = new Intent(context, ResultActivity.class);

		//把这个intent放到栈顶，这样设置之后我们通过Notification跳转到Activity后，点击back键的时候直接回到Home Screen.
		stackBuilder.addNextIntent(intent);

		PendingIntent resultPending = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		notifiBuilder.setContentIntent(resultPending);

		Notification notification = notifiBuilder.build();
		notification.defaults = Notification.DEFAULT_SOUND;

		notifiManger.notify(NOTIFIFIXID, notification);
	}

	public void showNotifiBigViewStyle(Context context) {
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				this).setSmallIcon(R.drawable.app_window)
				.setContentTitle("中奖啦！！！")
				.setContentText("恭喜你在本期的彩票中获得50000百万的大奖。");

		mBuilder.setLargeIcon(BitmapFactory.decodeResource(
				context.getResources(), R.drawable.app_window));
		mBuilder.setTicker("怎么没有出来列！");

		//创建sytle
		NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
		String[] events = new String[6];

		// Sets a title for the Inbox style big view
		inboxStyle.setBigContentTitle("这个是大块哦");

		// Moves events into the big view
		for (int i = 0; i < events.length; i++) {
			events[i] = "message : " + i;
			inboxStyle.addLine(events[i]);
		}

		// Moves the big view style object into the notification object.
		mBuilder.setStyle(inboxStyle);

		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(NOTIFIFIXID, mBuilder.build());

	}

	private void showNotifiRule(Context context) {
		Intent resultIntent = new Intent(context, ResultActivity1.class);
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		// 添加后台堆栈
		stackBuilder.addParentStack(ResultActivity1.class);
		// 添加Intent到栈顶
		stackBuilder.addNextIntent(resultIntent);
		// 获得一个PendingIntent包含整个后台堆栈 containing the entire back stack
		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				context);
		//必须设置的3个属性
		builder.setSmallIcon(R.drawable.app_window);
		builder.setContentTitle("消息提醒");
		builder.setContentText("您的电脑出问题了");

		builder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(NOTIFIFIXID, builder.build());
	}

	private void showNotifiGood(Context context) {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				context);
		Intent notifyIntent = new Intent(context, ResultActivity2.class);
		notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				| Intent.FLAG_ACTIVITY_CLEAR_TASK);
		PendingIntent notifyIntentPending = PendingIntent.getActivity(context,
				0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		//必须设置的3个属性
		builder.setSmallIcon(R.drawable.app_window);
		builder.setContentTitle("消息提醒");
		builder.setContentText("您的电脑出问题了");
		builder.setContentIntent(notifyIntentPending);
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(NOTIFIFIXID, builder.build());
	}

	/**
	 * 接受远程视图的点击广播，
	 * @author Administrator / Never give up, adhere to in the end.
	 * email: pengqinping@gmail.com
	 */
	public class RemoteClickRecevice extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (ACTION_PALY_MUSIC.equals(action)) {
				alert("ok we are play Music!");
			} else if (ACTION_NEXT_MUSIC.equals(action)) {
				alert("ok let's go to next music !");
			}

		}

	}

	public void alert(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

}
