
package com.feeljoy.library.utils;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.feeljoy.library.http.FzConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

/**
 * @author cate 2014-12-2 下午2:51:48
 */

public class LogUtils
{
	/**
	 * 日志开关
	 */
	private static final boolean LOG_OPEN_DEBUG = FzConfig.Log.isDubug;
//	private static final boolean LOG_OPEN_DEBUG = BuildConfig.DEBUG;
	private static final boolean LOG_OPEN_POINT = FzConfig.Log.isPoint;
	
	/**
	 * 日志类型开关，必须 LOG_OPEN_DEBUG = true的时候才能启作用
	 */
	private static boolean logOpeni = true;
	private static boolean logOpend = true;
	private static boolean logOpenw = true;
	private static boolean logOpene = true;
	
	public static final String ROOT = TextUtils.isEmpty(FzConfig.Log.LogPath)?(Environment.getExternalStorageDirectory().getPath() + "/fz/"):FzConfig.Log.LogPath;
	
	
	/**
	 * 应用日志目录文件
	 */
	public static String APP_LOG_PATH = ROOT + "log/";
	
	/**
	 * 日志文件路径
	 */
	public static String LOGFILE = APP_LOG_PATH + "log.txt";
	
	/**
	 * 日志目录
	 */
	private static final String PATH_LOG_INFO = APP_LOG_PATH + "info/";
	private static final String PATH_LOG_WARNING = APP_LOG_PATH + "warning/";
	public static final String PATH_LOG_ERROR = APP_LOG_PATH + "error/";
	private static final String AUTHOR = "CATE ";
	public static final boolean ENABLE_DEBUG = false;
	
	public static void d(String tag, String message)
	{
		if (message != null && message != null)
		{
			if (LOG_OPEN_DEBUG && logOpend)
			{
				Log.d(tag, AUTHOR + message);
			}
			if (LOG_OPEN_POINT) point(PATH_LOG_INFO, tag, message);
		}
		
	}
	
	public static void i(String tag, String message)
	{
		if (message != null && message != null)
		{
			if (LOG_OPEN_DEBUG && logOpeni)
			{
				Log.i(tag, AUTHOR + message);
			}
			if (LOG_OPEN_POINT) point(PATH_LOG_INFO, tag, message);
		}
		
	}
	
	public static void w(String tag, String message)
	{
		if (message != null && message != null)
		{
			if (LOG_OPEN_DEBUG && logOpenw)
			{
				Log.w(tag, AUTHOR + message);
			}
			if (LOG_OPEN_POINT) point(PATH_LOG_WARNING, tag, message);
		}
		
	}
	
	public static void e(String tag, String message)
	{
		if (message != null && message != null)
		{
			if (LOG_OPEN_DEBUG && logOpene)
			{
				Log.e(tag, AUTHOR + message);
			}
			if (LOG_OPEN_POINT) point(PATH_LOG_ERROR, tag, message);
		}
		
	}
	
	public static void point(String path, String tag, String msg)
	{
		if (FileUtils.isSDExits())
		{
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("", Locale.SIMPLIFIED_CHINESE);
			dateFormat.applyPattern("yyyy");
			path = path + dateFormat.format(date) + "/";
			dateFormat.applyPattern("MM");
			path += dateFormat.format(date) + "/";
			dateFormat.applyPattern("dd");
			path += dateFormat.format(date) + ".log";
			dateFormat.applyPattern("[yyyy-MM-dd HH:mm:ss]");
			String time = dateFormat.format(date);
			File file = new File(path);
			if (!file.exists()) FileUtils.createDipPath(path);
			BufferedWriter out = null;
			try
			{
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
				out.write(time + " " + tag + " " + msg + "\r\n");
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				try
				{
					if (out != null)
					{
						out.close();
					}
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
	

	
	/**
	 * A little trick to reuse a formatter in the same thread
	 */
	private static class ReusableFormatter
	{
		
		private Formatter formatter;
		private StringBuilder builder;
		
		public ReusableFormatter()
		{
			builder = new StringBuilder();
			formatter = new Formatter(builder);
		}
		
		public String format(String msg, Object... args)
		{
			formatter.format(msg, args);
			String s = builder.toString();
			builder.setLength(0);
			return s;
		}
	}
	
	private static final ThreadLocal<ReusableFormatter> thread_local_formatter = new ThreadLocal<ReusableFormatter>()
	{
		protected ReusableFormatter initialValue()
		{
			return new ReusableFormatter();
		}
	};
	
	public static String format(String msg, Object... args)
	{
		ReusableFormatter formatter = thread_local_formatter.get();
		return formatter.format(msg, args);
	}
	
}
