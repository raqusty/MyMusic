package music.hayasi.android.com.mymusic.module.AdapterView;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;

public class AdapterActivity extends BaseActivity {

    @Override
    protected int getContentViewResId() {
        return R.layout.adapter_view;
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


}
