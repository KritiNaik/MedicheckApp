package com.test.app32;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper2 extends SQLiteOpenHelper {

    public DatabaseHelper2(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public static final String DATABASE_NAM="routine.db";
    public static final String TABLE_NAM ="routine_table";
    public static final String COLm_1 ="Date";
    public static final String COLm_2 ="Weight";
    public static final String COLm_3 ="Height";
    public static final String COLm_4 ="BP";
    public static final String COLm_5 ="Pulse_rate";
    public static final String COLm_6 ="Diabetes";
    public static final String COLm_7 ="Oxymeter";

    public DatabaseHelper2(Context context) {
        super(context, DATABASE_NAM, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAM +" (Date TEXT PRIMARY KEY, Weight INTEGER, Height INTEGER,BP INTEGER, Pulse_rate INTEGER,Diabetes INTEGER, Oxymeter INTEGER )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAM);
        onCreate(db);
    }
    public boolean insertData(String date, String weight, String height, String blood_pressure,String pulse_rate, String diabetes, String oxymeter ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLm_1, date);
        contentValues.put(COLm_2, weight);
        contentValues.put(COLm_3, height);
        contentValues.put(COLm_4, blood_pressure);
        contentValues.put(COLm_5, pulse_rate);
        contentValues.put(COLm_6, diabetes);
        contentValues.put(COLm_7, oxymeter);
        long result= db.insert(TABLE_NAM, null, contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE_NAM, null);
        return res;

    }
    public boolean updateData(String date, String weight, String height, String blood_pressure,String pulse_rate, String diabetes, String oxymeter){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLm_1, date);
        contentValues.put(COLm_2, weight);
        contentValues.put(COLm_3, height);
        contentValues.put(COLm_4, blood_pressure);
        contentValues.put(COLm_5, pulse_rate);
        contentValues.put(COLm_6, diabetes);
        contentValues.put(COLm_7, oxymeter);
        db.update(TABLE_NAM, contentValues, "Date=?", new String[] {date} );
        return true;

    }
    public Integer deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAM, "Date=?", new String[] {id});
    }
}