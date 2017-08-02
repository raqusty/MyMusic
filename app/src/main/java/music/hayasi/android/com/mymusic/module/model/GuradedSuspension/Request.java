package music.hayasi.android.com.mymusic.module.model.GuradedSuspension;

import music.hayasi.android.com.mymusic.module.model.GuradedSuspension.entity.Data;

public class Request {
    private String name;
    private Data data;

    public Request(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "[ Request  " + name + "]";
    }

    public Data getData() {
        return data;
    }

    public void setData(Data response) {
        this.data = response;
    }
}