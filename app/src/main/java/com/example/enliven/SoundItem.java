package com.example.enliven;

public class SoundItem {
    String name;
    int soundData;
    int pictureData;

    public SoundItem(String name, int soundData, int pictureData) {
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

    public int getSoundData() {
        return soundData;
    }

    public void setSoundData(int soundData) {
        this.soundData = soundData;
    }

    public int getPictureData() {
        return pictureData;
    }

    public void setPictureData(int pictureData) {
        this.pictureData = pictureData;
    }
}
