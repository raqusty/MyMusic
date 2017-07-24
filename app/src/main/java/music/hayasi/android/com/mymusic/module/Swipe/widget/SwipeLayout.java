package music.hayasi.android.com.mymusic.module.Swipe.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class SwipeLayout extends LinearLayout {
    //检测手指在滑动过程中的速度
    private VelocityTracker mVelocityTracker;
    private Scroller mScroller;
    //上一次的触摸点
    private int mLastX, mLastY;
    //item是在否跟随手指移动
    private boolean isItemMoving;

    public SwipeLayout(Context context) {
        this(context, null);
    }

    public SwipeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mScroller = new Scroller(context, new LinearInterpolator());
        mVelocityTracker = VelocityTracker.obtain();
    }

    public SwipeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mVelocityTracker.addMovement(event);
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                return true;

            case MotionEvent.ACTION_MOVE:
                int dx = mLastX - x;
                int dy = mLastY - y;

                int scrollX = this.getScrollX();
                if (Math.abs(dx) > Math.abs(dy)) {//左边界检测
                    isItemMoving = true;
                    if (scrollX + dx <= 0) {
                        this.scrollTo(0, 0);
                        return true;
                    } else if (scrollX + dx >= this.getWidth()) {//右边界检测
                        this.scrollTo(this.getWidth(), 0);
                        return true;
                    }
                    this.scrollBy(dx, 0);//item跟随手指滑动
                }
                break;
            case MotionEvent.ACTION_UP:
//                if (!isItemMoving && !isDragging && mListener != null) {
//                    mListener.onItemClick(mItemLayout, mPosition);
//                }
                isItemMoving = false;

                mVelocityTracker.computeCurrentVelocity(1000);//计算手指滑动的速度
                float xVelocity = mVelocityTracker.getXVelocity();//水平方向速度（向左为负）
                float yVelocity = mVelocityTracker.getYVelocity();//垂直方向速度

                int deltaX = 0;
                int upScrollX = this.getScrollX();

                if (Math.abs(xVelocity) > 100 && Math.abs(xVelocity) > Math.abs(yVelocity)) {
                    if (xVelocity <= -100) {//左滑速度大于100，则删除按钮显示
                        deltaX = this.getWidth() - upScrollX;
//                        mDeleteBtnState = 2;
                    } else if (xVelocity > 100) {//右滑速度大于100，则删除按钮隐藏
                        deltaX = -upScrollX;
//                        mDeleteBtnState = 1;
                    }
                } else {
                    if (upScrollX >= this.getWidth() / 2) {//item的左滑动距离大于删除按钮宽度的一半，则则显示删除按钮
                        deltaX = this.getWidth() - upScrollX;
//                        mDeleteBtnState = 2;
                    } else if (upScrollX < this.getWidth() / 2) {//否则隐藏
                        deltaX = -upScrollX;
//                        mDeleteBtnState = 1;
                    }
                }

                //item自动滑动到指定位置
                mScroller.startScroll(upScrollX, 0, deltaX, 0, 200);
//                isStartScroll = true;
                invalidate();

                mVelocityTracker.clear();
                break;
        }

        mLastX = x;
        mLastY = y;
        return super.onTouchEvent(event);
    }

    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            this.scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
//        } else if (isStartScroll) {
//            isStartScroll = false;
//            if (mDeleteBtnState == 1) {
//                mDeleteBtnState = 0;
//            }
//
//            if (mDeleteBtnState == 2) {
//                mDeleteBtnState = 3;
//            }
        }
    }

    @Override
    protected void onDetachedFromWindow() {
//        mVelocityTracker.recycle();
        super.onDetachedFromWindow();
    }

}
