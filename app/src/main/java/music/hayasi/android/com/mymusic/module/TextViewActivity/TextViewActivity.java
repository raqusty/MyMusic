package music.hayasi.android.com.mymusic.module.TextViewActivity;

import android.databinding.DataBindingUtil;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.databinding.AnimActivityBinding;

public class TextViewActivity extends BaseActivity {

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
        binding.button3.setText("监听textview的行数");
        binding.button1.setText("startAnimation");
        binding.button2.setText("ObjectAnimator");

        binding.textview1.setText("林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林林");

        binding.textview1.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                Layout layout = binding.textview1.getLayout();
                binding.textview1.getViewTreeObserver().removeOnPreDrawListener(this);
                return false;
            }
        });
    }


    @Override
    public void setListener() {


        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

}
