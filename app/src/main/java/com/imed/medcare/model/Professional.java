package com.imed.medcare.model;

import com.imed.medcare.network.response.ProfileResponse;
import com.imed.medcare.network.response.TreatmentResponse;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Professional extends RealmObject {

    @PrimaryKey
    int id;
    private String forenames;
    private String surnames;
    private String name;
    private String speciality;
    private String avatar;
    private Integer status;
    private String  prefix;
    private String branchName;

    public Professional(){}

    public Professional(TreatmentResponse.DataBean.PatientBean.MembershipBean.ProfessionalBean professionalBean, String branchName){
        this.id = professionalBean.getId();
        this.name = professionalBean.getForenames() + " "+ professionalBean.getSurnames();
        this.forenames = professionalBean.getForenames();
        this.surnames = professionalBean.getSurnames();
        this.speciality = professionalBean.getSpecialty();
        this.avatar = professionalBean.getAvatar();
        this.status = professionalBean.getStatus();
        this.prefix = professionalBean.getPrefix();
        this.branchName =  branchName;
    }

    public Professional(ProfileResponse.DataBean.ProfessionalsBean professionalBean){
        this.id = professionalBean.getId();
        this.name = professionalBean.getForenames() + " "+ professionalBean.getSurnames();
        this.forenames = professionalBean.getForenames();
        this.surnames = professionalBean.getSurnames();
        this.speciality = professionalBean.getSpecialty();
        this.avatar = professionalBean.getAvatar();
        this.status = professionalBean.getStatus();
        this.prefix = professionalBean.getPrefix();
    }

    public String getForenames() {
        return forenames;
    }

    public String getSurnames() {
        return surnames;
    }

    public String getSpeciality() {
        return speciality;
    }

    public String getAvatar() {
        return avatar;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPreFix() {
        return prefix;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getBranchName() {
        return branchName;
    }

}
