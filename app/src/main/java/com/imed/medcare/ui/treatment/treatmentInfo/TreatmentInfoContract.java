package com.imed.medcare.ui.treatment.treatmentInfo;

import android.graphics.Bitmap;

import com.imed.medcare.model.Document;
import com.imed.medcare.model.Medicine;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.model.TreatmentPoll;

import io.realm.RealmList;

/**
 * Created by Ramiro on 25-05-2018.
 */

public interface TreatmentInfoContract {

    interface View{

        void addDocument();
        void setDocuments(RealmList<Document> documentRealmList);
        void setMedicines(RealmList<Medicine> medicineRealmList, Treatment treatment);
        void setTreatmentPoll(TreatmentPoll treatmentPoll);
        void showError(String message);
    }

    interface Presenter{
        void getDocuments(int idTreatment);
        void getMedicines(int idTreatment);
        void getTreatmentPoll(int treatmentPollId);
        String getAmountAnswered(int idTreatment);
        void setNewDocument(int idTreatment, Bitmap file, String name);
    }






    interface Interactor{

        void getDocuments(RealmList<Document> documentRealmList, int idTreatment,TreatmentInfoContract.Interactor.OnDocumentResult listener);
        String getAmountAnswered(int treatmentId);
        interface OnDocumentResult{
            void OnSuccessDocumentResult(int idTreatment);
            void OnErrorDocumentResult(String message);
        }
        void setNewDocument(int idTreatment, Bitmap file, String name, OnSetNewDocumentResult listener, TreatmentInfoContract.Interactor.OnDocumentResult listenerGetDocument);
        interface OnSetNewDocumentResult{
            void OnErrorSetNewDocumentResult(String message);
        }
    }
}
