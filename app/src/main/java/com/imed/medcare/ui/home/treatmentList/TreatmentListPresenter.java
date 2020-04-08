package com.imed.medcare.ui.home.treatmentList;

import com.imed.medcare.model.Professional;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.model.repository.GenericRepositoryRealm;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Ramiro on 23-05-2018.
 */

public class TreatmentListPresenter implements TreatmentListContract.Presenter, TreatmentListContract.Interactor.AcceptInvitationListener {

    private TreatmentListContract.View treatmentListView;
    private TreatmentListContract.Interactor treatmentInteractor;

    public TreatmentListPresenter(TreatmentListContract.View treatmentFragment) {
        this.treatmentListView = treatmentFragment;
        this.treatmentInteractor = new TreatmentListInteractor(this);
    }

    @Override
    public void getDataView() {
        GenericRepositoryRealm<Treatment>genericRepositoryRealmTreatment = new GenericRepositoryRealm<>(Treatment.class);
        genericRepositoryRealmTreatment.setRealm(Realm.getDefaultInstance());
        RealmList<Treatment> treatmentRealmList = new RealmList<>();
        treatmentRealmList.addAll(genericRepositoryRealmTreatment.getAll());



        Realm realm = Realm.getDefaultInstance();
        RealmResults<Professional> professionalRealmResults = realm.where(Professional.class)
                .equalTo("status", 1)
                .or()
                .equalTo("status", 2)
                .findAll();

        RealmResults<Treatment> treatmentRealmResults = realm.where(Treatment.class).findAll();
        RealmList<Professional> professionalRealmList = new RealmList<>();
        professionalRealmList.add(new Professional());
        if(treatmentRealmResults.size()>0) {
            for (Treatment treatment : treatmentRealmResults) {
                for (Professional professional : professionalRealmResults) {
                    if (treatment.getProfessional().getId() != professional.getId()) {
                        professionalRealmList.add(professional);
                    }
                }
            }
        }else{
            professionalRealmList.addAll(professionalRealmResults);
        }

        treatmentListView.showTreatments(treatmentRealmList,professionalRealmList.size());
        treatmentListView.showInvitations(professionalRealmList);
    }

    @Override
    public void acceptInvitation(Professional professional) {
        //treatmentListView.showLoader();
        treatmentListView.manageLoader();
        treatmentInteractor.acceptInvitation(professional,this);
    }

    @Override
    public void sendStatus(String message) {
        //treatmentListView.dismissLoader();
        treatmentListView.manageLoader();
        treatmentListView.showStatus(message);
    }
}
