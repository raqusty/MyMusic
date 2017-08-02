package music.hayasi.android.com.mymusic.module.AnimationView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import music.hayasi.android.com.mymusic.R;

public class WaveView extends View {
    private Context mContext;
    private Bitmap mEraserBitmap;
    private Canvas mEraserCanvas;
    private Paint mFullingPaint = new Paint();
    private Paint paint = new Paint();
    private int mWidth = 0;
    private int mHeight = 0;
    private int mRadius = 0;

    public WaveView(Context context) {
        this(context, null);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        mWidth = getResources().getDisplayMetrics().widthPixels;
        mHeight = getResources().getDisplayMetrics().heightPixels;
        mEraserBitmap = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888);
        mEraserCanvas = new Canvas(mEraserBitmap);
        mFullingPaint.setAlpha(150);

        paint.setColor(mContext.getResources().getColor(android.R.color.transparent));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        mEraserCanvas.drawColor(mContext.getResources().getColor(R.color.gray));
    }

    public void startAnim() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < mHeight; i += 5) {
                    mRadius = i;
                    try {
                        Thread.currentThread().sleep(5);//毫秒
                    } catch (Exception e) {
                    }

                    postInvalidate();
                }

            }
        }).start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mEraserCanvas.drawCircle(0, 0, mRadius, paint);
        canvas.drawBitmap(mEraserBitmap, 0, 0, null);

    }
}
