package com.example.yehonatanborochovdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "UserData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
    DB.execSQL("create Table Userdetails(firstname TEXT primary key,lastname TEXT,email TEXT,id TEXT,dob TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
    DB.execSQL("drop Table if exists Userdetails");
    }
    public Boolean insertuserdata(String firstname,String lastname,String email,String id,String dob,String password)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("firstname",firstname);
        contentValues.put("lastname",lastname);
        contentValues.put("email",email);
        contentValues.put("id",id);
        contentValues.put("dob",dob);
        contentValues.put("password",password);
        long result=DB.insert("UserDetails",null,contentValues);
        if(result==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }
    }
    public Boolean updateuserdata(String firstname,String lastname,String email,String id,String dob,String password)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("lastname",lastname);
        contentValues.put("email",email);
        contentValues.put("id",id);
        contentValues.put("dob",dob);
        contentValues.put("password",password);
        Cursor cursor=DB.rawQuery("Select * from Userdetails where firstname = ?",new String[] {firstname});
        if ((cursor.getCount()>0)) {
            long result = DB.update("UserDetails", contentValues, "firstname=?", new String[]{firstname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {return  false;}
    }
    public Boolean deletedata(String firstname)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select * from Userdetails where firstname = ?",new String[] {firstname});
        if ((cursor.getCount()>0)) {
            long result = DB.delete("UserDetails", "firstname=?", new String[]{firstname});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else
        {return  false;}
    }
    public Cursor getdata() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Userdetails", null);
        return cursor;
    }
}
