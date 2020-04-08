package com.imed.medcare.ui.treatment;

import com.imed.medcare.model.TreatmentPoll;
import com.imed.medcare.network.response.TreatmentPollResponse;

import io.realm.Realm;

/**
 * Created by Ramiro on 24-05-2018.
 */

public class TreatmentPresent implements TreatmentContract.Presenter, TreatmentContract.Interactor.onPollsResultListener{

    private TreatmentContract.View treatmentView;
    private TreatmentContract.Interactor treatmentInteractor;

    public TreatmentPresent(TreatmentContract.View treatmentActivity) {
        this.treatmentView = treatmentActivity;
        this.treatmentInteractor = new TreatmentInteractor();
    }

    @Override
    public void getPolls(int idTreatment) {
        //treatmentView.showLoading();
        treatmentView.manageLoader();
        treatmentInteractor.getPolls(idTreatment,this);

    }

    @Override
    public void onSuccessPolls(int idTreatment,TreatmentPollResponse.DataBean dataBeanList) {
        if(dataBeanList != null) {
            TreatmentPoll.saveToRealm(idTreatment,dataBeanList, Realm.getDefaultInstance());
            treatmentView.successPolls(dataBeanList.getId());
        }else {
            treatmentView.successPolls(-1);
        }
        treatmentView.manageLoader();
    }

    @Override
    public void onErrorPolls(String message) {
        treatmentView.showError(message);
        treatmentView.manageLoader();
    }
}
