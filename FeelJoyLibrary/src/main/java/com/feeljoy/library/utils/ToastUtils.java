
package com.feeljoy.library.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author cate 2014-12-4 下午5:47:07
 */

public class ToastUtils
{
	private static Toast mToast;
	private static Context mContext;
	private static boolean isInint = false;
	private static final String Tag = "ToastUtils";
	
	/**
	 * 在application里初始化,保证全局只有一个Toast
	 * 
	 * @param pContext
	 */
	public static void init(Context pContext)
	{
		mContext = pContext;
		isInint = true;
	}
	
	public static void showLongToast(int msg)
	{
		showLongToast(mContext.getResources().getString(msg));
	}
	
	public static void showLongToast(CharSequence msg)
	{
		if (isInint)
		{
			if (mToast == null)
			{
				mToast = Toast.makeText(mContext, msg, Toast.LENGTH_LONG);
			}
			else
			{
				mToast.setDuration(Toast.LENGTH_LONG);
				mToast.setText(msg);
			}
			mToast.show();
		}
		else
		{
			printLog();
		}
		
	}
	
	public static void showShortToast(int msg)
	{
		showShortToast(mContext.getResources().getString(msg));
	}
	
	public static void showShortToast(String msg)
	{
		if (isInint)
		{
			if (mToast == null)
			{
				mToast = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
			}
			else
			{
				mToast.setDuration(Toast.LENGTH_SHORT);
				mToast.setText(msg);
			}
			mToast.show();
		}
		else
		{
			printLog();
		}
		
	}
	
	/**
	 * 弹出自定义时间的Toast
	 * @param msg
	 * @param duration
	 */
	public static void showToast(String msg, int duration)
	{
		if (isInint)
		{
			if (mToast == null)
			{
				mToast = Toast.makeText(mContext, msg, duration);
			}
			else
			{
				mToast.setDuration(duration);
				mToast.setText(msg);
			}
			
			mToast.show();
		}
		else
		{
			printLog();
		}
		
	}
	public static void canlce()
	{
		if (isInint)
		{
			mToast.cancel();
		}
		
	}
	
	private static void printLog()
	{
		LogUtils.e(Tag, "Toast还未初始化，请在Application中调用ToastUtils.init()进行初始化");
	}
	
}
