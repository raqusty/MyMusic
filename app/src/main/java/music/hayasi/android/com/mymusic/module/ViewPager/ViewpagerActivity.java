package music.hayasi.android.com.mymusic.module.ViewPager;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;

public class ViewpagerActivity extends BaseActivity implements ViewPager.PageTransformer {

    @Bind(R.id.myViewpager)
    ViewPager mViewPager;

    MyAdapter myAdapter;

    @Override
    protected int getContentViewResId() {
        return R.layout.viewpager_activity;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void initViews() {

        ImageView imageView = null;
        List<ImageView> list = new ArrayList<ImageView>();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(ViewPager.LayoutParams.WRAP_CONTENT,
                ViewPager.LayoutParams.WRAP_CONTENT));
        for (int i = 0; i < 20; i++) {

            imageView = new ImageView(mContext);
            imageView.setBackground(mContext.getResources().getDrawable(R.mipmap.dog));
            imageView.setLayoutParams(params);
            list.add(imageView);
        }
        myAdapter = new MyAdapter(mContext, list);
        mViewPager.setAdapter(myAdapter);
        mViewPager.setPageTransformer(true, this);
        mViewPager.setClipToPadding(false);
        mViewPager.setPageMargin(20);
    }


    @Override
    public void setListener() {


    }


    @Override
    public int getToolBarResId() {
        return 0;
    }

    @Override
    public void initToolBar(ToolBarManager navigationBarMgr) {

    }


    @Override
    public void transformPage(View page, float position) {
        if (mViewPager == null) {
            mViewPager = (ViewPager) page.getParent();
        }

        int leftInScreen = page.getLeft() - mViewPager.getScrollX();
        int centerXInViewPager = leftInScreen + page.getMeasuredWidth() / 2;
        int offsetX = centerXInViewPager - mViewPager.getMeasuredWidth() / 2;
        float offsetRate = (float) offsetX * 0.38f / mViewPager.getMeasuredWidth();
        float scaleFactor = 1 - Math.abs(offsetRate);

        if (scaleFactor > 0) {
            page.setScaleX(scaleFactor);
            page.setScaleY(scaleFactor);
            page.setTranslationX(-180 * offsetRate);
        }
    }
}
