package music.hayasi.android.com.mymusic.module.Drag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.module.Footer.adapter.AbstractFooterAdapter;

/**
 * Created by Administrator on 2017/8/18.
 */


public class DragAdapter extends AbstractFooterAdapter<String> {


    public DragAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public RylViewHolder onCreateValidViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.first_activity_item, parent,
                false), viewType);
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