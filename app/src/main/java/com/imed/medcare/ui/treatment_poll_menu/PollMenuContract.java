package com.imed.medcare.ui.treatment_poll_menu;

import com.imed.medcare.model.TreatmentPoll;
import com.imed.medcare.model.TreatmentPollQuestion;

import io.realm.RealmResults;

public interface PollMenuContract {

    interface PollMenuView{
        void showData(TreatmentPoll treatmentPoll, RealmResults<TreatmentPollQuestion> treatmentPollQuestionRealmlist);
    }

    interface PollMenuPresenter{
        void getData(int id);
    }

    interface PollMenuInteractor{
        void getData(int id, PollMenuListener listener);

        interface PollMenuListener{
            void onSuccess(TreatmentPoll treatmentPoll, RealmResults<TreatmentPollQuestion> treatmentPollQuestionRealmList);
        }
    }
}
