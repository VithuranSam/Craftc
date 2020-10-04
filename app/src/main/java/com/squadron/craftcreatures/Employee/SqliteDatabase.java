package com.squadron.craftcreatures.Employee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SqliteDatabase extends SQLiteOpenHelper {

    private	static final int DATABASE_VERSION =	5;
    private	static final String	DATABASE_NAME = "employeeDB";
    private	static final String TABLE_EMPLOYEE = "employee";

    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "empname";
    private static final String COLUMN_JOBTITLE = "jobtitle";
    private static final String COLUMN_SALARY = "salary";
    private static final String COLUMN_NO = "phno";
    private static final String COLUMN_EMAIL = "email";

    public SqliteDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String	CREATE_EMPLOYEE_TABLE = "CREATE	TABLE " + TABLE_EMPLOYEE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME + " TEXT," + COLUMN_JOBTITLE + " TEXT," + COLUMN_SALARY + " INTEGER," + COLUMN_NO + " INTEGER" + "," + COLUMN_EMAIL + " TEXT" + ")";
        db.execSQL(CREATE_EMPLOYEE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        onCreate(db);
    }

    public ArrayList<emp> listemp(){
        String sql = "select * from " + TABLE_EMPLOYEE;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<emp> storeEmp = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String jobtitle = cursor.getString(2);
                String salary = cursor.getString(3);
                String phno = cursor.getString(4);
                String email = cursor.getString(5);
                storeEmp.add(new emp(id, name, jobtitle, salary, phno, email));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeEmp;
    }
    public void addemp(emp emp){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, emp.getName());
        values.put(COLUMN_JOBTITLE, emp.getJobtitle());
        values.put(COLUMN_SALARY, emp.getSalary());
        values.put(COLUMN_NO, emp.getPhno());
        values.put(COLUMN_EMAIL, emp.getEmail());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_EMPLOYEE, null, values);
    }
    public void updateemp(emp emp){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, emp.getName());
        values.put(COLUMN_JOBTITLE, emp.getJobtitle());
        values.put(COLUMN_SALARY, emp.getSalary());
        values.put(COLUMN_NO, emp.getPhno());
        values.put(COLUMN_EMAIL, emp.getEmail());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_EMPLOYEE, values, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(emp.getId())});
    }
    public void deleteemp(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EMPLOYEE, COLUMN_ID	+ "	= ?", new String[] { String.valueOf(id)});
    }
}
