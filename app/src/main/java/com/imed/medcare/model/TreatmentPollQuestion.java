package com.imed.medcare.model;

import com.imed.medcare.network.response.TreatmentPollResponse;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

import static com.imed.medcare.utils.MedcareUtils.dateInfoTreatment;

public class TreatmentPollQuestion extends RealmObject {

    @PrimaryKey
    int id;
    private int score;
    private String description;
    private int status;
    private String title;
    private int type;
    private boolean show;

    private RealmList<ChoicesPoll> choices;
    private RealmList<AnswerPoll> answerPolls;
    private MeasurementPoll measuremtPoll;
    private String lastDate;
    private String lastAnswer;
    private Integer questionOrder;

    public TreatmentPollQuestion(){}

    public TreatmentPollQuestion(int id, boolean show, String description, int score, int status, String title, int type, List<TreatmentPollResponse.DataBean.QuestionsBean.ChoicesBean> choicesBeans, List<TreatmentPollResponse.DataBean.QuestionsBean.AnswersBean> answersBeans, TreatmentPollResponse.DataBean.QuestionsBean.MeasurementBean measurementBean, List<TreatmentPollResponse.DataBean.QuestionsBean.LastAnswersBean> lastAnswersBeans, Integer measurementId, Integer questionOrder) {
        this.id = id;
        this.description = description;
        this.score = score;
        this.status = status;
        this.title = title;
        this.type = type;
        this.choices = setChoices(choicesBeans);
        this.answerPolls = setAnswers(answersBeans);
        this.measuremtPoll = setMeasurements(measurementBean);
        this.show = show;
        this.questionOrder = questionOrder;
        if(lastAnswersBeans != null && lastAnswersBeans.size()>0){
            lastDate = getLastDate(lastAnswersBeans.get(0).getDate());
            if(measurementId != null && lastAnswersBeans.get(0).getValue() !=null && measuremtPoll.getUnit() !=null && !measuremtPoll.getUnit().equalsIgnoreCase("-") ) {
                lastAnswer = measuremtPoll.getName()+": "+lastAnswersBeans.get(0).getValue()+measuremtPoll.getUnit();
            }else {
                lastAnswer = "";
            }
        }else {
            lastDate = "";
            lastAnswer = "";
        }
    }
    private String getLastDate(String lastDate){
        String date = dateInfoTreatment(lastDate+ " 00:00:00");
        return "Último control: "+date;
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

    public boolean isShow() {
        return show;
    }

    public String getLastDate() {
        return lastDate;
    }

    public String getLastAnswer() {
        return lastAnswer;
    }

    public Integer getQuestionOrder() { return questionOrder; }

    public int getType() {
        return type;
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
    public void setAnswerPolls(RealmList<AnswerPoll> answerPolls) {
        this.answerPolls = answerPolls;
    }

    private RealmList<ChoicesPoll> setChoices(List<TreatmentPollResponse.DataBean.QuestionsBean.ChoicesBean> choices) {
        RealmList<ChoicesPoll> choicesPollRealmList= new RealmList<>();
        if(choices !=null) {
            for (TreatmentPollResponse.DataBean.QuestionsBean.ChoicesBean choicesBean : choices) {
                choicesPollRealmList.add(new ChoicesPoll(choicesBean.getId(), choicesBean.getValue()));
            }
        }
        return choicesPollRealmList;
    }

    private RealmList<AnswerPoll> setAnswers(List<TreatmentPollResponse.DataBean.QuestionsBean.AnswersBean> answers) {

        RealmList<AnswerPoll> answerPollRealmList = new RealmList<>();
        if(answers !=null) {

            for (TreatmentPollResponse.DataBean.QuestionsBean.AnswersBean answersBean : answers) {
                answerPollRealmList.add(new AnswerPoll(answersBean.getId(),answersBean.getChoice_id(), answersBean.getValue(), answersBean.getScore(),answersBean.getDate(),false));
            }
        }
        return answerPollRealmList;
    }


    private MeasurementPoll setMeasurements(TreatmentPollResponse.DataBean.QuestionsBean.MeasurementBean measurementBean) {
        MeasurementPoll measurementPoll = new MeasurementPoll();
        if(measurementBean !=null){
            measurementPoll = new MeasurementPoll(measurementBean.getId(),measurementBean.getName(),measurementBean.getUnit(),measurementBean.getRequest_date());
        }
        return measurementPoll;
    }
}
