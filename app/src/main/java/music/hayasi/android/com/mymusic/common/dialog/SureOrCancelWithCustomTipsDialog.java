package music.hayasi.android.com.mymusic.common.dialog;

import android.content.Context;


public class SureOrCancelWithCustomTipsDialog extends AbstractCustomContentSureCancelDialog {

    // 提示语
    String tips = "";
    // 界面取消 确认的监听器
    private OnSureOrCancelListener mOnSureOrCancelListener;

    public SureOrCancelWithCustomTipsDialog(Context context, OnSureOrCancelListener listener) {
        this(context, "", listener);
    }

    public SureOrCancelWithCustomTipsDialog(Context context, String tips, OnSureOrCancelListener listener) {
        super(context);

        this.tips = tips;
        this.mOnSureOrCancelListener = listener;
        // 初始化
        init();
    }


    /**
     * 初始化
     */
    private void init() {
        // 设置默认的标题
        setTitleText(tips);
        // 设置确认按钮的字体样式
    }

    /**
     * 设置自定义的提示语
     *
     * @param tips
     */
    public void setTips(String tips) {
        setTitleText(tips);
    }

    @Override
    public void onSure() {
        if (mOnSureOrCancelListener != null) {
            mOnSureOrCancelListener.onSure();
        }
        dismiss();
    }

    @Override
    public void onCancel() {
        if (mOnSureOrCancelListener != null) {
            mOnSureOrCancelListener.onCancel();
        }
        super.dismiss();
    }
}
