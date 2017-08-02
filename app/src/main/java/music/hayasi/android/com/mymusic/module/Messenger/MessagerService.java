package music.hayasi.android.com.mymusic.module.Messenger;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

public class MessagerService extends Service {

    private static final String TAG = "linzehao";
    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }

    private static class MessengerHandler extends Handler {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Log.i(TAG, "receive " + msg.getData().getString("msg"));
                    break;
                case 2:
                    Messenger messenger = msg.replyTo;
                    Message smsg = Message.obtain(null, 2);
                    Bundle data = new Bundle();
                    data.putString("send", msg.getData().getString("send"));
                    smsg.setData(data);
                    try {
                        messenger.send(smsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "receive " + msg.getData().getString("send"));
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }
}
