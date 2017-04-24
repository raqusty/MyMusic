package music.hayasi.android.com.mymusic.common.myNet.Uitls;


import music.hayasi.android.com.mymusic.common.myNet.Contants.Contants;
import music.hayasi.android.com.mymusic.common.myNet.entity.HttpParams;

public class HttpParamsUitl {

    public static HttpParams getGetHttpParams() {
        return new HttpParams.Builder().readTimeOut(Contants.HTTP_CONFIG.READ_TIMEOUT)
                .connectTimeout(Contants.HTTP_CONFIG.CONNECT_TIMEOUT).writeTimeOut(Contants.HTTP_CONFIG.WRITE_TIMEOUT)
                .build();
    }
}
