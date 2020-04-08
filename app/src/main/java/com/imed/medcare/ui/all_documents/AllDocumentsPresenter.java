package com.imed.medcare.ui.all_documents;

import android.graphics.Bitmap;

import com.imed.medcare.model.Document;

import io.realm.RealmList;

public class AllDocumentsPresenter implements AllDocumentsContract.Presenter, AllDocumentsContract.Interactor.OnSetNewDocumentResult {
    AllDocumentsContract.View view;
    AllDocumentsContract.Interactor interactor;

    public AllDocumentsPresenter(AllDocumentsContract.View view){
        this.view = view;
        this.interactor = new AllDocumentsInteractor();
    }

    @Override
    public void setNewDocument(int idTreatment, Bitmap file, String name) {
        view.manageLoading();
        interactor.setNewDocument(idTreatment,file,name,this);
    }

    @Override
    public void OnErrorSetNewDocumentResult(String message) {
        view.manageLoading();
        view.showError(message);
    }

    @Override
    public void OnSuccessDocumentResult(RealmList<Document> documentRealmList) {
        view.manageLoading();
        view.updateNewDocument(documentRealmList);
    }
}
