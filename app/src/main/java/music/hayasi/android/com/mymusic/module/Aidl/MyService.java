package music.hayasi.android.com.mymusic.module.Aidl;


import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by raqusty on 2017/5/4.
 */

public class MyService extends Service {
    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    class MyBinder extends AIDLService.Stub {

        @Override
        public String getName() throws RemoteException {
            return "哈哈哈哈" +
                    "爽肤水";
        }

    }
}
