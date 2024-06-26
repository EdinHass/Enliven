package com.example.enliven.ui.welcomescreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.enliven.R;
import com.google.android.material.navigation.NavigationBarItemView;

public class Welcome_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.welcome_screen_fragmentView);
        NavController navController = navHostFragment.getNavController();
        if(getIntent().getBooleanExtra("changeSleep", false)){
            navController.navigate(R.id.welcome_sleep);
        }

    }

    @Override
    public void onBackPressed() {
        if(getIntent().getBooleanExtra("changeSleep", false)){
            finish();
        }
        super.onBackPressed();
    }
}