package music.hayasi.android.com.mymusic.module.Guide.Guide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2017/7/14.
 */

public class MaskView extends ViewGroup {

    //目标View
    private final RectF mTargetRect = new RectF();
    //覆盖的View
    private final RectF mFullingRect = new RectF();
    private final RectF mChildTmpRect = new RectF();
    private final Paint mFullingPaint = new Paint();

    private int mPadding = 0;
    private int mPaddingLeft = 0;
    private int mPaddingTop = 0;
    private int mPaddingRight = 0;
    private int mPaddingBottom = 0;

    private Paint mEraser;
    private Bitmap mEraserBitmap;
    private Canvas mEraserCanvas;
    private Paint mPaint;
    private Paint transparentPaint;
    private int mCorner = 0;

    private int mStyle = Component.ROUNDRECT;


    public MaskView(Context context) {
        this(context, null, 0);
    }

    public MaskView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaskView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setWillNotDraw(false);
        Point size = new Point();
        size.x = getResources().getDisplayMetrics().widthPixels;
        size.y = getResources().getDisplayMetrics().heightPixels;

        mEraserBitmap = Bitmap.createBitmap(size.x, size.y, Bitmap.Config.ARGB_8888);
        mEraserCanvas = new Canvas(mEraserBitmap);

        mPaint = new Paint();
        mPaint.setColor(0xcc000000);

        transparentPaint = new Paint();
        transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
        transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));

        mEraser = new Paint();
        mEraser.setColor(0xFFFFFFFF);
        mEraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mEraser.setFlags(Paint.ANTI_ALIAS_FLAG);

        //设置灰态的颜色
        mFullingPaint.setAlpha(150);
    }

    //重写布局
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();
        final float density = getResources().getDisplayMetrics().density;
        View child;
        for (int i = 0; i < count; i++) {
            child = getChildAt(i);
            if (child == null) {
                continue;
            }
            LayoutParams lp = (LayoutParams) child.getLayoutParams();
            if (lp == null) {
                continue;
            }
            switch (lp.targetAnchor) {
                case LayoutParams.ANCHOR_LEFT://左
                    mChildTmpRect.right = mTargetRect.left;
                    mChildTmpRect.left = mChildTmpRect.right - child.getMeasuredWidth();
                    horizontalChildPositionLayout(child, mChildTmpRect, lp.targetParentPosition);
                    break;
                case LayoutParams.ANCHOR_TOP://上
                    mChildTmpRect.bottom = mTargetRect.top;
                    mChildTmpRect.top = mChildTmpRect.bottom - child.getMeasuredHeight();
                    verticalChildPositionLayout(child, mChildTmpRect, lp.targetParentPosition);
                    break;
                case LayoutParams.ANCHOR_RIGHT://右
                    mChildTmpRect.left = mTargetRect.right;
                    mChildTmpRect.right = mChildTmpRect.left + child.getMeasuredWidth();
                    horizontalChildPositionLayout(child, mChildTmpRect, lp.targetParentPosition);
                    break;
                case LayoutParams.ANCHOR_BOTTOM://下
                    mChildTmpRect.top = mTargetRect.bottom;
                    mChildTmpRect.bottom = mChildTmpRect.top + child.getMeasuredHeight();
                    verticalChildPositionLayout(child, mChildTmpRect, lp.targetParentPosition);
                    break;
                case LayoutParams.ANCHOR_OVER://中心
                    mChildTmpRect.left = ((int) mTargetRect.width() - child.getMeasuredWidth()) >> 1;
                    mChildTmpRect.top = ((int) mTargetRect.height() - child.getMeasuredHeight()) >> 1;
                    mChildTmpRect.right = ((int) mTargetRect.width() + child.getMeasuredWidth()) >> 1;
                    mChildTmpRect.bottom = ((int) mTargetRect.height() + child.getMeasuredHeight()) >> 1;
                    mChildTmpRect.offset(mTargetRect.left, mTargetRect.top);
                    break;
            }
            //额外的xy偏移
            mChildTmpRect.offset((int) (density * lp.offsetX + 0.5f),
                    (int) (density * lp.offsetY + 0.5f));
            child.layout((int) mChildTmpRect.left, (int) mChildTmpRect.top, (int) mChildTmpRect.right,
                    (int) mChildTmpRect.bottom);
        }

    }

    private void verticalChildPositionLayout(View child, RectF rect, int targetParentPosition) {
        switch (targetParentPosition) {
            case LayoutParams.PARENT_START:
                rect.left = mTargetRect.left;
                rect.right = rect.left + child.getMeasuredWidth();
                break;
            case LayoutParams.PARENT_CENTER:
                rect.left = (mTargetRect.width() - child.getMeasuredWidth()) / 2;
                rect.right = (mTargetRect.width() + child.getMeasuredWidth()) / 2;
                rect.offset(mTargetRect.left, 0);
                break;
            case LayoutParams.PARENT_END:
                rect.right = mTargetRect.right;
                rect.left = rect.right - child.getMeasuredWidth();
                break;
        }
    }

    private void horizontalChildPositionLayout(View child, RectF rect, int targetParentPosition) {
        switch (targetParentPosition) {
            case LayoutParams.PARENT_START:
                rect.top = mTargetRect.top;
                rect.bottom = rect.top + child.getMeasuredHeight();
                break;
            case LayoutParams.PARENT_CENTER:
                rect.top = (mTargetRect.width() - child.getMeasuredHeight()) / 2;
                rect.bottom = (mTargetRect.width() + child.getMeasuredHeight()) / 2;
                rect.offset(0, mTargetRect.top);
                break;
            case LayoutParams.PARENT_END:
                rect.bottom = mTargetRect.bottom;
                rect.top = mTargetRect.bottom - child.getMeasuredHeight();
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mEraserBitmap.eraseColor(Color.TRANSPARENT);
        mEraserCanvas.drawColor(mFullingPaint.getColor());
        switch (mStyle) {
            case Component.ROUNDRECT:
                mEraserCanvas.drawRoundRect(mTargetRect, mCorner, mCorner, mEraser);
                break;
            case Component.CIRCLE:
                mEraserCanvas.drawCircle(mTargetRect.centerX(), mTargetRect.centerY(),
                        mTargetRect.width() / 2, mEraser);
                break;
            default:
                mEraserCanvas.drawRoundRect(mTargetRect, mCorner, mCorner, mEraser);
                break;
        }
        canvas.drawBitmap(mEraserBitmap, 0, 0, null);
    }

//    //不太知道为什么要用，不用好像也能绘制出来
//    @Override
//    protected void dispatchDraw(Canvas canvas) {
//        final long drawingTime = getDrawingTime();
//        try {
//            View child;
//            for (int i = 0; i < getChildCount(); i++) {
//                child = getChildAt(i);
//                drawChild(canvas, child, drawingTime);
//            }
//        } catch (NullPointerException e) {
//
//        }
//    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int w = MeasureSpec.getSize(widthMeasureSpec);
        final int h = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(w, h);
//        if (!mCustomFullingRect) {
//            mFullingRect.set(0, 0, w, h);
//            resetOutPath();
//        }

        final int count = getChildCount();
        View child;
        for (int i = 0; i < count; i++) {
            child = getChildAt(i);
            if (child != null) {
                LayoutParams lp = (LayoutParams) child.getLayoutParams();
                if (lp == null) {
                    child.setLayoutParams(lp);
                }
                measureChild(child, w + MeasureSpec.AT_MOST, h + MeasureSpec.AT_MOST);
            }
        }
    }


    public void setTargetRect(Rect rect) {
        mTargetRect.set(rect);
        resetOutPath();
        invalidate();
    }

    public void setFullingRect(Rect rect) {
        mFullingRect.set(rect);
        resetOutPath();
//        mCustomFullingRect = true;
        invalidate();
    }

    private void resetOutPath() {
        if (mPadding != 0 && mPaddingLeft == 0) {
            mTargetRect.left -= mPadding;
        }
        if (mPadding != 0 && mPaddingTop == 0) {
            mTargetRect.top -= mPadding;
        }
        if (mPadding != 0 && mPaddingRight == 0) {
            mTargetRect.right += mPadding;
        }
        if (mPadding != 0 && mPaddingBottom == 0) {
            mTargetRect.bottom += mPadding;
        }
        if (mPaddingLeft != 0) {
            mTargetRect.left -= mPaddingLeft;
        }
        if (mPaddingTop != 0) {
            mTargetRect.top -= mPaddingTop;
        }
        if (mPaddingRight != 0) {
            mTargetRect.right += mPaddingRight;
        }
        if (mPaddingBottom != 0) {
            mTargetRect.bottom += mPaddingBottom;
        }
    }

    public void setPadding(int padding, int l, int r, int t, int b) {
        mPadding = padding;
        mPaddingLeft = l;
        mPaddingTop = t;
        mPaddingRight = r;
        mPaddingBottom = b;
    }

    public void setCorner(int corner) {
        this.mCorner = corner;
    }

    public void setStyle(int style) {
        this.mStyle = style;
    }


    static class LayoutParams extends ViewGroup.LayoutParams {

        public static final int ANCHOR_LEFT = 0x01;
        public static final int ANCHOR_TOP = 0x02;
        public static final int ANCHOR_RIGHT = 0x03;
        public static final int ANCHOR_BOTTOM = 0x04;
        public static final int ANCHOR_OVER = 0x05;

        public static final int PARENT_START = 0x10;
        public static final int PARENT_CENTER = 0x20;
        public static final int PARENT_END = 0x30;

        public int targetAnchor = ANCHOR_BOTTOM;
        public int targetParentPosition = PARENT_CENTER;
        public int offsetX = 0;
        public int offsetY = 0;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}
