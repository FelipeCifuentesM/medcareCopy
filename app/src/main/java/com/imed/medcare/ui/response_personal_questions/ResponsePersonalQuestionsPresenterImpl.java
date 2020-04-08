package com.imed.medcare.ui.response_personal_questions;

import com.imed.medcare.model.PersonalProfile;

public class ResponsePersonalQuestionsPresenterImpl implements ResponsePersonalQuestionsContract.Presenter, ResponsePersonalQuestionsContract.Interactor.onSetDataListener, ResponsePersonalQuestionsContract.Interactor.onSetAnswerListener  {

    private ResponsePersonalQuestionsContract.View view;
    private ResponsePersonalQuestionsContract.Interactor interactor;

    public ResponsePersonalQuestionsPresenterImpl(ResponsePersonalQuestionsContract.View view){
        this.view = view;
        this.interactor = new ResponsePersonalQuestionsInteractorImpl();
    }

    @Override
    public PersonalProfile getData(int id) {
        return interactor.getData(id);
    }

    @Override
    public void setData(PersonalProfile personalProfile, int typeView) {
        view.manageLoader();
        interactor.setData(personalProfile,typeView,this);
    }

    @Override
    public void setAnswer(String value, String choiseId, PersonalProfile personalProfile) {
        interactor.setAnswer(value,choiseId,personalProfile,this);
    }

    @Override
    public void showResult(String message) {
        view.manageLoader();
        view.showResult(message);
    }

    @Override
    public void notifyDataSetChangedAdapter() {
        view.notifyDataSetChangedAdapter();
    }
}
