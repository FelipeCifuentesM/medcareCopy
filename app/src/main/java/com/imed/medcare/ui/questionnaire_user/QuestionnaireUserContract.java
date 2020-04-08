package com.imed.medcare.ui.questionnaire_user;

import com.imed.medcare.model.AnswerPoll;
import com.imed.medcare.model.UserPoll;
import com.imed.medcare.model.UserPollQuestion;

import io.realm.RealmList;

public interface QuestionnaireUserContract {

    interface View{
        void setValues(UserPoll userPoll,RealmList<AnswerPoll> answerPollRealmList);
        void showError(String message);
        //void showLoading(String message);
        //void hideLoading();
        void manageLoader();
        void successPostPollResponse(boolean allResponded);
        void notifyDataSetChangedAdapter();
        void onVerifyLocalDataFound();
        void onVerifyLocalDataNotFound();
    }

    interface Presenter{
        void getValues(int id);

        void setNewAnswer(RealmList<AnswerPoll> answerPollRealmList, UserPollQuestion userPollQuestion, Integer idChoice, String value, String date);

        void postPollResponse(UserPoll userPoll);

        void deleteLocalValues(UserPollQuestion userPollQuestion);

        void verifyLocalData(UserPollQuestion userPollQuestion);
    }

    interface Interactor{

        void verifyLocalData(UserPollQuestion userPollQuestion,onVerifyLocalDataListener listener);



        interface onVerifyLocalDataListener{
            void onVerifyLocalDataFound();
            void onVerifyLocalDataNotFound();
        }

        void deleteLocalValues(UserPollQuestion treatmentPollQuestion);


        void getValues(int id, onGetValuesListener listener);

        interface onGetValuesListener{
            void onSuccess(UserPoll treatmentPoll,RealmList<AnswerPoll> answerPollRealmList );
            void onError(String message);
        }

        void setNewAnswer(RealmList<AnswerPoll> answerPollRealmList,UserPollQuestion treatmentPollQuestion, Integer idChoice, String value,String date, onSetNewAnswerListener listener);
        interface onSetNewAnswerListener{
            void notifyDataSetChangedAdapter();
        }

        void postPollResponse(UserPoll treatmentPoll ,onPostPollResponseListener listener);

        interface onPostPollResponseListener{
            void onSuccessPostPollResponse(boolean allResponded);
            void onErrorPostPollResponse(String message);
        }
    }
}
