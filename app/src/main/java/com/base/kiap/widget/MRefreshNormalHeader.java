package com.base.kiap.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.base.kiap.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * author : seven
 * e-mail : clx2216049367@163.com
 * date   : 2019/7/47:53 PM
 * desc   :默认样式的下拉头
 */
public class MRefreshNormalHeader extends LinearLayout implements RefreshHeader {

    private ProgressBar mImage;
    private TextView tvHeadState;
    private AnimationDrawable mAnimPull;
    private AnimationDrawable mAnimRefresh;

    /**
     * 1，构造方法
     */
    public MRefreshNormalHeader(Context context) {
        this(context, null, 0);
    }

    public MRefreshNormalHeader(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MRefreshNormalHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = View.inflate(context, R.layout.m_refresh_normal_header, this);
         tvHeadState = view.findViewById(R.id.tvHeadState);
        mImage = view.findViewById(R.id.header_progress);
    }

    /**
     * 2，获取真实视图（必须返回，不能为null）一般就是返回当前自定义的view
     */
    @NonNull
    @Override
    public View getView() {
        return this;
    }

    /**
     * 3，获取变换方式（必须指定一个：平移、拉伸、固定、全屏）,Translate指平移，大多数都是平移
     */
    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }


    /**
     * 5，一般可以理解为一下case中的三种状态，在达到相应状态时候开始改变
     * 注意：这三种状态都是初始化的状态
     */
    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            //1,下拉刷新的开始状态：下拉可以刷新
            case PullDownToRefresh:
                tvHeadState.setText(getContext().getString(R.string.pull_down_refresh));
                break;
            //2,下拉到最底部的状态：释放立即刷新
            case ReleaseToRefresh:
                tvHeadState.setText(getContext().getString(R.string.release_refresh));
               // mAnimPull = (AnimationDrawable) mImage.getDrawable();
                //mAnimPull.start();
                break;
            //3,下拉到最底部后松手的状态：正在刷新
            case Refreshing:
                tvHeadState.setText(getContext().getString(R.string.refreshing));
//                mAnimRefresh = (AnimationDrawable) mImage.getDrawable();
//                mAnimRefresh.start();
                break;
        }
    }

    /**
     * 6，结束下拉刷新的时候需要关闭动画
     *
     * @param refreshLayout
     * @param success
     * @return
     */
    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        if (mAnimRefresh != null && mAnimRefresh.isRunning()) {
            mAnimRefresh.stop();
        }
        if (mAnimPull != null && mAnimPull.isRunning()) {
            mAnimPull.stop();
        }
        return 0;
    }


    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onPullingDown(float percent, int offset, int headerHeight, int extendHeight) {

    }

    @Override
    public void onReleasing(float percent, int offset, int headerHeight, int extendHeight) {

    }

    @Override
    public void onRefreshReleased(RefreshLayout layout, int headerHeight, int extendHeight) {

    }
}