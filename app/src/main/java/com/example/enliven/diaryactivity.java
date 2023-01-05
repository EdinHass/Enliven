package com.example.enliven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class diaryactivity extends AppCompatActivity {
    ListView notesListView;
    TextView emptyText;
    ImageView dodaj_note;
    TextView plus;

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
        dodaj_note = findViewById(R.id.imageView2);
        plus=findViewById(R.id.textView6);
        notes = new ArrayList<>();

        HashSet<String> noteSet = (HashSet<String>) sharedpref.getStringSet("notes", null);

        if(noteSet==null){
            emptyText.setVisibility(View.VISIBLE);
        }else{
            emptyText.setVisibility(View.GONE);
            notes=new ArrayList<>(noteSet);
        }
        adapter = new ArrayAdapter(getApplicationContext(), R.layout.custom_notes_row, R.id.example, notes);
        notesListView.setAdapter(adapter);

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(getApplicationContext(), NotesEditorActivity.class);
                intent.putExtra( "noteId", position);
                startActivity(intent);
            }
        });

        dodaj_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NotesEditorActivity.class));
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), NotesEditorActivity.class));
            }
        });
        notesListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                int itemToDelete = position;
                new AlertDialog.Builder(diaryactivity.this)
                        .setTitle("Da li ste sigurni?")
                        .setMessage("Želiš li izbrisati ovo?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                      notes.remove(itemToDelete);
                                      adapter.notifyDataSetChanged();

                                      HashSet<String> notesSet = new HashSet<>(notes);
                                      sharedpref.edit().putStringSet("notes", notesSet).apply();

                                if(noteSet.isEmpty() || noteSet==null){
                                    emptyText.setVisibility(View.VISIBLE);
                                }
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });

    }


    @Override
   public boolean onSupportNavigateUp() {
        finish();
        return true;
    }





}


