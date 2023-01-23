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
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class diaryactivity extends AppCompatActivity {
    ListView notesListView;
    TextView emptyText;
    ImageView dodaj_note;
    RelativeLayout plus;

    static List<String> notes;
    HashSet<String> noteSet;
    static ArrayAdapter adapter;

    SharedPreferences sharedpref;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diaryactivity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Emotion book");

        sharedpref = this.getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);

        notesListView = findViewById(R.id.notes_ListView);
        emptyText = findViewById(R.id.emptyText);
        dodaj_note = findViewById(R.id.imageView2);
        plus=findViewById(R.id.plusButton);
        notes = new ArrayList<>();

        noteSet = (HashSet<String>) sharedpref.getStringSet("notes", null);
        sharedPreferences = this.getSharedPreferences("com.example.enliven", Context.MODE_PRIVATE);

        if(noteSet==null){
            emptyText.setVisibility(View.VISIBLE);
        }else{
            emptyText.setVisibility(View.GONE);
            notes=new ArrayList<>(noteSet);
            if(notes.size()-1>=0) {
                if (Objects.equals(notes.get(notes.size() - 1), "")) {
                    notes.remove(notes.size() - 1);
                }
            }
        }
        adapter = new ArrayAdapter(getApplicationContext(), R.layout.custom_notes_row, R.id.example, notes);
        notesListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();




        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Intent intent = new Intent(getApplicationContext(), NotesEditorActivity.class);
                intent.putExtra( "noteId", position);
                startActivity(intent);
            }
        });

        Drawable happyIcon = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.happyicon);
        Drawable sadIcon = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.sadicon);
        Drawable angryIcon = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.angryicon);
        Drawable anxiousIcon = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.anxiousicon);
        Drawable scaredIcon = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.scaredicon);
        Drawable stressIcon = AppCompatResources.getDrawable(getApplicationContext(), R.drawable.stressicon);

        notesListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {


                    for(int i = 0; i<visibleItemCount; i++){
                        String emotion = sharedPreferences.getString("noteEmotion"+i, "");
                        ImageView viewIcon = getViewByPosition(i, notesListView).findViewById(R.id.emotionIcon);
                        switch(emotion){
                            case "sad":
                                viewIcon.setImageDrawable(sadIcon);
                                continue;
                            case "hap":
                                viewIcon.setImageDrawable(happyIcon);
                                continue;
                            case "ang":
                                viewIcon.setImageDrawable(angryIcon);
                                continue;
                            case "anx":
                                viewIcon.setImageDrawable(anxiousIcon);
                                continue;
                            case "sca":
                                viewIcon.setImageDrawable(scaredIcon);
                                continue;
                            case "str":
                                viewIcon.setImageDrawable(stressIcon);
                                continue;
                            default:
                                continue;
                        }
                    }

            }
        });

        dodaj_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), osjecanja_tips.class));
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), osjecanja_tips.class));
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
                                        int i = itemToDelete;
                                        while(sharedpref.getString("noteEmotion" + i, null)!=null){
                                            sharedpref.edit().putString("noteEmotion" + (i-1), sharedpref.getString("noteEmotion" + i, null)).apply();
                                            i++;
                                        }
                                      adapter.notifyDataSetChanged();
                                        if(notes.isEmpty()){
                                            emptyText.setVisibility(View.VISIBLE);
                                        }

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
    protected void onResume() {
        super.onResume();
        if(notes.size()-1>=0) {
            if (Objects.equals(notes.get(notes.size() - 1), "")) {
                notes.remove(notes.size() - 1);
            }
        }
        if(notes.isEmpty()) {
            emptyText.setVisibility(View.VISIBLE);
        }else{
            emptyText.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
    }

    @Override
   public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }





}


