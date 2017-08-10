package com.feeljoy.library.widget.pulltorefresh;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Description: recyclerview 空类型
 * Created by Cate on 2016/8/25.
 * Emial:liuh@80pm.com
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int space;
    private int columns;

    public SpaceItemDecoration(int space,int columns) {
        this.space = space;
        this.columns = columns;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = space;

        outRect.bottom = space;
        //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
        if (parent.getChildLayoutPosition(view) %columns==0) {
            outRect.left = 0;
        }
    }

}