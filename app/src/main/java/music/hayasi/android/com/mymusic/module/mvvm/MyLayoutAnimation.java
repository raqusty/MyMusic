package music.hayasi.android.com.mymusic.module.mvvm;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;

public class MyLayoutAnimation extends LayoutAnimationController {

    public MyLayoutAnimation(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLayoutAnimation(Animation animation) {
        super(animation);
    }

    public MyLayoutAnimation(Animation animation, float delay) {
        super(animation, delay);
    }

    private int mIndex = 0;
    private int mSize = 0;

    public void setIndex(int size, int index) {
        mSize = size;
        mIndex = index;
    }

    @Override
    protected int getTransformedIndex(AnimationParameters params) {
//        return super.getTransformedIndex(params);
        return 3;
    }
}
