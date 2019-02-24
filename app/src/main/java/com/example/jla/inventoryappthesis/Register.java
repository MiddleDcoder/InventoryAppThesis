package com.example.jla.inventoryappthesis;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//import android.widget.Toast;


public class Register extends AppCompatActivity {


    EditText editBusinessName, editUsername, editPassword;
    String NameHolder, PasswordHolder, BusinessNameHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder;
    Cursor cursor;
    String F_Result = "Not_Found";
    myDbAdapter sqLiteHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        sqLiteHelper = new myDbAdapter(this);


        editBusinessName = findViewById(R.id.editBusinessName);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);

    }


    public void registerData(View view) {


        // Creating SQLite database if dose n't exists
        SQLiteDataBaseBuild();

        // Creating SQLite table if dose n't exists.
        SQLiteTableBuild();

        // Checking EditText is empty or Not.
        CheckEditTextStatus();

        // Method to check Email is already exists or not.
        CheckingEmailAlreadyExistsOrNot();

        // Empty EditText After done inserting process.
        EmptyEditTextAfterDataInsert();


    }


    // SQLite database build method.
    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase(myDbAdapter.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    // SQLite table build method.
    public  void SQLiteTableBuild(){

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS "+ myDbAdapter.TABLE_NAME + "(" + myDbAdapter.Table_Column_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL, " + myDbAdapter.Table_Column_1_Name + " VARCHAR, " + myDbAdapter.Table_Column_2_Password + " VARCHAR, " + myDbAdapter.Table_Column_3_BusinessName + " VARCHAR);" );

    }

    // Insert data into SQLite database method.
    public void InsertDataIntoSQLiteDatabase(){

        // If editText is not empty then this block will executed.
        if(EditTextEmptyHolder == true){

            // SQLite query to insert data into table.
            SQLiteDataBaseQueryHolder = "INSERT INTO "+myDbAdapter.TABLE_NAME+" (name,password,business) VALUES('"+NameHolder+"', '"+PasswordHolder+"', '"+BusinessNameHolder+"');";

            // Executing query.
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            // Closing SQLite database object.
            sqLiteDatabaseObj.close();

            // Printing toast message after done inserting.

            Message.message(getApplicationContext(), "User Registered Successfully");
            //Toast.makeText(Register.this, "User Registered Successfully", Toast.LENGTH_LONG).show();

        }
        // This block will execute if any of the registration EditText is empty.
        else{

            // Printing toast message if any of EditText is empty.
            Message.message(getApplicationContext(), "Please Fill ALL The Required Fields.");
            //Toast.makeText(Register.this, "Please Fill ALL The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }


    // Empty EditText after done inserting process method.
    public void EmptyEditTextAfterDataInsert(){
        editUsername.getText().clear();

        editPassword.getText().clear();

        editBusinessName.getText().clear();


    }

    // Method to check EditText is empty or Not.
    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        NameHolder = editUsername.getText().toString();
        PasswordHolder = editPassword.getText().toString();
        BusinessNameHolder = editBusinessName.getText().toString();

        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(PasswordHolder) || TextUtils.isEmpty(BusinessNameHolder)){

            EditTextEmptyHolder = false;

        }
        else{

            EditTextEmptyHolder = true;

        }

    }

    // Checking Email is already exists or not.
    public  void CheckingEmailAlreadyExistsOrNot(){

        // Opening SQLite database write permission.
        cursor = sqLiteDatabaseObj.query(myDbAdapter.TABLE_NAME, null, " " + myDbAdapter.Table_Column_1_Name + "=?", new String[]{NameHolder}, null, null, null);

        while (cursor.moveToNext()){

            if(cursor.isFirst()){

                cursor.moveToFirst();

                // If Email is already exists then Result variable value set as Email Found.
                F_Result = "Name Found";

                //Closing cursor.
                cursor.close();

            }

        }

        // Calling method to check final result and insert data into SQLite database.
        CheckFinalResult();

    }

    // Checking result
    public void CheckFinalResult(){

        // Checking whether email is already exists or not.
        if(F_Result.equalsIgnoreCase("Email Found")){


            // If email is exists then toast msg will display.
            Toast.makeText(Register.this, "Email Already Exists", Toast.LENGTH_LONG).show();
        }
        else{
            // If email already dose n't exists then user registration details will entered to SQLite database.
            InsertDataIntoSQLiteDatabase();

        }

        F_Result = "Not_Found";
    }




}
