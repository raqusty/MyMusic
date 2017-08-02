package music.hayasi.android.com.mymusic.module.main;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.common.image.ImageUtil;

public class MyImage extends BaseActivity {

    @Bind(R.id.text2)
    TextView mMultiStateView;
    @Bind(R.id.text1)
    TextView mTextView;
    @Bind(R.id.image)
    ImageView mImageView;

    @Override
    protected int getContentViewResId() {
        return R.layout.my_rx_java;
    }

    @Override
    public void initViews() {

        String imageUrl = "http://img02.tooopen.com/images/20141231/sy_78327074576.jpg";

        ImageUtil.displayImage(0, imageUrl, mImageView);

//        ImageView mImageView = (ImageView) findViewById(R.id.image);

        //显示图片的配置
//        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.ic_stub)
//                .showImageOnFail(R.drawable.ic_error)
//                .cacheInMemory(true)
//                .cacheOnDisk(true)
//                .bitmapConfig(Bitmap.Config.RGB_565)
//                .build();
//
//        ImageLoader.getInstance().displayImage(imageUrl, mImageView, options);

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

    @Override
    public Toolbar setToolBar() {
        return null;
    }
}
