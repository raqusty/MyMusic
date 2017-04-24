package music.hayasi.android.com.mymusic.common.myNet.Impl;


import android.app.Application;

public interface INetUitl {

    /**
     * 初始化
     **/
    public void init(Application application);

    /**
     * 获取字符串数据
     **/
    public void requestString();

    /**
     * 获取文件数据
     **/
    public void requestFile();

    /**
     * 取消请求
     **/
    public void cancelRequest();


}
