package com.feeljoy.library.widget.refreshview;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by Cate on 2016/3/29.
 */
public class PullToRecyclerView extends FrameLayout{


    public PullToRecyclerView(Context context) {
        super(context);
    }

    public PullToRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public PullToRecyclerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }




}
