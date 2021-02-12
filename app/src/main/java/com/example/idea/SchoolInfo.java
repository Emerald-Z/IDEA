package com.example.idea;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SchoolInfo {
    private MyDB dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public SchoolInfo(Context c){
        context = c;
    }

    public SchoolInfo open() throws SQLException {
        dbHelper = new MyDB(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public SQLiteDatabase getDatabase(){
        return this.database;
    }
    public void close(){
        dbHelper.close();
    }

    public void insert(String school_name, String dayoftheweek, String mytime, String theirtime, String classlevel, String classtype){
        ContentValues contentValue = new ContentValues();
        contentValue.put(MyDB.SCHOOL_NAME, school_name);
        contentValue.put(MyDB.DAYOFTHEWEEK, dayoftheweek);
        contentValue.put(MyDB.MYTIME, mytime);
        contentValue.put(MyDB.THEIRTIME, theirtime);
        contentValue.put(MyDB.CLASSLEVEL, classlevel);
        contentValue.put(MyDB.CLASSTYPE, classtype);
        database.insert(MyDB.TABLE_NAME_SCHOOL, null, contentValue);
    }

    //Cursor?
    public Cursor fetch() {
        String[] columns = new String[] { MyDB._ID, MyDB.SCHOOL_NAME, MyDB.DAYOFTHEWEEK }; //add the rest
        Cursor cursor = database.query(MyDB.TABLE_NAME_SCHOOL, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String school_name, String dayoftheweek, String mytime, String theirtime, String classlevel, String classtype){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDB.SCHOOL_NAME, school_name);
        contentValues.put(MyDB.DAYOFTHEWEEK, dayoftheweek);
        contentValues.put(MyDB.MYTIME, mytime);
        contentValues.put(MyDB.THEIRTIME, theirtime);
        contentValues.put(MyDB.CLASSLEVEL, classlevel);
        contentValues.put(MyDB.CLASSTYPE, classtype);
        int i = database.update(MyDB.TABLE_NAME_SCHOOL, contentValues, MyDB._ID_SCHOOL + " = " + _id, null);
        return i;
    }

    public void delete(long _id){
        database.delete(MyDB.TABLE_NAME_SCHOOL, MyDB._ID_SCHOOL + "=" + _id, null);
    }

}
