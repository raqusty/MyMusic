package music.hayasi.android.com.mymusic.module.TopWindow;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.view.View;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.databinding.AnimActivityBinding;

public class TopWindowActivity extends BaseActivity {

    private AnimActivityBinding binding;

    @Override
    protected int getContentViewResId() {
        return 0;
    }

    @Override
    public void initViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.anim_activity);
        binding.button1.setText("打开");
        binding.button2.setText("关闭");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent hide = new Intent(mContext, TopWindowService.class);
        stopService(hide);
    }

    @Override
    public void setListener() {
        binding.button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent show = new Intent(mContext, TopWindowService.class);
                show.putExtra(TopWindowService.OPERATION,
                        TopWindowService.OPERATION_SHOW);
                startService(show);
            }
        });

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hide = new Intent(mContext, TopWindowService.class);
                hide.putExtra(TopWindowService.OPERATION,
                        TopWindowService.OPERATION_HIDE);
                startService(hide);
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
