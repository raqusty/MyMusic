package music.hayasi.android.com.mymusic.module.main;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.common.net.OkHttpUtils;
import music.hayasi.android.com.mymusic.common.net.callback.DialogCallback;
import music.hayasi.android.com.mymusic.common.widget.MultiStateView;
import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    Activity mActivity;

    final MyHandler handler = new MyHandler();
    @Bind(R.id.toolbar)
    Toolbar mToolBar;
    @Bind(R.id.common_lv_multi_state_view)
    MultiStateView mMultiStateView;
    @Bind(R.id.ssss)
    TextView mTextView;

    @Override
    protected int getContentViewResId() {
        return R.layout.activity_main;
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initViews() {
        mActivity = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpUtils.get("https://mail.qq.com/cgi-bin/loginpage?s=session_timeout&from=&r=8999d5c5b71fc4a3efa5d6aa7f0a0de5&tiptype=LOGIN_ERR_COOKIE_FORBIDDEN")//
                        .tag(this)//
                        .execute(new DialogCallback<String>(mActivity, MainActivity.class) {
                            @Override
                            public void onSuccess(String serverModel, Call call, Response response) {
                                mTextView.setText(serverModel);
                            }

                            @Override
                            public void onError(Call call, Response response, Exception e) {
                                super.onError(call, response, e);
//                            handleError(call, response);
                                Log.i("linzehao", "response "+e.getMessage());
                            }
                        });
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

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
        return mToolBar;
    }

    static class MyHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String val = data.getString("value");
            Log.i("linzehao", "请求结果为-->" + val);

        }
    }
}
