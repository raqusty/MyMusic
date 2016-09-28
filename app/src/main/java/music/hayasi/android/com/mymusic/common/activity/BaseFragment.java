package music.hayasi.android.com.mymusic.common.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment implements
        IBasePageOperations {

    // 自身所属Activity实例
    protected Activity mContext;
    // 根视图实例
    protected View mRootView;

    /**
     * 获取ContentView的资源ID
     *
     * @return ContentView的资源ID
     */
    protected abstract int getContentViewResId();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // 获取自身所属Activity实例
        mContext = getActivity();
        //判断某些条件，成立就不初始化界面

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getContentViewResId() > 0) {
            View view = null;
            if (mRootView == null) {
                view = inflater.inflate(getContentViewResId(), container, false);
            }

            // 绑定控件
            ButterKnife.bind(this, mRootView == null ? view : mRootView);

            if (mRootView == null) {
                mRootView = view;
                // 初始化界面
                initViews();
                // 设置监听器
                setListener();
            }
        }
        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!isHidden()) {
            // 开始统计分析
        }
    }

    @Override
    public void onPause() {
        if (!isHidden()) {
            // 暂停统计分析
        }

        super.onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden) {
            if (isResumed()) {
            }
        } else {
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        // 取消绑定控件
//        ButterKnife.unbind(this);
//    }
//
//
//    /**
//     * 显示Loading页面
//     */
//    protected void showLoadingView(MultiStateView  multiStateView) {
//        if (multiStateView == null) {
//            LogUtil.printError(getClass(), "method showLoadingView()：multiStateView = null.");
//            return;
//        }
//
//        multiStateView.setViewState(MultiStateView.ViewState.LOADING);
//    }
//
//    /**
//     * 显示默认的无数据的提示页面
//     */
//    protected void showEmptyView(MultiStateView multiStateView) {
//        showEmptyView(multiStateView,null, null);
//    }
//
//    /**
//     * 显示无数据的提示页面
//     *
//     * @param emptyPhotoResId 空数据图片资源Id(可以传null)
//     * @param emptyTips       空数据文字提示(可以传null)
//     */
//    protected void showEmptyView(MultiStateView multiStateView , Integer emptyPhotoResId, String emptyTips) {
//        if (multiStateView == null) {
//            LogUtil.printError(getClass(), "method showEmptyView()：multiStateView = null.");
//            return;
//        }
//
//        if (emptyPhotoResId != null) {
//            ((ImageView) multiStateView
//                    .getView(MultiStateView.ViewState.EMPTY).findViewById(
//                            R.id.empty_view_iv_empty_photo))
//                    .setImageResource(emptyPhotoResId);
//        }
//        if (emptyTips != null) {
//            ((TextView) multiStateView.getView(MultiStateView.ViewState.EMPTY)
//                    .findViewById(R.id.empty_view_tv_text_tips))
//                    .setText(emptyTips);
//        }
//        multiStateView.setViewState(MultiStateView.ViewState.EMPTY);
//    }
//
//    /**
//     * 显示网络异常的提示页面
//     */
//    protected void showNetworkErrorView(MultiStateView multiStateView) {
//        showNetworkErrorView(multiStateView,null);
//    }
//
//    /**
//     * 显示网络异常的提示页面
//     *
//     * @param errorTips 异常数据文字提示(可以传null)
//     */
//    protected void showNetworkErrorView(MultiStateView multiStateView,String errorTips) {
//        if (multiStateView == null) {
//            LogUtil.printError(getClass(), "method showEmptyView()：showNetworkErrorView = null.");
//            return;
//        }
//
//        if (!TextUtils.isEmpty(errorTips)) {
//            ((TextView) multiStateView.getView(MultiStateView.ViewState.ERROR)
//                    .findViewById(R.id.error_view_tv_text_tips))
//                    .setText(errorTips);
//        }
//
//        multiStateView.setViewState(MultiStateView.ViewState.ERROR);
//        multiStateView.getView(MultiStateView.ViewState.ERROR).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                refreshPage();
//            }
//        });
//    }
//
//    /**
//     * 显示正常内容的页面
//     */
//    protected void showContentView(MultiStateView multiStateView) {
//        if (multiStateView == null) {
//            return;
//        }
//
//        multiStateView.setViewState(MultiStateView.ViewState.CONTENT);
//    }
//
//    /**
//     * 获取当前页面的状态
//     *
//     * @return 当前页面状态
//     */
//    protected MultiStateView.ViewState getPageState(MultiStateView multiStateView) {
//        return multiStateView == null ? null : multiStateView.getViewState();
//    }

    @Override
    public void refreshPage() {
        // Let sub class override this method when you need to refresh the page
    }
}
