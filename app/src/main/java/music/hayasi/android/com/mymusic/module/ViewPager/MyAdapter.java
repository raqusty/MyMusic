package music.hayasi.android.com.mymusic.module.ViewPager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18.
 */

public class MyAdapter extends PagerAdapter {

    private List<ImageView> mDatalist;

    public MyAdapter(Context context, List<ImageView> list) {
        mDatalist = list;
    }

    @Override
    public int getCount() {
        return mDatalist.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(mDatalist.get(position % mDatalist.size()));

    }

    /**
     * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mDatalist.get(position));
        return mDatalist.get(position);
    }

}
