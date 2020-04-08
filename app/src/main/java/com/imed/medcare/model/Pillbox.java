package com.imed.medcare.model;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Pillbox extends RealmObject {

    @PrimaryKey
    String id;
    private int idPrescription;
    private int idSchuedule;
    private String time;
    private String name;
    private String urlImage;
    private Integer priority;
    private String dose;
    private boolean isOk = true;
    private String differenceTime;
    private Integer taken;
    private Integer repetition = 0;
    private String doseName;

    public Pillbox(){}

    public Pillbox(int idPrescription,int idSchuedule, String time, String name,String dose ,String urlImage, Integer priority, String doseName){
        this.id = String.valueOf(idPrescription)+String.valueOf(idSchuedule);
        this.idPrescription = idPrescription;
        this.idSchuedule = idSchuedule;
        this.time = time;
        this.name = name;
        this.urlImage = urlImage;
        this.dose = dose;
        this.priority = priority;
        this.doseName = doseName;

    }

    public Pillbox(int idPrescription,int idSchuedule, String time, String name,String dose ,String urlImage, Integer priority, Integer repetition){
        this.id = String.valueOf(idPrescription)+String.valueOf(idSchuedule);
        this.idPrescription = idPrescription;
        this.idSchuedule = idSchuedule;
        this.time = time;
        this.name = name;
        this.urlImage = urlImage;
        this.dose = dose;
        this.priority = priority;
        this.repetition = repetition;
    }


    public int getIdPrescription() { return idPrescription; }

    public String getId() { return id; }

    public int getIdSchuedule() {
        return idSchuedule;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getDose() {
        return dose;
    }

    public boolean isOk() {
        return isOk;
    }

    public Integer getPriority() { return priority; }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public String getDifferenceTime() {
        return differenceTime;
    }

    public void setDifferenceTime(String differenceTime) {
        this.differenceTime = differenceTime;
    }
    public Integer getTaken() {
        return taken;
    }

    public void setTaken(Integer taken) {
        this.taken = taken;
    }

    public Integer getRepetition() {
        return repetition;
    }

    public void setRepetition(Integer repetition) {
        this.repetition = repetition;
    }

    public String getDoseName() {
        return doseName;
    }

    public static void saveToRealm(final RealmList<Pillbox> pillboxRealmList, Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
            for(Pillbox pillbox : pillboxRealmList) {
                realm.insertOrUpdate(map(pillbox.getIdPrescription(), pillbox.getIdSchuedule(), pillbox.getTime(), pillbox.getName(), pillbox.getDose(), pillbox.getUrlImage(), pillbox.getPriority(), pillbox.getDoseName()));
            }
            }
        });
    }


    private static Pillbox map(int idPrescription,int idSchuedule, String time, String name,String dose ,String urlImage, Integer priority, String doseName) {
        return new Pillbox(idPrescription,idSchuedule,time,name, dose, urlImage, priority, doseName);
    }
}
