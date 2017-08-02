package music.hayasi.android.com.mymusic.module.MaterialDesign;

import android.support.v4.widget.NestedScrollView;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;

public class DesignScrollActivity extends BaseActivity {

    NestedScrollView mNestedScrollView;

    @Override
    public void initViews() {
        mNestedScrollView = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        mNestedScrollView.setSmoothScrollingEnabled(true);
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
    protected int getContentViewResId() {
        return R.layout.fragment_scroll_view_toolbar;
    }
}