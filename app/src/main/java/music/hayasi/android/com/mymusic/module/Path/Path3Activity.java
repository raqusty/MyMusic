package music.hayasi.android.com.mymusic.module.Path;

import android.widget.SeekBar;

import butterknife.Bind;
import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.module.Path.widget.Bezier3;

public class Path3Activity extends BaseActivity {

    @Bind(R.id.my_bezier)
    Bezier3 mBezier;

    @Bind(R.id.seekbar)
    SeekBar mSeekBar;


    @Override
    protected int getContentViewResId() {
        return R.layout.path_3_activity;
    }

    @Override
    public void initViews() {

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mBezier.setControl1(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public void setListener() {
    }


    @Override
    public int getToolBarResId() {
        return 0;
    }

    @Override
    public void initToolBar(ToolBarManager navigationBarMgr) {

    }

}
