package com.imed.medcare.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CurrentValue extends RealmObject {

    @PrimaryKey
    String id;
    private Integer value;

    public CurrentValue(){ }

    public CurrentValue(Integer value){
        id = UUID.randomUUID().toString();
        this.value = value;}

    public Integer getValue() {
        return value;
    }


}
