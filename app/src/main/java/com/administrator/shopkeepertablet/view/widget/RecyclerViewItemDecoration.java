package com.administrator.shopkeepertablet.view.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Description:
 * Author CC
 * Time 2018/6/14
 */
public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {
    private int space = 0;
    private int pos;

    public RecyclerViewItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = space;
        pos = parent.getChildAdapterPosition(view);
        outRect.left = space/2;
        outRect.right = space/2;
    }
}
