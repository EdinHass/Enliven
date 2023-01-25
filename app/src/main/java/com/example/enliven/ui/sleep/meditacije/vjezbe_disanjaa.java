package com.example.enliven.ui.sleep.meditacije;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

import com.example.enliven.R;

public class vjezbe_disanjaa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vjezbe_disanjaa);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Vježbe disanja");

        getIntent();

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.vjezbedisanja_fragmentView);
        NavController navController = navHostFragment.getNavController();
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}