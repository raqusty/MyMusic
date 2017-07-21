package music.hayasi.android.com.mymusic.module.QQReFresh.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import music.hayasi.android.com.mymusic.R;

/**
 * 一个仿iphone 下拉滑动的控件
 * 现在只有下拉，上拉还没有做
 * 思路：
 * 1：通过控制 headView 的margn（一开始是负数，就是top之上），通过ontouchEvent滑动的时候，设置margn 慢慢变成正数，就慢慢显示出来了
 * 2：回滚动画，当松手的时候，调用回滚动画
 * 3：因为可能嵌套的控件是可滑动控件，所以需要拦截滑动事件，如果是子控件是碰顶，且下拉，就把事件交给this处理
 * 4：提供 一个listten 来监听刷新的结束，可以用来作为触发刷新的点
 * 5：转圈是用Android原生的MaterialProgressDrawable ，我直接把它的源码复制出来调用了
 *
 * bug：
 * 1:还有各种刷新状态，没有处理好,
 * 2:上拉没有做，不过跟下拉一样的思路的
 */

public class QQReFreshLayout extends LinearLayout {

    private static final int CIRCLE_BG_LIGHT = 0xFFFAFAFA;
    static final int CIRCLE_DIAMETER = 40;

    /**
     * 收回到刷新位置状态
     */
    public static final int TAKEBACK_REFRESH = -1;
    /**
     * 收回到初始位置状态
     */
    public static final int TAKEBACK_RESET = -2;
    /**
     * 从头收到尾,不考虑中间状态
     */
    public static final int TAKEBACK_ALL = -3;

    /**
     * 收回状态
     */
    int mTakeBackState = TAKEBACK_RESET;

    /**
     * 下拉刷新状态
     */
    public static final int REFRESH_BY_PULLDOWN = 0;
    public static final int REFRESH_BY_RELEASE = 1;//松开刷新状态
    public static final int REFRESHING = 2;//正在刷新状态
    public static final int REFRESHING_SUCCESS = 3;//刷新成功状态
    public static final int REFRESHING_FAILD = 4;//刷新失败状态
    public static final int REFRESHING_RESET = 5;//重置状态
    public static final int REFRESHING_IDLE = 6;//空闲状态

    private float mLastY = 0;

    private LinearLayout mRefreshHeadView;
    private MaterialProgressDrawable mProgress;
    private ImageView mLoadImage;
    private TextView mRefreshText;

    private Context mContext;

    //这个值要转成dp
    private int refreshTargetTop = dp(-80);
    private boolean mIsButtom = false;
    private int mLastTop = refreshTargetTop;
    private static final float SCROLL_RATIO = 0.4f;// 阻尼系数,越小阻力就越大
    private int mRefreshState = REFRESHING_IDLE; //状态

    private ObjectAnimator anim;

    private RefreashListten mRefreashListten;

    public QQReFreshLayout(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public QQReFreshLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public QQReFreshLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        initView();
        initAnim();
    }

    private void initView() {
        mRefreshHeadView = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.qq_refresh_layout_head, null);
        mLoadImage = (ImageView) mRefreshHeadView.findViewById(R.id.refresh_image);
        mRefreshText = (TextView) mRefreshHeadView.findViewById(R.id.refresh_text);

        LayoutParams lp = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, -refreshTargetTop);
        lp.topMargin = refreshTargetTop;
        addView(mRefreshHeadView, lp);
        createProgressView();
    }

    private void initAnim() {
        anim = ObjectAnimator.ofFloat(mRefreshHeadView, "", 0.0f, 1.0f);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float cVal = (Float) valueAnimator.getAnimatedValue();
                LayoutParams lp = (LayoutParams) mRefreshHeadView.getLayoutParams();
                switch (mTakeBackState) {
                    case TAKEBACK_REFRESH:
                        lp.height = lp.height + (int) (cVal * (-refreshTargetTop - lp.height));
                        lp.topMargin = 0;
                        break;
                    case TAKEBACK_RESET:
                        lp.topMargin = lp.topMargin + (int) (cVal * (refreshTargetTop - lp.topMargin));
                        lp.height = -refreshTargetTop;
                        break;
                    case TAKEBACK_ALL:
                        lp.topMargin = lp.topMargin + (int) (cVal * (refreshTargetTop - lp.topMargin));
                        lp.height = lp.height + (int) (cVal * (-refreshTargetTop - lp.height));
                        //bezierView.reset((float) Math.pow(cVal, 2 / 5.0));
                        break;
                }

                mRefreshHeadView.setLayoutParams(lp);
                mRefreshHeadView.invalidate();
                invalidate();
                if (lp.height == -refreshTargetTop
                        && lp.topMargin == refreshTargetTop) {//动画完成
                    resetRefreshView();
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        int y = (int) e.getRawY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                if (y > mLastY && canScroll()) {
                    return true;
                }
                //记录下此刻y坐标
                this.mLastY = y;
                break;
        }
        return false;
    }

    private boolean canScroll() {
        View childView;
        if (getChildCount() > 1) {
            childView = this.getChildAt(1);
            if (childView instanceof ListView) {
                int top = ((ListView) childView).getChildAt(0).getTop();
                int pad = ((ListView) childView).getListPaddingTop();
                if ((Math.abs(top - pad)) < 3 &&
                        ((ListView) childView).getFirstVisiblePosition() == 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (childView instanceof ScrollView) {
                if (((ScrollView) childView).getScrollY() == 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (childView instanceof WebView) {
                if (((WebView) childView).getScrollY() == 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (childView instanceof GridView) {
                int top = ((GridView) childView).getChildAt(0).getTop();
                int pad = ((GridView) childView).getListPaddingTop();
                if ((Math.abs(top - pad)) < 3 &&
                        ((GridView) childView).getFirstVisiblePosition() == 0) {
                    return true;
                } else {
                    return false;
                }
            } else if (childView instanceof RecyclerView) {
                RecyclerView.LayoutManager manager = ((RecyclerView) childView).getLayoutManager();
                int top = 0;
                if (manager instanceof LinearLayoutManager) {
                    top = ((LinearLayoutManager) manager).findFirstVisibleItemPosition();
                } else if (manager instanceof StaggeredGridLayoutManager) {
                    top = ((StaggeredGridLayoutManager) manager).findFirstVisibleItemPositions(null)[0];
                }

                if (((RecyclerView) childView).getChildAt(0).getY() == 0 && top == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE://
                float distance = y - mLastY;
                if (distance > 0) {
                    doMoveEvent(distance, false);
                } else {
                    doMoveEvent(distance, true);
                }
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                //下拉碰底的回调
                //如果高度没有碰底 且 没有设置回调函数，就直接滚回去
                if (mIsButtom && mRefreashListten != null && mRefreshState == REFRESHING_IDLE) {
                    refreshing();
                    mRefreashListten.refreash();
                } else {
                    fling();
                }
                break;
        }
        return true;
    }

    //下拉的效果
    private void doMoveEvent(float distance, boolean ratto) {
        if (mRefreshState != REFRESHING_IDLE || anim.isRunning()) {
            return;
        }
        LayoutParams lp = (LayoutParams) mRefreshHeadView.getLayoutParams();
        if (ratto) {
            mLastTop += (distance);
        } else {
            mLastTop += (distance * SCROLL_RATIO);
        }
        if (mLastTop <= 0) {//还没有下拉完成
            lp.topMargin = mLastTop;
            mIsButtom = false;
            mRefreshHeadView.setLayoutParams(lp);
        } else {//下拉完成
            lp.topMargin = 0;
            mRefreshHeadView.setLayoutParams(lp);
            mIsButtom = true;
            invalidate();
        }
    }

    //回滚的效果
    private void fling() {
        animRefreshView(400, TAKEBACK_ALL);
    }

    public int dp(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, getContext().getResources().getDisplayMetrics());
    }

    public void animRefreshView(int duration, int takeBackState) {
        this.mTakeBackState = takeBackState;
        if (!anim.isRunning()) {
            anim.start();
            anim.setDuration(duration);
        }
    }

    /**
     * 正在刷新状态
     */
    public void refreshing() {
        setRefreshState(REFRESHING);
    }

    /**
     * 刷新成功状态
     */
    public void refreshSuccess() {
        setRefreshState(REFRESHING_SUCCESS);
    }

    /**
     * 刷新失败状态
     */
    public void refreshFailed() {
        setRefreshState(REFRESHING_FAILD);
    }

    public void resetRefreshView() {
        setRefreshState(REFRESHING_RESET);
    }

    private void createProgressView() {
        mProgress = new MaterialProgressDrawable(getContext(), mLoadImage);
        mProgress.setBackgroundColor(CIRCLE_BG_LIGHT);
        mProgress.setAlpha(255);
        mProgress.setColorSchemeColors(getContext().getResources().getColor(R.color.red_theme));
        mLoadImage.setImageDrawable(mProgress);
    }

    public void setRefreshState(int refreshState) {
        if (mRefreshState == refreshState)
            return;
        mRefreshState = refreshState;
        switch (mRefreshState) {
            case REFRESHING:
                mLoadImage.setVisibility(View.VISIBLE);
                mRefreshText.setVisibility(View.GONE);
                mProgress.start();
                break;
            case REFRESHING_SUCCESS:
                mRefreshText.setVisibility(View.VISIBLE);
                mRefreshText.setText("刷新成功");
                mLoadImage.setVisibility(View.GONE);
                mProgress.stop();
                //延迟一秒，之后收回去
                handler.postDelayed(mRunnable, 500);
                break;
            case REFRESHING_FAILD:
                mLoadImage.setVisibility(View.GONE);
                mRefreshText.setVisibility(View.VISIBLE);
                mRefreshText.setText("刷新失败");
                mProgress.stop();
                //延迟一秒，之后收回去
                handler.postDelayed(mRunnable, 500);
                break;
            case REFRESHING_RESET:
                mLastTop = refreshTargetTop;
                mLoadImage.setVisibility(View.VISIBLE);
                mRefreshText.setVisibility(View.GONE);
                LayoutParams lp = (LayoutParams) mRefreshHeadView.getLayoutParams();
                lp.height = -refreshTargetTop;
                mRefreshHeadView.setLayoutParams(lp);
                mProgress.stop();
                mLoadImage.clearAnimation();
                mRefreshState = REFRESHING_IDLE;
                break;


        }
    }

    public void setRefreashListten(RefreashListten l) {
        mRefreashListten = l;
    }

    public interface RefreashListten {
        public void refreash();
    }


    Thread mRunnable = new Thread(new Runnable() {
        public void run() {
            handler.sendEmptyMessage(1);
        }
    });

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    fling();
                    break;
            }
        }
    };

}
