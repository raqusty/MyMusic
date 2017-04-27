package music.hayasi.android.com.mymusic.module.model.GuradedSuspension;

public class Request {
    private String name;

    public Request(String n) {
        name = n;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "[ Request  " + name + "]";
    }
}