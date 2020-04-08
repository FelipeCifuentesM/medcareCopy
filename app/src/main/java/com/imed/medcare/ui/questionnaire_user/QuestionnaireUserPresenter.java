package com.imed.medcare.ui.questionnaire_user;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.AnswerPoll;
import com.imed.medcare.model.UserPoll;
import com.imed.medcare.model.UserPollQuestion;

import io.realm.RealmList;

public class QuestionnaireUserPresenter implements QuestionnaireUserContract.Presenter, QuestionnaireUserContract.Interactor.onGetValuesListener, QuestionnaireUserContract.Interactor.onPostPollResponseListener, QuestionnaireUserContract.Interactor.onSetNewAnswerListener, QuestionnaireUserContract.Interactor.onVerifyLocalDataListener {

    private QuestionnaireUserContract.View view;
    private QuestionnaireUserContract.Interactor interactor;

    public QuestionnaireUserPresenter(QuestionnaireUserContract.View view){
        this.view = view;
        this.interactor = new QuestionnaireUserInteractor();
    }

    @Override
    public void getValues(int id) {
        interactor.getValues(id,this);
    }

    @Override
    public void setNewAnswer(RealmList<AnswerPoll> answerPollRealmList, UserPollQuestion UserPollQuestion, Integer idChoice, String value, String date) {
        interactor.setNewAnswer(answerPollRealmList,UserPollQuestion,idChoice,value,date, this);

    }

    @Override
    public void postPollResponse(UserPoll UserPoll) {
        //view.showLoading(App.getContext().getResources().getString(R.string.messageLoading));
        view.manageLoader();
        interactor.postPollResponse(UserPoll,this);
    }

    @Override
    public void deleteLocalValues(UserPollQuestion UserPollQuestion) {
        interactor.deleteLocalValues(UserPollQuestion);
    }

    @Override
    public void verifyLocalData(UserPollQuestion UserPollQuestion) {
        interactor.verifyLocalData(UserPollQuestion,this);
    }




    @Override
    public void onSuccess(UserPoll UserPoll,RealmList<AnswerPoll> answerPollRealmList) {
        view.setValues(UserPoll, answerPollRealmList);
    }

    @Override
    public void onError(String message) {
        view.showError(message);
    }


    @Override
    public void onSuccessPostPollResponse(boolean allResponded) {
        //view.hideLoading();
        view.manageLoader();
        view.successPostPollResponse(allResponded);
    }

    @Override
    public void onErrorPostPollResponse(String message) {
        //view.hideLoading();
        view.manageLoader();
        view.showError(message);
    }

    @Override
    public void notifyDataSetChangedAdapter() {
        view.notifyDataSetChangedAdapter();
    }


    @Override
    public void onVerifyLocalDataFound() {
        view.onVerifyLocalDataFound();
    }

    @Override
    public void onVerifyLocalDataNotFound() {
        view.onVerifyLocalDataNotFound();
    }


}
