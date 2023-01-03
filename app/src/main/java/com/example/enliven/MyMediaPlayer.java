package com.example.enliven;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;

public class MyMediaPlayer {

    static MediaPlayer instance;

    public static MediaPlayer getInstance(Context context, int v){
        if(instance == null){
            instance = MediaPlayer.create(context, v);
        }
        return instance;

    }

    public static void freeMediaPlayer(){
        instance.stop();
        instance.release();
        instance = null;
    }


}
