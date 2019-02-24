package com.example.jla.inventoryappthesis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminDashboard extends AppCompatActivity {

    String UsernameHolder;
    TextView TextViewUsername;
    Button btn_inboundLogistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        TextViewUsername = findViewById(R.id.textViewUsername);

        Intent intent = getIntent();

        UsernameHolder = intent.getStringExtra(MainActivity.UserName);

        TextViewUsername.setText(TextViewUsername.getText().toString()+UsernameHolder);

        btn_inboundLogistics = findViewById(R.id.btn_inboundLogistics);

        btn_inboundLogistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, InboundLogisticsActivity.class);
                startActivity(intent);
            }
        });


    }


}
