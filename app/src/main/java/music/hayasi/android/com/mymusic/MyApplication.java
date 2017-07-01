package music.hayasi.android.com.mymusic;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import im.fir.sdk.FIR;
import music.hayasi.android.com.mymusic.common.image.ImageUtil;
import music.hayasi.android.com.mymusic.common.myNet.Impl.OkNetUitls;
import music.hayasi.android.com.mymusic.common.net.OkHttpUtils;

public class MyApplication extends Application {
    private static MyApplication instance;


    public static MyApplication getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //用作扩展dex保存的方法数超过65K
        MultiDex.install(this);
        OkHttpUtils.init(this);
        OkNetUitls.init(this);
//        mRefWatcher = LeakCanary.install(this);
        instance = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        ImageUtil.initImageLoader(this);
        FIR.init(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


}
