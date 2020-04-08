package com.imed.medcare.ui.history_list;

import com.imed.medcare.network.response.HistoryResponse;

import java.util.List;

public interface HistoryListContract {

    interface View{
        void manageLoader();
        void showMessage(String message);
        void showList(List<HistoryResponse.DataBean> list);
    }

    interface Presenter{
        void getListHistory(boolean isFromAttachment);

    }

    interface Interactor{
        void getListHistory(boolean isFromAttachment,InteractorOutputs outputs);
    }

    interface InteractorOutputs{
        void showMessage(String message);
        void showList(List<HistoryResponse.DataBean> historyResponseList);
    }
}
