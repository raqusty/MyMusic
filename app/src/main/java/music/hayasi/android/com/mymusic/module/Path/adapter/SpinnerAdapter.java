package music.hayasi.android.com.mymusic.module.Path.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.module.Path.entity.PathEntity;

/**
 * Created by Administrator on 2017/7/18.
 */

public class SpinnerAdapter extends BaseAdapter {
    private List<PathEntity> mPathEntities;
    private LayoutInflater mInflater;

    public SpinnerAdapter(Context context, List<PathEntity> list) {
        mPathEntities = list;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mPathEntities.size();
    }

    @Override
    public Object getItem(int i) {
        return mPathEntities.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.plan, null);
            holder.title = (TextView) convertView.findViewById(R.id.nama);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.title.setText(mPathEntities.get(i).getId() + "");
        return convertView;
    }

    public final class ViewHolder {
        public TextView title;
    }
}
