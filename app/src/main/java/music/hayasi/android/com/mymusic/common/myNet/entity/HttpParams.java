package music.hayasi.android.com.mymusic.common.myNet.entity;


import music.hayasi.android.com.mymusic.common.myNet.Contants.Contants;

public class HttpParams {

    private long readTimeOut;
    private long writeTimeOut;
    private long connectTimeout;
    //这两个没用到，暂时注释掉
//    private HttpsUtils.SSLParams sslParams;
//    private List<Cookie> userCookies;

    public HttpParams(Builder builder) {
        this.readTimeOut = builder.readTimeOut;
        this.writeTimeOut = builder.writeTimeOut;
        this.connectTimeout = builder.connectTimeout;
    }

    public long getReadTimeOut() {
        return readTimeOut;
    }

    public long getWriteTimeOut() {
        return writeTimeOut;
    }

    public long getConnectTimeout() {
        return connectTimeout;
    }

    public static class Builder {

        private long readTimeOut = Contants.HTTP_CONFIG.READ_TIMEOUT;
        private long writeTimeOut = Contants.HTTP_CONFIG.WRITE_TIMEOUT;
        private long connectTimeout = Contants.HTTP_CONFIG.CONNECT_TIMEOUT;

        public Builder() {

        }

        public HttpParams.Builder readTimeOut(long readTimeOut) {
            this.readTimeOut = readTimeOut;
            return this;
        }

        public HttpParams.Builder writeTimeOut(long writeTimeOut) {
            this.writeTimeOut = writeTimeOut;
            return this;
        }

        public HttpParams.Builder connectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }


        public HttpParams build() {
            return new HttpParams(this);
        }
    }


}
