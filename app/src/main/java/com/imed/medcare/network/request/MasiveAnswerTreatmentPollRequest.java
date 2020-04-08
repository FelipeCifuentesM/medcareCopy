package com.imed.medcare.network.request;

import com.google.gson.annotations.SerializedName;
import com.imed.medcare.model.AnswerPoll;
import com.imed.medcare.model.TreatmentPollQuestion;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class MasiveAnswerTreatmentPollRequest {

    @SerializedName("answers")
    private List<AnswerTreatmentPollRequest> masiveAnswerUserPollRequestList;

    public MasiveAnswerTreatmentPollRequest(RealmList<TreatmentPollQuestion> treatmentPollQuestions){
        this.masiveAnswerUserPollRequestList = new ArrayList<>();
        for(TreatmentPollQuestion treatmentPollQuestion :treatmentPollQuestions){
            for(AnswerPoll answerPoll : treatmentPollQuestion.getAnswerPolls()) {
                masiveAnswerUserPollRequestList.add(new AnswerTreatmentPollRequest(treatmentPollQuestion.getId(), answerPoll.getChoiseId(), answerPoll.getValue(),answerPoll.getDate()));
            }
        }
    }

    public class AnswerTreatmentPollRequest {


        @SerializedName("question_id")
        private Integer questionId;
        @SerializedName("choice_id")
        private Integer choiceId;
        private String value;
        private String date;

        public AnswerTreatmentPollRequest(int questionId, Integer choiceId, String value,String date){

            this.questionId = questionId;

            if(choiceId == null) {
                this.choiceId = -1;
            }else {
                this.choiceId = choiceId;
            }
            if(value == null){
                this.value = "";
            }else {
                this.value = value;
            }
            if(date !=null){
                this.date = date;
            }else {
                this.date = "";
            }
        }
    }


}
