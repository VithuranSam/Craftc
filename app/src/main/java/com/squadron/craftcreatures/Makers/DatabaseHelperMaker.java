package com.squadron.craftcreatures.Makers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelperMaker extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "craftcreatures_CC.db";
    public static final String TABLE_NAME = "Mak_er_1";
    public static final String COL_1 = "MakerID";
    public static final String COL_2 = "MakersName";
    public static final String COL_3 = "MakerEmail";
    public static final String COL_4 = "MakerPhone";
    public static final String COL_5 = "CraftQuantity";
    public static final String COL_6 = "UnitPrice";
    public static final String COL_7 = "BuyingPrice";

    public DatabaseHelperMaker(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Mak_er_1 (MakerID Integer PRIMARY KEY AUTOINCREMENT, MakersName text, MakerEmail text, MakerPhone text, CraftQuantity text, UnitPrice text, BuyingPrice text);");
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

    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM "+ TABLE_NAME,null);
        return res;
    }

    public boolean updateData( String makerid,String makersname, String makeremail, String makerphone, String craftquantity, String unitprice, String buyingprice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,makerid);
        contentValues.put(COL_2,makersname);
        contentValues.put(COL_3,makeremail);
        contentValues.put(COL_4,makerphone);
        contentValues.put(COL_5,craftquantity);
        contentValues.put(COL_6,unitprice);
        contentValues.put(COL_7,buyingprice);
        db.update(TABLE_NAME,contentValues,"MakerID = ?",new String[]{ makerid });
        return  true;
    }

    public Integer deleteData(String makerid){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,"MakerID = ?",new String[]{ makerid });
    }

}
