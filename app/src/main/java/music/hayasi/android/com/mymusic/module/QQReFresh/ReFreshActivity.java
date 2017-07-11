package music.hayasi.android.com.mymusic.module.QQReFresh;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
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
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.module.QQReFresh.widget.QQReFreshLayout;

public class ReFreshActivity extends BaseActivity {

    @Override
    protected int getContentViewResId() {
        return R.layout.qq_refresh_layout_activity;
    }

    @Bind(R.id.id_recyclerview)
    RecyclerView mRecyclerView;

    @Bind(R.id.qq_refresh_layout)
    QQReFreshLayout mRefreshLayout;

    List<String> mDataList = new ArrayList<String>();

    Thread runnable;

    @Override
    public void initViews() {
        addData();
        String[] toBeStored = mDataList.toArray(new String[mDataList.size()]);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_expandable_list_item_1, toBeStored);
//        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(new ItemAdapter());
        mRefreshLayout.setRefreashListten(new QQReFreshLayout.RefreashListten() {
            @Override
            public void refreash() {
                //网络请求
                handler.postDelayed(runnable, 1000);
            }
        });


        runnable = new Thread(new Runnable() {
            public void run() {
                handler.sendEmptyMessage(1);
            }
        });
    }


    private void addData() {
        for (int i = 0; i < 25; i++) {
            if (i % 3 == 0) {
                mDataList.add(i + "");
            } else {
                mDataList.add(i + "");
            }

        }
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    mRefreshLayout.refreshSuccess();
                    break;
            }
        }
    };

    @Override
    public void setListener() {

    }


    @Override
    public int getToolBarResId() {
        return 0;
    }

    @Override
    public void initToolBar(ToolBarManager navigationBarMgr) {

    }


    class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

        private int biaozhi = 0;

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            // 如果设置了回调，则设置点击事件
            holder.tv.setText(mDataList.get(position));
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
