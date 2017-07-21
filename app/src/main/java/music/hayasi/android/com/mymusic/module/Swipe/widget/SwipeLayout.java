package music.hayasi.android.com.mymusic.module.Swipe.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class SwipeLayout extends LinearLayout {


    public SwipeLayout(Context context) {
        this(context, null);
    }

    public SwipeLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("linzehao","SwipeLayout");
        return true;
    }

}
