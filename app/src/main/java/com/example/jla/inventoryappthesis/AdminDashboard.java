package com.example.jla.inventoryappthesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AdminDashboard extends AppCompatActivity {

    String UsernameHolder;
    TextView TextViewUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        TextViewUsername = findViewById(R.id.textViewUsername);

        Intent intent = getIntent();

        UsernameHolder = intent.getStringExtra(MainActivity.UserName);

        TextViewUsername.setText(TextViewUsername.getText().toString()+UsernameHolder);


    }
}
