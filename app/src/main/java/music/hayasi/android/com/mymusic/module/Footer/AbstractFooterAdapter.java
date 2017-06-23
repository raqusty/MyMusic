package music.hayasi.android.com.mymusic.module.Footer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import music.hayasi.android.com.mymusic.R;

public abstract class AbstractFooterAdapter<T> extends RecyclerView.Adapter<AbstractFooterAdapter.RylViewHolder> {

    public static final int TYPE_TIP_EMPTY_FOOTER = 0;
    public static final int TYPE_CARD = 1;

    List<T> mDataList = new ArrayList<T>();

    protected Context mContext;

    private View mFooterView;
    protected boolean mIsShow = false;

    public AbstractFooterAdapter(Context context, List<T> list) {
        mContext = context;
        mDataList = list;
        mFooterView = LayoutInflater.from(mContext).inflate(R.layout.footer_item, null);

    }

    public void SetFooterView(int layoutId) {
        mFooterView = LayoutInflater.from(mContext).inflate(layoutId, null);
    }

    public void ShowFooterView(boolean ishow) {
        mIsShow = ishow;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mFooterView != null && mIsShow ? mDataList.size() + 1 : mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        //先判断是否最后一个，如果是，再判断是不是需要显示footer
        if (mDataList.size() == position && mFooterView != null && mIsShow) {
            return TYPE_TIP_EMPTY_FOOTER;
        } else {
            return TYPE_CARD;
        }
    }


    public void onBindViewHolder(RylViewHolder holder, int position) {

    }

    @Override
    public RylViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TIP_EMPTY_FOOTER) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            parent.addView(mFooterView, layoutParams);
            return new FooterHolder(mFooterView, viewType);
        } else {
            return onCreateValidViewHolder(parent, viewType);
        }
    }


    /**
     * 卡片创建
     */
    public abstract RylViewHolder onCreateValidViewHolder(ViewGroup parent, int viewType);

    class RylViewHolder extends RecyclerView.ViewHolder {

        public RylViewHolder(View itemView, int type) {
            super(itemView);
        }
    }

    class FooterHolder extends RylViewHolder {

        public FooterHolder(View view, int type) {
            super(view, type);
        }
    }

}