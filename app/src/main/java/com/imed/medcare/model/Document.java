package com.imed.medcare.model;

import com.imed.medcare.network.response.DocumentSaveResponse;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Ramiro on 28-05-2018.
 */

public class Document extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private String path;
    private String picture;

    public Document() {
    }

    public Document(int id, String path, String name) {
        this.id = id;
        this.name = name;
        this.path = path;

    }

    public Document(DocumentSaveResponse.DataBean dataBean) {
        this.id = dataBean.getId();
        this.name = dataBean.getName();
        this.path = dataBean.getPath();
    }

    public Document(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }
}
