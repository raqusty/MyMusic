package music.hayasi.android.com.mymusic.module.model.GuradedSuspension.entity;

public class FutureData implements Data {

    private RealData realData;
    private boolean isReady = false;


    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        return realData.getResult();
    }

    public void DataChange(RealData r) {
        if (isReady) {
            return;
        }
        this.realData = r;
        isReady = true;
        notifyAll();
    }
}