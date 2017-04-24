package music.hayasi.android.com.mymusic.common.myNet.callback;


import okhttp3.Response;

public abstract class StringCallback extends BaseCallback<String> {


    @Override
    public String parseNetworkResponse(Response response) throws Exception {
        return response.body().string();
    }
}
