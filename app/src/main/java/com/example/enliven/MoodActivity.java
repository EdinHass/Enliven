package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;



public class MoodActivity extends AppCompatActivity {
      RelativeLayout sreca;
      RelativeLayout tuga;
      RelativeLayout ljutnja;
      RelativeLayout strah;
      RelativeLayout stres;
      RelativeLayout anksioznost;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Osjećanje");



        sreca=findViewById(R.id.textsreca1);
        tuga=findViewById(R.id.texttuga1);
        ljutnja=findViewById(R.id.textljutnja1);
        strah=findViewById(R.id.textstrah1);
        stres=findViewById(R.id.textstres1);
        anksioznost=findViewById(R.id.textanksioznost1);
        tuga=findViewById(R.id.texttuga1);

        String[] strOsjecanja = new String[5];


        tuga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), tuga_tips.class);
                startActivity(i);
                String pom1=strOsjecanja[0];
                strOsjecanja[0]="Tuga";
                String pom2=strOsjecanja[1];
                strOsjecanja[1]=pom1;
                String pom3=strOsjecanja[2];
                strOsjecanja[2]=pom2;
                String pom4=strOsjecanja[3];
                strOsjecanja[3]=pom3;
                strOsjecanja[4]=pom4;
            }
        });

        sreca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), sreca_tips.class);
                startActivity(i);
                String pom1=strOsjecanja[0];
                strOsjecanja[0]="Sreca";
                String pom2=strOsjecanja[1];
                strOsjecanja[1]=pom1;
                String pom3=strOsjecanja[2];
                strOsjecanja[2]=pom2;
                String pom4=strOsjecanja[3];
                strOsjecanja[3]=pom3;
                strOsjecanja[4]=pom4;
            }
        });



        ljutnja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ljutnja_tips.class);
                startActivity(i);
                String pom1=strOsjecanja[0];
                strOsjecanja[0]="Ljutnja";
                String pom2=strOsjecanja[1];
                strOsjecanja[1]=pom1;
                String pom3=strOsjecanja[2];
                strOsjecanja[2]=pom2;
                String pom4=strOsjecanja[3];
                strOsjecanja[3]=pom3;
                strOsjecanja[4]=pom4;
            }
        });

        strah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), strah_tips.class);
                startActivity(i);
                String pom1=strOsjecanja[0];
                strOsjecanja[0]="Strah";
                String pom2=strOsjecanja[1];
                strOsjecanja[1]=pom1;
                String pom3=strOsjecanja[2];
                strOsjecanja[2]=pom2;
                String pom4=strOsjecanja[3];
                strOsjecanja[3]=pom3;
                strOsjecanja[4]=pom4;
            }
        });

        stres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), stres_tips.class);
                startActivity(i);
                String pom1=strOsjecanja[0];
                strOsjecanja[0]="Stres";
                String pom2=strOsjecanja[1];
                strOsjecanja[1]=pom1;
                String pom3=strOsjecanja[2];
                strOsjecanja[2]=pom2;
                String pom4=strOsjecanja[3];
                strOsjecanja[3]=pom3;
                strOsjecanja[4]=pom4;
            }
        });

        anksioznost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), anksioznost_tips.class);
                startActivity(i);
                String pom1=strOsjecanja[0];
                strOsjecanja[0]="Anksioznost";
                String pom2=strOsjecanja[1];
                strOsjecanja[1]=pom1;
                String pom3=strOsjecanja[2];
                strOsjecanja[2]=pom2;
                String pom4=strOsjecanja[3];
                strOsjecanja[3]=pom3;
                strOsjecanja[4]=pom4;
            }
        });



    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

}