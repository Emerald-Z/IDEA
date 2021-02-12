package com.example.idea;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDB extends SQLiteOpenHelper {

    //Table Name
    public static final String TABLE_NAME = "USERS";
    //Table Columns
    public static final String _ID = "_id";
    public static final String FULL_NAME = "full_name";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String SCHOOL_INFO_ID = "school_info_id";

    public static final String TABLE_NAME_SCHOOL = "SCHOOLINFO";
    //Table Columns
    public static final String _ID_SCHOOL = "_id";
    public static final String SCHOOL_NAME = "school_name";
    public static final String DAYOFTHEWEEK = "dayoftheweek";
    public static final String MYTIME = "mytime";
    public static final String THEIRTIME = "theirtime";
    public static final String CLASSLEVEL = "classlevel";
    public static final String CLASSTYPE = "classtype";

    //Database Information
    static final String DB_NAME = "IDEA_USERS.DB";
    static final int DB_VERSION = 1;

    //Creating table query
    private static final String CREATE_USER_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FULL_NAME + " TEXT, " + EMAIL + " TEXT NOT NULL, " + PASSWORD + " TEXT, "
            + SCHOOL_INFO_ID + " TEXT);";

    private static final String CREATE_SCHOOLINFO_TABLE = "create table " + TABLE_NAME_SCHOOL + "(" + _ID_SCHOOL
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SCHOOL_NAME + " TEXT, " + DAYOFTHEWEEK + " TEXT NOT NULL, " + MYTIME + " TEXT, " +
            THEIRTIME + " TEXT, " + CLASSLEVEL + " TEXT, " + CLASSTYPE + " TEXT);";

    public MyDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_SCHOOLINFO_TABLE);
        db.execSQL("Insert into SCHOOLINFO values(1, \"PoJiao Elementary\", \"Monday\", \"7:00 PM CST\", \"9:00 AM Beijing Standard Time\", \"3rd Grade\", \"English\");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SCHOOL);
        onCreate(db);
    }
}
