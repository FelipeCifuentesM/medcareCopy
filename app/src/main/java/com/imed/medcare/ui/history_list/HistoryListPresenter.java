package com.imed.medcare.ui.history_list;

import com.imed.medcare.network.response.HistoryResponse;

import java.util.List;

public class HistoryListPresenter implements HistoryListContract.Presenter, HistoryListContract.InteractorOutputs {

    private HistoryListContract.View view;
    private HistoryListContract.Interactor interactor;

    public HistoryListPresenter(HistoryListContract.View view){
        this.view = view;
        this.interactor = new HistoryListInteractor();
    }

    @Override
    public void getListHistory(boolean isFromAttachment) {
        view.manageLoader();
        interactor.getListHistory(isFromAttachment,this);
    }

    @Override
    public void showMessage(String message) {
        view.manageLoader();
        view.showMessage(message);
    }

    @Override
    public void showList(List<HistoryResponse.DataBean> historyResponseList) {
        view.manageLoader();
        view.showList(historyResponseList);
    }
}
