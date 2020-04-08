package com.imed.medcare.network.request;

import com.google.gson.annotations.SerializedName;

public class AttachmentRequest {
    String date;
    String time;
    String dose;
    @SerializedName("response_date")
    String responseDate;
    @SerializedName("response_time")
    String responseTime;
    @SerializedName("response_dose")
    String responseDose;
    Integer reason;
    Integer taken;

    public AttachmentRequest(String date, String time, String dose, String responseDate, String responseTime, String responseDose, Integer reason, Integer taken){
        this.date = date;
        this.time = time;
        this.dose = dose;
        this.responseDate = responseDate;
        this.responseTime = responseTime;
        this.responseDose = responseDose;
        this.reason = reason;
        this.taken = taken;
    }

}
