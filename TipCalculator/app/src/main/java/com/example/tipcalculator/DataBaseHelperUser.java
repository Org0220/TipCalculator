package com.example.tipcalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelperUser extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TipCalculator.db";
    private static final String TABLE_NAME = "Shift";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "user_name";
    private static final String COL_3 = "password";
    private static final String COL_4 = "first_name";
    private static final String COL_5 = "last_name";
    private static final String COL_6 = "position";


    public DataBaseHelperUser(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (ID Integer primary key autoincrement, user_name Text, password Text, first_name Text, last_name Text, position Text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    public boolean insertData (String user_name, String password, String first_name, String last_name, String position) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,user_name);
        contentValues.put(COL_3,password);
        contentValues.put(COL_4,first_name);
        contentValues.put(COL_5,last_name);
        contentValues.put(COL_6,position);

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

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, " ID = ?", new String[] {id});

    }

    public boolean update (String id, String user_name, String password, String first_name, String last_name, String position) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, id);
        contentValues.put(COL_2,user_name);
        contentValues.put(COL_3,password);
        contentValues.put(COL_4,first_name);
        contentValues.put(COL_5,last_name);
        contentValues.put(COL_6,position);

        db.update(TABLE_NAME, contentValues, "ID = ? ", new String[] {id});
        return  true;
    }
}
