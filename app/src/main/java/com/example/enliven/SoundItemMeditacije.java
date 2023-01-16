package com.example.enliven;
public class SoundItemMeditacije {
    String name;
    String soundData;

    public SoundItemMeditacije(String name, String soundData) {
        this.name = name;
        this.soundData = soundData;

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
}
