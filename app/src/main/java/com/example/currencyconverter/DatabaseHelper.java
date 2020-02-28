package com.example.currencyconverter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table currency(currencyCode text primary key, currencyName text, countryName text, symbol text, blank text, lineNumber text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists currency");
    }

    public boolean insert(String currencyCode, String currencyName, String countryName, String symbol){
        if(!checkCurrency(currencyCode) || currencyCode.length()!=3)
            return false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("currencyCode", currencyCode);
        contentValues.put("currencyName", currencyName);
        contentValues.put("countryName", countryName);
        contentValues.put("symbol", symbol);
        contentValues.put("blank", "");
        int tmp = count()+1;
        contentValues.put("lineNumber", ""+tmp);
        long ins = db.insert("currency", null, contentValues);
        if(ins == -1)
            return false;
        else
            return true;
    }

    public boolean insert(String currencyCode, String currencyName, String countryName, String symbol, String lineNumber){
        if(!checkCurrency(currencyCode))
            return false;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("currencyCode", currencyCode);
        contentValues.put("currencyName", currencyName);
        contentValues.put("countryName", countryName);
        contentValues.put("symbol", symbol);
        contentValues.put("blank", "");
        contentValues.put("lineNumber", lineNumber);
        long ins = db.insert("currency", null, contentValues);
        if(ins == -1)
            return false;
        else
            return true;
    }

    public boolean checkCurrency(String currencyCode){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from currency where currencyCode=?", new String[]{currencyCode});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }

    public String getCurrency(String currencyName, String countryName, String symbol){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from currency where currencyName=? and countryName=? and symbol=?", new String[]{currencyName, countryName, symbol});
        cursor.moveToFirst();
        String currencyCode = cursor.getString(cursor.getColumnIndex("currencyCode"));
        return currencyCode;
    }

    public boolean checkAll(String currencyName, String countryName, String symbol){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from currency where currencyName=? and countryName=? and symbol=?", new String[]{currencyName, countryName, symbol});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }

    /*
    public boolean checkSeperately(String deneme){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from currency where name=?", new String[]{deneme});
        if(cursor.getCount()>0)
            return false;
        else
            return true;
    }
     */

    public String getCurrencyName(String currencyCode){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from currency where currencyCode=?", new String[]{currencyCode});
        cursor.moveToFirst();
        String countryName = cursor.getString(cursor.getColumnIndex("currencyName"));
        return countryName;
    }

    public String getCountryName(String currencyCode){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from currency where currencyCode=?", new String[]{currencyCode});
        cursor.moveToFirst();
        String countryName = cursor.getString(cursor.getColumnIndex("countryName"));
        return countryName;
    }

    public String getSymbol(String currencyCode){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from currency where currencyCode=?", new String[]{currencyCode});
        cursor.moveToFirst();
        String symbol = cursor.getString(cursor.getColumnIndex("symbol"));
        return symbol;
    }

    public String showList(String column){
        SQLiteDatabase db = this.getReadableDatabase();
        String list = "";
        System.out.println("listILKHALI:"+ list);
        int count = count();
        int i = 1;
        Cursor cursor1;
        while(i <= count){
            System.out.println("listBURADA: " + list);
            String line = "" + i;
            cursor1 = db.rawQuery("Select * from currency where lineNumber=?", new String[]{line});
            cursor1.moveToFirst();

            list = list + cursor1.getString(cursor1.getColumnIndex(column)) + "\n";
            i++;

        }
        System.out.println("list:" + list);
        return list;
    }

    public int count(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from currency where blank=?", new String[]{""});
        int count = cursor.getCount();
        System.out.println("COUNT DEGERIIIIII::::::" + count);
        return count;
    }
}