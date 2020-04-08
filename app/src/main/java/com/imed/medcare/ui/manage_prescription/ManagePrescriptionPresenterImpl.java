package com.imed.medcare.ui.manage_prescription;

import com.imed.medcare.model.Pillbox;

import io.realm.RealmList;

public class ManagePrescriptionPresenterImpl implements ManagePrescriptionContract.Presenter,ManagePrescriptionContract.Interactor.onResultManagePrescription {

    ManagePrescriptionContract.View view;
    ManagePrescriptionContract.Interactor interactor;


    public ManagePrescriptionPresenterImpl(ManagePrescriptionContract.View view){
        this.view = view;
        this.interactor = new ManagePrescriptionInteractorImpl();
    }

    @Override
    public RealmList<Pillbox> getListPillbox() {
        return interactor.getListPillbox();
    }

    @Override
    public void managePrescription(RealmList<Pillbox> pillboxRealmList, String date,String taken, String reason) {
        interactor.managePrescription(pillboxRealmList,date,taken,reason,this);
    }

    @Override
    public void showResult(String message) {
        view.showMessage(message);
    }
}
