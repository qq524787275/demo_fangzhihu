package com.feeljoy.library.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feeljoy.library.R;
import com.feeljoy.library.utils.LogUtils;


/**
 * 对话框 两种类型的对话框 带两个按钮（确定和取消）一个标题栏 按钮事件通过OnButtonClickListener进行监听 1、DialogType.DIALOG_TIPS 主体：消息栏。
 * 2、DialogType.DIALOG_INPUT 主体：输入框 的对话框 。
 * 
 * @author Administrator
 * 
 */
public class FzDialog extends Dialog
{
	private final static String TAG = "FzDialog";
	private TextView mTitle;
	private TextView mMessage;
	private TextView mCancle;
	private TextView mCommit;
	private TextView mClose;
	private OnButtonClickListener mButtonClickListener;
	
	private LinearLayout mDialogLL;
	
	private EditText mEdit;
	private DialogType Type = DialogType.DIALOG_TIPS;// 提示的dialog


	/**
	 * ProgressDialog
	 */
	public enum DialogType
	{
		DIALOG_TIPS, // 提示的dialog
		DIALOG_TIPS_ONEBUTTON, // 提示的dialog 只带一个按钮
		DIALOG_INPUT // 带输入的dialog
	}
	
	public FzDialog(Context context)
	{
		this(context, R.style.CustomDialog, DialogType.DIALOG_TIPS);
	}
	
	public FzDialog(Context context, String mMessage, DialogType type)
	{
		this(context, R.style.CustomDialog, type);
		this.setMessage(mMessage);
	}
	
	public FzDialog(Context context, String title, String mMessage, DialogType type)
	{
		this(context, R.style.CustomDialog, type);
		this.setMessage(mMessage);
		this.setTitle(title);
	}
	
	
	/**
	 * 
	 * @param context
	 * @param mMessage
	 * @param mButtonClickListener
	 */
	public FzDialog(Context context, String mMessage, OnButtonClickListener mButtonClickListener)
	{
		this(context, "", mMessage, mButtonClickListener);
	}
	
	/**
	 * 
	 * @param context
	 * @param mTitle
	 *            标题
	 * @param mMessage
	 *            消息
	 * @param mButtonClickListener
	 */
	public FzDialog(Context context, String mTitle, String mMessage, OnButtonClickListener mButtonClickListener)
	{
		this(context, R.style.CustomDialog, DialogType.DIALOG_TIPS);
		this.setTitle(mTitle);
		this.setMessage(mMessage);
		this.setOnButtonClickListener(mButtonClickListener);
	}
	
	public FzDialog(Context context, String mTitle, boolean isInput, OnButtonClickListener mButtonClickListener)
	{
		this(context, R.style.CustomDialog, DialogType.DIALOG_INPUT);
		this.setTitle(mTitle);
		this.setOnButtonClickListener(mButtonClickListener);
	}
	
	private FzDialog(Context arg0, int arg1, DialogType type)
	{
		super(arg0, arg1);
		this.Type = type;
		initView();
	}
	
	public FzDialog setCommitText(String text)
	{
		mCommit.setText(text);
		return this;
	}
	
	public FzDialog setCancleText(String text)
	{
		mCancle.setText(text);
		return this;
	}
	
	/**
	 * 
	 * @param text
	 * @return
	 */
	public FzDialog setCloseText(String text)
	{
		if (Type != DialogType.DIALOG_TIPS_ONEBUTTON)
		{
			LogUtils.e(TAG, "只有DIALOG_TIPS_ONEBUTTON 类型的dialog才能设置此项");
			return this;
		}
		mClose.setText(text);
		return this;
	}
	
	private void initView()
	{
		this.setContentView(R.layout.dialog_normal);
		mTitle = (TextView) findViewById(R.id.dialog_title);
		mCancle = (TextView) findViewById(R.id.dialog_cancel);
		mCommit = (TextView) findViewById(R.id.dialog_commit);
		mMessage = (TextView) findViewById(R.id.dialog_message);
		mClose = (TextView) findViewById(R.id.dialog_close);
		mDialogLL = (LinearLayout) findViewById(R.id.dialog_btnll);
		initEvent();
		switch (Type)
		{
			case DIALOG_TIPS:
				break;
			case DIALOG_INPUT:
				mEdit = (EditText) findViewById(R.id.dialog_input);
				mEdit.setVisibility(View.VISIBLE);
				mMessage.setVisibility(View.GONE);
				break;
			case DIALOG_TIPS_ONEBUTTON:
				mDialogLL.setVisibility(View.GONE);
				mClose.setVisibility(View.VISIBLE);
				break;
			default:
				break;
		}
		
	}
	
	public String getInputText()
	{
		if (Type == DialogType.DIALOG_INPUT)
		{
			return mEdit.getText().toString();
		}
		else
		{
			return "";
		}
	}
	
	public EditText getInput()
	{
		if (Type == DialogType.DIALOG_INPUT)
		{
			return mEdit == null ? null : mEdit;
		}
		else
		{
			LogUtils.e(TAG, "只有输入dialog(DialogType.DIALOG_INPUT)才可以调用getInput()");
			return null;
		}
	}
	
	private void initEvent()
	{
		mCancle.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if (mButtonClickListener != null)
				{
					mButtonClickListener.onCancleClick();
				}
				dismiss();
			}

		});
		mCommit.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if (mButtonClickListener != null)
				{
					mButtonClickListener.onCommitClick(getInputText());
				}
				dismiss();
			}

		});
		mClose.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View arg0)
			{
				if (mButtonClickListener != null)
				{
					mButtonClickListener.onCancleClick();
				}
				dismiss();
			}
			
		});
	}
	
	@Override
	public void setTitle(CharSequence title)
	{
		if (TextUtils.isEmpty(title))
		{
			mTitle.setVisibility(View.GONE);
		}
		else
		{
			mTitle.setText(title);
			mTitle.setVisibility(View.VISIBLE);
		}
	}
	
	public FzDialog setMessage(CharSequence message)
	{
		try
		{
			mMessage.setText(message);
		}
		catch (Exception e)
		{
			Log.e("FzDialog", e.getMessage());
		}
		return this;
	}
	
	public OnButtonClickListener getmButtonClickListener()
	{
		return mButtonClickListener;
	}
	
	/**
	 * 设置dialog的按扭监听事件
	 * 
	 * @param mButtonClickListener
	 */
	public void setOnButtonClickListener(OnButtonClickListener mButtonClickListener)
	{
		this.mButtonClickListener = mButtonClickListener;
	}
	
	public interface OnButtonClickListener
	{
		void onCancleClick();
		
		/**
		 * 
		 * @param inputtext
		 *            当dialog类型为 DIALOG_INPUT 时，该参数才有返回值
		 */
		void onCommitClick(String inputtext);
	}
}
