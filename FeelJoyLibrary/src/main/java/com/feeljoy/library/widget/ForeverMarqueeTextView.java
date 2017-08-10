package com.feeljoy.library.widget;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 永动跑马灯效果TextView
 * @author Administrator
 *
 */
public class ForeverMarqueeTextView extends TextView{

	public ForeverMarqueeTextView(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public ForeverMarqueeTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ForeverMarqueeTextView(Context context) {
		super(context);
		init();
	}
	

	
	private void init()
	{
		setSingleLine(true);
		setEllipsize(TruncateAt.MARQUEE);
	
	}

	@Override
	public boolean isFocused() {
		return true;
	}

}
