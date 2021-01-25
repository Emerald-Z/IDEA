package com.example.idea;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private User dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c){
        context = c;
    }

    public DBManager open() throws SQLException{
        dbHelper = new User(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public SQLiteDatabase getDatabase(){
        return this.database;
    }
    public void close(){
        dbHelper.close();
    }

    public void insert(String full_name, String email, String password){
        ContentValues contentValue = new ContentValues();
        contentValue.put(User.FULL_NAME, full_name);
        contentValue.put(User.EMAIL, email);
        contentValue.put(User.PASSWORD, password);
        database.insert(User.TABLE_NAME, null, contentValue);
    }

    //Cursor?
    public Cursor fetch() {
        String[] columns = new String[] { User._ID, User.EMAIL, User.PASSWORD };
        Cursor cursor = database.query(User.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String full_name, String email, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(User.FULL_NAME, full_name);
        contentValues.put(User.EMAIL, email);
        contentValues.put(User.PASSWORD, password);
        int i = database.update(User.TABLE_NAME, contentValues, User._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id){
        database.delete(User.TABLE_NAME, User._ID + "=" + _id, null);
    }

}
