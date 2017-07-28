package music.hayasi.android.com.mymusic.module.MaterialDesign;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseFragment;
import music.hayasi.android.com.mymusic.module.Guide.GuideActivity;

public class DesignListFragment extends BaseFragment {

    private int num = 0;

    public void setNum(int n) {
        num = n;
    }

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
        mRecyclerView.setAdapter(new ItemAdapter());

        Intent intent = new Intent(mContext, GuideActivity.class);
        startActivityForResult(intent, 222);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("linzehao", "fasdfadf ");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    private void addData() {
        for (int i = 0; i < 25; i++) {
            if (i % 3 == 0) {
                mDataList.add(i + "1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
            } else {
                mDataList.add(i + "");
            }

        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView != null) {
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setListener() {

    }

    @Override
    protected int getContentViewResId() {
        return R.layout.custom_recyclerview_layout;
    }

    class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

        private int biaozhi = 0;

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            // 如果设置了回调，则设置点击事件
            holder.tv.setText(mDataList.get(position));
            biaozhi = position;
            holder.tv.setMaxLines(4);
            holder.tv.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    Layout layout = holder.tv.getLayout();
                    int a = layout.getEllipsisCount(3);
                    holder.tv.getViewTreeObserver().removeOnPreDrawListener(this);
                    return false;
                }
            });
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