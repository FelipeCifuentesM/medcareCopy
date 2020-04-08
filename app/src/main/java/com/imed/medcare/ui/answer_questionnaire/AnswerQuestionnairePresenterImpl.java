package com.imed.medcare.ui.answer_questionnaire;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.AnswerPoll;
import com.imed.medcare.model.TreatmentPoll;
import com.imed.medcare.model.TreatmentPollQuestion;

import io.realm.RealmList;
import io.realm.RealmResults;

public class AnswerQuestionnairePresenterImpl  implements AnswerQuestionnaireContract.Presenter, AnswerQuestionnaireContract.InteractorOutputs{

    private AnswerQuestionnaireContract.View view;
    private AnswerQuestionnaireContract.Interactor interactor;

    public AnswerQuestionnairePresenterImpl(AnswerQuestionnaireContract.View view){
        this.view = view;
        interactor = new AnswerQuestionnaireInteractorImpl();
    }

    @Override
    public void getData() {
        interactor.getData(this);
    }

    @Override
    public void showData(RealmResults<TreatmentPollQuestion> treatmentPollQuestionRealmResults) {
        view.showData(treatmentPollQuestionRealmResults);
    }

    @Override
    public void setNewAnswer(RealmList<AnswerPoll> answerPollRealmList, TreatmentPollQuestion treatmentPollQuestion, Integer idChoice, String value, String date) {
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
