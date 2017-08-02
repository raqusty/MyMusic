package music.hayasi.android.com.mymusic.module.model.GuradedSuspension;

import android.util.Log;

import music.hayasi.android.com.mymusic.module.model.GuradedSuspension.entity.FutureData;
import music.hayasi.android.com.mymusic.module.model.GuradedSuspension.entity.RealData;

public class ServerThread extends Thread {

    private RequestQueue requestQueue;

    public ServerThread(RequestQueue rq, String name) {
        super(name);
        requestQueue = rq;
    }

    public void run() {
        while (true) {
            final Request request = requestQueue.getRequest();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            FutureData data = (FutureData) request.getData();
            RealData real = new RealData("haha");
            data.DataChange(real);
            Log.i("linzehao", request.getName());
        }
    }
}