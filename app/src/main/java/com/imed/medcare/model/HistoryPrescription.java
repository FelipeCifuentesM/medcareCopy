package com.imed.medcare.model;

import com.imed.medcare.network.response.HistoryPrescriptionResponse;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class HistoryPrescription extends RealmObject {
    @PrimaryKey
    int id;
    private int prescriptionId;
    private String date;
    private String responseDate;
    private String time;
    private String responseTime;
    private Integer taken;

    public HistoryPrescription(){ }

    public HistoryPrescription(int id, int prescriptionId, String date, String responseDate, String time, String responseTime, Integer taken){
        this.id = id;
        this.prescriptionId = prescriptionId;
        this.date = date;
        this.time = time;
        this.taken = taken;
        this.responseTime = responseTime;
        this.responseDate = responseDate;
    }

    public String getResponseDate() {
        return responseDate;
    }
    public int getPrescriptionId() {
        return prescriptionId;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public Integer getTaken() {
        return taken;
    }

    public String getResponseTime() { return responseTime; }

    public static void saveToRealm(final HistoryPrescriptionResponse response, Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if(response.getData() !=null && response.getData().size()>0) {
                    for(int i=0;response.getData().size()>i;i++) {
                        for(int j=0;response.getData().get(i).size()>j;j++) {
                            realm.insertOrUpdate(map(response.getData().get(i).get(j)));
                        }
                    }
                }
            }
        });
    }

    private static HistoryPrescription map(HistoryPrescriptionResponse.DataBean response) {
        return new HistoryPrescription(response.getId(),response.getPrescription_id(),response.getDate(),response.getResponse_date(),response.getTime(),response.getResponse_time(), response.getTaken());
    }
}
