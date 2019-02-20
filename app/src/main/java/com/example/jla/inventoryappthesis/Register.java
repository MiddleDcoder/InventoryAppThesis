package com.example.jla.inventoryappthesis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class Register extends AppCompatActivity {

    Spinner spinner2;
    EditText editBusinessName, editUsername, editPassword;
    myDbAdapter helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        helper = new myDbAdapter(this);

        spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.usertype, R.layout.support_simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);


        editBusinessName = findViewById(R.id.editBusinessName);
        editUsername = findViewById(R.id.editUsername);
        editPassword = findViewById(R.id.editPassword);

    }

    public void addUser(View view){
            String t1 = editBusinessName.getText().toString();
            String t2 = editUsername.getText().toString();
            String t3 = editPassword.getText().toString();
            String t4 = spinner2.getSelectedItem().toString();

            if(t1.isEmpty() || t2.isEmpty() || t3.isEmpty() || t4.isEmpty()){
                    Message.message(getApplicationContext(), "Please fill in the blanks");
            }else{
                long id = helper.insertData(t1, t2, t3, t4);
                if(id<=0){
                    Message.message(getApplicationContext(), "Insertion Unsuccessful");
                    editBusinessName.setText("");
                    editUsername.setText("");
                    editPassword.setText("");
                    spinner2.setAdapter(null);
                }else{
                    Message.message(getApplicationContext(), "Insertion Successful");
                    editBusinessName.setText("");
                    editUsername.setText("");
                    editPassword.setText("");
                    spinner2.setAdapter(null);
                }
            }
    }
}