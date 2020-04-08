package com.imed.medcare.model;

import java.util.ArrayList;

public class PillboxHeader {

    String name;
    ArrayList<Pillbox> pillboxArrayList;

    boolean show;

    public PillboxHeader(String name, boolean show){
        this.name = name;
        this.show = show;
        this.pillboxArrayList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public ArrayList<Pillbox> getPillboxArrayList() {
        return pillboxArrayList;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
}
