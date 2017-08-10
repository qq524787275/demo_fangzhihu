
package com.feeljoy.library.widget.tab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.feeljoy.library.R;
import com.feeljoy.library.utils.DensityUtils;


public class FzTab extends FrameLayout implements OnFocusChangeListener
{
	private TextView mTextView;
	private int mMsgcount = 0;
	private TextView mMessage;
	private ImageView mNew;
	private static final int DEFAULT_CORNER_RADIUS_DIP = 8;
	private int badgeColor;
	private static final int DEFAULT_BADGE_COLOR = Color.parseColor("#CCFF0000"); //Color.RED;
	
	
	
	private float scale;
	
	@SuppressLint("NewApi")
	public FzTab(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
	{
		super(context, attrs, defStyleAttr, defStyleRes);
		initTabWidget(attrs);
	}
	
	public FzTab(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		initTabWidget(attrs);
	}
	
	public FzTab(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initTabWidget(attrs);
	}
	
	public FzTab(Context context)
	{
		super(context);
		initTabWidget(null);
	}
	
	@SuppressLint("NewApi")
	private void initTabWidget(AttributeSet attrs)
	{
//		LinearLayout linearLayout = new LinearLayout(getContext());
		mTextView = new TextView(getContext(), attrs);
		mTextView.setGravity(Gravity.CENTER);
		mTextView.setSingleLine(true);
	
		
		if (Build.VERSION.SDK_INT<16)
		{
			mTextView.setBackgroundDrawable(null);
		}else {
			mTextView.setBackground(null);
		}
		
//		LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(0, LayoutParams.WRAP_CONTENT, 1);
//		linearLayout.addView(mTextView, textParams);
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, Gravity.CENTER);
//		this.addView(linearLayout, params);


		FrameLayout content = new FrameLayout(getContext());
		content.addView(mTextView,params);



		this.addView(content, params);

		mMessage = new TextView(getContext());
		mMessage.setTextColor(Color.WHITE);
		mMessage.setGravity(Gravity.CENTER);
		scale = getContext().getResources().getDisplayMetrics().density;
		mMessage.setMinWidth((int) (16 * scale + 0.5f));
		mMessage.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
		mMessage.setBackgroundResource(R.drawable.shape_count);


		badgeColor = DEFAULT_BADGE_COLOR;

		if (Build.VERSION.SDK_INT<16)
		{
			mMessage.setBackgroundDrawable(getDefaultBackground());
		}else {
			mMessage.setBackground(getDefaultBackground());
		}

		mMessage.setVisibility(View.INVISIBLE);
		int padding = (int) (2 * scale + 0.5f);
		mMessage.setPadding(padding, 0, padding, 0);

		mNew = new ImageView(getContext());
		mNew.setImageResource(R.drawable.shape_tiny_count);
		mNew.setVisibility(View.INVISIBLE);

//		LinearLayout mMessageLayout = new LinearLayout(getContext());
//		padding = (int) (5 * scale + 0.5f);
//		mMessageLayout.setPadding(padding, padding, padding, padding);
//		FrameLayout.LayoutParams msgParams1 = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,
//				LayoutParams.MATCH_PARENT);
//		mMessageLayout.setGravity(Gravity.RIGHT | Gravity.TOP);
//		LinearLayout.LayoutParams msgParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
//				(int) (16 * scale + 0.5f));
//
////		mMessageLayout.addView(mMessage, msgParams);
//		LinearLayout.LayoutParams msgParams33 = new LinearLayout.LayoutParams((int) (10* scale + 0.5f),
//				(int) (10 * scale + 0.5f));
//		mMessageLayout.addView(mNew,msgParams33);


//		this.addView(mMessageLayout, msgParams1);

//		FrameLayout.LayoutParams msg = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
//				LayoutParams.WRAP_CONTENT,Gravity.RIGHT|Gravity.TOP);


		LayoutParams msgParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
				(int) (16 * scale + 0.5f),Gravity.RIGHT|Gravity.TOP);

		LayoutParams msg = new LayoutParams((int) (8* scale + 0.5f),
				(int) (8 * scale + 0.5f),Gravity.RIGHT|Gravity.TOP);
		
		msgParams.leftMargin = 10;
		msg.leftMargin = 10;
		content.getMeasuredWidth();
		content.getWidth();
		content.addView(mMessage,msgParams);
		content.addView(mNew,msg);
		
		
		
		this.setOnFocusChangeListener(this);
	}
	
	@Override
	public void setSelected(boolean selected)
	{
		super.setSelected(selected);
		mTextView.setSelected(selected);
	}
	
	public void setText(CharSequence txt){
		mTextView.setText(txt);
	}
	
	private ShapeDrawable getDefaultBackground() {
		int r = DensityUtils.dip2px(getContext(), DEFAULT_CORNER_RADIUS_DIP);
		float[] outerR = new float[] {r, r, r, r, r, r, r, r};
		RoundRectShape rr = new RoundRectShape(outerR, null, null);
		ShapeDrawable drawable = new ShapeDrawable(rr);
		drawable.getPaint().setColor(badgeColor);
		return drawable;
	}
	
	
	/**
	 * 设置选项卡的消息条数
	 * @param msgCount
	 *            消息条数 为0时隐藏消息提示 大于等于100的显示99+ 其他则正常显示
	 */
	public void setMessageCount(int msgCount)
	{
		mMsgcount = msgCount;
		if (mMsgcount < 0)
		{
			mMessage.setVisibility(View.INVISIBLE);
			mNew.setVisibility(View.GONE);
		}
		else if (mMsgcount==0){
//			mMessage.setText("");
//			mMessage.setHeight((int) (2 * scale + 0.5f));
//			mMessage.setWidth((int) (2 * scale + 0.5f));
//			mMessage.setBackgroundResource(R.drawable.shape_tiny_count);
			mMessage.setVisibility(View.GONE);
			mNew.setVisibility(View.VISIBLE);
		}
		else if (msgCount >= 100)
		{
			mMessage.setText("99+");
			mMessage.setVisibility(View.VISIBLE);
			
			mNew.setVisibility(View.GONE);
		}
		else
		{
			mMessage.setText(mMsgcount + "");
			mMessage.setVisibility(View.VISIBLE);
			
			mNew.setVisibility(View.GONE);
		}
	}
	
	@Override
	public void onFocusChange(View v, boolean hasFocus)
	{
		System.out.println(hasFocus);
	}
	
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect)
	{
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}
}
