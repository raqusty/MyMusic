package music.hayasi.android.com.mymusic.common.utils;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeUtils {
//
//    public static String getCurrentTime(String format) {
//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());
//        String currentTime = sdf.format(date);
//
//        return currentTime;
//    }

//    public static String getCurrentTime() {
//        return getCurrentTime("yyyy-MM-dd  HH:mm:ss");
//    }


    public static String getTimeWithFormat(String format, long a) {
        String res;
        long lt = 1494992947*1000L;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();
        date.setTime(lt);
        res = simpleDateFormat.format(date);
        Log.i("linzehao",res + "  1");

        Date date1 = new Date();


        Log.i("linzehao","2   "+date1.getTime());
        Log.i("linzehao","3   "+ simpleDateFormat.format(date1));

        return res;
    }
}
