package com.example.enliven;

import java.io.Serializable;

public class HabitsDomain implements Serializable {
    private String title;
    private String pic;
    private String cilj;
    private int goalnumber;

    public HabitsDomain(String title, String pic, String cilj, int goalnumber) {
        this.title = title;
        this.pic = pic;
        this.cilj = cilj;
        this.goalnumber = goalnumber;
    }

    public HabitsDomain(String title, String pic) {
        this.title = title;
        this.pic = pic;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {

        this.pic = pic;
    }

    public int getGoalnumber() {

        return goalnumber;
    }
    
    public void setGoalnumber(int goalnumber)
    {
        this.goalnumber = goalnumber;
    }

    public String getCilj() {
        return cilj;
    }

    public void setCilj(String cilj) {
        this.cilj = cilj;
    }
}
