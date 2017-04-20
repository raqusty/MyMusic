package music.hayasi.android.com.mymusic.module.MaterialDesign;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseFragment;

public class DesignListFragment extends BaseFragment {

    @Bind(R.id.id_recyclerview)
    RecyclerView mRecyclerView;

    List<String> mDataList = new ArrayList<String>();

    @Override
    public void initViews() {
        addData();
        String[] toBeStored = mDataList.toArray(new String[mDataList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_expandable_list_item_1, toBeStored);
//        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter( new ItemAdapter());

    }

    private void addData() {
        for (int i = 0; i < 25; i++) {
            mDataList.add(i + "");
        }
    }


    @Override
    public void setListener() {

    }

    @Override
    protected int getContentViewResId() {
        return R.layout.custom_recyclerview_layout;
    }

    class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {


        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.tv.setText(mDataList.get(position));

            // 如果设置了回调，则设置点击事件
        }


        public void removeData(int position) {
            mDataList.remove(position);
            notifyItemRemoved(position);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.first_activity_item, parent,
                    false));
            return holder;
        }


        @Override
        public int getItemCount() {
            return mDataList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }

}