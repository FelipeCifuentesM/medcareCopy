package com.imed.medcare.network.request;

import com.google.gson.annotations.SerializedName;

public class AnswerUserPollRequest {

    @SerializedName("question_id")
    private Integer questionId;
    @SerializedName("choice_id")
    private Integer choiceId;
    private String value;

    public AnswerUserPollRequest(int questionId, int choiceId, String value){
        this.choiceId = choiceId;
        this.questionId = questionId;
        this.value = value;
    }
}
