package com.imed.medcare.ui.responded_polls;

import com.imed.medcare.network.response.RespondedPollResponse;

import java.util.List;

public interface RespondedPollsContract {

    interface View{
        void manageLoader();
        void showMessage(String message);
        void showPoll(List<RespondedPollResponse.DataBean> listRespondedPolls);
    }

    interface Presenter{
        void getPoll(int id);
    }

    interface Interactor{
        void getPoll(int id,OutputInteractor output);
        void removeHiddenPoll(List<RespondedPollResponse.DataBean> listRespondedPolls,OutputInteractor output);
    }

    interface OutputInteractor{
        void showMessage(String message);
        void ShowPoll(List<RespondedPollResponse.DataBean> listRespondedPolls);
        void removeHiddenPoll(List<RespondedPollResponse.DataBean> listRespondedPolls);
    }
}
