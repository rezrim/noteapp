package com.example.noteapp2;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addKaryawan extends AppCompatActivity {
    private EditText id,nama,email;
    private Button simpan,hapus;

    private Database db;
    private Intent dataIntent;
    private boolean isEditK = false;
    private int karyawanNO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_karyawan);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tambah Karyawan");

        db = new Database(this);
        dataIntent = getIntent();
        isEditK = dataIntent.getBooleanExtra("isEditK", false);

        id = findViewById(R.id.et_id);
        nama = findViewById(R.id.et_nama);
        email = findViewById(R.id.et_email);
        hapus = findViewById(R.id.btn_delete);
        simpan = findViewById(R.id.btn_save);

        if (isEditK) {
            int no = dataIntent.getIntExtra("karyawanNO", 0);
            if (no != 0) {
                KaryawanModel karyawanModel = db.getKaryawan(no);
                karyawanNO = no;
                id.setText(karyawanModel.getK_Id());
                nama.setText(karyawanModel.getK_Nama());
                email.setText(karyawanModel.getK_Email());
            }
            hapus.setVisibility(View.VISIBLE);
        }
        else {
            hapus.setVisibility(View.GONE);
        }
        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteKaryawan();
            }
        });
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idK = id.getText().toString();
                String namaK = nama.getText().toString();
                String emailK = email.getText().toString();
                if (isInputValid()) {
                    if (isEditK) {
                        updateKaryawan(karyawanNO,idK, namaK, emailK);
                    } else {
                        saveKaryawan(idK, namaK, emailK);
                    }
                }
            }
        });
    }
    private boolean isInputValid() {
        if (TextUtils.isEmpty(id.getText()) || TextUtils.isEmpty(nama.getText()) || TextUtils.isEmpty(email.getText())) {
            if (TextUtils.isEmpty(id.getText())) {
                id.setError("ID Karyawan tidak boleh kosong!");
            }
            if (TextUtils.isEmpty(nama.getText())) {
                nama.setError("Nama Karyawan tidak boleh kosong!");
            }
            if (TextUtils.isEmpty(email.getText())) {
                email.setError("Email Karyawan tidak boleh kosong!");
            }
            return false;
        }
        return true;
    }

    private void saveKaryawan(String id, String nama, String email) {

        KaryawanModel karyawanModel = new KaryawanModel(id, nama, email);
        int success = db.addKaryawan(karyawanModel);
        String message = "Karyawan gagal disimpan";
        if (success != 0) {
            message = "Karyawan berhasil disimpan";
            finish();
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void updateKaryawan(int karyawanNO, String id, String nama, String email) {
        KaryawanModel karyawanModel = new KaryawanModel(karyawanNO, id, nama, email);
        int success = db.updateKaryawan(karyawanModel);

        String message = "Karyawan gagal di update";

        if (success != 0) {
            message = "Karyawan berhasil di update";
            finish();
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void deleteKaryawan() {
        int success = db.deleteKaryawan(karyawanNO);
        String message = "Note gagal di hapus";
        if (success != 0) {
            message = "Note berhasil di hapus";
            finish();
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
