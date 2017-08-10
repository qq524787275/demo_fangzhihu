
package com.feeljoy.library.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.feeljoy.library.R;
import com.feeljoy.library.utils.LogUtils;


/**
 * @author cate 2014-12-3 下午6:09:06
 */
public class FzProgressDialog extends Dialog implements BaseFzProgressDialog
{
	private TextView mMessage;
	private String message="";
	/**
	 * 创建自定义style的progressdialog
	 * 
	 * @param context
	 * @param style
	 */
	public FzProgressDialog(Context context, int style)
	{
		super(context, style);
		initView();
	}
	/**
	 * 创建自定义的progressdialog 默认style (style 为无边框 透明 无标题)
	 * 
	 * @param context
	 */
	public FzProgressDialog(Context context)
	{
		this(context, R.style.CustomDialog);
		initView();
	}
	/**
	 * 创建自定义的progressdialog 默认style (style 为无边框 透明 无标题)
	 * 
	 * @param context
	 *            上下文
	 * @param message
	 *            提示消息
	 */
	public FzProgressDialog(Context context, String message)
	{
		this(context, R.style.CustomDialog, message);
	}
	
	/**
	 * 创建自定义style的的progressdialog
	 * 
	 * @param context
	 *            上下文
	 * @param message
	 *            提示消息
	 */
	public FzProgressDialog(Context context, int style, String message)
	{
		this(context, style);
		this.message = message;
		initView();
		
		
	}
	
	private void initView()
	{
		WindowManager.LayoutParams lp = getWindow().getAttributes();
		lp.dimAmount=0f;
//		lp.flags = WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
//                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
//                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
		setCanceledOnTouchOutside(false);
		this.setContentView(R.layout.dialog_progress);
		mMessage = (TextView) findViewById(R.id.tvLoad);
		setShowMessage(message);
	}
	
	@Override
	public BaseFzProgressDialog setShowMessage(String msg)
	{

		
		if (!TextUtils.isEmpty(msg))
		{
			mMessage.setText(msg);
			mMessage.setVisibility(View.VISIBLE);
		}
		else
		{
			mMessage.setVisibility(View.GONE);
		}
		
		return this;
	}
	
	@Override
	public BaseFzProgressDialog showProgress()
	{

		try
		{
			this.show();
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
		
		return this;
	}
	
	@Override
	public BaseFzProgressDialog dismissProgress()
	{

		try
		{
			this.dismiss();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			LogUtils.e("FzProgressDialog", e.getMessage());
		}
		return this;
	}
	@Override
	public boolean isDialogShowing()
	{
		return this.isShowing();
	}
	
	
}
