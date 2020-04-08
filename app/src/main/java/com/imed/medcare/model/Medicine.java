package com.imed.medcare.model;

import com.imed.medcare.network.response.TreatmentResponse;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static com.imed.medcare.network.RestClient.BASE_URL;

/**
 * Created by Ramiro on 15-05-2018.
 */

public class Medicine extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private String dosage;
    private String startDate;
    private String endDate;
    private String time;
    private boolean isOk;
    private String image;

    public Medicine() { }

    public Medicine(TreatmentResponse.DataBean.PrescriptionsBean prescription) {
        this.id = prescription.getId();
        this.startDate = prescription.getStart();
        this.endDate = prescription.getEnd();
        this.image = BASE_URL + prescription.getMedicament_format().getImg();
        this.name = prescription.getMedicament().getName();
    }

    public int getId() {
        return id;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = this.time;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public String getImage() {
        return image;
    }
}
