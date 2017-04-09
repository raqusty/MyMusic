package music.hayasi.android.com.mymusic;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import music.hayasi.android.com.mymusic.common.image.ImageUtil;
import music.hayasi.android.com.mymusic.common.net.OkHttpUtils;

public class MyApplication extends Application {
    private static MyApplication instance;

    private RefWatcher mRefWatcher;

    public static MyApplication getInstance() {
        return instance;
    }

    public static RefWatcher getRefWatcher() {
        return getInstance().mRefWatcher;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //用作扩展dex保存的方法数超过65K
        MultiDex.install(this);
        OkHttpUtils.init(this);
//        mRefWatcher = LeakCanary.install(this);
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ImageUtil.initImageLoader(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


}
