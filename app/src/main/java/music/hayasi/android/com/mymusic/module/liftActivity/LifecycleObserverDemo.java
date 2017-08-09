package music.hayasi.android.com.mymusic.module.liftActivity;


import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.util.Log;

/**
 * Created by Administrator on 2017/8/8.
 */

public class LifecycleObserverDemo implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onAny(LifecycleOwner owner, Lifecycle.Event event) {
        System.out.println("onAny:" + event.name());
        Log.i("linzehao", "onAny:" + event.name());
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        Log.i("linzehao", "onCreate:" );
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy() {
        Log.i("linzehao", "onDestroy:" );
    }

}
