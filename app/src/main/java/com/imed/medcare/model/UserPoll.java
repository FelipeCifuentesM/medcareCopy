package com.imed.medcare.model;

import com.imed.medcare.network.response.UserPollResponse;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserPoll extends RealmObject {

    @PrimaryKey
    int id;
    private int type;
    private String name;
    private String description;
    private String startAt;
    private String finishAt;
    private int pollPeriodId;
    private RealmList<UserPollQuestion> userPollQuestions;

    public UserPoll(){}

    public UserPoll(UserPollResponse.DataBean dataBean){

        this.id = dataBean.getId();
        this.type = dataBean.getType();
        this.name = dataBean.getName();
        this.description = dataBean.getDescription();
        this.startAt = dataBean.getStart();
        this.finishAt = dataBean.getEnd();
        this.userPollQuestions = getPollQuestion(dataBean.getQuestions());
        this.pollPeriodId = dataBean.getPoll_period_id();
    }

    private RealmList<UserPollQuestion> getPollQuestion(List<UserPollResponse.DataBean.QuestionsBean> questions) {
        RealmList<UserPollQuestion> treatmentPollQuestionRealmList = new RealmList<>();
        for(UserPollResponse.DataBean.QuestionsBean questionsBean : questions){
            treatmentPollQuestionRealmList.add(new UserPollQuestion(questionsBean.getId(), questionsBean.getMeasurement_id() ,questionsBean.getDescription(),questionsBean.getStatus(), questionsBean.getStatus(),questionsBean.getTitle(),questionsBean.getType(),questionsBean.getChoices(), questionsBean.getAnswers(),questionsBean.getMeasurement(), questionsBean.getQuestion_order()));
        }
        return  treatmentPollQuestionRealmList;
    }

    public int getId() {
        return id;
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

    public int getPollPeriodId() {
        return pollPeriodId;
    }

    public RealmList<UserPollQuestion> getuserPollQuestionsRealmList() {

        return userPollQuestions;
    }

    public static void saveToRealm(final UserPollResponse.DataBean response, Realm realm) {

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(mapUserPollData(response));
            }
        });
    }

    private static UserPoll mapUserPollData(UserPollResponse.DataBean response) {
        return new UserPoll(response);
    }
}
