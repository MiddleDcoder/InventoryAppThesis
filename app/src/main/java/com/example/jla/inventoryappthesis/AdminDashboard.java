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
    Button btn_inboundLogistics, btn_outboundLogistics, btn_operations, btn_inventoryStocks, btn_statisticalReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        TextViewUsername = findViewById(R.id.textViewUsername);

        Intent intent = getIntent();

        UsernameHolder = intent.getStringExtra(MainActivity.UserName);

        TextViewUsername.setText(TextViewUsername.getText().toString()+UsernameHolder);

        btn_inboundLogistics = findViewById(R.id.btn_inboundLogistics);
        btn_outboundLogistics = findViewById(R.id.btn_outboundLogistics);
        btn_operations = findViewById(R.id.btn_operations);
        btn_inventoryStocks = findViewById(R.id.btn_inventory_stock);
        btn_statisticalReports = findViewById(R.id.btn_statistical_reports);

        btn_inboundLogistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, InboundLogisticsActivity.class);
                startActivity(intent);
            }
        });

        btn_outboundLogistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, OutboundLogistics.class);
                startActivity(intent);
            }
        });

        btn_operations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, Operations.class);
                startActivity(intent);
            }
        });

        btn_inventoryStocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, InventoryStocks.class);
                startActivity(intent);
            }
        });

        btn_statisticalReports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboard.this, StatisticalReports.class);
                startActivity(intent);
            }
        });


    }


}
