package music.hayasi.android.com.mymusic.common.dialog;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import music.hayasi.android.com.mymusic.R;


public abstract class AbstractCustomContentSureCancelDialog extends
        AbstractBaseDialog implements OnSureOrCancelListener {

    // 标题TextView
    protected TextView mTitleTV;
    // 包裹内容的Layout，内容显示什么控件需要自己去添加(Layout为RelativeLayout)
    protected ViewGroup mContentLayout;
    // 确认按钮
    protected TextView mSureBtnTV;
    // 取消按钮
    protected TextView mCancelBtnTV;
    // 标题文字
    private String titleText = "";
    // 根布局
    private CardView mRootView;

    public AbstractCustomContentSureCancelDialog(Context context) {
        super(context);
    }

    @Override
    protected View getContentView() {
        if (mRootView == null) {
            mRootView = (CardView) getLayoutInflater().inflate(R.layout.dialog_one_title_custom_content_sure_cancel_btn, null);
            // 绑定控件
            mTitleTV = (TextView) mRootView.findViewById(R.id.dialog_custom_content_tv_title);
            mContentLayout = (ViewGroup) mRootView.findViewById(R.id.dialog_custom_content_rl_content_container);
            mSureBtnTV = (TextView) mRootView.findViewById(R.id.dialog_custom_content_tv_sure_btn);
            mCancelBtnTV = (TextView) mRootView.findViewById(R.id.dialog_custom_content_tv_cancel_btn);
        }

        mSureBtnTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSure();
            }
        });

        mCancelBtnTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancel();
            }
        });

        return mRootView;
    }

    /**
     * 设置弹出框的圆角大小(单位：px)
     *
     * @param radius 弹出框的圆角大小(单位：px)
     */
    protected void setCornerRadius(float radius) {
        mRootView.setRadius(radius);
    }

    /**
     * 设置标题文字
     *
     * @param title 标题文字
     */
    protected void setTitleText(String title) {
        this.titleText = title;
        mTitleTV.setText(titleText);
    }

    /**
     * 设置标题文字的样式
     *
     * @param resId 样式的资源Id
     */
    public void setTitleTextAppearance(int resId) {
        mTitleTV.setTextAppearance(context, resId);
    }

    //不需要标题，就隐藏
    public void setTitleGone() {
        mTitleTV.setVisibility(View.GONE);
    }

    /**
     * 往包裹内容的Layout添加自定义的控件
     * Layout为RelativeLayout
     *
     * @param view
     */
    public void addCustomContentView(View view) {
        if (mContentLayout != null) {
            mContentLayout.addView(view);
        }
    }

    /**
     * 设置确认按钮的文字
     *
     * @param text 确认按钮的文字
     */
    public void setSureBtnText(String text) {
        if (mSureBtnTV != null) {
            mSureBtnTV.setText(text);
        }
    }

    /**
     * 设置确认按钮文字的样式
     *
     * @param resId 样式的资源Id
     */
    public void setSureBtnTextAppearance(int resId) {
        if (mSureBtnTV != null) {
            mSureBtnTV.setTextAppearance(context, resId);
        }
    }

    /**
     * 设置确认按钮背景的样式
     *
     * @param resId 样式的资源Id
     */
    public void setSureBtnColor(int resId) {
        if (mSureBtnTV != null) {
            mSureBtnTV.setBackgroundResource(resId);
        }
    }

    /**
     * 设置取消按钮的文字
     *
     * @param text 取消按钮的文字
     */
    public void setCancelBtnText(String text) {
        if (mCancelBtnTV != null) {
            mCancelBtnTV.setText(text);
        }
    }

    /**
     * 设置确认按钮背景的样式
     *
     * @param resId 样式的资源Id
     */
    public void setCancelBtnColor(int resId) {
        if (mCancelBtnTV != null) {
            mCancelBtnTV.setBackgroundResource(resId);
        }
    }

    /**
     * 设置取消按钮文字的样式
     *
     * @param resId 样式的资源Id
     */
    public void setCancelBtnTextAppearance(int resId) {
        if (mCancelBtnTV != null) {
            mCancelBtnTV.setTextAppearance(context, resId);
        }
    }

    /**
     * 设置为只显示一个确认按钮的模式
     */
    public void setOnlySureBtnMode() {
        mSureBtnTV.setVisibility(View.VISIBLE);
        mCancelBtnTV.setVisibility(View.GONE);
    }

    /**
     * 设置为确认和取消按钮都显示的模式
     */
    public void setSureAndCancelBtnMode() {
        mSureBtnTV.setVisibility(View.VISIBLE);
        mCancelBtnTV.setVisibility(View.VISIBLE);
    }

    /**
     * 取消按钮的点击事件
     */
    public void onCancel() {
        dismiss();
    }
}
