package com.example.enliven;

public class SoundItem {
    String name;
    String soundData;
    int pictureData;

    public SoundItem(String name, String soundData, int pictureData) {
        this.name = name;
        this.soundData = soundData;
        this.pictureData = pictureData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSoundData() {
        return soundData;
    }

    public void setSoundData(String soundData) {
        this.soundData = soundData;
    }

    public int getPictureData() {
        return pictureData;
    }

    public void setPictureData(int pictureData) {
        this.pictureData = pictureData;
    }
}
