package music.hayasi.android.com.mymusic.module.mvvm;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.module.mvvm.adapter.mvvmAdapter;
import music.hayasi.android.com.mymusic.module.mvvm.entity.User;

public class MvvmListActivity extends BaseActivity {

    private mvvmAdapter mAdapter;
    private ListView mListView;

    @Override
    protected int getContentViewResId() {
        return R.layout.mvvm_list_activity;
    }

    @Override
    public void initViews() {
        mListView = (ListView) findViewById(R.id.list);

        List<User> mDataList = new ArrayList<User>();
        User user;
        for (int i = 0; i < 20; i++) {
            user = new User(i + "", i + "");
            mDataList.add(user);
        }
        mAdapter = new mvvmAdapter(mDataList, this);
        mListView.setAdapter(mAdapter);
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
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



}
