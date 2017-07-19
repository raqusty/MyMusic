package music.hayasi.android.com.mymusic.module.Path;

import android.view.View;

import butterknife.Bind;
import butterknife.OnClick;
import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.module.Path.widget.Bezier;

public class PathActivity extends BaseActivity {

    @Bind(R.id.my_bezier)
    Bezier mBezier;

    @Override
    protected int getContentViewResId() {
        return R.layout.path_1_activity;
    }

    @Override
    public void initViews() {

    }


    @Override
    public void setListener() {
    }

    @OnClick({R.id.id_num1, R.id.id_num2})
    void onClick(View v) {
        if (v.getId() == R.id.id_num1) {
            mBezier.setMode(true);
        } else {
            mBezier.setMode(false);
        }
    }

    @Override
    public int getToolBarResId() {
        return 0;
    }

    @Override
    public void initToolBar(ToolBarManager navigationBarMgr) {

    }

}
