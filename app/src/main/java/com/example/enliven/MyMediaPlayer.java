package com.example.enliven;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.View;

public class MyMediaPlayer {

    static MediaPlayer instance;

    public static MediaPlayer getInstance(Context context){
        if(instance == null){
            instance = new MediaPlayer();
        }
        return instance;

    }

    public static void freeMediaPlayer(){
        if(instance!=null) {
            instance.stop();
            instance.release();
        }
        instance = null;
    }


}
