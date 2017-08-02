package music.hayasi.android.com.mymusic.module.Swipe.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/*
* 本来打算用ViewDragHelper 来做的，但发现任意方向滑动还好，如果是单纯水平移动，就不行了
* 不知道为什么，只要稍微向下移动一下，就会触发垂直的移动，水平的移动就结束了
* */
public class SwipeView extends LinearLayout {

    ViewDragHelper mDragger;

    public SwipeView(Context context) {
        this(context, null);
    }

    public SwipeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return true;
            }

            @Override
            public int clampViewPositionHorizontal(View child, int left, int dx) {

//                Log.i("linzehao", "left " + left + "  dx  " + dx);
                return left;
            }

            //当边缘被触摸的时候调用
            public void onEdgeTouched(int edgeFlags, int pointerId) {
//                Log.i("linzehao", "onEdgeTouched");
            }

//            //关键方法：设置水平拖动的距离
//            public int getViewHorizontalDragRange(View child) {
//                return 20;
//            }

            //边缘设置不可用，
            public boolean onEdgeLock(int edgeFlags) {
//                Log.i("linzehao", "onEdgeLock");
                return false;
            }

        });
    }

    public SwipeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean s = mDragger.shouldInterceptTouchEvent(event);
        return s;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDragger.processTouchEvent(event);
        return true;
    }

}
