package music.hayasi.android.com.mymusic.module.CardView;

import android.databinding.DataBindingUtil;
import android.view.View;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.databinding.AnimActivityBinding;
import music.hayasi.android.com.mymusic.module.Guide.Guide.Component;
import music.hayasi.android.com.mymusic.module.Guide.Guide.Guide;
import music.hayasi.android.com.mymusic.module.Guide.Guide.GuideBuilder;
import music.hayasi.android.com.mymusic.module.Guide.Simple1Component;
import music.hayasi.android.com.mymusic.module.Guide.Simple2Component;
import music.hayasi.android.com.mymusic.module.Guide.Simple3Component;
import music.hayasi.android.com.mymusic.module.Guide.Simple4Component;
import music.hayasi.android.com.mymusic.module.Guide.Simple5Component;
import music.hayasi.android.com.mymusic.module.Guide.Simple6Component;

public class CardViewActivity extends BaseActivity {

    @Override
    protected int getContentViewResId() {
        return R.layout.cardview_activity;
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
