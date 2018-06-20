package com.administrator.shopkeepertablet.utils;

import android.util.Log;

/**
 * Description:
 * Author CC
 * Time 2018/6/11
 */
public class MLog {
	
	public static final boolean DEBUG_LOG = true;
	public static final String symbol = "------>";

	public static void v(String tag, String msg){
		if (DEBUG_LOG) {
			Log.v(tag, symbol + msg);
		}
	}
	
	public static void d(String tag, String msg){
		if (DEBUG_LOG) {
			Log.d(tag, symbol + msg);
		}
	}
	
	public static void i(String tag, String msg){
		if (DEBUG_LOG) {
			Log.i(tag, symbol + msg);
		}
	}
	
	public static void w(String tag, String msg){
		if (DEBUG_LOG) {
			Log.w(tag, symbol + msg);
		}
	}
	
	public static void e(String tag, String msg){
		if (DEBUG_LOG) {
			Log.e(tag,symbol + msg);
		}
	}
}
