package com.imed.medcare.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PersonalOptions extends RealmObject {
    @PrimaryKey
    String UUID;
    private int id;
    private String name;
    private boolean isSelected = false;


    public PersonalOptions() {
    }

    PersonalOptions(int id, String name, RealmList<CurrentValue> currentValue) {
        UUID = java.util.UUID.randomUUID().toString();
        this.id = id;
        this.name = name;


        if (currentValue != null && currentValue.size() > 0) {
            for (CurrentValue value : currentValue) {
                if (value.getValue() == id) {
                    isSelected = true;
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
