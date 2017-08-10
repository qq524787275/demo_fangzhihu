package com.feeljoy.library.base;

import android.view.View;

import java.util.Calendar;

/**
 * Description:防止多次点击 监听
 * Created by Cate on 2016/9/9.
 * Emial:liuh@80pm.com
 */
public abstract  class NoDoubleClickListener implements View.OnClickListener{
    public static final int MIN_CLICK_DELAY_TIME = 800;
    private long lastClickTime = 0;


    @Override
    public  void onClick(View v) {
        long currentTime = Calendar.getInstance().getTimeInMillis();
        if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
            lastClickTime = currentTime;
            onNoDoubleClick(v);
        }
    }
    public abstract void  onNoDoubleClick(View v);
}
