package com.example.noteapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class KaryawanList extends AppCompatActivity {
    private RecyclerView rvList_;
    private TextView tvEmpty_;
    private FloatingActionButton fabAdd_;

    private Database db;
    private KaryawanAdapter adapterK;
    private List<KaryawanModel> karyawan = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karyawan_list);

        db = new Database(this);

        rvList_ = findViewById(R.id.rv_listK);
        tvEmpty_ = findViewById(R.id.tv_emptyK);
        fabAdd_ = findViewById(R.id.fab_addK);

        adapterK = new KaryawanAdapter(this);
        rvList_.setLayoutManager(new LinearLayoutManager(this));
        rvList_.setAdapter(adapterK);

        fabAdd_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KaryawanList.this, addKaryawan.class));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        getKaryawan();
    }

    private void getKaryawan() {
        karyawan = db.getKaryawan();
        adapterK.setKaryawan(karyawan);

        if (karyawan.size() != 0) {
            tvEmpty_.setVisibility(View.GONE);
            rvList_.setVisibility(View.VISIBLE);
        } else {
            tvEmpty_.setVisibility(View.VISIBLE);
            rvList_.setVisibility(View.GONE);
        }
    }
}
