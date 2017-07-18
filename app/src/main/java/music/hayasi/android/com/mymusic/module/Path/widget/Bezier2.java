package music.hayasi.android.com.mymusic.module.Path.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import music.hayasi.android.com.mymusic.module.Path.entity.PathEntity;

/**
 * Created by Administrator on 2017/7/17.
 */

public class Bezier2 extends View {
    private Paint mPaint;
    private int centerX, centerY;

    private PointF start, end, control1, control2;

    private int isDo = -1;
    private int mType = -1;
    private PointListten mPointListten;

    private List<List<PointF>> mList;
    private List<PointF> mDrawList = new ArrayList<PointF>();

    public Bezier2(Context context) {
        this(context, null);

    }

    public Bezier2(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        control1 = new PointF(0, 0);
        control2 = new PointF(0, 0);
    }

    public void setDo(int d) {
        this.isDo = d;
    }

    public void setType(int type) {
        this.mType = type;
    }

    public int getType() {
        return mType;
    }

    public void setListten(PointListten l) {
        mPointListten = l;
    }

    public void setPoint(PointF start, PointF end, PointF control1, PointF control2) {
        this.start = start;
        this.end = end;
        this.control1 = control1;
        this.control2 = control2;
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 根据触摸位置更新控制点，并提示重绘
        switch (mType) {
            case 1:
                start.x = event.getX();
                start.y = event.getY();
                if (mPointListten != null)
                    mPointListten.pointChange(start.x, start.y, mType);
                break;
            case 2:
                end.x = event.getX();
                end.y = event.getY();
                if (mPointListten != null)
                    mPointListten.pointChange(end.x, end.y, mType);
                break;
            case 3:
                control1.x = event.getX();
                control1.y = event.getY();
                if (mPointListten != null)
                    mPointListten.pointChange(control1.x, control1.y, mType);
                break;
            case 4:
                control2.x = event.getX();
                control2.y = event.getY();
                if (mPointListten != null)
                    mPointListten.pointChange(control2.x, control2.y, mType);
                break;
        }
        invalidate();
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w / 2;
        centerY = h / 2;

        // 初始化数据点和控制点的位置
        start.x = centerX - 200;
        start.y = centerY;
        end.x = centerX + 200;
        end.y = centerY;
        control1.x = centerX;
        control1.y = centerY - 100;
        control2.x = centerX;
        control2.y = centerY - 100;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //drawCoordinateSystem(canvas);
        if (isDo == 1) {
            // 绘制数据点和控制点
            mPaint.setColor(Color.GRAY);
            mPaint.setStrokeWidth(20);
            canvas.drawPoint(start.x, start.y, mPaint);
            canvas.drawPoint(end.x, end.y, mPaint);
            canvas.drawPoint(control1.x, control1.y, mPaint);
            canvas.drawPoint(control2.x, control2.y, mPaint);

            // 绘制辅助线
            mPaint.setStrokeWidth(4);
            canvas.drawLine(start.x, start.y, control1.x, control1.y, mPaint);
//            canvas.drawLine(control1.x, control1.y, control2.x, control2.y, mPaint);
            canvas.drawLine(control2.x, control2.y, end.x, end.y, mPaint);

            // 绘制贝塞尔曲线
            mPaint.setColor(Color.RED);
            mPaint.setStrokeWidth(8);

            Path path = new Path();

            path.moveTo(start.x, start.y);
            path.cubicTo(control1.x, control1.y, control2.x, control2.y, end.x, end.y);

            canvas.drawPath(path, mPaint);
        } else if (isDo == 2) {
            for (int i = 0; i < mDrawList.size(); i += 4) {
                PointF pointStart = mDrawList.get(i);
                PointF pointEnd = mDrawList.get(i + 1);
                PointF point1 = mDrawList.get(i + 2);
                PointF point2 = mDrawList.get(i + 3);

                // 绘制贝塞尔曲线
                mPaint.setColor(Color.RED);
                mPaint.setStrokeWidth(8);

                Path path = new Path();

                path.moveTo(pointStart.x, pointStart.y);
                path.cubicTo(point1.x, point1.y, point2.x, point2.y, pointEnd.x, pointEnd.y);

                canvas.drawPath(path, mPaint);
            }

        }

    }

    public void setDataList(List<PathEntity> dataList, int minCount) {
        mList = new ArrayList<List<PointF>>();
        List<PointF> list = null;
        for (int i = 0; i < minCount; i++) {
            PointF pointstart = new PointF(0, 0);
            PointF pointend = new PointF(0, 0);
            PointF point1 = new PointF(0, 0);
            PointF point2 = new PointF(0, 0);
            mDrawList.add(pointstart);
            mDrawList.add(pointend);
            mDrawList.add(point1);
            mDrawList.add(point2);
        }

        for (int i = 0; i < dataList.size(); i++) {
            list = new ArrayList<PointF>();

            PathEntity entity = dataList.get(i);
            List<PathEntity> pointList = entity.getmList();
            for (int j = 0; j < pointList.size(); j++) {
                if (j >= minCount)
                    break;
                PathEntity point = pointList.get(j);
                PointF pointstart = new PointF(point.getPoint1_x(), point.getPoint1_y());
                PointF pointend = new PointF(point.getPoint2_x(), point.getPoint2_y());
                PointF point1 = new PointF(point.getPoint3_x(), point.getPoint3_y());
                PointF point2 = new PointF(point.getPoint4_x(), point.getPoint4_y());
                list.add(pointstart);
                list.add(pointend);
                list.add(point1);
                list.add(point2);
            }
            mList.add(list);
        }
    }


    public void setControl(int per) {
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
            PointF controlPoint = mDrawList.get(i);

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


    public interface PointListten {
        public void pointChange(float x, float y, int type);
    }
}
