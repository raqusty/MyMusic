package music.hayasi.android.com.mymusic.module.Messenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.view.View;
import android.widget.TextView;

import music.hayasi.android.com.mymusic.R;
import music.hayasi.android.com.mymusic.common.activity.BaseActivity;
import music.hayasi.android.com.mymusic.common.activity.ToolBarManager;
import music.hayasi.android.com.mymusic.databinding.MyActivityBinding;

public class MessageActivity extends BaseActivity {

    public TextView mText1;
    public TextView mText2;
    private int i = 0;

    private Messenger mService;

    private MyActivityBinding binding;

    @Override
    protected int getContentViewResId() {
        return R.layout.my_activity;
    }

    @Override
    public void initViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.my_activity);
        binding.setHandlers(new EventHandlers());

        Intent intent = new Intent(this, MessagerService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);


    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mService = new Messenger(service);
            Message msg = Message.obtain(null, 1);
            Bundle data = new Bundle();
            data.putString("msg", "hello, this is client");
            msg.setData(data);

            try {
                mService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public class EventHandlers {
        public void handleClick(View view) {

        }
    }

    private Messenger mGetReplyMessenger = new Messenger(new MessengerHandler());

    private class MessengerHandler extends Handler {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 2:
                    binding.text2.setText(msg.getData().getString("send"));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    @Override
    public void setListener() {
        binding.text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = Message.obtain(null, 2);
                Bundle data = new Bundle();
                data.putString("send", "" + i++);
                msg.setData(data);
                msg.replyTo = mGetReplyMessenger;
                try {
                    mService.send(msg);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getToolBarResId() {
        return R.menu.main;
    }

    @Override
    public void initToolBar(ToolBarManager navigationBarMgr) {

    }

    @Override
    protected void onDestroy() {
        unbindService(mConnection);
        super.onDestroy();
    }
}
