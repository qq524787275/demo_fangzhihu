package com.feeljoy.library.widget.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Description: 修复ViewPager和PhotoView结合使用时偶现的事件拦截bug
 * Created by Cate on 2016/6/24.
 * Emial:liuh@80pm.com
 */
public class FixViewPager  extends ViewPager{


    public FixViewPager(Context context) {
        super(context);
    }

    public FixViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            return super.onInterceptTouchEvent(ev);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
