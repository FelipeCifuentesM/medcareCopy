package com.imed.medcare.network.response;

/**
 * Created by Ramiro on 03-04-2018.
 */

public class BaseResponse {

    
    private int code;
    private String status;
    private LinksBean links;
    public BaseResponse() {}

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }

    public LinksBean getLinks() {
        return links;
    }

    public void setLinks(LinksBean links) {
        this.links = links;
    }

    public static class LinksBean {
    }
}