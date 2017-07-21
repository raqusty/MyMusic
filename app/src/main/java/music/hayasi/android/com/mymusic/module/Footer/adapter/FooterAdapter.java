package music.hayasi.android.com.mymusic.module.Footer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import music.hayasi.android.com.mymusic.R;

public class FooterAdapter extends RecyclerView.Adapter<FooterAdapter.RylViewHolder> {

    public static final int TYPE_TIP_EMPTY_FOOTER = 0;
    public static final int TYPE_CARD = 1;

    List<String> mDataList = new ArrayList<String>();

    Context mContext;


    private View mFooterView;
    private boolean mIsShow = false;

    public FooterAdapter(Context context, List<String> list) {
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
    public void onBindViewHolder(final RylViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_CARD) {
            //处理自己的业务
            ((MyViewHolder) holder).tv.setText(mDataList.get(position));
            ((MyViewHolder) holder).tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowFooterView(!mIsShow);
                }
            });

        } else if (getItemViewType(position) == TYPE_TIP_EMPTY_FOOTER) {
        }
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

    @Override
    public RylViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_TIP_EMPTY_FOOTER) {
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            parent.addView(mFooterView, layoutParams);
            return new FooterHolder(mFooterView, viewType);
        } else {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.first_activity_item, parent,
                    false), viewType);
            return holder;
        }

    }

    class MyViewHolder extends RylViewHolder {

        TextView tv;

        public MyViewHolder(View view, int type) {
            super(view, type);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }

    class FooterHolder extends RylViewHolder {

        public FooterHolder(View view, int type) {
            super(view, type);
        }
    }

    class RylViewHolder extends RecyclerView.ViewHolder {

        public RylViewHolder(View itemView, int type) {
            super(itemView);
        }
    }

}