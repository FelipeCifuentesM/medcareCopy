package com.imed.medcare.ui.answer_questionnaire;


import com.imed.medcare.model.AnswerPoll;
import com.imed.medcare.model.TreatmentPoll;
import com.imed.medcare.model.TreatmentPollQuestion;

import io.realm.RealmList;
import io.realm.RealmResults;

public interface AnswerQuestionnaireContract {

    interface View{
        void showData(RealmResults<TreatmentPollQuestion> treatmentPollQuestionRealmResults);
        void showError(String message);
        //void showLoading(String message);
        //void hideLoading();
        void manageLoader();
        void successPostPollResponse();
        void notifyDataSetChangedAdapter();
        void deleteLocalValuesSuccess();
        void onVerifyLocalDataFound();
        void onVerifyLocalDataNotFound();
    }

    interface Presenter{
        void getData();
        void setNewAnswer(RealmList<AnswerPoll> answerPollRealmList, TreatmentPollQuestion treatmentPollQuestion, Integer idChoice, String value, String date);

        void postPollResponse(TreatmentPoll treatmentPoll);

        void deleteLocalValues(TreatmentPollQuestion treatmentPollQuestion);

        void verifyLocalData(TreatmentPollQuestion treatmentPollQuestion);
    }

    interface Interactor{
        void getData(InteractorOutputs outputs);
        void verifyLocalData(TreatmentPollQuestion treatmentPollQuestion, InteractorOutputs listener);
        void postPollResponse(TreatmentPoll treatmentPoll , InteractorOutputs listener);
        void setNewAnswer(RealmList<AnswerPoll> answerPollRealmList,TreatmentPollQuestion treatmentPollQuestion, Integer idChoice, String value,String date, InteractorOutputs listener);
        void deleteLocalValues(TreatmentPollQuestion treatmentPollQuestion, InteractorOutputs listener);

    }

    interface InteractorOutputs{
        void showData(RealmResults<TreatmentPollQuestion> treatmentPollQuestionRealmResults);
        void onSuccessPostPollResponse();
        void onErrorPostPollResponse(String message);
        void notifyDataSetChangedAdapter();
        void onSuccessDeleteLocalValues();
        void onVerifyLocalDataFound();
        void onVerifyLocalDataNotFound();
    }
}
