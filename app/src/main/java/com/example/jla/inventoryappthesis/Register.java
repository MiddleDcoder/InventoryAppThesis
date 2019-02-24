package com.example.jla.inventoryappthesis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Register extends AppCompatActivity {


    EditText editBusinessName, editUsername, editPassword;
    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        helper = new myDbAdapter(this);


        editBusinessName = findViewById(R.id.editBusinessName);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);

    }

    public void addUser(View view){
            //String t1 = editBusinessName.getText().toString();
            String t1 = editUsername.getText().toString();
            String t2 = editPassword.getText().toString();


            if(  t1.isEmpty() || t2.isEmpty() ){
                    Message.message(getApplicationContext(), "Please fill in the blanks");
            }else {
                long id = helper.insertData( t1, t2);
                    if(id<=0){
                    Message.message(getApplicationContext(), "Insertion Unsuccessful");
                    //editBusinessName.setText("");
                    editUsername.setText("");
                    editPassword.setText("");

                }else{
                    Message.message(getApplicationContext(), "Insertion Successful");
                  //editBusinessName.setText("");
                    editUsername.setText("");
                    editPassword.setText("");

                }
            }
    }
}
