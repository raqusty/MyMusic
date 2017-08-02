package music.hayasi.android.com.mymusic.module.Path.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */

public class PathEntity {
    public List<PathEntity> mList = new ArrayList<PathEntity>();
    float point1_x;
    float point1_y;
    float point2_x;
    float point2_y;
    float point3_x;
    float point3_y;
    float point4_x;
    float point4_y;
    int Id;
    int parentId;
    boolean isRead = false;

    public float getPoint1_x() {
        return point1_x;
    }

    public void setPoint1_x(float point1_x) {
        this.point1_x = point1_x;
    }

    public float getPoint1_y() {
        return point1_y;
    }

    public void setPoint1_y(float point1_y) {
        this.point1_y = point1_y;
    }

    public float getPoint2_x() {
        return point2_x;
    }

    public void setPoint2_x(float point2_x) {
        this.point2_x = point2_x;
    }

    public float getPoint2_y() {
        return point2_y;
    }

    public void setPoint2_y(float point2_y) {
        this.point2_y = point2_y;
    }

    public float getPoint3_x() {
        return point3_x;
    }

    public void setPoint3_x(float point3_x) {
        this.point3_x = point3_x;
    }

    public float getPoint4_y() {
        return point4_y;
    }

    public void setPoint4_y(float point4_y) {
        this.point4_y = point4_y;
    }

    public float getPoint3_y() {
        return point3_y;
    }

    public void setPoint3_y(float point3_y) {
        this.point3_y = point3_y;
    }

    public float getPoint4_x() {
        return point4_x;
    }

    public void setPoint4_x(float point4_x) {
        this.point4_x = point4_x;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public List<PathEntity> getmList() {
        return mList;
    }

    public void setmList(List<PathEntity> mList) {
        this.mList = mList;
    }
}
