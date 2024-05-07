package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String q1="create table users(Username text,Email email,Password password)";
        db.execSQL(q1);

        String q2 = "create table cart(username text,Product text,Price float,otype text)";
        db.execSQL(q2);

        String q3 = "create table orderplace(username text,fullname text,address text,contactno text,pincode int,date text,time text,amount float,otype text)";
        db.execSQL(q3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public void register(String username,String email,String password){
        ContentValues cv =new ContentValues();
        cv.put("Username" ,username);
        cv.put("Email" ,email);
        cv.put("Password" ,password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }
    public int login(String username,String password){
        int result=0;
        String[] str=new String[2];
        str[0]=username;
        str[1]=password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c =db.rawQuery ("select * from users where username=? and password =?",str);
        if(c.moveToFirst()){
            result = 1;
        }
        return result;
    }
    public void addcart(String username , String product, float price,String otype){
        ContentValues cv =new ContentValues();
        cv.put("Username" ,username);
        cv.put("Product" ,product);
        cv.put("Price" ,price);
        cv.put("otype" ,otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }
    public int checkcart(String username,String product) {
        int result = 0;
        String[] str = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username=? and product =?", str);
        if (c.moveToFirst()) {
            result = 1;

        }
        db.close();
        return result;
    }
    public void removecart(String username, String otype) {
        int result = 0;
        String[] str = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = getReadableDatabase();
        db.delete("cart","username=? and otype=?", str);
        db.close();
    }
    public ArrayList getCartData(String username,String otype){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String[] str = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery("select * from cart where username=? and otype=?",str);
        if(c.moveToFirst()){
            do{
                String product = c.getString(1);
                String price = c.getString(2);
                arr.add(product +"$"+price);
            }while (c.moveToNext());
        }
        db.close();
        return arr;

    }

    public void addorder(String username,String fullname,String address,String contact,int pincode,String date,String time,float price,String otype){
        ContentValues cv =new ContentValues();
        cv.put("username" ,username);
        cv.put("fullName" ,fullname);
        cv.put("address" ,address);
        cv.put("contactno" ,contact);
        cv.put("pincode" ,pincode);
        cv.put("date" ,date);
        cv.put("time" ,time);
        cv.put("amount" ,price);
        cv.put("otype" ,otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orderplace",null,cv);
        db.close();
    }

    public ArrayList getorderdata(String username){
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db =getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("select * from orderplace where username=?",str);
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1)+"$"+c.getString(2)+"$"+c.getString(3)+"$"+c.getString(4)+"$"+c.getString(5)+"$"+c.getString(6)+"$"+c.getString(7)+"$"+c.getString(8));
            }while (c.moveToNext());
        }
        db.close();
        return arr;

    }
}