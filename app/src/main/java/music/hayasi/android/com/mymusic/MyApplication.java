package music.hayasi.android.com.mymusic;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import music.hayasi.android.com.mymusic.common.net.OkHttpUtils;

public class MyApplication extends Application {
//    private static Context instance;
//    public static Context getInstance() {
//        return instance;
//    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //用作扩展dex保存的方法数超过65K
        MultiDex.install(this);
        OkHttpUtils.init(this);
//        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
