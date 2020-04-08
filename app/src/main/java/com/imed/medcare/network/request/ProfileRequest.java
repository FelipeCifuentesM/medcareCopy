package com.imed.medcare.network.request;

import com.google.gson.annotations.SerializedName;

public class ProfileRequest {

    @SerializedName("access_token")
    String accessToken;

    public ProfileRequest(String accessToken){
        this.accessToken = accessToken;
    }

}
