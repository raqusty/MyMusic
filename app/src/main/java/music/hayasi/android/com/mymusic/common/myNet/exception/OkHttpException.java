package music.hayasi.android.com.mymusic.common.myNet.exception;

/**
===========================================
 */
public class OkHttpException extends Exception {

    public OkHttpException() {
        super();
    }

    public OkHttpException(String detailMessage) {
        super(detailMessage);
    }

    public OkHttpException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public OkHttpException(Throwable throwable) {
        super(throwable);
    }

    public static OkHttpException INSTANCE(String msg) {
        return new OkHttpException(msg);
    }
}