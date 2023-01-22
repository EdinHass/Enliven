package com.example.enliven;

import static com.example.enliven.ui.UtilsKt.getcurrentDateAndTime;

import android.app.Dialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.enliven.ui.auth.AuthActivity;
import com.example.enliven.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.enliven.databinding.ActivityMainBinding;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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