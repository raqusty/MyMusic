package music.hayasi.android.com.mymusic.module.liftActivity;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import music.hayasi.android.com.mymusic.R;

public class liftActivity extends LifecycleActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anim_list_activity);

        getLifecycle().addObserver(new LifecycleObserverDemo());
    }
}
