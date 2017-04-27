package music.hayasi.android.com.mymusic.module.model.Future;

import java.util.concurrent.Callable;

public class FutureDataJDK implements Callable<String> {

    private String para;

    public FutureDataJDK(String p) {
        para = p;
    }

    @Override
    public String call() throws Exception {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {

            }
        }
        return sb.toString();
    }
}