package music.hayasi.android.com.mymusic.module.Path.sp;

import android.content.Context;

import music.hayasi.android.com.mymusic.common.utils.SharedPreferencesUtils;

/**
 * Created by Administrator on 2017/7/17.
 */

public class pathSp extends SharedPreferencesUtils {
    public pathSp(Context context) {
        super(context);
    }

    @Override
    protected String getDatabase() {
        return "path";
    }

    public String getKey(String group, String index, String key) {
        return group + "_" + index + "_" + key;
    }
}
