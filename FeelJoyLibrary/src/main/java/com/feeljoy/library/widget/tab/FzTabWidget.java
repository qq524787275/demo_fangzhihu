package com.feeljoy.library.widget.tab;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.LinearLayout;
import android.widget.TabHost;


public class FzTabWidget extends LinearLayout 
implements OnFocusChangeListener 
{
    private OnTabSelectionChanged mSelectionChangedListener;

    // This value will be set to 0 as soon as the first tab is added to TabHost.
    private int mSelectedTab = -1;

    private Drawable mLeftStrip;
    private Drawable mRightStrip;

    private boolean mDrawBottomStrips = true;
    private boolean mStripMoved;

    private final Rect mBounds = new Rect();



    public FzTabWidget(Context context) {
        this(context, null);
    }

    public FzTabWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTabWidget();
    }

    @SuppressLint("NewApi") public FzTabWidget(Context context, AttributeSet attrs, int defStyleAttr) {
    	super(context, attrs, defStyleAttr);
        initTabWidget();
    }



    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mStripMoved = true;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        if (mSelectedTab == -1) {
            return i;
        } else {
            // Always draw the selected tab last, so that drop shadows are drawn
            // in the correct z-order.
            if (i == childCount - 1) {
                return mSelectedTab;
            } else if (i >= mSelectedTab) {
                return i + 1;
            } else {
                return i;
            }
        }
    }

    private void initTabWidget() {
        setChildrenDrawingOrderEnabled(true);

        // Deal with focus, as we don't want the focus to go by default
        // to a tab other than the current tab
        setFocusable(true);
        setOnFocusChangeListener(this);
    }


    /**
     * Returns the tab indicator view at the given index.
     *
     * @param index the zero-based index of the tab indicator view to return
     * @return the tab indicator view at the given index
     */
    public View getChildTabViewAt(int index) {
        return getChildAt(index);
    }

    /**
     * Returns the number of tab indicator views.
     * @return the number of tab indicator views.
     */
    public int getTabCount() {
        return getChildCount();
    }

    /**
     * Sets the drawable to use as a divider between the tab indicators.
     * @param drawable the divider drawable
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB) @Override
    public void setDividerDrawable(Drawable drawable) {
        super.setDividerDrawable(drawable);
    }

    /**
     * Sets the drawable to use as a divider between the tab indicators.
     * @param resId the resource identifier of the drawable to use as a
     * divider.
     */
    public void setDividerDrawable(int resId) {
        setDividerDrawable(getContext().getResources().getDrawable(resId));
    }
    
    /**
     * Sets the drawable to use as the left part of the strip below the
     * tab indicators.
     * @param drawable the left strip drawable
     */
    public void setLeftStripDrawable(Drawable drawable) {
        mLeftStrip = drawable;
        requestLayout();
        invalidate();
    }

    /**
     * Sets the drawable to use as the left part of the strip below the
     * tab indicators.
     * @param resId the resource identifier of the drawable to use as the
     * left strip drawable
     */
    public void setLeftStripDrawable(int resId) {
        setLeftStripDrawable(getContext().getResources().getDrawable(resId));
    }

    /**
     * Sets the drawable to use as the right part of the strip below the
     * tab indicators.
     * @param drawable the right strip drawable
     */
    public void setRightStripDrawable(Drawable drawable) {
        mRightStrip = drawable;
        requestLayout();
        invalidate();
    }

    /**
     * Sets the drawable to use as the right part of the strip below the
     * tab indicators.
     * @param resId the resource identifier of the drawable to use as the
     * right strip drawable
     */
    /**
     * @hide
     */
    public void setRightStripDrawable(int resId) {
        setRightStripDrawable(getContext().getResources().getDrawable(resId));
    }

    /**
     * Controls whether the bottom strips on the tab indicators are drawn or
     * not.  The default is to draw them.  If the user specifies a custom
     * view for the tab indicators, then the TabHost class calls this method
     * to disable drawing of the bottom strips.
     * @param stripEnabled true if the bottom strips should be drawn.
     */
    /**
     * @hide
     */
    public void setStripEnabled(boolean stripEnabled) {
        mDrawBottomStrips = stripEnabled;
        invalidate();
    }

    /**
     * Indicates whether the bottom strips on the tab indicators are drawn
     * or not.
     */
    /**
     * @hide
     */
    public boolean isStripEnabled() {
        return mDrawBottomStrips;
    }

    @Override
    public void childDrawableStateChanged(View child) {
        if (getTabCount() > 0 && child == getChildTabViewAt(mSelectedTab)) {
            // To make sure that the bottom strip is redrawn
            invalidate();
        }
        super.childDrawableStateChanged(child);
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        // Do nothing if there are no tabs.
        if (getTabCount() == 0) return;
//
        // If the user specified a custom view for the tab indicators, then
        // do not draw the bottom strips.
        if (!mDrawBottomStrips) {
            // Skip drawing the bottom strips.
            return;
        }

    }

    /**
     * Sets the current tab.
     * This method is used to bring a tab to the front of the Widget,
     * and is used to post to the rest of the UI that a different tab
     * has been brought to the foreground.
     *
     * Note, this is separate from the traditional "focus" that is
     * employed from the view logic.
     *
     * For instance, if we have a list in a tabbed view, a user may be
     * navigating up and down the list, moving the UI focus (orange
     * highlighting) through the list items.  The cursor movement does
     * not effect the "selected" tab though, because what is being
     * scrolled through is all on the same tab.  The selected tab only
     * changes when we navigate between tabs (moving from the list view
     * to the next tabbed view, in this example).
     *
     * To move both the focus AND the selected tab at once, please use
     * {@link #setCurrentTab}. Normally, the view logic takes care of
     * adjusting the focus, so unless you're circumventing the UI,
     * you'll probably just focus your interest here.
     *
     *  @param index The tab that you want to indicate as the selected
     *  tab (tab brought to the front of the widget)
     *
     *  @see #focusCurrentTab
     */
    public void setCurrentTab(int index) {
        if (index < 0 || index >= getTabCount() || index == mSelectedTab) {
            return;
        }

        if (mSelectedTab != -1) {
            getChildTabViewAt(mSelectedTab).setSelected(false);
        }
        mSelectedTab = index;
        getChildTabViewAt(mSelectedTab).setSelected(true);
        mStripMoved = true;

        if (isShown()) {
            sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_SELECTED);
        }
    }

   /**
    * 设置tab选项卡的消息条数 
    * @param index   tab索引号 从0开始
    * @param msgCount   消息条数  为0时隐藏消息提示  大于等于100的显示99+ 其他则正常显示
    */
   public void setTabMessageCount(int index ,int msgCount){
	   if (index < 0 || index >= getTabCount() ) {
           return;
       }
	   
	   ((FzTab)getChildTabViewAt(index)).setMessageCount(msgCount);
   }
    
    
    
    
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH) @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        onPopulateAccessibilityEvent(event);
        // Dispatch only to the selected tab.
        if (mSelectedTab != -1) {
            View tabView = getChildTabViewAt(mSelectedTab);
            if (tabView != null && tabView.getVisibility() == VISIBLE) {
                return tabView.dispatchPopulateAccessibilityEvent(event);
            }
        }
        return false;
    }

//
    /**
     * Sets the current tab and focuses the UI on it.
     * This method makes sure that the focused tab matches the selected
     * tab, normally at {@link #setCurrentTab}.  Normally this would not
     * be an issue if we go through the UI, since the UI is responsible
     * for calling TabWidget.onFocusChanged(), but in the case where we
     * are selecting the tab programmatically, we'll need to make sure
     * focus keeps up.
     *
     *  @param index The tab that you want focused (highlighted in orange)
     *  and selected (tab brought to the front of the widget)
     *
     *  @see #setCurrentTab
     */
    public void focusCurrentTab(int index) {
        final int oldTab = mSelectedTab;
        // set the tab
        setCurrentTab(index);
        // change the focus if applicable.
        if (oldTab != index) {
            getChildTabViewAt(index).requestFocus();
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        final int count = getTabCount();
        for (int i = 0; i < count; i++) {
            View child = getChildTabViewAt(i);
            child.setEnabled(enabled);
        }
    }


    @Override
    public void addView(View child, int index,
    		ViewGroup.LayoutParams params) {

//    	super.addView(child, index, params);
        if (child.getLayoutParams() == null) {
            final LayoutParams lp = new LayoutParams(
                    0,
                    ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
            lp.setMargins(0, 0, 0, 0);
            child.setLayoutParams(lp);
        }

        // Ensure you can navigate to the tab with the keyboard, and you can touch it
        child.setFocusable(true);
        child.setClickable(true);
//        super.addView(child);
        // TODO: detect this via geometry with a tabwidget listener rather
        // than potentially interfere with the view's listener
        child.setOnClickListener(new TabClickListener(getTabCount() ));
        child.setOnFocusChangeListener(this);
        if (mSelectedTab==-1) {
			setCurrentTab(0);
		}
        super.addView(child, index, params);
    }


    @Override
    public void removeAllViews() {
        super.removeAllViews();
        mSelectedTab = -1;
    }

    /**
     * Provides a way for {@link TabHost} to be notified that the user clicked on a tab indicator.
     */
    public void setTabSelectionListener(OnTabSelectionChanged listener) {
        mSelectionChangedListener = listener;
    }

    /** {@inheritDoc} */
    public void onFocusChange(View v, boolean hasFocus) {
        if (v == this && hasFocus && getTabCount() > 0) {
            getChildTabViewAt(mSelectedTab).requestFocus();
            return;
        }

        if (hasFocus) {
            int i = 0;
            int numTabs = getTabCount();
            while (i < numTabs) {
                if (getChildTabViewAt(i) == v) {
                    setCurrentTab(i);
                    mSelectionChangedListener.onTabSelectionChanged(i, false);
                    if (isShown()) {
                        // a tab is focused so send an event to announce the tab widget state
                        sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
                    }
                    break;
                }
                i++;
            }
        }
    }

    
    // registered with each tab indicator so we can notify tab host
    private class TabClickListener implements OnClickListener {

        private final int mTabIndex;

        private TabClickListener(int tabIndex) {
            mTabIndex = tabIndex;
        }

        public void onClick(View v) {
        	setCurrentTab(mTabIndex);
        	if (mSelectionChangedListener!=null) {
				
        		mSelectionChangedListener.onTabSelectionChanged(mTabIndex, true);
			}
        }
    }

    /**
     * Let {@link TabHost} know that the user clicked on a tab indicator.
     */
    public interface OnTabSelectionChanged {
        /**
         * Informs the TabHost which tab was selected. It also indicates
         * if the tab was clicked/pressed or just focused into.
         *
         * @param tabIndex index of the tab that was selected
         * @param clicked whether the selection changed due to a touch/click
         * or due to focus entering the tab through navigation. Pass true
         * if it was due to a press/click and false otherwise.
         */
        void onTabSelectionChanged(int tabIndex, boolean clicked);
    }
    
    
    @Override
    protected void onFocusChanged(boolean gainFocus, int direction,
    		Rect previouslyFocusedRect) {

    	super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }
}
