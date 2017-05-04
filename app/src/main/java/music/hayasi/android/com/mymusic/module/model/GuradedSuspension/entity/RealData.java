package music.hayasi.android.com.mymusic.module.model.GuradedSuspension.entity;

public class RealData implements Data {

    private String result;

    public RealData(String n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(n);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            result = sb.toString();
        }
    }

    @Override
    public String getResult() {
        return result;
    }
}