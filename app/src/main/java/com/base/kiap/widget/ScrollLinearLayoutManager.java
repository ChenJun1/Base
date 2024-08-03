package com.base.kiap.widget;

import android.content.Context;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @Author: June
 * @CreateDate: 1/5/21 5:34 PM
 * @Description: java类作用描述
 */
public class ScrollLinearLayoutManager extends LinearLayoutManager {
    private static final float MILLISECONDS_PER_INCH = 25f;
    public ScrollLinearLayoutManager(Context context) {
        super(context);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, final int position) {
        LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext())
        {

            @Nullable
            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return ScrollLinearLayoutManager.this.computeScrollVectorForPosition(targetPosition);
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                View view = getChildAt(0);
                if(view != null) {
                    final int firstChildPos = getPosition(getChildAt(0)); //获取当前item的position
                    int delta = Math.abs(position - firstChildPos);//算出需要滑动的item数量
                    if(delta == 0)
                        delta = 1;
                    return (MILLISECONDS_PER_INCH/delta) / displayMetrics.densityDpi;
                }
                else
                {
                    return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
                }
            }

        };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }
}
