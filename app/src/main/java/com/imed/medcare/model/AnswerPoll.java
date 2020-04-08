package com.imed.medcare.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class AnswerPoll extends RealmObject{

    @PrimaryKey
    int id;
    private Integer choiseId;
    private String value;
    private int score;
    private String date;
    private boolean isLocal;

    public AnswerPoll(){}
    public AnswerPoll(int id,Integer choiseId, String value, int score,String date,boolean isLocal){
        this.id = id;
        this.value = value;
        this.score = score;
        this.choiseId = choiseId;
        this.date = date;
        this.isLocal = isLocal;
    }

    public int getId() {
        return id;
    }
    public String getValue() {
        return value;
    }
    public Integer getChoiseId() { return choiseId; }
    public int getScore() {
        return score;
    }
    public String getDate(){return date;}

    public void setChoiseId(Integer choiseId) {
        this.choiseId = choiseId;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public void setDate(String date){this.date = date;}
    public void setScore(int score) {
        this.score = score;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public void setLocal(boolean local) {
        isLocal = local;
    }
}
