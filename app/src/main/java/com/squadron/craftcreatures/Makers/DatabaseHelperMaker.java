package com.squadron.craftcreatures.Makers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelperMaker extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "craftcreatures_1.db";
    public static final String TABLE_NAME = "Makers";
    public static final String COL_1 = "MakerID";
    public static final String COL_2 = "MakersName";
    public static final String COL_3 = "MakerEmail";
    public static final String COL_4 = "MakerPhone";
    public static final String COL_5 = "CraftQuantity";
    public static final String COL_6 = "UnitPrice";
    public static final String COL_7 = "BuyingPrice";

    public DatabaseHelperMaker(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE TABLE_NAME (MakerID Integer PRIMARY KEY AUTOINCREMENT,MakersName text,MakerEmail text,MakerPhone text,CraftQuantity text,UnitPrice text,BuyingPrice text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String makersname, String makeremail, String makerphone, String craftquantity, String unitprice, String buyingprice){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,makersname);
        contentValues.put(COL_3,makeremail);
        contentValues.put(COL_4,makerphone);
        contentValues.put(COL_5,craftquantity);
        contentValues.put(COL_6,unitprice);
        contentValues.put(COL_7,buyingprice);
        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
}
