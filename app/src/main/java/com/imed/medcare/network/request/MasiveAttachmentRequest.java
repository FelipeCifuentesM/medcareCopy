package com.imed.medcare.network.request;

import com.google.gson.annotations.SerializedName;
import com.imed.medcare.model.Pillbox;
import com.imed.medcare.utils.MedcareUtils;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class MasiveAttachmentRequest {
    @SerializedName("attachments")
    private List<PillboxResponse> pillboxResponseList;

    public MasiveAttachmentRequest(RealmList<Pillbox> pillboxRealmList, String date, String taken, String reason){
        this.pillboxResponseList = new ArrayList<>();
        String currentDateAndHour = MedcareUtils.CurrentDateAndHour();
        String currentDate = currentDateAndHour.split(" ")[0];
        String currentTime = currentDateAndHour.split(" ")[1];

        for(Pillbox pillbox : pillboxRealmList) {
            this.pillboxResponseList.add(new PillboxResponse(pillbox.getIdPrescription(), date, pillbox.getDose(),currentDate,pillbox.getDose(),currentTime,taken,reason,pillbox.getTime()));
        }
    }

    private class  PillboxResponse{
        @SerializedName("prescription_id")
        int idPrescription;
        String date;
        String dose;
        @SerializedName("response_date")
        String responseDate;
        @SerializedName("response_dose")
        String responseDose;
        @SerializedName("response_time")
        String responseTime;
        String time;
        String reason;
        String taken;
        public PillboxResponse (int idPrescription, String date, String dose, String responseDate, String responseDose, String responseTime, String taken,String reason, String time){
            this.idPrescription = idPrescription;
            this.responseDate = responseDate;
            this.responseDose= responseDose;
            this.responseTime = responseTime;
            this.dose = dose;
            this.time = time;
            this.date = date;
            this.reason = reason;
            this.taken = taken;
        }
    }

}
