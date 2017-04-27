package music.hayasi.android.com.mymusic.module.model.Future;

public class RealData implements Data {
    protected String result;

    @Override
    public String getResult() {
        return result;
    }

    public RealData(String para) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
            result = sb.toString();
        }
    }
}