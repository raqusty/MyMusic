package music.hayasi.android.com.mymusic.module.CustomView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.Bind;
import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;

public class CustomViewActivity extends BaseActivity {

    @Bind(R.id.container)
    HorizontalScrollViewEx mScrollviewEx;

    @Override

    protected int getContentViewResId() {
        return R.layout.custom_view_activity;
    }

    @Override
    public void initViews() {
        LayoutInflater inflater = getLayoutInflater();
        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.custom_list_layout, mScrollviewEx, false);
            layout.getLayoutParams().width=200;
            layout.getLayoutParams().height=500;
            ListView listview = (ListView) layout.findViewById(R.id.listview);
            ArrayList<String> datas = new ArrayList<String>();
            for (int j = 0; j < 50; j++) {
                datas.add("name" + j);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_dropdown_item_1line, datas);
            listview.setAdapter(adapter);
            mScrollviewEx.addView(layout);
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
}
