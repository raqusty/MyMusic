package music.hayasi.android.com.mymusic.common.myNet.request;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import music.hayasi.android.com.mymusic.common.myNet.Impl.OkNetUitls;
import music.hayasi.android.com.mymusic.common.myNet.callback.BaseCallback;
import music.hayasi.android.com.mymusic.common.myNet.entity.HttpParams;
import music.hayasi.android.com.mymusic.common.myNet.exception.OkHttpException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class BaseRequest<R> {
    protected String mUrl;

    private BaseCallback mCallBack;
    private Request mRequest;
    private HttpParams mHttpParams;

    /**
     * * 构造函数，初始化
     */
    BaseRequest(String url) {
        mUrl = url;
    }

    /**
     * * 抽象方法，给每个不同的实现自己的方法
     */
    protected abstract Request generateRequest(RequestBody requestBody);

    /**
     * 根据不同的请求方式和参数，生成不同的RequestBody
     */
    protected abstract RequestBody generateRequestBody();

    /**
     * * 网络请求的调用
     */
    public <T> void execute(final BaseCallback<T> callback) {
        RequestBody requestBody = generateRequestBody();
        Request request = generateRequest(requestBody);
        //默认的回调函数
        mCallBack = callback;
        if (mCallBack == null) mCallBack = BaseCallback.CALLBACK_DEFAULT;
        Call call = generateCall(request);

        try {
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    sendFailResultCallback(call, null, mCallBack, OkHttpException.INSTANCE("访问出错！！"));
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        R data = (R) mCallBack.parseNetworkResponse(response);
                        sendSuccessResultCallback(data, call, response, mCallBack);
                    } catch (Exception e) {
                        sendFailResultCallback(call, null, mCallBack, OkHttpException.INSTANCE("解析报异常"));
                    }
                }
            });
        } catch (Exception e) {
            sendFailResultCallback(call, null, mCallBack, OkHttpException.INSTANCE("解析报异常"));
        }

    }

    /**
     * 根据当前的请求参数，生成对应的 Call 任务
     */
    protected Call generateCall(Request request) {
        mRequest = request;
        OkHttpClient.Builder newClientBuilder = OkNetUitls.getInstance().getOkHttpClient().newBuilder();
        newClientBuilder.readTimeout(mHttpParams.getReadTimeOut(), TimeUnit.MILLISECONDS);
        newClientBuilder.writeTimeout(mHttpParams.getWriteTimeOut(), TimeUnit.MILLISECONDS);
        newClientBuilder.connectTimeout(mHttpParams.getConnectTimeout(), TimeUnit.MILLISECONDS);
        return newClientBuilder.build().newCall(request);
    }

    /**
     * 设置http属性
     *
     * @param params
     */
    public void setHttpParams(HttpParams params) {
        mHttpParams = params;
    }

    /**
     * 发送成功的数据 给主线程
     *
     * @param t        数据
     * @param call
     * @param response
     * @param callback
     * @param <R>
     */
    private <R> void sendSuccessResultCallback(final R t, final Call call, final Response response, final BaseCallback<R> callback) {
        OkNetUitls.getInstance().getDelivery().post(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(t, call, response);        //请求成功回调 （UI线程）
            }
        });
    }

    /**
     * 发送成功的数据 给主线程
     *
     * @param call
     * @param response
     * @param callback
     * @param <R>
     */
    private <R> void sendFailResultCallback(final Call call, final Response response, final BaseCallback<R> callback, final Exception e) {
        OkNetUitls.getInstance().getDelivery().post(new Runnable() {
            @Override
            public void run() {
                callback.onFail(call, response, e);
            }
        });
    }
}
