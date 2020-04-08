package com.imed.medcare.network.request;

import com.google.gson.annotations.SerializedName;

import static com.imed.medcare.utils.MedcareUtils.formatRut;

/**
 * Created by Ramiro on 03-04-2018.
 */

public class LoginRequest {
    @SerializedName("username")
    private String rut;
    private String password;


    public LoginRequest(String rut, String password) {
        this.rut = formatRut(rut);
        this.password = password;
    }


    public String getRut() {
        return rut;
    }

    public String getPassword() {
        return password;
    }

}
