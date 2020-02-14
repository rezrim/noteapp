package com.example.noteapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private TextView tvEmpty;
    private FloatingActionButton fabAdd;

    private Database db;
    private NoteAdapter adapter;
    private List<NoteModel> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new Database(this);

        rvList = findViewById(R.id.rv_list);
        tvEmpty = findViewById(R.id.tv_empty);
        fabAdd = findViewById(R.id.fab_add);

        adapter = new NoteAdapter(this);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(adapter);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, addNoteActivity.class));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        getNotes();
    }

    private void getNotes() {
        notes = db.getNotes();
        adapter.setNotes(notes);

        if (notes.size() != 0) {
            tvEmpty.setVisibility(View.GONE);
            rvList.setVisibility(View.VISIBLE);
        } else {
            tvEmpty.setVisibility(View.VISIBLE);
            rvList.setVisibility(View.GONE);
        }
    }
}
