package com.feeljoy.library.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/** 
 * 一些数学计算   
 * @author cate
 * 2014-12-6 上午11:53:02   
 */

public class MathUtils
{
	
	private static final int DEF_DIV_SCALE = 10;
	/**
	* 提供精确的加法运算。
	* @param v1 被加数
	* @param v2 加数
	* @return 两个参数的和
	*/
	public static double add(double d1, double d2)
	{
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.add(b2).doubleValue();
	}
	
	/**
	* 提供精确的减法运算。
	* @param v1 被减数
	* @param v2 减数
	* @return 两个参数的差
	*/
	public static double sub(double d1, double d2)
	{
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.subtract(b2).doubleValue();
		
	}
	/**
	* 提供精确的乘法运算。
	* @param v1 被乘数
	* @param v2 乘数
	* @return 两个参数的积
	*/
	public static double mul(double d1, double d2)
	{
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.multiply(b2).doubleValue();
		
	}
	/**
	* 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
	* 小数点以后10位，以后的数字四舍五入。
	* @param v1 被除数
	* @param v2 除数
	* @return 两个参数的商
	*/
	public static double div(double d1, double d2)
	{
		return div(d1, d2, DEF_DIV_SCALE);
	}
	
	/**
	* 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	* 定精度，以后的数字四舍五入。
	* @param v1 被除数
	* @param v2 除数
	* @param scale 表示表示需要精确到小数点以后几位。
	* @return 两个参数的商
	*/
	public static double div(double d1, double d2, int scale)
	{
		if (scale < 0) { throw new IllegalArgumentException("The scale must be a positive integer or zero"); }
		BigDecimal b1 = new BigDecimal(Double.toString(d1));
		BigDecimal b2 = new BigDecimal(Double.toString(d2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		
	}
	
	
	/**
	 * 保存两位小数点
	 */
	public static double twolittercount(double point)
	{
		DecimalFormat df = new DecimalFormat("######0.00");
		point = Double.parseDouble(df.format(point));
		return point;
	}
	
	/**
	 * 保存两位小数点(按四舍五入)
	 */
	public static String twolittercountString(double point)
	{
		String result = String.format("%.2f", point);
		return result;
	}
	
	
	/**
	* 提供精确的小数位四舍五入处理。
	* @param v 需要四舍五入的数字
	* @param scale 小数点后保留几位
	* @return 四舍五入后的结果
	*/
	public static double round(double v, int scale) {
	   if (scale < 0) {
	    throw new IllegalArgumentException(
	      "The scale must be a positive integer or zero");
	   }
	   BigDecimal b = new BigDecimal(Double.toString(v));
	   BigDecimal ne = new BigDecimal("1");
	   return b.divide(ne, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
