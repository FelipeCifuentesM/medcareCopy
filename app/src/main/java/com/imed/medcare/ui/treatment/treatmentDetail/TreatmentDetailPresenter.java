package com.imed.medcare.ui.treatment.treatmentDetail;

import com.imed.medcare.model.Treatment;
import com.imed.medcare.model.repository.GenericRepositoryRealm;

import io.realm.Realm;

/**
 * Created by Ramiro on 06-06-2018.
 */

public class TreatmentDetailPresenter implements TreatmentDetailContract.Presenter{
    TreatmentDetailContract.View view;

    public TreatmentDetailPresenter (TreatmentDetailContract.View view){
        this.view = view;
    }

    @Override
    public void getTreatment(int id) {
        GenericRepositoryRealm<Treatment> treatmentGenericRepositoryRealm = new GenericRepositoryRealm<>(Treatment.class);
        treatmentGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());
        view.setTreatment(treatmentGenericRepositoryRealm.get(id,"id"));
    }
}
