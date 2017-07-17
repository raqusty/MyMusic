package music.hayasi.android.com.mymusic.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public abstract class SharedPreferencesUtils {
    private Context mContext;

    protected SharedPreferencesUtils(Context context) {
        mContext = context;
    }

    protected abstract String getDatabase();

    public void putValue(String key, int value) {
        Editor sp = mContext.getSharedPreferences(getDatabase(), Context.MODE_PRIVATE).edit();
        sp.putInt(key, value);
        sp.commit();
    }

    public void putValue(String key, boolean value) {
        Editor sp = mContext.getSharedPreferences(getDatabase(), Context.MODE_PRIVATE).edit();
        sp.putBoolean(key, value);
        sp.commit();
    }

    public void putValue(String key, String value) {
        Editor sp = mContext.getSharedPreferences(getDatabase(), Context.MODE_PRIVATE).edit();
        sp.putString(key, value);
        sp.commit();
    }

    public int getValue(String key, int defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(getDatabase(), Context.MODE_PRIVATE);
        int value = sp.getInt(key, defValue);
        return value;
    }

    public boolean getValue(String key, boolean defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(getDatabase(), Context.MODE_PRIVATE);
        boolean value = sp.getBoolean(key, defValue);
        return value;
    }

    public String getValue(String key, String defValue) {
        SharedPreferences sp = mContext.getSharedPreferences(getDatabase(), Context.MODE_PRIVATE);
        String value = sp.getString(key, defValue);
        return value;
    }

    //保存list的写法
//    public boolean saveArray(List<String> list) {
//        SharedPreferences sp = mContext.getSharedPreferences("ingoreList", mContext.MODE_PRIVATE);
//        SharedPreferences.Editor mEdit1= sp.edit();
//        mEdit1.putInt("Status_size",list.size());
//
//        for(int i=0;i<list.size();i++) {
//            mEdit1.remove("Status_" + i);
//            mEdit1.putString("Status_" + i, list.get(i));
//        }
//        return mEdit1.commit();
//    }

}
