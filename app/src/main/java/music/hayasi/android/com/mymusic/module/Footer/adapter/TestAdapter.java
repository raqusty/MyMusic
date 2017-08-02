package music.hayasi.android.com.mymusic.module.Footer.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.List;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.module.Footer.testEntity;

public class TestAdapter extends AbstractFooterAdapter<testEntity> {
    private Context mContext;

    public TestAdapter(Context context, List<testEntity> list) {
        super(context, list);
        mContext = context;
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
            ((MyViewHolder) holder).tv.setText(mDataList.get(position).getA());
            ((MyViewHolder) holder).tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ShowFooterView(!mIsShow);
                }
            });
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.animation_2);
            if (position == mDataList.size() - 1)
                ((MyViewHolder) holder).tv.setAnimation(animation);
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