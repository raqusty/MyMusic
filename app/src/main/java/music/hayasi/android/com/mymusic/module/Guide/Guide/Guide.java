package music.hayasi.android.com.mymusic.module.Guide.Guide;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

import music.hayasi.android.com.mymusic.common.utils.UIUtils;

/**
 * Created by Administrator on 2017/7/14.
 */

public class Guide implements View.OnClickListener {

    //引导页的View
    private MaskView mMaskView;
    //配置属性的

    private Context mContext;

    private List<Component> mComponents = new ArrayList<Component>();

    private GuideListten mGuideListten;

    private int mPadding = 0;
    private int mPaddingLeft = 0;
    private int mPaddingTop = 0;
    private int mPaddingRight = 0;
    private int mPaddingBottom = 0;
    int mCorner = 0;//高亮区域的底部padding
    int mStyle = Component.ROUNDRECT;

    int mEnterAnimationId = -1;

    int mExitAnimationId = -1;

    public Guide(Context context) {
        mContext = context;
    }

    public void createMaskView(Activity activity, View targetView) {
        if (mMaskView == null) {
            mMaskView = new MaskView(mContext);
        }
        mMaskView.setPadding(mPadding, mPaddingLeft, mPaddingRight, mPaddingTop, mPaddingBottom);
        mMaskView.setCorner(mCorner);
        mMaskView.setStyle(mStyle);
        //这个要在measure后才能调用，不然会没有数据的
        mMaskView.setTargetRect(UIUtils.getViewAbsRect(targetView, mContext, true));
        for (Component c : mComponents) {
            MaskView.LayoutParams lp = new MaskView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            View view = c.getView(activity.getLayoutInflater());
            lp.targetAnchor = c.getAnchor();
            lp.offsetX = c.getXOffset();
            lp.offsetY = c.getYOffset();
            lp.targetParentPosition = c.getFitPosition();
            view.setLayoutParams(lp);
            mMaskView.addView(view);
        }
        mMaskView.setOnClickListener(this);
    }

    public void setComponent(List<Component> list) {
        if (list != null)
            mComponents.addAll(list);
    }

    public void showGuide(Activity activity) {
        //把View 添加的 mContext的父控件里
        final ViewGroup viewGroup = (ViewGroup) activity.findViewById(android.R.id.content);
        if (mMaskView.getParent() == null) {
            viewGroup.addView(mMaskView);
            if (mEnterAnimationId != -1) {
                // mMaskView may leak if context is null
                Context context = mMaskView.getContext();
                assert context != null;

                Animation anim = AnimationUtils.loadAnimation(context, mEnterAnimationId);
                assert anim != null;
                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        if (mGuideListten != null) {
                            mGuideListten.showGuide();
                        }
                    }
                });
                mMaskView.startAnimation(anim);
            } else {
                if (mGuideListten != null) {
                    mGuideListten.showGuide();
                }
            }
        }
    }


    public void dismiss() {
        if (mMaskView == null) {
            return;
        }
        final ViewGroup vp = (ViewGroup) mMaskView.getParent();
        if (vp == null) {
            return;
        }
        if (mExitAnimationId != -1) {
            // mMaskView may leak if context is null
            Context context = mMaskView.getContext();
            assert context != null;

            Animation anim = AnimationUtils.loadAnimation(context, mExitAnimationId);
            assert anim != null;
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    vp.removeView(mMaskView);
                    if (mGuideListten != null) {
                        mGuideListten.dissGuide();
                    }
                    onDestroy();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            mMaskView.startAnimation(anim);
        } else {
            vp.removeView(mMaskView);
            if (mGuideListten != null) {
                mGuideListten.dissGuide();
            }
            onDestroy();
        }

    }

    private void onDestroy() {
        mMaskView.removeAllViews();
        mComponents.clear();
        mMaskView = null;
    }


    @Override
    public void onClick(View view) {
        dismiss();
    }

    public void setGuideListten(GuideListten listten) {
        mGuideListten = listten;
    }


    public void setPadding(int padding, int l, int r, int t, int b) {
        mPadding = padding;
        mPaddingLeft = l;
        mPaddingTop = t;
        mPaddingRight = r;
        mPaddingBottom = b;
    }

    public void setCorner(int corner) {
        this.mCorner = corner;
    }

    public void setStyle(int style) {
        this.mStyle = style;
    }

    public void setEnterAnimId(int anim) {
        this.mEnterAnimationId = anim;
    }

    public void setExitAnimId(int anim) {
        this.mExitAnimationId = anim;
    }

    public interface GuideListten {
        public void showGuide();

        public void dissGuide();
    }
}
