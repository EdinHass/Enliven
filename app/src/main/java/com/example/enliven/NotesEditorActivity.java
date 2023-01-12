package com.example.enliven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.List;

public class NotesEditorActivity extends AppCompatActivity {

    EditText noteEditText;




    String emotion;
    int noteId;

    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_editor);
        emotion = getIntent().getStringExtra("emotion");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Emotion book");

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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.save_note_menu, menu);
        return  super.onCreateOptionsMenu(menu);}


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if(item.getItemId() == R.id.save_note){
            if(noteEditText.getText().toString().trim().isEmpty()){
                Toast.makeText(getApplicationContext(), "Polje ne smije biti prazno!", Toast.LENGTH_SHORT).show();
            }else {
                diaryactivity.notes.set(noteId, noteEditText.getText().toString().trim());
                if(emotion==null){
                    emotion=sharedPreferences.getString("noteEmotion" + noteId, null);
                }
                sharedPreferences.edit().putString("noteEmotion" + noteId, emotion).apply();
                diaryactivity.adapter.notifyDataSetChanged();
                HashSet<String> notesSet = new HashSet<>(diaryactivity.notes);
                updateLast();
                sharedPreferences.edit().putStringSet("notes", notesSet).apply();
                finish();
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void updateLast(){
        if(sharedPreferences.getString("lastEmotion1",null)==null) {
            sharedPreferences.edit().putString("lastEmotion1", emotion).apply();
        } else
            if(sharedPreferences.getString("lastEmotion2",null)==null){
                sharedPreferences.edit().putString("lastEmotion2", emotion).apply();
            } else
            if(sharedPreferences.getString("lastEmotion3",null)==null){
                sharedPreferences.edit().putString("lastEmotion3", emotion).apply();
            } else
                if(sharedPreferences.getString("lastEmotion4",null)==null){
                    sharedPreferences.edit().putString("lastEmotion4", emotion).apply();
                }else
                if(sharedPreferences.getString("lastEmotion5",null)==null){
                    sharedPreferences.edit().putString("lastEmotion5", emotion).apply();
                }else{
                    sharedPreferences.edit().putString("lastEmotion1", sharedPreferences.getString("lastEmotion2",null)).apply();
                    sharedPreferences.edit().putString("lastEmotion2", sharedPreferences.getString("lastEmotion3",null)).apply();
                    sharedPreferences.edit().putString("lastEmotion3", sharedPreferences.getString("lastEmotion4",null)).apply();
                    sharedPreferences.edit().putString("lastEmotion4", sharedPreferences.getString("lastEmotion5",null)).apply();
                    sharedPreferences.edit().putString("lastEmotion5", emotion).apply();
                }
    }
}