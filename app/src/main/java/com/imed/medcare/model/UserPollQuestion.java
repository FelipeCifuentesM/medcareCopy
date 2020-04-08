package com.imed.medcare.model;

import com.imed.medcare.network.response.UserPollResponse;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserPollQuestion extends RealmObject {

    @PrimaryKey
    int id;
    private int score;
    private String description;
    private int status;
    private String title;
    private int type;
    private Integer measumenetType;
    private RealmList<ChoicesPoll> choices;
    private RealmList<AnswerPoll> answerPolls;
    private Integer questionOrder;
    private MeasurementPoll measuremtPoll;

    public UserPollQuestion(){}

    public UserPollQuestion(int id, Integer measumenetType, String description, int score, int status, String title, int type, List<UserPollResponse.DataBean.QuestionsBean.ChoicesBean> choicesBeans, List<UserPollResponse.DataBean.QuestionsBean.AnswersBean> answersBeans, UserPollResponse.DataBean.QuestionsBean.MeasurementBean measurementBean, Integer questionOrder) {
        this.id = id;
        this.description = description;
        this.score = score;
        this.status = status;
        this.title = title;
        this.type = type;
        this.measumenetType = measumenetType;
        this.questionOrder = questionOrder;
        this.choices = setChoices(choicesBeans);
        this.answerPolls = setAnswers(answersBeans);
        this.measuremtPoll = setMeasurements(measurementBean);
    }

    public String getTitle(){return title;}

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return status;
    }

    public Integer getQuestionOrder() {
        return questionOrder;
    }

    public int getType() {
        return type;
    }
    public Integer getMeasumenetType() {
        return measumenetType;
    }

    public RealmList<ChoicesPoll> getChoices() {
        return choices;
    }
    public RealmList<AnswerPoll> getAnswerPolls() {
        return answerPolls;
    }
    public void setAnswerPolls(RealmList<AnswerPoll> answerPolls) {
        this.answerPolls = answerPolls;
    }

    public MeasurementPoll getMeasuremtPoll() {
        return measuremtPoll;
    }


    public MeasurementPoll setMeasurements(UserPollResponse.DataBean.QuestionsBean.MeasurementBean measurementBean) {
        MeasurementPoll measurementPoll = new MeasurementPoll();
        if(measurementBean !=null){
            measurementPoll = new MeasurementPoll(measurementBean.getId(),measurementBean.getName(),measurementBean.getUnit(),measurementBean.getRequest_date());
        }
        return measurementPoll;
    }

    public RealmList<ChoicesPoll> setChoices(List<UserPollResponse.DataBean.QuestionsBean.ChoicesBean> choices) {
        RealmList<ChoicesPoll> choicesPollRealmList= new RealmList<>();
        if(choices !=null && choices.size()>0 ) {
            for (UserPollResponse.DataBean.QuestionsBean.ChoicesBean choicesBean : choices) {
                choicesPollRealmList.add(new ChoicesPoll(choicesBean.getId(), choicesBean.getValue()));
            }
        }
        return choicesPollRealmList;
    }

    public RealmList<AnswerPoll> setAnswers(List<UserPollResponse.DataBean.QuestionsBean.AnswersBean> answers) {
        RealmList<AnswerPoll> answerPollRealmList = new RealmList<>();
        if(answers !=null && answers.size()>0) {
            for (UserPollResponse.DataBean.QuestionsBean.AnswersBean answersBean : answers) {
                answerPollRealmList.add(new AnswerPoll(answersBean.getId(), answersBean.getChoice_id(), answersBean.getValue(), answersBean.getScore(), answersBean.getDate(), false));
            }
        }
        return answerPollRealmList;
    }



}
