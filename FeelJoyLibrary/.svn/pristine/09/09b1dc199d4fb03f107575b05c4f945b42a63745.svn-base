package com.feeljoy.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Description: 正方形ImageView  多用于网格方式的布局的item为正方形的ImageView的场景
 * eg: GridView item分两列排列，每个item为屏幕宽度的一半的imageview
 *
 * Created by Cate on 2016/6/24.
 * Emial:liuh@80pm.com
 */
public class FourSquareImageView extends ImageView {
    private int mSize = 0;

    public FourSquareImageView(Context context) {
        this(context, null);
    }

    public FourSquareImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FourSquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //动态设置ImageView的高宽
    public void setSize(int size) {
        mSize = size;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mSize, mSize);
    }
}