package com.juntian.basicapp.utils;

import android.annotation.SuppressLint;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by WQ on 2017/10/12.
 */

public class RecycleHelper {
    public static void setLinearLayoutManager(RecyclerView recyclerView, int orientation){
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(),orientation,false));
    }

    @SuppressLint("WrongConstant")
    public static void setVERTICAL(RecyclerView...recyclerViews ){
        for (RecyclerView recyclerView : recyclerViews) {
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL,false));
        }
    }
    public static void setHORIZONTAL(RecyclerView...recyclerViews ){
        for (RecyclerView recyclerView : recyclerViews) {
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL,false));
        }
    }
    public static void setGridLayoutManager(RecyclerView recyclerView, int spanCount){
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(),spanCount));
    }

    /**
     * RecyclerView禁止滑动
     */
    public static void recycleProhibitScroll(RecyclerView... recyclerView) {
        for (RecyclerView mRecyclerView : recyclerView) {
            //解决滑动冲突、滑动不流畅
            mRecyclerView.setHasFixedSize(true);
            mRecyclerView.setNestedScrollingEnabled(false);
        }
    }
}
