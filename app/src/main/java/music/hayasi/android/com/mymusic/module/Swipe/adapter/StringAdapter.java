package music.hayasi.android.com.mymusic.module.Swipe.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.module.Footer.adapter.AbstractFooterAdapter;

public class StringAdapter extends AbstractFooterAdapter<String> {


    public StringAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public RylViewHolder onCreateValidViewHolder(ViewGroup parent, int viewType) {
        View contain = LayoutInflater.from(mContext).inflate(R.layout.swipe_activity, parent, false);
        LinearLayout layout = (LinearLayout) contain.findViewById(R.id.swipe_layout);
        View context = LayoutInflater.from(mContext).inflate(R.layout.swipe_item, parent, false);
        layout.addView(context, 0);
        MyViewHolder holder = new MyViewHolder(contain, viewType);
        return holder;
    }

    @Override
    public void onBindViewHolder(AbstractFooterAdapter.RylViewHolder holder, int position) {
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
            Log.i("linzehao", "111");
        }
    }


    class MyViewHolder extends RylViewHolder {

        TextView tv;

        public MyViewHolder(View view, int type) {
            super(view, type);
            tv = (TextView) view.findViewById(R.id.id_num);
        }
    }
}