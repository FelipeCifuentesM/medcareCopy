package com.imed.medcare.network.request;

import com.google.gson.annotations.SerializedName;
import com.imed.medcare.model.AnswerPoll;
import com.imed.medcare.model.UserPollQuestion;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class MasiveAnswerUserPollRequest {

    @SerializedName("answers")
    private List<AnswerUserPollRequest> masiveAnswerUserPollRequestList;

    public MasiveAnswerUserPollRequest(RealmList<UserPollQuestion> userPollQuestions){
        this.masiveAnswerUserPollRequestList = new ArrayList<>();
        for(UserPollQuestion userPollQuestion :userPollQuestions){
            for(AnswerPoll answerPoll : userPollQuestion.getAnswerPolls()) {
                masiveAnswerUserPollRequestList.add(new AnswerUserPollRequest(userPollQuestion.getId(), answerPoll.getChoiseId(), answerPoll.getValue(),answerPoll.getDate()));
            }
        }
    }

    public class AnswerUserPollRequest {


        @SerializedName("question_id")
        private Integer questionId;
        @SerializedName("choice_id")
        private Integer choiceId;
        private String value;
        private String date;

        public AnswerUserPollRequest(int questionId, Integer choiceId, String value,String date){

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

