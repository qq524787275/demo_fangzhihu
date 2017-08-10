package com.fz.zhihunews.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.feeljoy.library.utils.DensityUtils;
import com.feeljoy.library.utils.SystemUtils;
import com.fz.zhihunews.R;

/**
 * @author cate 2015-1-6 下午3:27:46
 */

public class EmptyView extends LinearLayout
{
	
	private ImageView mImage;
	private TextView mTextTop;
	private TextView mBtn;
	
	public static final int MODE_LOADING = 0;
	public static final int MODE_NODATA = 1;
	public static final int MODE_ERROR = 2;
	
	@SuppressLint("NewApi")
	public EmptyView(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		initView();
	}
	
	public EmptyView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initView();
	}
	
	public EmptyView(Context context)
	{
		super(context);
		initView();
	}
	
	@SuppressLint("NewApi")
	private void initView()
	{
		setId(R.id.emptyview);
		this.setGravity(Gravity.CENTER);
		this.setOrientation(LinearLayout.VERTICAL);

		
		
		mImage = new ImageView(getContext());
		mImage.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		this.addView(mImage);
		
		mTextTop = new TextView(getContext());
		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
//		params.setMargins(0, DensityUtils.dip2px(getContext(), 10), 0, DensityUtils.dip2px(getContext(), 10));
		params.setMargins(0, DensityUtils.dip2px(getContext(), 20), 0, 0);
//		mTextTop.setPadding(DensityUtils.dip2px(getContext(), 25), 0, 0, 0);
		mTextTop.setLayoutParams(params);
		mTextTop.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
		this.addView(mTextTop);

		
		LayoutParams params4 = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		params4.setMargins(0, DensityUtils.dip2px(getContext(), 20), 0, 0);

		
		mBtn = new TextView(getContext());
	
		mBtn.setLayoutParams(params4);
		mBtn.setGravity(Gravity.CENTER);
		
		int padding = DensityUtils.dip2px(getContext(), 5);
		int paddingLeft = DensityUtils.dip2px(getContext(), 10);
		mBtn.setPadding(paddingLeft, padding, paddingLeft, padding);

		mBtn.setBackgroundResource(R.drawable.shape_refresh);
		this.addView(mBtn);
		mBtn.setVisibility(View.GONE);
		
		this.setMode(MODE_LOADING);
		
		this.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				
			}
		});
		
	}
	
	public void setBtnTextAndListener(String text, OnClickListener listener)
	{
		mBtn.setVisibility(View.VISIBLE);
		mBtn.setText(text);
		mBtn.setOnClickListener(listener);
	}
	
	public void setImageResource(int rId)
	{
		if (getVisibility() == View.GONE)
		{
			this.setVisibility(View.VISIBLE);
		}
		
		if (mImage.getVisibility()== View.GONE) {
			mImage.setVisibility(View.VISIBLE);
			
		}
		mImage.setImageResource(rId);
	}
	
	public void setText(String text)
	{
		if (getVisibility() == View.GONE)
		{
			this.setVisibility(View.VISIBLE);
		}
		mTextTop.setText(text);
		mTextTop.setTextColor(getResources().getColor(R.color.grey_96));
	}
	
	public void setMode(int mode)
	{
		if (getVisibility() == View.GONE)
		{
			this.setVisibility(View.VISIBLE);
		}
		
		switch (mode)
		{
			case MODE_LOADING:
				mTextTop.setText("努力加载中...");
				mTextTop.setTextColor(getResources().getColor(R.color.grey_96));
//				mImage.setImageResource(R.drawable.ic_jiazai);
				if (mImage.getVisibility()== View.VISIBLE) {
					mImage.setVisibility(View.GONE);
//				if (mImage.getVisibility()==View.GONE) {
//					mImage.setVisibility(View.VISIBLE);
				}
//				if(mTextCenter.getVisibility()==View.VISIBLE){
//					mTextCenter.setVisibility(View.INVISIBLE);
//				}if(mTextBottom.getVisibility()==View.VISIBLE){
//					mTextBottom.setVisibility(View.INVISIBLE);
//				}
				
				if(mBtn.getVisibility()== View.VISIBLE){
					mBtn.setVisibility(View.GONE);
				}
				
				// mAnimation.cancel();
				break;
			case MODE_NODATA:
				this.setText("未找到数据");
				// mAnimation.cancel();
				mImage.setVisibility(View.GONE);
//				if (mTextCenter.getVisibility() == View.VISIBLE) {
//					mTextCenter.setVisibility(View.INVISIBLE);
//				}
//				if (mTextBottom.getVisibility() == View.VISIBLE) {
//					mTextBottom.setVisibility(View.INVISIBLE);
//				}
				mBtn.setVisibility(View.GONE);
				break;
			case MODE_ERROR:
				mImage.setVisibility(View.VISIBLE);
				if (!SystemUtils.checkNet(getContext())){
					mTextTop.setText("未连接互联网～");
					mImage.setImageResource(R.drawable.ic_none_wifi);
				}else {
					mImage.setImageResource(R.drawable.ic_none_failed);
					mTextTop.setText("数据加载失败～");
				}


				mBtn.setVisibility(View.VISIBLE);
				mBtn.setTextColor(Color.WHITE);
				mBtn.setText("刷新重试");
				break;
			
			default:
				break;
		}
	}
	
	public ImageView getmImage()
	{
		return mImage;
	}
	
	public void setmImage(ImageView mImage)
	{
		this.mImage = mImage;
	}
	
	public TextView getmBtn()
	{
		return mBtn;
	}
	
	public void setmBtn(TextView mBtn)
	{
		this.mBtn = mBtn;
	}
	
	public TextView getmTextTop()
	{
		return mTextTop;
	}
	
	public void setmTextTop(TextView mTextTop)
	{
		this.mTextTop = mTextTop;
	}
	

	
}
