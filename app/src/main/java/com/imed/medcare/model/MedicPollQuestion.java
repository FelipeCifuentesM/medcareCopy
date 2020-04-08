package com.imed.medcare.model;

import com.imed.medcare.network.response.MedicPollResponse;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MedicPollQuestion extends RealmObject {


    @PrimaryKey
    int id;
    private String description;
    private int status;
    private String title;
    private int type;
    private Integer measumenetType;
    private RealmList<ChoicesPoll> choices;
    private RealmList<AnswerPoll> answerPolls;
    private MeasurementPoll measuremtPoll;

    public MedicPollQuestion(){}

    public MedicPollQuestion(MedicPollResponse.DataBean.QuestionsBean data) {
        id = data.getId();
        description = data.getDescription();
        status = data.getStatus();
        type = data.getType();
        title = data.getTitle();
        measumenetType = getMeasumenetType();
        choices = setChoices(data.getChoices());
        answerPolls = setAnswers(data.getAnswers());
        measuremtPoll = setMeasurements(data.getMeasurement());
    }



    public String getTitle(){return title;}

    public int getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return status;
    }

    public int getType() {
        return type;
    }
    public Integer getMeasumenetType() {
        return measumenetType;
    }
    public MeasurementPoll getMeasuremtPoll() {
        return measuremtPoll;
    }
    public RealmList<ChoicesPoll> getChoices() {
        return choices;
    }
    public RealmList<AnswerPoll> getAnswerPolls() {
        return answerPolls;
    }


    public RealmList<ChoicesPoll> setChoices(List<MedicPollResponse.DataBean.QuestionsBean.ChoicesBean> choices) {
        RealmList<ChoicesPoll> choicesPollRealmList= new RealmList<>();
        if(choices !=null) {
            for (MedicPollResponse.DataBean.QuestionsBean.ChoicesBean choicesBean : choices) {
                choicesPollRealmList.add(new ChoicesPoll(choicesBean.getId(), choicesBean.getValue()));
            }
        }
        return choicesPollRealmList;
    }

    public RealmList<AnswerPoll> setAnswers(List<MedicPollResponse.DataBean.QuestionsBean.AnswersBean> answers) {

        RealmList<AnswerPoll> answerPollRealmList = new RealmList<>();
        if(answers !=null) {

            for (MedicPollResponse.DataBean.QuestionsBean.AnswersBean answersBean : answers) {
                answerPollRealmList.add(new AnswerPoll(answersBean.getId(),answersBean.getChoice_id(), answersBean.getValue(), answersBean.getScore(),answersBean.getDate(),false));
            }
        }
        return answerPollRealmList;
    }


    public MeasurementPoll setMeasurements(MedicPollResponse.DataBean.QuestionsBean.MeasurementBean measurementBean) {
        MeasurementPoll measurementPoll = new MeasurementPoll();
        if(measurementBean !=null){
            measurementPoll = new MeasurementPoll(measurementBean.getId(),measurementBean.getName(),measurementBean.getUnit(),measurementBean.getRequest_date());
        }
        return measurementPoll;
    }

    public static void saveToRealm(final MedicPollResponse response, Realm realm) {
        if(response.getData().size()>0){
            for (final MedicPollResponse.DataBean.QuestionsBean dataBean : response.getData().get(0).getQuestions()) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.insertOrUpdate(mapTreatmentData(dataBean));
                    }
                });
            }
        }
    }

    private static MedicPollQuestion mapTreatmentData(MedicPollResponse.DataBean.QuestionsBean data) {
        return new MedicPollQuestion(data);
    }
}
