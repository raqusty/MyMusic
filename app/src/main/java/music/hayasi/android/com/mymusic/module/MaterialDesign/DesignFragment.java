package music.hayasi.android.com.mymusic.module.MaterialDesign;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseFragment;

public class DesignFragment extends BaseFragment {
    private int num = 0;

     ;
    @Bind(R.id.nested_scroll_view)
    NestedScrollView mNestedScrollView;

    public void setNum(int n) {
        num = n;
    }

    @Override
    public void initViews() {
        mNestedScrollView.setSmoothScrollingEnabled(true);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        Log.i("linzehao", "num +" + num + isVisibleToUser);
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void setListener() {

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView != null) {
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    @Override
    protected int getContentViewResId() {
        return R.layout.fragment_scroll_view;
    }
}