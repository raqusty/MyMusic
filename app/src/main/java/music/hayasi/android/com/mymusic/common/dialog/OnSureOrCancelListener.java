package music.hayasi.android.com.mymusic.common.dialog;

/**
 * 只有确定和取消的接口
 */
public interface OnSureOrCancelListener {

    /**
     * 点击取消按钮的回调方法
     */
    public void onCancel();

    /**
     * 点击确定按钮的回调方法
     */
    public void onSure();
}
