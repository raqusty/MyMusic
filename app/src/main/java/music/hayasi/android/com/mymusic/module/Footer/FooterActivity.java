package music.hayasi.android.com.mymusic.module.Footer;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.common.widget.CustomRecyclerView;
import music.hayasi.android.com.mymusic.module.Footer.adapter.IntAdapter;
import music.hayasi.android.com.mymusic.module.Footer.adapter.StringAdapter;
import music.hayasi.android.com.mymusic.module.Footer.adapter.TestAdapter;

public class FooterActivity extends BaseActivity {

    private int num = 0;

    public void setNum(int n) {
        num = n;
    }

    @Bind(R.id.id_recyclerview)
    CustomRecyclerView mRecyclerView;

    List<String> mDataList = new ArrayList<String>();
    List<Integer> mDataList1 = new ArrayList<Integer>();
    List<testEntity> mDataList2 = new ArrayList<testEntity>();
    StringAdapter mAdatper;
    IntAdapter mAdatper1;
    TestAdapter mAdatper2;

    @Override
    public void initViews() {
        addData();
//        String[] toBeStored = mDataList.toArray(new String[mDataList.size()]);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_expandable_list_item_1, toBeStored);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdatper = new StringAdapter(mContext, mDataList);
        mAdatper1 = new IntAdapter(mContext, mDataList1);
        mAdatper2 = new TestAdapter(mContext, mDataList2);

        mRecyclerView.setAdapter(mAdatper2);
        mRecyclerView.setScrollListten(new CustomRecyclerView.ScrollListten() {
            @Override
            public void onScrolledToBottom() {
                mAdatper2.ShowFooterView(true);
            }

        });


    }

    private void addData() {
        for (int i = 0; i < 5; i++) {
            mDataList.add(i + "");
        }
        for (int i = 0; i < 7; i++) {
            mDataList1.add(i);
        }
        for (int i = 0; i < 17; i++) {
            testEntity entity = new testEntity();
            entity.setA(i + "" + i);
            mDataList2.add(entity);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void setListener() {

    }

    @Override
    protected int getContentViewResId() {
        return R.layout.custom_c_recyclerview_layout;
    }

    @Override
    public int getToolBarResId() {
        return 0;
    }

    @Override
    public void initToolBar(ToolBarManager navigationBarMgr) {

    }


}