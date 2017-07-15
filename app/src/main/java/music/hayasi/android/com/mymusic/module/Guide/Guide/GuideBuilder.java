package music.hayasi.android.com.mymusic.module.Guide.Guide;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */

public class GuideBuilder {

    GuideBuilder(Builder builer) {

        Guide guide = new Guide(builer.mContext);
        guide.setComponent(builer.mComponents);
        guide.setExitAnimId(builer.mExitAnimationId);
        guide.setEnterAnimId(builer.mEnterAnimationId);
        guide.setGuideListten(builer.mListten);
        guide.setPadding(builer.mPadding, builer.mPaddingLeft, builer.mPaddingRight, builer.mPaddingTop, builer.mPaddingBottom);
        guide.setCorner(builer.mCorner);
        guide.setStyle(builer.mStyle);
        //最后的显示
        guide.createMaskView((Activity) builer.mContext, builer.mTargetView);
        guide.showGuide((Activity) builer.mContext);
    }

    public static class Builder {

        private Context mContext;
        private List<Component> mComponents = new ArrayList<Component>();
        private View mTargetView;
        private Guide.GuideListten mListten;

        int mPadding = 0;//高亮区域的padding
        int mPaddingLeft = 0;//高亮区域的左侧padding
        int mPaddingTop = 0;//高亮区域的顶部padding
        int mPaddingRight = 0;//高亮区域的右侧padding
        int mPaddingBottom = 0;//高亮区域的底部padding

        int mCorner = 0;//高亮区域的底部padding
        int mEnterAnimationId = -1;

        int mExitAnimationId = -1;

        int mStyle = Component.ROUNDRECT;

        public Builder(Context context, View view) {
            mContext = context;
            mTargetView = view;
        }

        public GuideBuilder.Builder GuideListten(Guide.GuideListten l) {
            mListten = l;
            return this;
        }

        public GuideBuilder.Builder Corner(int corner) {
            this.mCorner = corner;
            return this;
        }

        public GuideBuilder.Builder Style(int style) {
            this.mStyle = style;
            return this;
        }

        public GuideBuilder.Builder Padding(int p) {
            this.mPadding = p;
            return this;
        }

        public GuideBuilder.Builder PaddingLeft(int p) {
            this.mPaddingLeft = p;
            return this;
        }

        public GuideBuilder.Builder PaddingTop(int p) {
            this.mPaddingTop = p;
            return this;
        }

        public GuideBuilder.Builder PaddingRight(int p) {
            this.mPaddingRight = p;
            return this;
        }

        public GuideBuilder.Builder PaddingBottom(int p) {
            this.mPaddingBottom = p;
            return this;
        }

        public GuideBuilder.Builder addComponent(Component c) {
            if (c != null)
                this.mComponents.add(c);
            return this;
        }

        public GuideBuilder.Builder ExitAnimId(int amimId) {
            this.mExitAnimationId = amimId;
            return this;
        }

        public GuideBuilder.Builder EnterAnimId(int amimId) {
            this.mEnterAnimationId = amimId;
            return this;
        }

        public GuideBuilder build() {
            return new GuideBuilder(this);
        }
    }
}
