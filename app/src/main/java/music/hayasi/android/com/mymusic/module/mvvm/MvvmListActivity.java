package music.hayasi.android.com.mymusic.module.mvvm;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ListView;
import android.widget.TextView;

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
    private List<User> mDataList = new ArrayList<User>();
    private MyLayoutAnimation controller;

    @Override
    protected int getContentViewResId() {
        return R.layout.mvvm_list_activity;
    }

    @Override
    public void initViews() {
        mListView = (ListView) findViewById(R.id.list);


        TextView textView = new TextView(mContext);
        textView.setText("hahah");

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_2);   //得到一个LayoutAnimationController对象；
        controller = new MyLayoutAnimation(animation);   //设置控件显示的顺序；
        controller.setOrder(LayoutAnimationController.ORDER_RANDOM);   //设置控件显示间隔时间；
        controller.setDelay(0.1f);   //为ListView设置LayoutAnimationController属性；
        mListView.setLayoutAnimation(controller);

        addData();
        mAdapter = new mvvmAdapter(mDataList, this);
        controller.setIndex(mDataList.size() - 1, 0);
        mListView.setAdapter(mAdapter);

        mListView.startLayoutAnimation();
        mListView.addHeaderView(textView);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int size = addData();
                controller.setIndex(mDataList.size() - 1, size - 1);
                mListView.startLayoutAnimation();
                mAdapter.notifyDataSetChanged();

            }
        });
    }

    private int addData() {
        User user;
        int size = mDataList.size();
        for (int i = 0; i < 5; i++) {
            user = new User(i + "", i + "");
            mDataList.add(user);
        }
        return size;
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
