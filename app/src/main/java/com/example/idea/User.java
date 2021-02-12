package com.example.idea;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class User {
    private MyDB dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public User(Context c){
        context = c;
        MyDB db = new MyDB(c);
        database = db.getWritableDatabase();
    }


    public SQLiteDatabase getDatabase(){
        return this.database;
    }
    public void close(){
        dbHelper.close();
    }

    public void insert(String full_name, String email, String password){
        ContentValues contentValue = new ContentValues();
        contentValue.put(MyDB.FULL_NAME, full_name);
        contentValue.put(MyDB.EMAIL, email);
        contentValue.put(MyDB.PASSWORD, password);
        contentValue.put(MyDB.SCHOOL_INFO_ID, 1);
        database.insert(MyDB.TABLE_NAME, null, contentValue);
    }

    //Cursor?
    public Cursor fetch() {
        String[] columns = new String[] { MyDB._ID, MyDB.EMAIL, MyDB.PASSWORD };
        Cursor cursor = database.query(MyDB.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String full_name, String email, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDB.FULL_NAME, full_name);
        contentValues.put(MyDB.EMAIL, email);
        contentValues.put(MyDB.PASSWORD, password);
        int i = database.update(MyDB.TABLE_NAME, contentValues, MyDB._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id){
        database.delete(MyDB.TABLE_NAME, MyDB._ID + "=" + _id, null);
    }

}
