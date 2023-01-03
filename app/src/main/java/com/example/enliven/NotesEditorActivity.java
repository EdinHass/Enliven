package com.example.enliven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.HashSet;

public class NotesEditorActivity extends AppCompatActivity {

    EditText noteEditText;

    int noteId;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_editor);

        ActionBar actionBar = getSupportActionBar();

        sharedPreferences = this.getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);
        noteEditText = findViewById(R.id.note_EditText);

        Intent intent = getIntent();
        noteId =intent.getIntExtra("noteId", -1);

        if(noteId != -1){

            noteEditText.setText(diaryactivity.notes.get(noteId));
            actionBar.setTitle("Uredi");
        }
        else{
            diaryactivity.notes.add("");
            noteId=diaryactivity.notes.size() - 1;
            actionBar.setTitle("Dodaj");
        }

        noteEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                  diaryactivity.notes.set(noteId, String.valueOf(charSequence));
                  diaryactivity.adapter.notifyDataSetChanged();

                HashSet<String> notesSet = new HashSet<>(diaryactivity.notes);
                sharedPreferences.edit().putStringSet("notes", notesSet).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_note_menu, menu);
        return  super.onCreateOptionsMenu(menu);}


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.save_note){

            startActivity(new Intent(getApplicationContext(), diaryactivity.class));
            finish();

            return true;
        }
        return false;
    }





}