package music.hayasi.android.com.mymusic.module.AnimationView;

import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.databinding.DataBindingUtil;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.databinding.AnimActivityBinding;

public class AnimActivity extends BaseActivity {

    private AnimActivityBinding binding;

    Animation scaleAnimation;

    @Override
    protected int getContentViewResId() {
        return 0;
    }

    @Override
    public void initViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.anim_activity);

        scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_1);
        binding.button3.setText("ValueAnimator");
        binding.button1.setText("startAnimation");
        binding.button2.setText("ObjectAnimator");
    }


    @Override
    public void setListener() {
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textview1.startAnimation(scaleAnimation);

            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textview1.clearAnimation();
                ViewWrapper wrapper = new ViewWrapper(binding.textview1);
                ObjectAnimator.ofInt(wrapper, "width", 500).setDuration(1000).start();
            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            performAnimate(binding.textview1,binding.textview1.getWidth(),300);
            }
        });
    }

    @Override
    public int getToolBarResId() {
        return 0;
    }

    @Override
    public void initToolBar(ToolBarManager navigationBarMgr) {

    }

    private void performAnimate(final View target,final int start,final int end){
        final ValueAnimator valueAnimator = ValueAnimator.ofInt(1,100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private IntEvaluator mEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentValue = (Integer) animation.getAnimatedValue();
                float fraction = valueAnimator.getAnimatedFraction();
                target.getLayoutParams().width = mEvaluator.evaluate(fraction,start,end);
                target.requestLayout();
            }
        });
        valueAnimator.setDuration(1000).start();
    }

    private class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View target) {
            mTarget = target;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }
}
