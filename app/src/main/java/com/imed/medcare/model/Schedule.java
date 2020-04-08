package com.imed.medcare.model;

import com.imed.medcare.network.response.TreatmentResponse;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Schedule extends RealmObject {

    @PrimaryKey
    private int id;
    private int type;
    private Integer day;
    private String dose;
    private String hour;

    public Schedule(){}

    public Schedule (TreatmentResponse.DataBean.PrescriptionsBean.SchedulesBean schedulesBean){
        this.id   = schedulesBean.getId();
        this.type = schedulesBean.getType();
        this.day  = schedulesBean.getDay();
        this.dose = schedulesBean.getDose();
        this.hour = schedulesBean.getHour();
    }
    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public Integer getDay() {
        return day;
    }

    public String getDose() {
        return dose;
    }

    public String getHour() {
        return hour;
    }

}
