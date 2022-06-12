package com.example.housecleaners;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    //database name
    public static final String DATABASE_NAME = "houseCleaner.db";
    private static final String TABLE1 = "User";
    private static final String TABLE2 = "HouseInfo";
    private static final String TABLE3 = "Post";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create tables
        String table1 = "CREATE TABLE "+TABLE1+"(userName TEXT primary key, password TEXT, securityQuestion TEXT, answer TEXT, userType TEXT)";
        String table2 = "CREATE TABLE "+TABLE2+"(houseId TEXT primary key, userName TEXT, noOfRooms TEXT, noOfBathRooms TEXT, floorType TEXT, address TEXT, image blob)";
        String table3 = "CREATE TABLE "+TABLE3+"(userName TEXT primary key, houseId TEXT, noOfRooms TEXT, noOfBathRooms TEXT, floorType TEXT, address TEXT, image blob, price TEXT, date TEXT)";
        //db.execSQL("create table User (userName TEXT primary key, password TEXT, securityQuestion TEXT, answer TEXT, userType TEXT)");
        //db.execSQL("create table HouseInfo (houseId TEXT primary key, userName TEXT, noOfRooms TEXT, noOfBathRooms TEXT, floorType TEXT, address TEXT, image blob)");
        db.execSQL(table1);
        db.execSQL(table2);
        db.execSQL(table3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop tables if existed
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE3);

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

    public ArrayList<Post> getPost() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Post> arrayList = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE3, null);
        while (cursor.moveToNext()){
            String user = cursor.getString(0);
            String hId = cursor.getString(1);
            String rooms = cursor.getString(2);
            String bathrooms = cursor.getString(3);
            String floor = cursor.getString(4);
            String adrs = cursor.getString(5);
            byte[] img = cursor.getBlob(6);
            String prc = cursor.getString(7);
            String dt = cursor.getString(8);

            Post post = new Post(user, hId, rooms, bathrooms, floor, adrs, img, prc, dt);
            arrayList.add(post);
        }

        return arrayList;

    }

    public int deleteHouse(String userName) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE2,"userName = ?", new String[] {userName});
    }

    public int deletePost(String userName) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE3,"userName = ?", new String[] {userName});
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

    public boolean insertPost(String userName, String houseId, String noOfRooms, String noOfBathRooms, String floorType, String address, byte[] image, String price, String date) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("userName", userName);
        contentValues.put("houseId", houseId);
        contentValues.put("noOfRooms", noOfRooms);
        contentValues.put("noOfBathRooms", noOfBathRooms);
        contentValues.put("floorType", floorType);
        contentValues.put("address", address);
        contentValues.put("image", image);
        contentValues.put("price", price);
        contentValues.put("date", date);

        long result = sqLiteDatabase.insert(TABLE3, null, contentValues);

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

    public boolean checkPost (String userName) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Post where userName = ?", new String [] {userName});

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
