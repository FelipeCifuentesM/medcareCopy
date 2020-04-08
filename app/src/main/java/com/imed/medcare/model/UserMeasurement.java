package com.imed.medcare.model;

import com.imed.medcare.network.response.ProfileResponse;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserMeasurement extends RealmObject {

    @PrimaryKey
    int id;
    String name;
    String unit;
    String unitDescription;
    int type;
    Integer typeView;
    RealmList<UserTratmentMeasurement> userTratmentMeasurementRealmList;

    public UserMeasurement(){}
    public UserMeasurement(Integer typeView){
        this.typeView = typeView;
    }
    public UserMeasurement(int id, String name, String unit, String unitDescription, int type ,List<ProfileResponse.DataBean.UserBean.MeasurementsBean.TreatmentMeasurementsBean> treatmentMeasurementsBeanList ){
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.unitDescription = unitDescription;
        this.type = type;
        this.userTratmentMeasurementRealmList = getUserTreatmentMeasurement(treatmentMeasurementsBeanList);
    }

    private RealmList<UserTratmentMeasurement> getUserTreatmentMeasurement(List<ProfileResponse.DataBean.UserBean.MeasurementsBean.TreatmentMeasurementsBean> treatmentMeasurementsBeanList) {

        RealmList<UserTratmentMeasurement> userTratmentMeasurementRealmList = new RealmList<>();
        for (ProfileResponse.DataBean.UserBean.MeasurementsBean.TreatmentMeasurementsBean treatmentMeasurementsBean : treatmentMeasurementsBeanList){
            userTratmentMeasurementRealmList.add(new UserTratmentMeasurement(treatmentMeasurementsBean.getId(),treatmentMeasurementsBean.getDate(), treatmentMeasurementsBean.getValue()));
        }
        return userTratmentMeasurementRealmList;
    }


    public String getName() {
        return name;
    }

    public String getUnit() {
        return unit;
    }

    public RealmList<UserTratmentMeasurement> getUserTratmentMeasurementRealmList() {
        return userTratmentMeasurementRealmList;
    }

    public String getUnitDescription() {
        return unitDescription;
    }

    public int getType() {
        return type;
    }
    public Integer getTypeView() {
        return typeView;
    }

}
