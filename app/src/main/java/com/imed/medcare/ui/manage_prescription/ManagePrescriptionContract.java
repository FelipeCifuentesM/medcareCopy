package com.imed.medcare.ui.manage_prescription;

import com.imed.medcare.model.Pillbox;

import io.realm.RealmList;


public interface ManagePrescriptionContract {

    interface View{
        void showMessage(String message);
        void setReason(String reason);
        void closeBottomSheet();
    }

    interface Presenter{
        RealmList<Pillbox> getListPillbox();
        void managePrescription(RealmList<Pillbox> pillboxRealmList, String date, String taken, String reason);
    }

    interface Interactor{
        RealmList<Pillbox> getListPillbox();
        void managePrescription(RealmList<Pillbox> pillboxRealmList, String date, String taken, String reason,onResultManagePrescription listener);
        interface onResultManagePrescription{
            void showResult(String message);
        }
    }


}
