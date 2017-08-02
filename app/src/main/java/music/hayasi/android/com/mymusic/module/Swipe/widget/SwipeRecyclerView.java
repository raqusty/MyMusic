package music.hayasi.android.com.mymusic.module.Swipe.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class SwipeRecyclerView extends RecyclerView {


    int mLastXIntercept = 0;
    int mLastYIntercept = 0;
    //是否经过判断，这个标志位的左右：
    // 1：如果move的方向是左右，之后所有点都不拦截，
    // 2：如果是上下，会被拦截，就再不会调用这里了
    boolean isHasJudge = false;

    public SwipeRecyclerView(Context context) {
        this(context, null);
    }
    public SwipeRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercepted = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                intercepted = false;
                isHasJudge = false;
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                if (isHasJudge == true) {
                    return false;
                }
                int deltaX = x - mLastXIntercept;
                int deltaY = y - mLastYIntercept;
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    intercepted = false;
                    isHasJudge = true;
                } else {
                    intercepted = true;
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                intercepted = false;
                x = y = 0;
                isHasJudge = false;
                break;
            }
            default:
                break;
        }
        mLastXIntercept = x;
        mLastYIntercept = y;
        if (intercepted) {
            return true;
        } else {
            return super.onInterceptTouchEvent(ev);
        }
    }
}
