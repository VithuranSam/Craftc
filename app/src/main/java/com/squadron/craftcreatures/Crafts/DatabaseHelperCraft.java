package com.squadron.craftcreatures.Crafts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelperCraft extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "craftcreatures_1.db";
    public static final String TABLE_NAME = "Craft_S";
    public static final String COL_1 = "CID";
    public static final String COL_2 = "CraftName";
    public static final String COL_3 = "CraftPrice";
    public static final String COL_4 = "CraftStock";
    public static final String COL_5 = "CraftCategory";
    public static final String COL_6 = "CraftDes";


    public DatabaseHelperCraft(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (CID Integer PRIMARY KEY AUTOINCREMENT,CraftName text,CraftPrice text,CraftStock text,CraftCategory text,CraftDes text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String craftname,String craftprice,String craftstock,String craftcate,String craftdes){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,craftname);
        contentValues.put(COL_3,craftprice);
        contentValues.put(COL_4,craftstock);
        contentValues.put(COL_5,craftcate);
        contentValues.put(COL_6,craftdes);
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

    public boolean updateData( String crid,String craftname,String craftprice,String craftstock,String craftcate,String craftdes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,crid);
        contentValues.put(COL_2,craftname);
        contentValues.put(COL_3,craftprice);
        contentValues.put(COL_4,craftstock);
        contentValues.put(COL_5,craftcate);
        contentValues.put(COL_6,craftdes);
        db.update(TABLE_NAME,contentValues,"CID = ?",new String[]{ crid });
        return  true;
    }

    public Integer deleteData(String crid){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,"CID = ?",new String[]{ crid });
    }

}