package music.hayasi.android.com.mymusic.module.Drag;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.common.widget.CustomRecyclerView;

//https://github.com/kuyue/WeChatPublishImagesDrag

public class DragActivity extends BaseActivity implements View.OnKeyListener {
    private int dragFlags;
    private int swipeFlags;
    private boolean up;//手指抬起标记位

    @Bind(R.id.id_recyclerview)
    CustomRecyclerView mRecyclerView;

    List<String> mDataList1 = new ArrayList<String>();
    DragAdapter mAdatper;

    @Override
    protected int getContentViewResId() {
        return R.layout.custom_recyclerview_foot_layout;
    }

    @Override
    public void initViews() {
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new myCallBack());
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
        for (int i = 0; i < 7; i++) {
            mDataList1.add(i + "");
        }
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
//        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3));
        mAdatper = new DragAdapter(mContext, mDataList1);
        mRecyclerView.setAdapter(mAdatper);
    }

    class myCallBack extends ItemTouchHelper.Callback {

        @Override
        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//            Log.i("linzehao",12312323+"");
//            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                if (recyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;//设置能拖拽的方向
//                swipeFlags = 1;//0则不响应事件
//                Log.i("linzehao",1111+"");
            }
            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {

            int fromPosition = viewHolder.getAdapterPosition();//得到item原来的position
            int toPosition = target.getAdapterPosition();//得到目标position
//            Log.i("linzehao",2222+"");
            if (toPosition == mDataList1.size()  || mDataList1.size()  == fromPosition) {
                return true;
            }
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(mDataList1, i, i + 1);
//                    Collections.swap(originImages, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(mDataList1, i, i - 1);
//                    Collections.swap(originImages, i, i - 1);
                }
            }
            mAdatper.notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            Log.i("linzehao", "3333");
        }
    }

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

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        return false;
    }


}
