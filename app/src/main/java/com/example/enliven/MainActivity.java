package com.example.enliven;

import static com.example.enliven.ui.UtilsKt.getcurrentDateAndTime;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.enliven.ui.auth.AuthActivity;
import com.example.enliven.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.enliven.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prefs = getSharedPreferences("com.example.enliven", MODE_PRIVATE);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        lastLoginSetup();

    }

    protected void fullscreen(){
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrun", true)) {
            startActivity(new Intent(this, Welcome_Activity.class));
        }

        lastLoginSetup();



    }

    public void lastLoginSetup(){
        if (!prefs.getString("LastLogin", "n/a").equals(getcurrentDateAndTime(0))){
            int currentStreak = prefs.getInt("currentStreak", 1);
            if(prefs.getString("LastLogin", "n/a").equals(getcurrentDateAndTime(-1))){
                currentStreak++;
            }else{
                currentStreak=1;
            }
            prefs.edit().putInt("currentStreak", currentStreak).apply();

            prefs.edit().putString("LastLogin", getcurrentDateAndTime(0)).apply();

            Set<String> currentLoginDates = new HashSet<String>(prefs.getStringSet("LoginDates", new HashSet<String>()));
            currentLoginDates.add(getcurrentDateAndTime(0));
            prefs.edit().putStringSet("LoginDates", currentLoginDates).apply();

            int currentXP = prefs.getInt("XP", 0);
            prefs.edit().putInt(getcurrentDateAndTime(-1), currentXP).apply();
        }
    }


}