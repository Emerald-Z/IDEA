package com.example.idea;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DBManager(Context c){
        context = c;
    }

    public DBManager open() throws SQLException{
        dbHelper = new DatabaseHelper(context);
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
        contentValue.put(DatabaseHelper.FULL_NAME, full_name);
        contentValue.put(DatabaseHelper.EMAIL, email);
        contentValue.put(DatabaseHelper.PASSWORD, password);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    //Cursor?
    public Cursor fetch() {
        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.EMAIL, DatabaseHelper.PASSWORD };
        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String full_name, String email, String password){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.FULL_NAME, full_name);
        contentValues.put(DatabaseHelper.EMAIL, email);
        contentValues.put(DatabaseHelper.PASSWORD, password);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValues, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id){
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}
