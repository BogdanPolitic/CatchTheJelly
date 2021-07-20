package co.snOmOtiOn.bogdan.catchthejellyv112020;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Statistics1.db";
    public static final String TABLE_NAME = "statistics_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "LIVES";
    public static final String COL_3 = "MAX_REACHED_LEVEL";
    public static final String COL_4 = "REWARD_NEXT_WIN";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, LIVES INTEGER, MAX_REACHED_LEVEL INTEGER, REWARD_NEXT_WIN INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(int lives, int maxReachedLevel, int rewardNextWin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, lives);
        contentValues.put(COL_3, maxReachedLevel);
        contentValues.put(COL_4, rewardNextWin);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String id, int lives, int maxReachedLevel, int rewardNextWin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, lives);
        contentValues.put(COL_3, maxReachedLevel);
        contentValues.put(COL_4, rewardNextWin);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] { id });
        return true;
    }

    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] { id });
    }
}