package com.example.jla.inventoryappthesis;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    Button btnLogin, btnRegister;
    EditText editTextUsername, editTextPassword; //, editTextBusinessName;

    String UsernameHolder, PasswordHolder ; //, BusinessNameHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    SQLiteOpenHelper sqLiteHelper;
    Cursor cursor;
    String TempPassword = "NOT_FOUND";
    public static final  String UserName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        sqLiteHelper = new myDbAdapter (this);

        btnLogin = findViewById(R.id.btnLogin);
        editTextUsername = findViewById(R.id.editText);
        editTextPassword = findViewById(R.id.editText2);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Calling EdiText is empty or no method.

                CheckEditTextStatus();
                //Calling Login method.
                LoginFunction();

            }
        });







        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });

    }

    // login function start from here.
    public void LoginFunction(){

        if(EditTextEmptyHolder){
            //Opening SQLite database write permission.
            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

            //Adding search username query to cursor.
            cursor = sqLiteDatabaseObj.query(myDbAdapter.TABLE_NAME, null, " "+myDbAdapter.Table_Column_1_Name + "=?", new String[]{UsernameHolder}, null, null, null );

                while (cursor.moveToNext()){
                    if(cursor.isFirst()){
                        cursor.moveToFirst();
                        //Storing Password associated with entered email.
                    TempPassword = cursor.getString(cursor.getColumnIndex(myDbAdapter.Table_Column_2_Password));

                    //closing cursor
                        cursor.close();
                    }
                }

            //Calling method to check final result . .
            CheckFinalResult();
        }else{

            //If any of login EditText empty then this block will be executed.
            pleaseFillUp();

        }
    }


    // checking no entered data in EditText

    public void pleaseFillUp(){

        UsernameHolder = editTextUsername.getText().toString();
        PasswordHolder = editTextPassword.getText().toString();
          if(TextUtils.isEmpty(UsernameHolder) || TextUtils.isEmpty(PasswordHolder) ){
              Toast.makeText(MainActivity.this, "Please fill up the form", Toast.LENGTH_LONG).show();
          }
    }

    //Checking EditText is empty or not.
    public  void CheckEditTextStatus(){
        //Getting value from All EditText and storing into String Variables.
        UsernameHolder = editTextUsername.getText().toString();
        PasswordHolder = editTextPassword.getText().toString();

        //Checking EditText is empty or no using TextUtils.
        if(TextUtils.isEmpty(UsernameHolder) || TextUtils.isEmpty(PasswordHolder)){
                EditTextEmptyHolder = false;
        }else{
                EditTextEmptyHolder = true;
        }

    }

    public void CheckFinalResult(){
        // Checking entered password from SQLite database email associated password
        if(TempPassword.equalsIgnoreCase(PasswordHolder)){

            Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();

            // Going to Dashboard activity after login success message.
            Intent intent = new Intent(MainActivity.this, AdminDashboard.class);

            // Sending Email to Dashboard Activity using intent.
            intent.putExtra(UserName, UsernameHolder);

            startActivity(intent);

        }else{
            Toast.makeText(MainActivity.this, "UserName or Password is Wrong, Please Try Again.", Toast.LENGTH_LONG).show();
        }

        TempPassword = "NOT FOUND";


    }



}





