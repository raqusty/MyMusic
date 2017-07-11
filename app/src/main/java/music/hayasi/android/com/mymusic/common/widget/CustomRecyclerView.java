package music.hayasi.android.com.mymusic.common.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2017/7/11.
 */

public class CustomRecyclerView extends RecyclerView {
    private ScrollListten mScrollListten;

    public CustomRecyclerView(Context context) {
        super(context);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void onScrollStateChanged(int state) {
        super.onScrollStateChanged(state);
        //RecyclerView.canScrollVertically(1)的值表示是否能向上滚动，false表示已经滚动到底部
        if (state == RecyclerView.SCROLL_STATE_IDLE && !this.canScrollVertically(1)) {
            if (mScrollListten != null) {
                mScrollListten.onScrolledToBottom();
            }
        }
    }

    public void setScrollListten(ScrollListten l) {
        mScrollListten = l;
    }

    public interface ScrollListten {
        /**
         * 拖动到底部
         */
        void onScrolledToBottom();

    }
}
