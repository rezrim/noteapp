package com.example.noteapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Dashboard extends AppCompatActivity {
    Button bt1,bt2,bt3,bt4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        bt1 = findViewById(R.id.dashboard_employee);
        bt3 = findViewById(R.id.dashboard_task);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent karyawan = new Intent(Dashboard.this,KaryawanList.class);
                startActivity(karyawan);
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent task = new Intent(Dashboard.this,MainActivity.class);
                startActivity(task);
            }
        });
    }
}
