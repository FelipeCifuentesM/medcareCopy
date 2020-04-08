package com.imed.medcare.ui.treatment.treatmentInfo;


import android.graphics.Bitmap;

import com.imed.medcare.model.Document;
import com.imed.medcare.model.Medicine;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.model.TreatmentPoll;
import com.imed.medcare.model.repository.GenericRepositoryRealm;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Ramiro on 25-05-2018.
 */

public class TreatmentInfoPresenter implements TreatmentInfoContract.Presenter, TreatmentInfoContract.Interactor.OnDocumentResult, TreatmentInfoContract.Interactor.OnSetNewDocumentResult {

    TreatmentInfoContract.View view;
    TreatmentInfoContract.Interactor interactor;
    public TreatmentInfoPresenter(TreatmentInfoContract.View view){
        this.view = view;
        this.interactor = new TreatmentInfoInteractor();
    }

    @Override
    public void getDocuments(int id) {
        GenericRepositoryRealm<Treatment>genericRepositoryRealmTreatment = new GenericRepositoryRealm<>(Treatment.class);
        genericRepositoryRealmTreatment.setRealm(Realm.getDefaultInstance());
        if(genericRepositoryRealmTreatment.get(id, "id").getDocumentRealmList().size()>0) {
            interactor.getDocuments(genericRepositoryRealmTreatment.get(id, "id").getDocumentRealmList(), id, this);
        }else {
            OnSuccessDocumentResult(id);
        }

    }

    @Override
    public void getMedicines(int idTreatment) {
        GenericRepositoryRealm<Treatment>genericRepositoryRealmTreatment = new GenericRepositoryRealm<>(Treatment.class);
        genericRepositoryRealmTreatment.setRealm(Realm.getDefaultInstance());
        RealmList<Medicine> medicineRealmList = new RealmList<>();
        medicineRealmList.addAll(genericRepositoryRealmTreatment.get(idTreatment,"id").getMedicines());
        view.setMedicines(medicineRealmList,genericRepositoryRealmTreatment.getFirst());
    }

    @Override
    public void getTreatmentPoll(int treatmentPollId) {

        GenericRepositoryRealm<TreatmentPoll>genericRepositoryRealmTreatmentPoll = new GenericRepositoryRealm<>(TreatmentPoll.class);
        genericRepositoryRealmTreatmentPoll.setRealm(Realm.getDefaultInstance());
        view.setTreatmentPoll(genericRepositoryRealmTreatmentPoll.get(treatmentPollId,"id"));
    }

    @Override
    public String getAmountAnswered(int treatmentId) {
        return  interactor.getAmountAnswered(treatmentId);
    }

    @Override
    public void setNewDocument(int idTreatment, Bitmap file, String name) {
        interactor.setNewDocument(idTreatment,file,name,this,this);
    }

    @Override
    public void OnSuccessDocumentResult(int idTreatment) {
        GenericRepositoryRealm<Treatment>genericRepositoryRealmTreatment = new GenericRepositoryRealm<>(Treatment.class);
        genericRepositoryRealmTreatment.setRealm(Realm.getDefaultInstance());
        RealmList<Document> documentRealmList = new RealmList<>();
        documentRealmList.add(new Document(-1));
        documentRealmList.addAll(genericRepositoryRealmTreatment.get(idTreatment,"id").getDocumentRealmList());
        view.setDocuments(documentRealmList);
    }

    @Override
    public void OnErrorDocumentResult(String message) {
        view.showError(message);
    }

    @Override
    public void OnErrorSetNewDocumentResult(String message) {
        view.showError(message);
    }
}
