package music.hayasi.android.com.mymusic.common.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.widget.MultiStateView;

public abstract class BaseActivity extends AppCompatActivity implements IToolBar,
        IBasePageOperations {

    // 上下文
    protected Context mContext;
    // ActionBar实例
    private Toolbar mToolbar;

    /**
     * 获取ContentView的资源Id
     * 如果返回的资源Id小于等于0，那么就不会调用setContentView()方法，即该Activity没有界面
     *
     * @return ContentView的资源Id
     */
    protected abstract int getContentViewResId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 获取Application实例
        // 获取自身上下文
        mContext = this;
        // 注册Activity
        if (getContentViewResId() > 0) {
            // 设置布局
            setContentView(getContentViewResId());
        }
        // 绑定控件
        ButterKnife.bind(this);


        // 初始化界面
        initViews();
        // 获取顶部导航栏实例
        initBar();
        // 设置监听器
        setListener();
    }

    private void initBar() {
        mToolbar = setToolBar();
        if (mToolbar != null) {
            initToolBar(new ToolBarManager(this, mToolbar));
            //在这里初始化toolbar，下面那句一定要在最后
            setSupportActionBar(mToolbar);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (getToolBarResId() > 0 && mToolbar != null) {
            getMenuInflater().inflate(getToolBarResId(), menu);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 开始统计分析
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 暂停统计分析
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * 显示Loading页面
     */
    protected void showLoadingView(MultiStateView multiStateView) {
        if (multiStateView == null) {
            return;
        }

        multiStateView.setViewState(MultiStateView.ViewState.LOADING);
    }

    /**
     * 显示默认的无数据的提示页面
     */
    protected void showEmptyView(MultiStateView multiStateView) {
        showEmptyView(multiStateView,null, null);
    }

    /**
     * 显示无数据的提示页面
     *
     * @param emptyPhotoResId 空数据图片资源Id(可以传null)
     * @param emptyTips       空数据文字提示(可以传null)
     */
    protected void showEmptyView(MultiStateView multiStateView, Integer emptyPhotoResId, String emptyTips) {
        if (multiStateView == null) {
            return;
        }

        if (emptyPhotoResId != null) {
            ((ImageView) multiStateView
                    .getView(MultiStateView.ViewState.EMPTY).findViewById(
                            R.id.empty_view_iv_empty_photo))
                    .setImageResource(emptyPhotoResId);
        }
        if (emptyTips != null) {
            ((TextView) multiStateView.getView(MultiStateView.ViewState.EMPTY)
                    .findViewById(R.id.empty_view_tv_text_tips))
                    .setText(emptyTips);
        }
        multiStateView.setViewState(MultiStateView.ViewState.EMPTY);
    }

    /**
     * 显示网络异常的提示页面
     */
    protected void showNetworkErrorView(MultiStateView multiStateView) {
        showNetworkErrorView(multiStateView,null);
    }

    /**
     * 显示网络异常的提示页面
     *
     * @param errorTips 异常数据文字提示(可以传null)
     */
    protected void showNetworkErrorView(MultiStateView multiStateView,String errorTips) {
        if (multiStateView == null) {
            return;
        }

        if (!TextUtils.isEmpty(errorTips)) {
            ((TextView) multiStateView.getView(MultiStateView.ViewState.ERROR)
                    .findViewById(R.id.error_view_tv_text_tips))
                    .setText(errorTips);
        }

        multiStateView.setViewState(MultiStateView.ViewState.ERROR);
        multiStateView.getView(MultiStateView.ViewState.ERROR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshPage();
            }
        });
    }

    /**
     * 显示正常内容的页面
     */
    protected void showContentView(MultiStateView multiStateView) {
        if (multiStateView == null) {
            return;
        }

        multiStateView.setViewState(MultiStateView.ViewState.CONTENT);
    }

    /**
     * 获取当前页面的状态
     *
     * @return 当前页面状态
     */
    protected MultiStateView.ViewState getPageState(MultiStateView multiStateView) {
        return multiStateView == null ? null : multiStateView.getViewState();
    }

    @Override
    public void refreshPage() {
        // Let sub class override this method when you need to refresh the page
    }
}
