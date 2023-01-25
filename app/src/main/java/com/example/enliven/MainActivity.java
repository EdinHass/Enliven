package com.example.enliven;

import static com.example.enliven.ui.UtilsKt.getcurrentDateAndTime;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.enliven.databinding.ActivityMainBinding;
import com.example.enliven.ui.welcomescreen.Welcome_Activity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import nl.dionsegijn.konfetti.core.Angle;
import nl.dionsegijn.konfetti.core.PartyFactory;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.Spread;
import nl.dionsegijn.konfetti.core.emitter.Emitter;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.xml.KonfettiView;

public class MainActivity extends AppCompatActivity {

    NavController navController;
    private ActivityMainBinding binding;
    SharedPreferences prefs = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        prefs = getSharedPreferences("com.example.enliven", MODE_PRIVATE);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        lastLoginSetup();
    }

    protected void fullscreen() {
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

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void lastLoginSetup() {
        if (!prefs.getString("LastLogin", "n/a").equals(getcurrentDateAndTime(0))) {
            int currentStreak = prefs.getInt("currentStreak", 1);
            if (prefs.getString("LastLogin", "n/a").equals(getcurrentDateAndTime(-1))) {
                currentStreak++;
            } else {
                currentStreak = 1;
            }
            prefs.edit().putInt("currentStreak", currentStreak).apply();

            prefs.edit().putString("LastLogin", getcurrentDateAndTime(0)).apply();

            Set<String> currentLoginDates = new HashSet<String>(prefs.getStringSet("LoginDates", new HashSet<String>()));
            currentLoginDates.add(getcurrentDateAndTime(0));
            prefs.edit().putStringSet("LoginDates", currentLoginDates).apply();

            int currentXP = prefs.getInt("XP", 0);
            prefs.edit().putInt(getcurrentDateAndTime(-1), currentXP).apply();

            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.MyDialogTheme);
            builder.setView(R.layout.streaks_dialog);
            builder.setCancelable(true);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            final Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.fire_icon);
            Shape.DrawableShape drawableShape = new Shape.DrawableShape(drawable, true);
            KonfettiView konfettiView = dialog.findViewById(R.id.konfettiView);
            EmitterConfig emitterConfig = new Emitter(3, TimeUnit.SECONDS).perSecond(100);
            konfettiView.start(
                    new PartyFactory(emitterConfig)
                            .angle(Angle.BOTTOM)
                            .spread(Spread.ROUND)
                            .shapes(Arrays.asList(drawableShape))
                            .colors(Arrays.asList(0xc61366, 0x2d1a4e, 0x46434a))
                            .setSpeedBetween(0f, 15f)
                            .position(new Position.Relative(0.0, 0.0).between(new Position.Relative(1.0, 0.0)))
                            .build()
            );
            final Handler handler = new Handler();
            int finalCurrentStreak = currentStreak;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation fadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_slow);
                    TextView text1 = dialog.findViewById(R.id.lvlStreaksDialog1);
                    TextView text2 = dialog.findViewById(R.id.lvlStreaksDialog2);
                    RelativeLayout streaksLayout = dialog.findViewById(R.id.streaklayout);
                    assert text1 != null;
                    text1.setText(String.valueOf(finalCurrentStreak));
                    text1.startAnimation(fadein);
                    text1.setVisibility(View.VISIBLE);
                    assert text2 != null;
                    text2.startAnimation(fadein);
                    text2.setVisibility(View.VISIBLE);
                    assert streaksLayout != null;
                    streaksLayout.startAnimation(fadein);
                    streaksLayout.setVisibility(View.VISIBLE);

                }
            }, 3000);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Animation fadein = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_slow);
                    TextView text1 = dialog.findViewById(R.id.textstreakmain);
                    TextView text2 = dialog.findViewById(R.id.textstreakmain2);
                    assert text1 != null;
                    text1.startAnimation(fadein);
                    text1.setVisibility(View.VISIBLE);
                    assert text2 != null;
                    text2.startAnimation(fadein);
                    text2.setVisibility(View.VISIBLE);

                }
            }, 2000);

        }
    }


}