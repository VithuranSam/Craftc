package com.squadron.craftcreatures;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "craftcreatures.db";
    public static final String TABLE_NAME = "Delivery";
    public static final String COL_1 = "DID";
    public static final String COL_2 = "DItemName";
    public static final String COL_3 = "DItemAmount";
    public static final String COL_4 = "CName";
    public static final String COL_5 = "CAddress";
    public static final String COL_6 = "CPhone";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Delivery (DID Integer PRIMARY KEY AUTOINCREMENT,DItemName text,DItemAmount text,CName text,CAddress text,CPhone text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String ditemname,String ditemAmount,String cname,String caddress,String cphone){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,ditemname);
        contentValues.put(COL_3,ditemAmount);
        contentValues.put(COL_4,cname);
        contentValues.put(COL_5,caddress);
        contentValues.put(COL_6,cphone);
        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
        return res;
    }

    public boolean updateData( String id,String ditemname,String ditemAmount,String cname,String caddress,String cphone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,ditemname);
        contentValues.put(COL_3,ditemAmount);
        contentValues.put(COL_4,cname);
        contentValues.put(COL_5,caddress);
        contentValues.put(COL_6,cphone);
        db.update(TABLE_NAME,contentValues,"DID = ?",new String[]{ id });
        return  true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,"DID = ?",new String[]{id});
    }
}
