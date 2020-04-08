package com.imed.medcare.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FirebaseTokenResponse {


    @SerializedName("status")
    private String status;
    @SerializedName("message")
    private String message;
    @SerializedName("resource")
    private String resource;
    @SerializedName("links")
    private LinksBean links;
    @SerializedName("page")
    private Object page;
    @SerializedName("total")
    private int total;
    @SerializedName("per_page")
    private Object perPage;
    @SerializedName("data")
    private List<?> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public LinksBean getLinks() {
        return links;
    }

    public void setLinks(LinksBean links) {
        this.links = links;
    }

    public Object getPage() {
        return page;
    }

    public void setPage(Object page) {
        this.page = page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getPerPage() {
        return perPage;
    }

    public void setPerPage(Object perPage) {
        this.perPage = perPage;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public static class LinksBean {
    }
}
