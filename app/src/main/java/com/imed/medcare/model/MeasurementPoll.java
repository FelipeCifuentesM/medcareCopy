package com.imed.medcare.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MeasurementPoll extends RealmObject {
    @PrimaryKey
    int id;
    String name;
    private String unit;
    private Boolean requestDate;


    public MeasurementPoll() {
    }

    public MeasurementPoll(int id, String name, String unit, Boolean requestDate) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.requestDate = requestDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }


    public Boolean getRequestDate() {
        return requestDate;
    }



}
