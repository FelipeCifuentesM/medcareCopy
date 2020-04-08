package com.imed.medcare.ui.responded_polls;

import com.imed.medcare.network.response.RespondedPollResponse;

import java.util.List;

public class RespondedPollsPresenter implements  RespondedPollsContract.Presenter, RespondedPollsContract.OutputInteractor{

    private RespondedPollsContract.View view;
    private RespondedPollsContract.Interactor interactor;

    public RespondedPollsPresenter (RespondedPollsContract.View view){
        this.view = view;
        this.interactor = new RespondendPollsInteractor();
    }

    @Override
    public void getPoll(int id) {
        view.manageLoader();
        interactor.getPoll(id,this);
    }

    @Override
    public void showMessage(String message) {
        view.manageLoader();
        view.showMessage(message);
    }

    @Override
    public void ShowPoll(List<RespondedPollResponse.DataBean> listRespondedPolls) {
        view.manageLoader();
        view.showPoll(listRespondedPolls);
    }

    @Override
    public void removeHiddenPoll(List<RespondedPollResponse.DataBean> listRespondedPolls) {
        interactor.removeHiddenPoll(listRespondedPolls,this);
    }
}
