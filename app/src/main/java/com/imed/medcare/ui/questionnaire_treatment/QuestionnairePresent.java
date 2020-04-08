package com.imed.medcare.ui.questionnaire_treatment;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.AnswerPoll;
import com.imed.medcare.model.TreatmentPoll;
import com.imed.medcare.model.TreatmentPollQuestion;

import io.realm.RealmList;

public class QuestionnairePresent implements QuestionnaireContract.Presenter, QuestionnaireContract.Interactor.onGetValuesListener, QuestionnaireContract.Interactor.onPostPollResponseListener, QuestionnaireContract.Interactor.onSetNewAnswerListener, QuestionnaireContract.Interactor.onDeleteLocalValuesListener, QuestionnaireContract.Interactor.onVerifyLocalDataListener {

    QuestionnaireContract.View view;
    QuestionnaireContract.Interactor interactor;

    public QuestionnairePresent(QuestionnaireContract.View view){
        this.view = view;
        this.interactor = new QuestionnaireInteractor();
    }

    @Override
    public void getValues(int id) {
        interactor.getValues(id,this);
    }

    @Override
    public void setNewAnswer(RealmList<AnswerPoll> answerPollRealmList,TreatmentPollQuestion treatmentPollQuestion, Integer idChoice, String value,String date) {
        interactor.setNewAnswer(answerPollRealmList,treatmentPollQuestion,idChoice,value,date, this);

    }

    @Override
    public void postPollResponse(TreatmentPoll treatmentPoll) {
        //view.showLoading(App.getContext().getResources().getString(R.string.messageLoading));
        view.manageLoader();
        interactor.postPollResponse(treatmentPoll,this);
    }

    @Override
    public void deleteLocalValues(TreatmentPollQuestion treatmentPollQuestion) {
        interactor.deleteLocalValues(treatmentPollQuestion,this);
    }

    @Override
    public void verifyLocalData(TreatmentPollQuestion treatmentPollQuestion) {
        interactor.verifyLocalData(treatmentPollQuestion,this);
    }


    @Override
    public void onSuccess(TreatmentPoll treatmentPoll,RealmList<AnswerPoll> answerPollRealmList) {
        view.setValues(treatmentPoll, answerPollRealmList);
    }

    @Override
    public void onError(String message) {
        view.showError(message);
    }


    @Override
    public void onSuccessPostPollResponse() {
        //view.hideLoading();
        view.manageLoader();
        view.successPostPollResponse();
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
    public void onSuccessDeleteLocalValues() {
        view.deleteLocalValuesSuccess();
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
