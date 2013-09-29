/*
 * 文  件  名:  Logger.java
 * 创建日期:  2013-9-29:下午10:09:26.
 * 版       权:  pengqinping@mail.com, All rights reserved
 * 作       者:  pengqinping
 * 座 右  铭:  想要看到璀璨的星空，就要忘记平趟的大地
 */
package org.pqp.notification;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Environment;
import android.util.Log;

/**
 * 
 * @author <font color='#0f767334' size='4' bold=true>org.pengqinping</font> [Never give up, adhere to in the end.]<br>
 * @time [2013-9-29:下午10:13:50.]<br>
 * @description 日志管理类<br>
 */
public class Logger {
	/**
	 * 默认日志TAG
	 */
	private static final String TAG = Logger.class.getSimpleName();
	/**
	 * 是否允许W和E写入SD卡
	 */
	private static final boolean WRITE_W_D_TO_SDCARD = true;

	/**
	 * 是否允许V,I,D写入SD卡
	 */
	private static final boolean WRITE_V_I_D_TO_SDCARD = true;

	/**
	 * 时间格式
	 */
	private static final SimpleDateFormat SDF = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss ms");

	/**
	 * 异常日志大小限制在5M
	 */
	private static final float ERR_LOG_SIZE = 5 * 1024.0f;

	/**
	 * 是否允许debug日志
	 */
	public static boolean isLog = true;

	private Logger() {
	}

	private static String buildMsg(String msg) {
		StringBuilder buffer = new StringBuilder();
		Date date = new Date();
		String time = SDF.format(date);

		buffer.append(time);
		final StackTraceElement stackTraceElement = Thread.currentThread()
				.getStackTrace()[2];
		buffer.append(" [");
		buffer.append(Thread.currentThread().getName());
		buffer.append(":");
		buffer.append(stackTraceElement.getFileName());
		buffer.append(":");
		buffer.append(stackTraceElement.getLineNumber());
		buffer.append(":");
		buffer.append(stackTraceElement.getMethodName());
		buffer.append("()] ");
		buffer.append(msg);
		buffer.append("\n");
		return buffer.toString();
	}

	public static void v(String msg) {
		if (isLog && Log.isLoggable(TAG, Log.VERBOSE)) {
			String info = buildMsg(msg);
			Log.v(TAG, info);
			if (WRITE_V_I_D_TO_SDCARD) {
				writeFileToSD(info);
			}
		}
	}

	public static void v(String tag, String msg) {
		if (isLog && Log.isLoggable(TAG, Log.VERBOSE)) {
			String info = buildMsg(msg);
			Log.v(tag, info);
			if (WRITE_V_I_D_TO_SDCARD) {
				writeFileToSD(info);
			}
		}
	}

	public static void d(String msg) {
		if (isLog && Log.isLoggable(TAG, Log.DEBUG)) {
			String info = buildMsg(msg);
			Log.d(TAG, info);
			if (WRITE_V_I_D_TO_SDCARD) {
				writeFileToSD(info);
			}
		}
	}

	public static void d(String tag, String msg) {
		if (isLog && Log.isLoggable(TAG, Log.DEBUG)) {

			String info = buildMsg(msg);

			Log.d(tag, info);
			if (WRITE_V_I_D_TO_SDCARD) {
				writeFileToSD(info);
			}
		}
	}

	public static void i(String msg) {
		if (isLog && Log.isLoggable(TAG, Log.INFO)) {
			String info = buildMsg(msg);
			Log.i(TAG, info);
			if (WRITE_V_I_D_TO_SDCARD) {
				writeFileToSD(info);
			}
		}
	}

	public static void i(String tag, String msg) {
		if (isLog && Log.isLoggable(TAG, Log.INFO)) {
			String info = buildMsg(msg);
			Log.i(tag, info);
			if (WRITE_V_I_D_TO_SDCARD) {
				writeFileToSD(info);
			}
		}
	}

	public static void w(String msg) {
		if (isLog && Log.isLoggable(TAG, Log.WARN)) {
			String info = buildMsg(msg);
			Log.w(TAG, info);

			if (WRITE_W_D_TO_SDCARD) {
				writeFileToSD(info);
			}
		}
	}

	public static void w(String tag, String msg) {
		if (isLog && Log.isLoggable(TAG, Log.WARN)) {
			String info = buildMsg(msg);
			Log.w(tag, info);

			if (WRITE_W_D_TO_SDCARD) {
				writeFileToSD(info);
			}
		}
	}

	public static void w(String msg, Exception e) {
		if (isLog && Log.isLoggable(TAG, Log.WARN)) {
			String info = buildMsg(msg);
			Log.w(TAG, info, e);

			if (WRITE_W_D_TO_SDCARD) {
				writeFileToSD(info + getStackTrace(e));
			}
		}
	}

	public static void w(String tag, String msg, Exception e) {
		if (isLog && Log.isLoggable(TAG, Log.WARN)) {
			String info = buildMsg(msg);
			Log.w(tag, info, e);

			if (WRITE_W_D_TO_SDCARD) {
				writeFileToSD(info + getStackTrace(e));
			}
		}
	}

	public static void e(String msg) {
		if (isLog && Log.isLoggable(TAG, Log.ERROR)) {
			String info = buildMsg(msg);
			Log.e(TAG, info);

			if (WRITE_W_D_TO_SDCARD) {
				writeFileToSD(info);
			}
		}
	}

	public static void e(String tag, String msg) {
		if (isLog && Log.isLoggable(TAG, Log.ERROR)) {
			String info = buildMsg(msg);
			Log.e(tag, info);

			if (WRITE_W_D_TO_SDCARD) {
				writeFileToSD(info);
			}
		}
	}

	public static void e(String msg, Exception e) {
		if (isLog && Log.isLoggable(TAG, Log.ERROR)) {
			String info = buildMsg(msg);
			Log.e(TAG, info, e);

			if (WRITE_W_D_TO_SDCARD) {
				writeFileToSD(info + getStackTrace(e));
			}
		}
	}

	public static void e(String tag, String msg, Exception e) {
		if (isLog && Log.isLoggable(TAG, Log.ERROR)) {
			String info = buildMsg(msg);
			Log.e(tag, info, e);

			if (WRITE_W_D_TO_SDCARD) {
				writeFileToSD(info + getStackTrace(e));
			}
		}
	}

	public static String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw, true);
		t.printStackTrace(pw);
		pw.flush();
		sw.flush();
		return sw.toString();
	}

	private static void writeFileToSD(String context) {
		RandomAccessFile raf = null;
		String sdStatus = Environment.getExternalStorageState();
		if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) {
			Log.d(TAG, "SD card is not avaiable right now.");
			return;
		}
		try {
			String pathName = "/sdcard/Android_Service/";
			String fileName = "service.log";
			File path = new File(pathName);
			File file = new File(pathName + fileName);
			if (!path.exists()) {
				Log.d(TAG, "Create the path:" + pathName);
				path.mkdir();
			}
			if (!file.exists()) {
				Log.d(TAG, "Create the file:" + fileName);
				file.createNewFile();
			}

			raf = new RandomAccessFile(file, "rw");
			if (file.length() > ERR_LOG_SIZE) {
				raf.seek(0);
			} else {
				raf.seek(file.length());
			}

			raf.write(context.getBytes());
		} catch (Exception e) {
			Log.e(TAG, "Error to write SD card.");
		} finally {
			if (null != raf) {
				try {
					raf.close();
				} catch (IOException e) {
					Log.e(TAG, "close raf exception, msg=" + e.toString());
				}
			}
		}
	}

}
