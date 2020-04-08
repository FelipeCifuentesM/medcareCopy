package com.imed.medcare.model;

import com.imed.medcare.network.response.TreatmentPollResponse;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TreatmentPoll extends RealmObject{

    @PrimaryKey
    int id;
    private int type;
    private int pollPeriodId;
    private int idTreatment;
    private String name;
    private String description;
    private String startAt;
    private String finishAt;
    private RealmList<TreatmentPollQuestion> treatmentPollQuestionsRealmList;


    public TreatmentPoll(){}

    public TreatmentPoll(int idTreatment, TreatmentPollResponse.DataBean dataBean){
        this.id = dataBean.getId();
        this.type = dataBean.getType();
        this.name = dataBean.getName();
        this.description = dataBean.getDescription();
        this.startAt = dataBean.getStart();
        this.finishAt = dataBean.getEnd();
        this.pollPeriodId = dataBean.getPoll_period_id();
        this.idTreatment = idTreatment;
        this.treatmentPollQuestionsRealmList = getPollQuestion(dataBean.getQuestions());
    }

    private RealmList<TreatmentPollQuestion> getPollQuestion(List<TreatmentPollResponse.DataBean.QuestionsBean> questions) {
        RealmList<TreatmentPollQuestion> treatmentPollQuestionRealmList = new RealmList<>();
        for(TreatmentPollResponse.DataBean.QuestionsBean questionsBean : questions){
            treatmentPollQuestionRealmList.add(new TreatmentPollQuestion(questionsBean.getId(),questionsBean.isShow() ,questionsBean.getDescription(),questionsBean.getStatus(), questionsBean.getStatus(),questionsBean.getTitle(),questionsBean.getType(),questionsBean.getChoices(), questionsBean.getAnswers(),questionsBean.getMeasurement(), questionsBean.getLastAnswersBeans(), questionsBean.getMeasurement_id(), questionsBean.getQuestion_order()));
        }
        return  treatmentPollQuestionRealmList;
    }

    public int getId() {
        return id;
    }

    public int getPollPeriodId() {
        return pollPeriodId;
    }
    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStartAt() {
        return startAt;
    }

    public String getFinishAt() {
        return finishAt;
    }

    public RealmList<TreatmentPollQuestion> getTreatmentPollQuestionsRealmList() {
        return treatmentPollQuestionsRealmList;
    }
    public void setTreatmentPollQuestionsRealmList(RealmList<TreatmentPollQuestion> treatmentPollQuestionsRealmList) {
        this.treatmentPollQuestionsRealmList = treatmentPollQuestionsRealmList;
    }
    public int getIdTreatment() {
        return idTreatment;
    }

    public static void saveToRealm(final int idTreatment, final TreatmentPollResponse.DataBean response, Realm realm) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(mapTreatmentPollData(idTreatment,response));
            }
        });
    }

    private static TreatmentPoll mapTreatmentPollData(int idTreatment,TreatmentPollResponse.DataBean response) {
        return new TreatmentPoll(idTreatment,response);
    }
}
