package com.imed.medcare.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ChoicesPoll extends RealmObject{

    @PrimaryKey
    int id;
    private String value;
    private boolean isSelected;

    public ChoicesPoll(){}

    public ChoicesPoll(int id, String value){
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
