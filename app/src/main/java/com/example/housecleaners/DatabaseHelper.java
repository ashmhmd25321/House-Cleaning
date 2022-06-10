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
    private static final String TABLE1 = "User";
    private static final String TABLE2 = "HouseInfo";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create tables
        String table1 = "CREATE TABLE "+TABLE1+"(userName TEXT primary key, password TEXT, securityQuestion TEXT, answer TEXT, userType TEXT)";
        String table2 = "CREATE TABLE "+TABLE2+"(houseId TEXT primary key, userName TEXT, noOfRooms TEXT, noOfBathRooms TEXT, floorType TEXT, address TEXT, image blob)";
        //db.execSQL("create table User (userName TEXT primary key, password TEXT, securityQuestion TEXT, answer TEXT, userType TEXT)");
        //db.execSQL("create table HouseInfo (houseId TEXT primary key, userName TEXT, noOfRooms TEXT, noOfBathRooms TEXT, floorType TEXT, address TEXT, image blob)");
        db.execSQL(table1);
        db.execSQL(table2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop tables if existed
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE2);

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

        long result = sqLiteDatabase.insert(TABLE1, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int deleteHouse(String userName) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE2,"userName = ?", new String[] {userName});
    }

    public boolean insertHouseInfo(String houseId, String userName, String noOfRooms, String noOfBathRooms, String floorType, String address, byte[] image) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("houseId", houseId);
        contentValues.put("userName", userName);
        contentValues.put("noOfRooms", noOfRooms);
        contentValues.put("noOfBathRooms", noOfBathRooms);
        contentValues.put("floorType", floorType);
        contentValues.put("address", address);
        contentValues.put("image", image);

        long result = sqLiteDatabase.insert(TABLE2, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkHouse (String userName) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from HouseInfo where userName = ?", new String [] {userName});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor viewHouse (String userName) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from HouseInfo where userName=?", new String[] {userName});
        return cursor;
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
