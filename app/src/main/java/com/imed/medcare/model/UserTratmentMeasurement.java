package com.imed.medcare.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserTratmentMeasurement extends RealmObject {


    @PrimaryKey
    String uUId;
    int id;
    String date;
    String value;

    public UserTratmentMeasurement(){}

    public UserTratmentMeasurement(int id, String date, String value){
        this.uUId = UUID.randomUUID().toString();
        this.id = id;
        this.date = date + " 00:00:00";
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public String getValue() {
        return value;
    }


}
