package music.hayasi.android.com.mymusic.module.Path.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import music.hayasi.android.com.mymusic.module.Path.entity.PathEntity;

/**
 * Created by Administrator on 2017/7/17.
 */

public class DatabaseUtils {
    DatabaseHelper databaseHelper = null;

    public DatabaseUtils(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void insertData(PathEntity entity) {
        if (databaseHelper == null)
            return;
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ParentId", entity.getParentId());
        values.put("point1_x", entity.getPoint1_x());
        values.put("point1_y", entity.getPoint1_y());
        values.put("point2_x", entity.getPoint2_x());
        values.put("point2_y", entity.getPoint2_y());
        values.put("point3_x", entity.getPoint3_x());
        values.put("point3_y", entity.getPoint3_y());
        values.put("point4_x", entity.getPoint4_x());
        values.put("point4_y", entity.getPoint4_y());
        long rowid = db.insert("path", null, values);//返回新添记录的行号，与主键id无关
        entity.setId((int) rowid);
        db.close();
    }

    public void insertDataParentID(PathEntity entity) {
        if (databaseHelper == null)
            return;
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ParentId", entity.getParentId());
        long rowid = db.insert("path", null, values);//返回新添记录的行号，与主键id无关
        entity.setId((int) rowid);
        db.close();
    }

    public void updataData(PathEntity entity) {
        if (databaseHelper == null)
            return;
        Log.i("linzehao", "Id " + entity.getId() + " x3  " + entity.getPoint3_x());
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ParentId", entity.getParentId());
        values.put("point1_x", entity.getPoint1_x());
        values.put("point1_y", entity.getPoint1_y());
        values.put("point2_x", entity.getPoint2_x());
        values.put("point2_y", entity.getPoint2_y());
        values.put("point3_x", entity.getPoint3_x());
        values.put("point3_y", entity.getPoint3_y());
        values.put("point4_x", entity.getPoint4_x());
        values.put("point4_y", entity.getPoint4_y());
        long rowid = db.update("path", values, "Id=?", new String[]{entity.getId() + ""});
        db.close();
    }

    public List<PathEntity> getData(int parentId) {
        if (databaseHelper == null)
            return null;
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from path where parentId = ?", new String[]{parentId + ""});
        List<PathEntity> mDatas = new ArrayList<PathEntity>();
        while (cursor.moveToNext()) {
            PathEntity path = new PathEntity();
            path.setId(cursor.getInt(0));  //获取第一列的值,第一列的索引从0开始
            path.setParentId(cursor.getInt(1));

            path.setPoint1_x(cursor.getFloat(2));
            path.setPoint1_y(cursor.getFloat(3));
            path.setPoint2_x(cursor.getFloat(4));
            path.setPoint2_y(cursor.getFloat(5));
            path.setPoint3_x(cursor.getFloat(6));
            path.setPoint3_y(cursor.getFloat(7));
            path.setPoint4_x(cursor.getFloat(8));
            path.setPoint4_y(cursor.getFloat(9));
            mDatas.add(path);
        }

        cursor.close();
        db.close();
        return mDatas;
    }




}
