package com.example.jla.inventoryappthesis;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

public class AdminDashboard extends AppCompatActivity {

    String UsernameHolder;
    TextView TextViewUsername;
    ImageButton more;
    Button btn_inboundLogistics, btn_outboundLogistics, btn_operations, btn_inventoryStocks, btn_statisticalReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        //full screen,

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextViewUsername = findViewById(R.id.textViewUsername);

        Intent intent = getIntent();

        UsernameHolder = intent.getStringExtra(MainActivity.UserName);

        TextViewUsername.setText(TextViewUsername.getText().toString()+UsernameHolder);

        btn_inboundLogistics = findViewById(R.id.btn_inboundLogistics);
        btn_outboundLogistics = findViewById(R.id.btn_outboundLogistics);
        btn_operations = findViewById(R.id.btn_operations);
        btn_inventoryStocks = findViewById(R.id.btn_inventory_stock);
        btn_statisticalReports = findViewById(R.id.btn_statistical_reports);

        more = findViewById(R.id.more);

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


        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popupMenu = new PopupMenu(AdminDashboard.this, more);
                popupMenu.getMenuInflater().inflate(R.menu.pop_menu, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        Intent intent, chooser;
                        int id = menuItem.getItemId();
                        if (id == R.id.feedback){
                            intent = new Intent(Intent.ACTION_SEND);
                            intent.setData(Uri.parse("mailto:"));
                            String[] to = {"barentoy55@gmail.com"}; // my email
                            intent.putExtra(Intent.EXTRA_EMAIL, to);
                            intent.setType("message/rfc822");
                            chooser = Intent.createChooser(intent, "Send Feedback");
                            startActivity(chooser);

                        }

                        if (id == R.id.signOut){
                            intent = new Intent(AdminDashboard.this, MainActivity.class);
                            startActivity(intent);

                        }

                        return true;
                    }
                });
                popupMenu.show();
            }

        });


    }


    public void onClickInboundLogistics(View view) {

    }

    public void onClickOutboundLogistics(View view) {
    }

    public void onClickOperations(View view) {
    }

    public void onClickInventoryStocks(View view) {
    }

    public void onClickStatisticalReport(View view) {
    }
}
