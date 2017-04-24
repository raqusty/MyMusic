package music.hayasi.android.com.mymusic.common.myNet.Impl;


import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import music.hayasi.android.com.mymusic.common.myNet.request.GetRequest;
import okhttp3.OkHttpClient;

public class OkNetUitls {

    static OkNetUitls mInstance;
    //切主线程
    private Handler mDelivery;

    private OkHttpClient.Builder okHttpClientBuilder;     //ok请求的客户端

    /**
     * 初始化
     **/
    public static void init(Application application) {
        mInstance = getInstance();
    }

    public static OkNetUitls getInstance() {
        if (mInstance == null) {
            synchronized (OkNetUitls.class) {
                if (mInstance == null) {
                    mInstance = new OkNetUitls();
                }
            }
        }
        return mInstance;
    }

    private OkNetUitls() {
        okHttpClientBuilder = new OkHttpClient.Builder();
        mDelivery = new Handler(Looper.getMainLooper());
    }

    /**
     * 获取当前主线程 Handel
     **/
    public Handler getDelivery() {
        return mDelivery;
    }

    /**
     * 获取当前OkHttpClient
     *
     * @return
     */
    public OkHttpClient getOkHttpClient() {
        return okHttpClientBuilder.build();
    }

    /**
     * get请求
     */
    public static GetRequest get(String url) {
        return new GetRequest(url);
    }

}
