package com.imed.medcare.ui.all_documents;

import android.graphics.Bitmap;

import com.imed.medcare.model.Document;

import io.realm.RealmList;

public interface AllDocumentsContract {

    interface View{
        void showError(String message);
        void manageLoading();
        void updateNewDocument(RealmList<Document> documentRealmList);
        void closeBottomSheet();
        void setNewDocument(String name);
    }

    interface Presenter{
        void setNewDocument(int idTreatment, Bitmap file, String name);
    }

    interface Interactor{

        void setNewDocument(int idTreatment, Bitmap file, String name, OnSetNewDocumentResult listener);
        interface OnSetNewDocumentResult{
            void OnErrorSetNewDocumentResult(String message);
            void OnSuccessDocumentResult(RealmList<Document> documentRealmList);
        }
    }

}
