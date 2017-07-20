package music.hayasi.android.com.mymusic.module.ijkplayer;

import android.view.KeyEvent;
import android.view.View;

import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;

public class PlayerActivity extends BaseActivity implements View.OnKeyListener {


    @Override
    protected int getContentViewResId() {
        return 0;
    }

    @Override
    public void initViews() {

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

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return false;
    }
}
