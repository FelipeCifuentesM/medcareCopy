package com.imed.medcare.ui.treatment;

import com.imed.medcare.model.Document;
import com.imed.medcare.network.response.TreatmentPollResponse;

/**
 * Created by Ramiro on 24-05-2018.
 */

public interface TreatmentContract {

    interface View{

        void backPressed();
        void showDetail();
        void showPollTreatment();
        void showPollMedic();
        void seeAllDocuments(int idTreatment);
        void showHistorical();
        void showDocument(Document document);
        void showLoading();
        void showError(String message);
        void successPolls(int idTreatmentPoll);
        void manageLoader();
    }

    interface Presenter{
        void getPolls(int idTreatment);
    }

    interface Interactor{
        void getPolls(int idTreatment, onPollsResultListener listener);

        interface onPollsResultListener{
            void onSuccessPolls(int idTreatment,TreatmentPollResponse.DataBean dataBeanList );
            void onErrorPolls(String message);
        }
    }
}
