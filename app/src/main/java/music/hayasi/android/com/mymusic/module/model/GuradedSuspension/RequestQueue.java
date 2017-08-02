package music.hayasi.android.com.mymusic.module.model.GuradedSuspension;

import java.util.LinkedList;

public class RequestQueue {
    private LinkedList<Request> queue = new LinkedList<Request>();

    public synchronized Request getRequest() {
        while (queue.size() == 0) {
            try {
                wait();
            } catch (Exception e) {

            }
        }
        return queue.remove();
    }

    public synchronized void addRequest(Request request) {
        queue.add(request);
        notifyAll();
    }

}