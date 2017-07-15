package music.hayasi.android.com.mymusic.common.utils;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import java.lang.reflect.Field;


public class UIUtils {

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        if (statusBarHeight == 0) {
            Class<?> c;
            try {
                c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = context.getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }

    /**
     * 获取window rect
     * 是否包括 bar
     */
    public static Rect getViewAbsRect(View view, Context context, boolean isBar) {
        int[] loc = new int[2];
        view.getLocationInWindow(loc);
        Rect rect = new Rect();
        int y = loc[1];
        if (isBar) {
            y = loc[1] - getStatusBarHeight(context);
        }
        rect.set(loc[0], y, loc[0] + view.getMeasuredWidth(), y + view.getMeasuredHeight());
        return rect;
    }
}
