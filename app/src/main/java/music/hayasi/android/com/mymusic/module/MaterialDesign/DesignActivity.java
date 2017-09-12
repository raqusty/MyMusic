package music.hayasi.android.com.mymusic.module.MaterialDesign;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;

public class DesignActivity extends BaseActivity {

    private TabLayout tab_FindFragment_title;                            //定义TabLayout
    private ViewPager vp_FindFragment_pager;                             //定义viewPager
    private FragmentPagerAdapter fAdapter;                               //定义adapter

    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表

    private DesignFragment hotRecommendFragment;              //热门推荐fragment
    private DesignFragment hotCollectionFragment;            //热门收藏fragment
    private DesignFragment hotMonthFragment;                      //本月热榜fragment
    private DesignFragment hotToday;                                      //今日热榜fragment
    private DesignFragment hotToday2;
    private DesignFragment hotToday3;
    private DesignFragment hotToday4;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
    }

    /**
     * 初始化各控件
     */
    private void initControls() {

        tab_FindFragment_title = (TabLayout) findViewById(R.id.tab_FindFragment_title);
        vp_FindFragment_pager = (ViewPager) findViewById(R.id.vp_FindFragment_pager);

        //初始化各fragment
        hotRecommendFragment = new DesignFragment();
        hotRecommendFragment.setNum(11);
        hotCollectionFragment = new DesignFragment();
        hotCollectionFragment.setNum(22);
        hotMonthFragment = new DesignFragment();
        hotMonthFragment.setNum(33);
        hotToday = new DesignFragment();
        hotToday.setNum(44);
        hotToday2 = new DesignFragment();
        hotToday2.setNum(55);
        hotToday3 = new DesignFragment();
        hotToday3.setNum(66);
        hotToday4 = new DesignFragment();
        hotToday4.setNum(77);

        //将fragment装进列表中
        list_fragment = new ArrayList<>();
        list_fragment.add(hotRecommendFragment);
        list_fragment.add(hotCollectionFragment);
        list_fragment.add(hotMonthFragment);
        list_fragment.add(hotToday);
        list_fragment.add(hotToday2);
        list_fragment.add(hotToday3);
        list_fragment.add(hotToday4);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("热门推荐");
        list_title.add("热门收藏");
        list_title.add("本月热榜");
        list_title.add("今日热榜");
        list_title.add("今日热榜");
        list_title.add("今日热榜");
        list_title.add("今日热榜");


        //设置TabLayout的模式
        tab_FindFragment_title.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(0)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(1)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(2)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(3)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(4)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(5)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(6)));


        fAdapter = new Find_tab_Adapter(this.getSupportFragmentManager(), list_fragment, list_title);
        vp_FindFragment_pager.setOffscreenPageLimit(1);
        //viewpager加载adapter
        vp_FindFragment_pager.setAdapter(fAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        tab_FindFragment_title.setupWithViewPager(vp_FindFragment_pager);
        //tab_FindFragment_title.set
    }

    @Override
    public void initViews() {
        Log.i("linzehao","initViews");
        initControls();
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
    protected int getContentViewResId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("linzehao", "onDestroyView  1" );
    }
}