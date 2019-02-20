package com.example.jla.inventoryappthesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinner = findViewById(R.id.spinner);
        btnLogin = findViewById(R.id.btnLogin);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.usertype, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String item = spinner.getSelectedItem().toString();
                if(item.equals("Admin")){
                    Intent intent = new Intent(MainActivity.this, AdminDashboard.class);
                        startActivity(intent);
                }else if(item.equals("Employee")){
                    Intent intent = new Intent(MainActivity.this, EmployeeDashboard.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
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
}
