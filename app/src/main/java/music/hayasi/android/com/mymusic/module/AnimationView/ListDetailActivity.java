package music.hayasi.android.com.mymusic.module.AnimationView;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;

public class ListDetailActivity extends BaseActivity {
    ImageView mImageView;

    @Override
    protected int getContentViewResId() {
        return R.layout.anim_list_activity;
    }

    @Override
    public void initViews() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mImageView = (ImageView) findViewById(R.id.anim_detail_activity_dog);
    }


    @Override
    public void setListener() {
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("hight", mImageView.getHeight());
                intent.putExtra("width", mImageView.getWidth());
                intent.putExtra("top", mImageView.getTop());
                intent.putExtra("left", mImageView.getLeft());
//                Log.i("linzehao", "hight   " + mImageView.getHeight() + "  width  " + mImageView.getWidth() + "top  " + mImageView.getTop());
                startActivity(intent);
            }
        });
    }

    @Override
    public int getToolBarResId() {
        return R.menu.menu_buttom;
    }

    @Override
    public void initToolBar(ToolBarManager navigationBarMgr) {
    }

    @Override
    public Toolbar setToolBar() {
        return mToolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.button1:

                break;
            case R.id.button2:
                break;
            case R.id.button3:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

}
