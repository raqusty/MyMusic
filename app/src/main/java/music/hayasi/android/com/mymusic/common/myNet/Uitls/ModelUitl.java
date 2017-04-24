package music.hayasi.android.com.mymusic.common.myNet.Uitls;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import music.hayasi.android.com.mymusic.common.myNet.Impl.OkNetUitls;
import music.hayasi.android.com.mymusic.common.myNet.callback.StringCallback;
import music.hayasi.android.com.mymusic.common.myNet.entity.HttpParams;
import music.hayasi.android.com.mymusic.common.myNet.request.GetRequest;

public class ModelUitl {
    /**
     * @param url      不能为空
     * @param params
     * @param callback
     */
    public static void getStringModel(@NonNull String url, HttpParams params, StringCallback callback) {
        GetRequest request = OkNetUitls.get(url);
        if (params == null) {
            request.setHttpParams(HttpParamsUitl.getGetHttpParams());
        } else {
            request.setHttpParams(params);
        }
        request.execute(callback);
    }

}
