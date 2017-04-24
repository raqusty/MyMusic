package music.hayasi.android.com.mymusic.common.myNet.callback;


import okhttp3.Call;
import okhttp3.Response;

public abstract class BaseCallback<T> {

    public static final BaseCallback CALLBACK_DEFAULT = new BaseCallback() {

        @Override
        public void onFail(Call call, Response response, Exception e) {

        }

        @Override
        public void onSuccess(Object data, Call call, Response response) {
        }

        @Override
        public Response parseNetworkResponse(Response response) {
            return response;
        }
    };

    /**
     * * 成功回调
     */
    public abstract void onSuccess(T data, Call call, Response response);

    /**
     * * 成功回调
     */
    public abstract void onFail(Call call, Response response, Exception e);

    /**
     * 拿到响应后，将数据转换成需要的格式，子线程中执行，可以是耗时操作
     */
    public abstract T parseNetworkResponse(Response response) throws Exception;
}
