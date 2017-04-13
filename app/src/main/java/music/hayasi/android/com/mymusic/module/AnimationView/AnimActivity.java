package music.hayasi.android.com.mymusic.module.AnimationView;

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

    }


    @Override
    public void setListener() {
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.textview1.startAnimation(scaleAnimation);
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
