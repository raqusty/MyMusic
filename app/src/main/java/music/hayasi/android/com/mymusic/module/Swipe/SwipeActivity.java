package music.hayasi.android.com.mymusic.module.Swipe;

import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.common.widget.CustomRecyclerView;
import music.hayasi.android.com.mymusic.module.Swipe.adapter.StringAdapter;

public class SwipeActivity extends BaseActivity {

    @Bind(R.id.id_recyclerview)
    CustomRecyclerView mRecyclerView;

    List<String> mDataList = new ArrayList<String>();
    StringAdapter mAdatper;

    @Override
    protected int getContentViewResId() {
        return R.layout.custom_c_recyclerview_layout;
    }

    @Override
    public void initViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        for (int i = 0; i < 5; i++) {
            mDataList.add(i + "");
        }

        mAdatper = new StringAdapter(mContext, mDataList);
        mRecyclerView.setAdapter(mAdatper);

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
}
