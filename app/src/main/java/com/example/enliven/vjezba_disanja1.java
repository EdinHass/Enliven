package com.example.enliven;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class vjezba_disanja1 extends AppCompatActivity {

    String vjezbaIme, SoundData;
    int vrijemeUzdisanja, vrijemeIzdisanja;
    TextView Title, text;
    MediaPlayer mediaPlayer;
    Button buttonStart;
    ProgressBar progressBar;
    int progressStatus = 0;
    private Handler handler = new Handler();
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vjezba_disanja1);
        vjezbaIme = getIntent().getStringExtra("vjezbaIme");
        SoundData = getIntent().getStringExtra("SoundData");
        vrijemeIzdisanja = getIntent().getIntExtra("vrijemeIzdisanja", 6);
        vrijemeUzdisanja = getIntent().getIntExtra("vrijemeUzdisanja", 4);
        Title = findViewById(R.id.vjezbaIme);
        text = findViewById(R.id.text);
        buttonStart = findViewById(R.id.buttonStart);
        imageView = findViewById(R.id.vjezbaSlika);
        progressBar=findViewById(R.id.progressBar2);

        mediaPlayer = MyMediaPlayer.getInstance(this);
        mediaPlayer.setLooping(true);
        AudioManager audioManager = (AudioManager) this.getSystemService(getApplicationContext().AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );


        try {
            mediaPlayer.setDataSource(SoundData);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(vjezbaIme);


        Title.setText(vjezbaIme);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.seekTo(0);
                mediaPlayer.setVolume(1,7);
                mediaPlayer.start();

                new Thread(new Runnable() {
                    public void run() {

                        for(int i=0;i<=7;i++) {
                            progressStatus=0;
                            while (progressStatus < (vrijemeIzdisanja+vrijemeUzdisanja)) {
                                progressStatus += 1;
                                handler.post(new Runnable() {
                                    public void run() {
                                        progressBar.setProgress(progressStatus);
                                        progressBar.setMax((vrijemeIzdisanja+vrijemeUzdisanja));
                                        if (progressStatus<=vrijemeUzdisanja) {
                                            text.setText("Udahni");
                                        } else if (progressStatus>vrijemeUzdisanja && progressStatus<=vrijemeIzdisanja) {
                                            text.setText("Izdahni");
                                        }
                                    }
                                });
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                        mediaPlayer.pause();

                    }
                }).start();
            }
        });


    }
    public void onBackPressed(){
        mediaPlayer.pause();
        this.finish();
    }
}
