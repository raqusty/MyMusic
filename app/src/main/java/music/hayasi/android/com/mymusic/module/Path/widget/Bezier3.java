package music.hayasi.android.com.mymusic.module.Path.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */

public class Bezier3 extends View {
    private Paint mPaint;
    private int centerX, centerY;

    private PointF point1, point2, point3, point4, point5, point6;

    private List<List<PointF>> mList;
    private List<PointF> mControlList;

    public Bezier3(Context context) {
        this(context, null);

    }

    public Bezier3(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        point1 = new PointF(200, 200);
        point2 = new PointF(400, 200);
        point3 = new PointF(300, 700);
        point4 = new PointF(200, 100);
        point5 = new PointF(330, 50);
        point6 = new PointF(400, 800);


        List<PointF> list1 = new ArrayList<>();
        list1.add(point1);
        list1.add(point2);
        list1.add(point3);

        List<PointF> list2 = new ArrayList<>();
        list2.add(point6);
        list2.add(point4);
        list2.add(point5);

        mList = new ArrayList<List<PointF>>();
        mList.add(list1);
        mList.add(list2);

        mControlList = new ArrayList<PointF>();
        PointF control1 = new PointF(200, 200);
        PointF control2 = new PointF(400, 200);
        PointF control3 = new PointF(300, 700);
        mControlList.add(control1);
        mControlList.add(control2);
        mControlList.add(control3);
    }

    public void setControl1(int per) {
        //获取列表的数目，如果小于两个，不成一条线，不处理
        int count = mList.size();
        if (count < 2)
            return;
        //划分等长区间，区间数  100除以数组长度 -1  如：100 / (list。size - 1) = 100 /(3-1) = 50
        float partCount = 100 / (count - 1);
        //区间从零开始
        int partNum = 0;//从零开始
        //用下面的方法快速定位在哪个区间100 做特殊处理
        if (per == 100) {
            partNum = count - 2;
        } else {
            partNum = per / (int) partCount;
        }

        List<PointF> firstLayout = mList.get(partNum);
        List<PointF> secondLayout = mList.get(partNum + 1);
        for (int i = 0; i < firstLayout.size(); i++) {
            //获取开始点跟结束点
            PointF firstPoint = firstLayout.get(i);
            PointF secondePoint = secondLayout.get(i);
            PointF controlPoint = mControlList.get(i);

            //算出开始点跟结束点的距离
            float disx = secondePoint.x - firstPoint.x;
            float disy = secondePoint.y - firstPoint.y;

            //算出距离跟百分比算出要走的距离  如：per_s = 60 -1* 50 = 10  ;s = per_s / partCount = 10 / 50
            int per_s = per - partNum * (int) partCount;
            float s = per_s / partCount;

            //算出当前位置
            float x = disx * s;
            float y = disy * s;
            controlPoint.x = firstPoint.x + x;
            controlPoint.y = firstPoint.y + y;
        }

        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //drawCoordinateSystem(canvas);

        // 绘制数据点和控制点
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(3);

        canvas.drawLine(point1.x, point1.y, point6.x, point6.y, mPaint);

        canvas.drawLine(point2.x, point2.y, point4.x, point4.y, mPaint);

        canvas.drawLine(point3.x, point3.y, point5.x, point5.y, mPaint);


        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(6);
        for (PointF control : mControlList) {
            canvas.drawCircle(control.x, control.y, 6, mPaint);
        }


    }
}
