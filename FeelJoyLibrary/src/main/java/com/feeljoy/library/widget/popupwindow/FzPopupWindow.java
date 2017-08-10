package com.feeljoy.library.widget.popupwindow;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.feeljoy.library.R;


/**
 * IOS7样式的PopWindow
 * @author Administrator
 *
 */
public class FzPopupWindow extends Dialog implements View.OnClickListener{

	private static final int CANCEL_BUTTON_ID = 100;
	private OnMenuClicktListener mListener;
	private LinearLayout mPanel;
	private Attributes mAttrs;
	private CharSequence[] mOtherTitles;
	private String mCancleTitle;
	
	private Activity mContext;
	private boolean outSideClickble = false;
	
	public final static int THEME_IOS7 = R.style.PopupWindowStyleIOS7;
	
	/**
	 * 
	 * @param context 上下文
	 * @param otherTitles    item的标题数组
	 * @param theme    主题样式
	 */
	public FzPopupWindow(Activity context,String[] otherTitles ,int theme) {
		super(context, theme);
		this.mContext = context;
		if(otherTitles!=null && otherTitles.length!=0){
			setItems(otherTitles);
		}
		init();
		
	}
	/**
	 * 
	 * @param context        上下文
	 * @param cancleTitle    取消item的标题
	 * @param otherTitles    item的标题数组
	 * @param menuClickListener    item的按下监听
	 * @param theme          主题样式
	 */
	public FzPopupWindow(Activity context,String cancleTitle,CharSequence[] otherTitles,OnMenuClicktListener menuClickListener,int theme) {
		super(context,theme);
		this.mContext = context;
		
		if(otherTitles!=null && otherTitles.length!=0){
			setItems(otherTitles);
		}
		if(!TextUtils.isEmpty(cancleTitle)){
			setCancleTitle(cancleTitle);
		}
		if(menuClickListener!=null){
			setOnMenuClicktListener(menuClickListener);
		}
		init();
	}
	
	public FzPopupWindow(Activity context,CharSequence[] otherTitles,OnMenuClicktListener menuClickListener,int theme) {
		super(context, theme);
		this.mContext = context;
		if(otherTitles!=null && otherTitles.length!=0){
			setItems(otherTitles);
		}if(menuClickListener!=null){
			setOnMenuClicktListener(menuClickListener);
		}
		init();
		
	}
	public FzPopupWindow(Activity context,CharSequence[] otherTitles,OnMenuClicktListener menuClickListener) {
		super(context,  R.style.PopupWindowStyleIOS7);
		this.mContext = context;
		if(otherTitles!=null && otherTitles.length!=0){
			setItems(otherTitles);
		}if(menuClickListener!=null){
			setOnMenuClicktListener(menuClickListener);
		}
		init();
		
	}
	
	/**
	 * 自定义view的弹出Pop  从下方弹出
	 * @param context
	 * @param mView
	 */
	public FzPopupWindow(Activity context,View mView) {
		super(context, R.style.default_dialog);
		this.mContext = context;
		setContentView(mView);
		Window window = getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		int screenW = getScreenWidth(context);
		lp.width = screenW;
		window.setGravity(Gravity.BOTTOM); // dialog的位置
		window.setWindowAnimations(R.style.PopupWindowAnimation); // 动画效果
		
	}
	
	
	private void init(){
		initView();
		setOutSideClickble(true);
		Window window = getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();
		int screenW = getScreenWidth(mContext);
		lp.width = screenW;
		window.setGravity(Gravity.BOTTOM); // �˴���������dialog��ʾ��λ��
		window.setWindowAnimations(R.style.PopupWindowAnimation); // ��Ӷ���
	}
	
	private void initView(){
		InputMethodManager imm = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
		if (imm.isActive()) {
			View focusView = getCurrentFocus();
			if (focusView != null) {
				imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
			}
		}
		mAttrs = readAttribute();
		setContentView(createView());
		createItems();

	}
	
	
	private View createView() {
		mPanel = new LinearLayout(getContext());
		mPanel.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		mPanel.setOrientation(LinearLayout.VERTICAL);
		return mPanel;
	}
	@SuppressWarnings("deprecation")
	private void createItems() {
		if (mOtherTitles != null) {
			for (int i = 0; i < mOtherTitles.length; i++) {
				Button bt = new Button(getContext());
				bt.setId(CANCEL_BUTTON_ID + i + 1);
				bt.setOnClickListener(this);
				if (Build.VERSION.SDK_INT>=16)
				{
					bt.setBackground(getOtherButtonBg(mOtherTitles, i));
				}else {
					bt.setBackgroundDrawable(getOtherButtonBg(mOtherTitles, i));
				}
				bt.setText(mOtherTitles[i]);
				bt.setTextColor(mAttrs.otherButtonTextColor);
				bt.setTextSize(TypedValue.COMPLEX_UNIT_PX, mAttrs.actionSheetTextSize);
				if (i > 0) {
					LinearLayout.LayoutParams params = createButtonLayoutParams();
					params.topMargin = mAttrs.otherButtonSpacing;
					mPanel.addView(bt, params);
				} else {
					mPanel.addView(bt);
				}
			}
		}
		Button bt = new Button(getContext());
		bt.getPaint().setFakeBoldText(true);
		bt.setTextSize(TypedValue.COMPLEX_UNIT_PX, mAttrs.actionSheetTextSize);
		bt.setId(CANCEL_BUTTON_ID);
		
		if (Build.VERSION.SDK_INT>=16)
		{
			bt.setBackground(mAttrs.cancelButtonBackground);
		}else {
			bt.setBackgroundDrawable(mAttrs.cancelButtonBackground);
		}
		
		bt.setText(getCancleTitle());
		bt.setTextColor(mAttrs.cancelButtonTextColor);
		bt.setOnClickListener(this);
		LinearLayout.LayoutParams params = createButtonLayoutParams();
		params.topMargin = mAttrs.cancelButtonMarginTop;
		mPanel.addView(bt, params);

		
		if (Build.VERSION.SDK_INT>=16)
		{
			mPanel.setBackground(mAttrs.background);
		}else {
			mPanel.setBackgroundDrawable(mAttrs.background);
		}
		mPanel.setPadding(mAttrs.padding, mAttrs.padding, mAttrs.padding,
				mAttrs.padding);
	}
	
	private Drawable getOtherButtonBg(CharSequence[] titles, int i) {
		if (titles.length == 1) {
			return mAttrs.otherButtonSingleBackground;
		}
		if (titles.length == 2) {
			switch (i) {
			case 0:
				return mAttrs.otherButtonTopBackground;
			case 1:
				return mAttrs.otherButtonBottomBackground;
			}
		}
		if (titles.length > 2) {
			if (i == 0) {
				return mAttrs.otherButtonTopBackground;
			}
			if (i == (titles.length - 1)) {
				return mAttrs.otherButtonBottomBackground;
			}
			return mAttrs.getOtherButtonMiddleBackground();
		}
		return null;
	}
	
	public LinearLayout.LayoutParams createButtonLayoutParams() {
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		return params;
	}
	private static class Attributes {
		private Context mContext;
		public Attributes(Context context) {
			mContext = context;
			this.background = new ColorDrawable(Color.TRANSPARENT);
			this.cancelButtonBackground = new ColorDrawable(Color.BLACK);
			ColorDrawable gray = new ColorDrawable(Color.GRAY);
			this.otherButtonTopBackground = gray;
			this.otherButtonMiddleBackground = gray;
			this.otherButtonBottomBackground = gray;
			this.otherButtonSingleBackground = gray;
			this.cancelButtonTextColor = Color.WHITE;
			this.otherButtonTextColor = Color.BLACK;
			this.padding = dp2px(20);
			this.otherButtonSpacing = dp2px(2);
			this.cancelButtonMarginTop = dp2px(10);
			this.actionSheetTextSize = dp2px(16);
		}
		
		private int dp2px(int dp){
			return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
					dp, mContext.getResources().getDisplayMetrics());
		}

		public Drawable getOtherButtonMiddleBackground() {
			if (otherButtonMiddleBackground instanceof StateListDrawable) {
				TypedArray a = mContext.getTheme().obtainStyledAttributes(null,
						R.styleable.Popupwindow, R.attr.popupWindowStyle, 0);
				otherButtonMiddleBackground = a
						.getDrawable(R.styleable.Popupwindow_otherButtonMiddleBackground);
				a.recycle();
			}
			return otherButtonMiddleBackground;
		}

		Drawable background;
		Drawable cancelButtonBackground;
		Drawable otherButtonTopBackground;
		Drawable otherButtonMiddleBackground;
		Drawable otherButtonBottomBackground;
		Drawable otherButtonSingleBackground;
		int cancelButtonTextColor;
		int otherButtonTextColor;
		int padding;
		int otherButtonSpacing;
		int cancelButtonMarginTop;
		float actionSheetTextSize;
	}
	

	private Attributes readAttribute() {
		Attributes attrs = new Attributes(getContext());
		TypedArray a = getContext().getTheme().obtainStyledAttributes(null,
				R.styleable.Popupwindow, R.attr.popupWindowStyle, 0);
		Drawable background = a
				.getDrawable(R.styleable.Popupwindow_popupWindowBackground);
		if (background != null) {
			attrs.background = background;
		}
		Drawable cancelButtonBackground = a
				.getDrawable(R.styleable.Popupwindow_cancelButtonBackground);
		if (cancelButtonBackground != null) {
			attrs.cancelButtonBackground = cancelButtonBackground;
		}
		Drawable otherButtonTopBackground = a
				.getDrawable(R.styleable.Popupwindow_otherButtonTopBackground);
		if (otherButtonTopBackground != null) {
			attrs.otherButtonTopBackground = otherButtonTopBackground;
		}
		Drawable otherButtonMiddleBackground = a
				.getDrawable(R.styleable.Popupwindow_otherButtonMiddleBackground);
		if (otherButtonMiddleBackground != null) {
			attrs.otherButtonMiddleBackground = otherButtonMiddleBackground;
		}
		Drawable otherButtonBottomBackground = a
				.getDrawable(R.styleable.Popupwindow_otherButtonBottomBackground);
		if (otherButtonBottomBackground != null) {
			attrs.otherButtonBottomBackground = otherButtonBottomBackground;
		}
		Drawable otherButtonSingleBackground = a
				.getDrawable(R.styleable.Popupwindow_otherButtonSingleBackground);
		if (otherButtonSingleBackground != null) {
			attrs.otherButtonSingleBackground = otherButtonSingleBackground;
		}
		attrs.cancelButtonTextColor = a.getColor(
				R.styleable.Popupwindow_cancelButtonTextColor,
				attrs.cancelButtonTextColor);
		attrs.otherButtonTextColor = a.getColor(
				R.styleable.Popupwindow_otherButtonTextColor,
				attrs.otherButtonTextColor);
		attrs.padding = (int) a.getDimension(
				R.styleable.Popupwindow_popupWindowPadding, attrs.padding);
		attrs.otherButtonSpacing = (int) a.getDimension(
				R.styleable.Popupwindow_otherButtonSpacing,
				attrs.otherButtonSpacing);
		attrs.cancelButtonMarginTop = (int) a.getDimension(
				R.styleable.Popupwindow_cancelButtonMarginTop,
				attrs.cancelButtonMarginTop);
		attrs.actionSheetTextSize = a.getDimensionPixelSize(R.styleable.Popupwindow_popupWindowTextSize, (int) attrs.actionSheetTextSize);

		a.recycle();
		return attrs;
	}
	
	
	public  int getScreenWidth(Activity context) {
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.widthPixels;
	}

	public  int getScreenHeight(Activity context) {
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm.heightPixels;
	}
	

	public CharSequence[] getItems() {
		return mOtherTitles;
	}

	public void setItems(CharSequence[] items) {
		this.mOtherTitles = items;
	}
	
	public String getCancleTitle() {
		if(TextUtils.isEmpty(mCancleTitle)){
			return "取消";
		}
		return mCancleTitle;
	}

	public void setCancleTitle(String mCancleTitle) {
		this.mCancleTitle = mCancleTitle;
	}
	public OnMenuClicktListener getOnMenuClicktListener() {
		return mListener;
	}
	public void setOnMenuClicktListener(OnMenuClicktListener mListener) {
		this.mListener = mListener;
	}
	
	
	public interface OnMenuClicktListener {
		void onDismiss(FzPopupWindow actionSheet, boolean isCancel);
		void onOtherButtonClick(FzPopupWindow actionSheet, int index);
	}



	@Override
	public void onClick(View v) {
		if (v.getId() != CANCEL_BUTTON_ID ) {
			if (mListener != null) {
				mListener.onOtherButtonClick(this, v.getId() - CANCEL_BUTTON_ID
						- 1);
				dismiss();
			}
		}else if(v.getId() == CANCEL_BUTTON_ID){
			if (mListener != null) {
				mListener.onDismiss(this, true);
				dismiss();
			}
		}
	}
	
	
	public boolean isOutSideClickble() {
		return outSideClickble;
	}
	
	/**
	 * 点击外部是否取消
	 * @param outSideClickble   false 不取消 true 取消
	 */
	public void setOutSideClickble(boolean outSideClickble) {
		this.outSideClickble = outSideClickble;
		setCanceledOnTouchOutside(outSideClickble);
		
	}

}
