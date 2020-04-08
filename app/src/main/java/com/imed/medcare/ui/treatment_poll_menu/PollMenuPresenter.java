package com.imed.medcare.ui.treatment_poll_menu;

import com.imed.medcare.model.TreatmentPoll;
import com.imed.medcare.model.TreatmentPollQuestion;

import io.realm.RealmResults;

public class PollMenuPresenter implements PollMenuContract.PollMenuPresenter, PollMenuContract.PollMenuInteractor.PollMenuListener {
    PollMenuContract.PollMenuView view;
    PollMenuContract.PollMenuInteractor interactor;

    public PollMenuPresenter (PollMenuContract.PollMenuView view){
        this.view = view;
        this.interactor = new PollMenuInteractor();

    }

    @Override
    public void getData(int id) {
        interactor.getData(id,this);
    }

    @Override
    public void onSuccess(TreatmentPoll treatmentPoll, RealmResults<TreatmentPollQuestion> treatmentPollQuestionRealmList) {
        view.showData(treatmentPoll, treatmentPollQuestionRealmList);
    }
}
