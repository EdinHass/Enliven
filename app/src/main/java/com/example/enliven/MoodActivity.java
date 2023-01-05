package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.enliven.databinding.ActivityMoodBinding;
import com.example.enliven.databinding.FragmentDashboardBinding;

public class MoodActivity extends AppCompatActivity {
      RelativeLayout sreca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Osjećanje");

        sreca=findViewById(R.id.textsreca1);

        sreca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), diaryactivity.class));
            }
        });



    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}