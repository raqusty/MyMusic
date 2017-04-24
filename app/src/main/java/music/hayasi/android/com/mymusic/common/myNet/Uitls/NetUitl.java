package music.hayasi.android.com.mymusic.common.myNet.Uitls;


import okhttp3.Request;

public class NetUitl {
    /**
     * 通用的拼接请求头
     *
     * HttpHeaders 这里应该有这些属性的，但现在还没又写
     */
    public static Request.Builder appendHeaders() {
        Request.Builder requestBuilder = new Request.Builder();
        return requestBuilder;
    }

}
