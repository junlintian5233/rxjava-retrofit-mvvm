package com.junzhitian.basicapp.qmui.qmuiwidget.recyclerview;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int hspace, vspace, offect;
    boolean needTop;

    public SpaceItemDecoration(int hspace, int vspace) {
        this.hspace = hspace;
        this.vspace = vspace;
    }

    public SpaceItemDecoration setHeadOffect(int offect) {
        this.offect = offect;
        return this;
    }

    public SpaceItemDecoration setNeedTop(boolean needTop) {
        this.needTop = needTop;
        return this;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (childAdapterPosition < this.offect) return;
        //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager layoutManage = (GridLayoutManager) parent.getLayoutManager();
            if (needTop && childAdapterPosition - offect < layoutManage.getSpanCount()) {
                outRect.top = vspace;
            }
            childAdapterPosition = childAdapterPosition + Math.abs(layoutManage.getSpanCount() - offect);
            //不是第一个的格子都设一个左边和底部的间距
            outRect.right = hspace;
            outRect.bottom = vspace;
            if (childAdapterPosition % layoutManage.getSpanCount() == 0) {
                outRect.left = hspace;
            }
            Log.d("SpaceItemDecoration", "childAdapterPosition:"+childAdapterPosition+" getChildAdapterPosition" + parent.getChildAdapterPosition(view)+" outRect:"+outRect);

//            int column = childAdapterPosition % layoutManage.getSpanCount();
//            outRect.left =  hspace*layoutManage.getSpanCount() - column * hspace; // spacing - column * ((1f / spanCount) * spacing)
//            outRect.right = (column + 1) * hspace; //

//            outRect.left = column * hspace; // column * ((1f / spanCount) * spacing)
//            outRect.right = hspace*layoutManage.getSpanCount() - (column + 1) * hspace ; // spacing - (column + 1) *
        } else if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManage = (LinearLayoutManager) parent.getLayoutManager();
            if (needTop && childAdapterPosition - offect <= 0) {
                outRect.top = vspace;
            }
            if (layoutManage.getOrientation() == LinearLayoutManager.HORIZONTAL) {
                outRect.right = hspace;
            } else {
                outRect.bottom = vspace;
            }


        }

    }
}