
package com.feeljoy.library.utils;

import android.content.Context;
import android.database.Cursor;
import android.os.Environment;
import android.os.StatFs;
import android.provider.MediaStore.Images.Thumbnails;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileUtils
{
	/**
	 * 通过这个方法来添加要提交的文件。
	 * 
	 * @param file
	 *            提交的文件,如果文件为空或者不存在或者不可读，则不提交这个文件，重复的文件只提交一次。
	 */
	@SuppressWarnings("null")
	public static List<File> AddFileToFiles(File file)
	{
		List<File> files = null;
		if (file == null || !file.exists() || !file.canRead())
		{
			return files;
		}
		else
		{
			for (int i = 0; i < files.size(); i++)
			{
				if (file.getPath().equalsIgnoreCase(files.get(i).getPath())) { return files; }
			}
			files.add(file);
		}
		return files;
	}
	
	/**
	 * 获取sd卡的路径
	 * 
	 * @return
	 */
	public static String getSDPath()
	{
		boolean sdCardExist = isSDExits();
		if (sdCardExist)
		{
			File sdDir = Environment.getExternalStorageDirectory();
			return sdDir.toString() + "/";
		}
		
		return null;
	}
	
	/**
	 * SD卡是否存在
	 * @return
	 */
	public static boolean isSDExits(){
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	
	
	/**
	 * 检查sd卡是否可用
	 * 
	 * @param fileLength
	 * @return
	 */
	public static boolean checkSDStorageAvailable(long fileLength)
	{
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state))
		{
			File sdcardDir = Environment.getExternalStorageDirectory();
			StatFs sf = new StatFs(sdcardDir.getPath());
			long blockSize = sf.getBlockSize();
			long blockCount = sf.getBlockCount();
			long availCount = sf.getAvailableBlocks();
			if (availCount * blockSize >= fileLength) return true;
		}
		
		return false;
	}
	
	/**
	 * 根据文件路径 递归创建文件
	 * 
	 * @param file
	 */
	public static void createDipPath(String file)
	{
		String parentFile = file.substring(0, file.lastIndexOf("/"));
		File file1 = new File(file);
		File parent = new File(parentFile);
		if (!file1.exists())
		{
			parent.mkdirs();
			try
			{
				file1.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 获取系统dcim路径
	 * 
	 * @param context
	 * @return
	 */
	public static String getSystemDCIMPath(Context context)
	{
		
		String[] projection =
		{
				Thumbnails._ID, Thumbnails._ID, Thumbnails.IMAGE_ID, Thumbnails.DATA
		};
		Cursor cur = context.getContentResolver().query(Thumbnails.EXTERNAL_CONTENT_URI, projection, null, null, null);
		if (cur != null && cur.moveToFirst())
		{
			int dataColumn = cur.getColumnIndex(Thumbnails.DATA);
			do
			{
				String image_path = cur.getString(dataColumn);
				if (TextUtils.isEmpty(image_path) == false)
				{
					image_path = image_path.toLowerCase();
					String[] paths = image_path.split(".thumbnails");
					if (paths != null && paths.length > 0)
					{
						String dcimPath = paths[0];
						if (!TextUtils.isEmpty(dcimPath))
						{
							if (!dcimPath.endsWith(File.separator)) dcimPath += File.separator;
						}
						return dcimPath;
					}
				}
				break;
			}
			while (cur.moveToNext());
		}


		if (cur!=null){
			cur.close();
		}
		return null;
	}
}
