package com.imed.medcare.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FileTreatment extends RealmObject {

    @PrimaryKey
    int id;
    String file;
    String name;

    public FileTreatment() {
    }

    public FileTreatment(int id, String file, String name) {
        this.id = id;
        this.file = file;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getFile() {
        return file;
    }

    public String getName() {
        return name;
    }
}
