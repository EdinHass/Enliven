package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class diaryactivity extends AppCompatActivity {
    ListView notesListView;
    TextView emptyText;

    static List<String> notes;
    static ArrayAdapter adapter;

    SharedPreferences sharedpref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaryactivity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Dnevnik");

        sharedpref = this.getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);

        notesListView = findViewById(R.id.notes_ListView);
        emptyText = findViewById(R.id.emptyText);

        notes = new ArrayList<>();

        HashSet<String> noteSet = (HashSet<String>) sharedpref.getStringSet("notes", null);

        if(noteSet.isEmpty() )
        adapter = new ArrayAdapter(getApplicationContext(), R.layout.custom_notes_row, R.id.textView5, notes);
        notesListView.setAdapter(adapter);

    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}


