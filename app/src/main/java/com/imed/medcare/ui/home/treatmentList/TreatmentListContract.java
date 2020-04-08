package com.imed.medcare.ui.home.treatmentList;

import com.imed.medcare.model.Professional;
import com.imed.medcare.model.Treatment;

import io.realm.RealmList;

/**
 * Created by Ramiro on 23-05-2018.
 */

public interface TreatmentListContract {

    interface View{

        void showTreatment(int position);
        void showTreatments(RealmList<Treatment> treatments, int professionalSize);
        void showInvitations(RealmList<Professional> professionalRealmList);
        //void showLoader();
        void manageLoader();
        void showStatus(String message);
        //void dismissLoader();
    }

    interface Presenter{
        void getDataView();
        void acceptInvitation(Professional professional);
    }

    interface Interactor{

        void acceptInvitation(Professional professional, AcceptInvitationListener listener);

        interface AcceptInvitationListener{
            void sendStatus(String message);
        }
    }
}
