// ICallBack.aidl
package music.hayasi.android.com.mymusic;

// Declare any non-default types here with import statements

interface ICallBack {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
       void handleByServer(String param);
}
