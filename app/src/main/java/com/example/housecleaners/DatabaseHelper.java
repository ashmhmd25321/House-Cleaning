package com.example.housecleaners;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //database name
    public static final String DATABASE_NAME = "houseCleaner.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create tables
        db.execSQL("create table User (userName TEXT primary key, password TEXT, securityQuestion TEXT, answer TEXT, userType TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop tables if existed
        db.execSQL("DROP TABLE IF EXISTS User");

        // creating tables again
        onCreate(db);
    }

    public boolean insertUser(String userName, String password, String securityQuestion, String answer, String userType) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userName", userName);
        contentValues.put("password", password);
        contentValues.put("securityQuestion", securityQuestion);
        contentValues.put("answer", answer);
        contentValues.put("userType", userType);

        long result = sqLiteDatabase.insert("User", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkUsername (String userName) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from User where userName = ?", new String [] {userName});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUsernamePassword (String userName, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from User where userName= ? and password= ?", new String[] {userName, password});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor viewPassword (String userName, String answer, String securityQuestion) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from User where userName=? and answer=? and securityQuestion=?", new String[] {userName, answer, securityQuestion});
        return cursor;
    }

    public Cursor viewUserType (String userName) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from User where userName=?", new String[] {userName});
        return cursor;
    }
}
