package music.hayasi.android.com.mymusic.module.AnimationView;

import android.util.Log;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;

public class DetailActivity extends BaseActivity {

    ImageView mImageView;
    WaveView mWaveView;

    @Override
    protected int getContentViewResId() {
        return R.layout.anim_detail_activity;
    }

    @Override
    public void initViews() {
        mWaveView = (WaveView) findViewById(R.id.anim_detail_activity_wave);
        mImageView = (ImageView) findViewById(R.id.anim_detail_activity_dog);

        int hight = getIntent().getIntExtra("hight", 0);
        int width = getIntent().getIntExtra("width", 0);
        int top = getIntent().getIntExtra("top", 0);
        int left = getIntent().getIntExtra("left", 0);


        TranslateAnimation anim1 = new TranslateAnimation(left, 0, top, 0);
        anim1.setDuration(1000);

//        ScaleAnimation anim2 = new ScaleAnimation(0.2f, 1, 0.2f, 1);
//        anim2.setDuration(2000);

        AnimationSet set = new AnimationSet(true);
        set.addAnimation(anim1);
//        set.addAnimation(anim2);
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mWaveView.startAnim();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mImageView.setAnimation(set);
        mImageView.startAnimation(set);
    }


    @Override
    public void setListener() {

    }

    @Override
    public int getToolBarResId() {
        return R.menu.menu_buttom;
    }

    @Override
    public void initToolBar(ToolBarManager navigationBarMgr) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.button1:
                Log.i("linzehao", "asdfasdfasd");
                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
