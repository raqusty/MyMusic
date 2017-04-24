package music.hayasi.android.com.mymusic.common.myNet.request;


import music.hayasi.android.com.mymusic.common.myNet.Uitls.NetUitl;
import okhttp3.Request;
import okhttp3.RequestBody;

public class GetRequest extends BaseRequest<GetRequest> {


    /**
     * * 构造函数，初始化
     *
     * @param url
     */
    public GetRequest(String url) {
        super(url);
    }

    @Override
    protected Request generateRequest(RequestBody requestBody) {
        Request.Builder requestBuilder = NetUitl.appendHeaders();
        return requestBuilder.get().url(mUrl).build();
    }

    @Override
    protected RequestBody generateRequestBody() {
        return null;
    }
}
