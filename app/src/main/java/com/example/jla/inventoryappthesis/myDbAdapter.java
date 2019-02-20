package com.example.jla.inventoryappthesis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class myDbAdapter {

    myDbHelper myhelper;


    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    // methods for CRUD database

    public long insertData(String business_name, String name, String pass){

        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.BUSINESS_NAME, business_name);
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.PASSWORD, pass);

        long id = dbb.insert(myDbHelper.TABLE_NAME,null, contentValues);
        return id;

    }

        static class myDbHelper extends SQLiteOpenHelper{

            public static final String DATABASE_NAME="myDatabase";   // Database Name
            public static final String TABLE_NAME = "myTable";    // Table Name
            public static final int DATABASE_Version = 1; // Database Version
            public static final String UID = "id";  // Column I (Primary Key)
            public static final String BUSINESS_NAME = "Business_Name";  //Column II
            public static final String NAME = "Name";  //Column III
            public static final String PASSWORD = "Password"; //Column IV


     private static  final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME +"(" +UID +"INTEGER PRIMARY KEY AUTOINCREMENT, "+BUSINESS_NAME+" VARCHAR(255),"+NAME+" VARCHAR(255),"+PASSWORD+" VARCHAR(255)"+ ");";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " +TABLE_NAME;

            private final Context context;

            public myDbHelper(Context context){
                super(context, DATABASE_NAME, null, DATABASE_Version);
                this.context = context;
            }

            @Override
            public void onCreate(SQLiteDatabase db) {

                try{
                    db.execSQL(CREATE_TABLE);
                }catch (Exception e){
                        Message.message(context, ""+e);
                }

            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                try{
                    Message.message(context, "OnUpgrade");
                    db.execSQL(DROP_TABLE);
                }catch (Exception e){
                    Message.message(context, ""+e);
                }

            }
        }



}