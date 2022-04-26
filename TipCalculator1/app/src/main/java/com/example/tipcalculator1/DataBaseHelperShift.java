package com.example.tipcalculator1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelperShift extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "TipCalculator.db";
    private static final String TABLE_NAME = "Shift";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "date";
    private static final String COL_4 = "worked_hours";
    private static final String COL_5 = "tips";
    private static final String COL_6 = "user_id";


    public DataBaseHelperShift(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (ID Integer primary key autoincrement, date Text, worked_hours Text, tips Text, user_id Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean createShift (String date, String worked_hours, String tips, String user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,date);
        contentValues.put(COL_4,worked_hours);
        contentValues.put(COL_5,tips);
        contentValues.put(COL_6,user_id);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getALlData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Cursor getShift(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where ID = '" + id + "'", null);
        return res;
    }

    public Cursor getShiftByUser(String user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where user_id = '" + user_id + "'", null);
        return res;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, " ID = ?", new String[] {id});
    }
    public Integer deleteDataByUser (String user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, " user_id = ?", new String[] {user_id});
    }

    public boolean update(String id, String date , String tips, String hourly_rate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2,date);
        contentValues.put(COL_4,tips);
        contentValues.put(COL_5,hourly_rate);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});
        return  true;
    }

    public boolean update(String id, String date , String worked_hours, String tips, String user_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2,date);
        contentValues.put(COL_4,worked_hours);
        contentValues.put(COL_5,tips);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {id});
        return  true;
    }
}
