package music.hayasi.android.com.mymusic.module.main;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.module.main.entity.AppInfo;

public class FirstActivity extends BaseActivity {

    @Bind(R.id.id_recyclerview)
    RecyclerView mRecyclerView;

    List<AppInfo> mDatas;
    HomeAdapter mAdapter;
    String[] mNameList = {"MVVMList", "MVVM", "MessageActivity", "MyImage", "DragMove", "CustomViewActivity", "Anim"};

    @Override
    protected int getContentViewResId() {
        return R.layout.first_activity;
    }


    private void initData() {
        mDatas = new ArrayList<AppInfo>();
        AppInfo info = null;
        for (String name : mNameList) {
            info = new AppInfo(name, name);
            mDatas.add(info);
        }
    }

    @Override
    public void initViews() {
        initData();
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());

        mAdapter.setOnItemClickLitener(new OnItemClickLitener() {

            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(FirstActivity.this, position + " click",
                        Toast.LENGTH_SHORT).show();
                AppInfo info = mDatas.get(position);
                jumpToActivityFromRight(new Intent(info.getIntent()));
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(FirstActivity.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
                mAdapter.removeData(position);
            }
        });


    }

    @Override
    public void setListener() {

    }

    @Override
    public int getToolBarResId() {
        return R.menu.main;
    }

    @Override
    public void initToolBar(ToolBarManager navigationBarMgr) {

    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {


        private OnItemClickLitener mOnItemClickLitener;

        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.tv.setText(mDatas.get(position).getName());

            // 如果设置了回调，则设置点击事件
            if (mOnItemClickLitener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(holder.itemView, pos);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                        return false;
                    }
                });
            }
        }


        public void removeData(int position) {
            mDatas.remove(position);
            notifyItemRemoved(position);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    FirstActivity.this).inflate(R.layout.first_activity_item, parent,
                    false));
            return holder;
        }


        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.id_num);
            }
        }
    }

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

}
