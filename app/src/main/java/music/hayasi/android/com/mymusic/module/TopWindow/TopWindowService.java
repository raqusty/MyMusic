package music.hayasi.android.com.mymusic.module.TopWindow;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;

import java.util.List;

import music.hayasi.android.com.mymusic.R;

public class TopWindowService extends Service {
    public static final String OPERATION = "operation";
    public static final int OPERATION_SHOW = 100;
    public static final int OPERATION_HIDE = 101;

    private static final int HANDLE_SHOW_ACTIVITY = 200;
    private static final int HANDLE_HIDE_ACTIVITY = 201;

    private boolean isAdded = false; // �Ƿ�������������
    private static WindowManager wm;
    private static WindowManager.LayoutParams params;
    private Button btn_floatView;

    private List<String> homeList; // ����Ӧ�ó�������б�
    private ActivityManager mActivityManager;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createFloatView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isAdded) {
            wm.removeView(btn_floatView);
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        int operation = intent.getIntExtra(OPERATION, OPERATION_SHOW);
        switch (operation) {
            case OPERATION_SHOW:
                mHandler.sendEmptyMessage(HANDLE_SHOW_ACTIVITY);
                break;
            case OPERATION_HIDE:
                mHandler.sendEmptyMessage(HANDLE_HIDE_ACTIVITY);
                break;
        }
        return super.onStartCommand(intent, flags, startId);
    }


    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLE_SHOW_ACTIVITY:
                    if (!isAdded) {
                        wm.addView(btn_floatView, params);
                        isAdded = true;
                    }
                    break;
                case HANDLE_HIDE_ACTIVITY:
                    if (isAdded) {
                        wm.removeView(btn_floatView);
                        isAdded = false;
                    }
                    break;
            }
        }
    };




    private void createFloatView() {
        btn_floatView = new Button(getApplicationContext());
        btn_floatView.setText("1123");
        btn_floatView.setBackgroundResource(R.mipmap.blue_torus);

        wm = (WindowManager) getApplicationContext().getSystemService(
                Context.WINDOW_SERVICE);
        params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        params.format = PixelFormat.RGBA_8888;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        params.width = 80;
        params.height = 80;

        btn_floatView.setOnTouchListener(new OnTouchListener() {
            int lastX, lastY;
            int paramX, paramY;

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastX = (int) event.getRawX();
                        lastY = (int) event.getRawY();
                        paramX = params.x;
                        paramY = params.y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int dx = (int) event.getRawX() - lastX;
                        int dy = (int) event.getRawY() - lastY;
                        params.x = paramX + dx;
                        params.y = paramY + dy;
                        wm.updateViewLayout(btn_floatView, params);
                        break;
                }
                return true;
            }
        });

        wm.addView(btn_floatView, params);
        isAdded = true;
    }


}
