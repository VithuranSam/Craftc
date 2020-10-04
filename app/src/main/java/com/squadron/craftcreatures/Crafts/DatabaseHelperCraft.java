package com.squadron.craftcreatures.Crafts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelperCraft extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "CCfine.db";
    public static final String TABLE_NAME = "CC_Craft";
    public static final String COL_1 = "CID";
    public static final String COL_2 = "CraftName";
    public static final String COL_3 = "CraftActualPrice";
    public static final String COL_4 = "CraftSellingPrice";
    public static final String COL_5 = "Profit";
    public static final String COL_6 = "CraftStock";
    public static final String COL_7 = "CraftCategory";
    public static final String COL_8 = "CraftDes";


    public DatabaseHelperCraft(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (CID Integer PRIMARY KEY AUTOINCREMENT,CraftName text,CraftActualPrice text,CraftSellingPrice text,Profit text,CraftStock text,CraftCategory text,CraftDes text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String craftname,String craftactualprice,String craftsellingprice,String profit,String craftstock,String craftcate,String craftdes){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,craftname);
        contentValues.put(COL_3,craftactualprice);
        contentValues.put(COL_4,craftsellingprice);
        contentValues.put(COL_5,profit);
        contentValues.put(COL_6,craftstock);
        contentValues.put(COL_7,craftcate);
        contentValues.put(COL_8,craftdes);
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
    public boolean updateData( String crid,String craftname,String craftactualprice,String craftsellingprice,String profit,String craftstock,String craftcate,String craftdes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,crid);
        contentValues.put(COL_2,craftname);
        contentValues.put(COL_3,craftactualprice);
        contentValues.put(COL_4,craftsellingprice);
        contentValues.put(COL_5,profit);
        contentValues.put(COL_6,craftstock);
        contentValues.put(COL_7,craftcate);
        contentValues.put(COL_8,craftdes);
        db.update(TABLE_NAME,contentValues,"CID = ?",new String[]{ crid });
        return  true;
    }
    public Integer deleteData(String crid){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,"CID = ?",new String[]{ crid });
    }
    public Cursor searchData(String crid) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor data = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_1 + "= '" + crid + "'",null);
        return data;
    };

}