package com.imed.medcare.ui.questionnaire_treatment;

import com.imed.medcare.model.AnswerPoll;
import com.imed.medcare.model.TreatmentPoll;
import com.imed.medcare.model.TreatmentPollQuestion;

import io.realm.RealmList;

/**
 * Created by Ramiro on 08-06-2018.
 */

public interface QuestionnaireContract {

    interface View{
        void setValues(TreatmentPoll treatmentPoll,RealmList<AnswerPoll> answerPollRealmList);
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
        void getValues(int id);

        void setNewAnswer(RealmList<AnswerPoll> answerPollRealmList,TreatmentPollQuestion treatmentPollQuestion, Integer idChoice,String value,String date);

        void postPollResponse(TreatmentPoll treatmentPoll);

        void deleteLocalValues(TreatmentPollQuestion treatmentPollQuestion);

        void verifyLocalData(TreatmentPollQuestion treatmentPollQuestion);
    }

    interface Interactor{

        void verifyLocalData(TreatmentPollQuestion treatmentPollQuestion,onVerifyLocalDataListener listener);



        interface onVerifyLocalDataListener{
            void onVerifyLocalDataFound();
            void onVerifyLocalDataNotFound();
        }

        void deleteLocalValues(TreatmentPollQuestion treatmentPollQuestion, onDeleteLocalValuesListener listener);
        interface onDeleteLocalValuesListener{
            void onSuccessDeleteLocalValues();
        }

        void getValues(int id, onGetValuesListener listener);

        interface onGetValuesListener{
            void onSuccess(TreatmentPoll treatmentPoll,RealmList<AnswerPoll> answerPollRealmList );
            void onError(String message);
        }

        void setNewAnswer(RealmList<AnswerPoll> answerPollRealmList,TreatmentPollQuestion treatmentPollQuestion, Integer idChoice, String value,String date, onSetNewAnswerListener listener);
        interface onSetNewAnswerListener{
            void notifyDataSetChangedAdapter();
        }

        void postPollResponse(TreatmentPoll treatmentPoll ,onPostPollResponseListener listener);

        interface onPostPollResponseListener{
            void onSuccessPostPollResponse();
            void onErrorPostPollResponse(String message);
        }
    }
}
