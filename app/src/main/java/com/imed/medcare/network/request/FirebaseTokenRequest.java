package com.imed.medcare.network.request;

import com.google.gson.annotations.SerializedName;

public class FirebaseTokenRequest {

    @SerializedName("device_token")
    private String firebaseToken;
    @SerializedName("user_id")
    private int idUser;

    public FirebaseTokenRequest(String firebaseToken, int idUser){
        this.firebaseToken = firebaseToken;
        this.idUser = idUser;
    }
}
