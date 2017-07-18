package music.hayasi.android.com.mymusic.module.Path.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017/7/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    private static final String dataBase = "myDataBase"; //数据库名称

    private static final int version = 1; //数据库版本

    public DatabaseHelper(Context context) {
        super(context, dataBase, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE IF NOT EXISTS person (personid integer primary key autoincrement, name varchar(20), age INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS path (Id integer primary key autoincrement," +
                " ParentId INTEGER," +
                " point1_x FLOAT,point1_y FLOAT,point2_x FLOAT,point2_y FLOAT," +
                " point3_x FLOAT,point3_y FLOAT,point4_x FLOAT,point4_y FLOAT" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
