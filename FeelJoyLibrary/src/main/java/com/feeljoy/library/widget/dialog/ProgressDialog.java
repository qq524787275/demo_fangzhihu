package com.feeljoy.library.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.feeljoy.library.R;
import com.feeljoy.library.utils.DensityUtils;
import com.feeljoy.library.utils.LogUtils;


/**
 * 滚动加载条
 * @author cate
 * 2015-10-23 上午11:15:54   
 */

public class ProgressDialog extends Dialog implements BaseFzProgressDialog
{
	
	
	private ProgressWheel progress;
	
	
	/**
	 * 创建自定义style的progressdialog
	 * 
	 * @param context
	 * @param style
	 */
	public ProgressDialog(Context context, int style)
	{
		super(context, style);
		initView();
	}
	/**
	 * 创建自定义的progressdialog 默认style (style 为无边框 透明 无标题)
	 * 
	 * @param context
	 */
	public ProgressDialog(Context context)
	{
		this(context, R.style.ProgressDialog);
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
	public ProgressDialog(Context context, String message)
	{
		this(context, R.style.ProgressDialog, message);
	}
	
	/**
	 * 创建自定义style的的progressdialog
	 * 
	 * @param context
	 *            上下文
	 * @param message
	 *            提示消息
	 */
	public ProgressDialog(Context context, int style, String message)
	{
		this(context, style);
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
		FrameLayout frameLayout  = new FrameLayout(getContext());
		
		progress = new ProgressWheel(getContext());
		progress.setFillRadius(true);
		progress.spin();
		progress.setBarWidth(6);
		int width = DensityUtils.dip2px(getContext(), 60);
		
		progress.setBarColor(Color.parseColor("#5592FB"));
		frameLayout.addView(progress, new FrameLayout.LayoutParams(width,width));
		
		this.setContentView(frameLayout);
//		mMessage = (TextView) findViewById(R.id.tvLoad);
//		setShowMessage(message);
	}
	
	@Override
	public BaseFzProgressDialog setShowMessage(String msg)
	{

		
//		if (!TextUtils.isEmpty(msg))
//		{
//			mMessage.setText(msg);
//			mMessage.setVisibility(View.VISIBLE);
//		}
//		else
//		{
//			mMessage.setVisibility(View.GONE);
//		}
		
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

