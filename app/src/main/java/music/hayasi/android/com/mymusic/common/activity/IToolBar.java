package music.hayasi.android.com.mymusic.common.activity;

import android.support.v7.widget.Toolbar;

/**
 * 导航栏相关的接口
 */
public interface IToolBar {

    /**
     * 导航栏风格的枚举
     *
     * @author heweiyan
     */
//    public enum ToolBarStyle {
//        NORMAL, //普通
//        SEARCH, //搜索
//        OTHER;
//    }

    /**
     * 获取导航栏的R
     *
     * @return 导航栏的R
     * 普通： -1 ，其他：对应的R文件
     */
    int getToolBarResId();

    /**
     * 初始化顶部导航栏
     *
     * @param navigationBarMgr 顶部导航栏对象
     */
    void initToolBar(ToolBarManager navigationBarMgr);

    /**
     * 设置bar对象
     */
    Toolbar setToolBar();

//    /**
//     * 显示导航栏与内容之间的gap
//     *
//     * @param gapHeight gap的高度(单位:px)
//     */
//    void showGapBetweenNavigationBarAndContent(int gapHeight);
//
//    /**
//     * 隐藏导航栏与内容之间的gap
//     */
//    void hideGapBetweenNavigationBarAndContent();

}
