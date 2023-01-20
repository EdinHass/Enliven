package com.example.enliven;
public class PostaviVjezbeDisanja {

    String name;
    String soundData;
    int vrijemeUzdisanja;
    int vrijemeIzdisanja;

    public PostaviVjezbeDisanja(String name, String soundData, int vrijemeIzdisanja, int vrijemeUzdisanja){
        this.name=name;
        this.soundData=soundData;
        this.vrijemeIzdisanja=vrijemeIzdisanja;
        this.vrijemeUzdisanja=vrijemeUzdisanja;
    }

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public String getSoundData(){return  soundData;}
    public void setSoundData(String soundData){this.soundData=soundData;}

    public int getVrijemeUzdisanja(){return vrijemeUzdisanja;}
    public void setVrijemeUzdisanja(int vrijemeUzdisanja){this.vrijemeUzdisanja=vrijemeUzdisanja;}

    public int getVrijemeIzdisanja(){return  vrijemeIzdisanja;}
    public void setVrijemeIzdisanja(int vrijemeIzdisanja){this.vrijemeIzdisanja=vrijemeIzdisanja;}
}
