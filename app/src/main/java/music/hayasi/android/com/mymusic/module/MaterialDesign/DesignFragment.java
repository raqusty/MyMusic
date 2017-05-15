package music.hayasi.android.com.mymusic.module.MaterialDesign;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseFragment;

public class DesignFragment extends BaseFragment {
    private int num = 0;

    public void setNum(int n) {
        num = n;
    }

    @Override
    public void initViews() {
        Log.i("linzehao", "num  " + num);
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
            Log.i("linzehao", "onCreateView " + num);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onPause() {
        Log.i("linzehao", "num  onPause " + num);
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        Log.i("linzehao", "num  onDestroyView " + num);
        super.onDestroyView();
    }


    @Override
    protected int getContentViewResId() {
        return R.layout.error_view;
    }
}