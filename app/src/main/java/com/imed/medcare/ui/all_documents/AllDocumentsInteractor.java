package com.imed.medcare.ui.all_documents;

import android.graphics.Bitmap;
import android.util.Log;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.Document;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.model.User;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.network.RestClient;
import com.imed.medcare.network.response.DocumentSaveResponse;
import com.imed.medcare.utils.MedcareUtils;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmList;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllDocumentsInteractor implements AllDocumentsContract.Interactor {


    @Override
    public void setNewDocument(final int idTreatment, Bitmap fileBitmap, String fileName, final OnSetNewDocumentResult listener) {
        File file = MedcareUtils.saveBitmap(fileBitmap);


        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);

        builder.addFormDataPart("name", fileName);
        builder.addFormDataPart("file",fileName,RequestBody.create(MediaType.parse("image/jpg"), file));
        MultipartBody requestBody = builder.build();

        GenericRepositoryRealm<User> userGenericRepositoryRealm = new GenericRepositoryRealm<>(User.class);
        userGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());

        Call<DocumentSaveResponse> postLoginUser = RestClient.get().saveDocumentResponse(idTreatment,requestBody.parts(), App.getContext().getString(R.string.API_KEY),userGenericRepositoryRealm.getFirst().getAccessToken());

        postLoginUser.enqueue(new Callback<DocumentSaveResponse>() {
            @Override
            public void onResponse(Call<DocumentSaveResponse> call, Response<DocumentSaveResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getStatus() !=null && response.body().getStatus().equals("ok")) {

                        Document document = new Document(response.body().getData());

                        GenericRepositoryRealm<Treatment> treatmentGenericRepositoryRealm = new GenericRepositoryRealm<>(Treatment.class);
                        treatmentGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());
                        Treatment treatment = treatmentGenericRepositoryRealm.get(idTreatment, "id");
                        RealmList<Document> documentRealmResults = treatment.getDocumentRealmList();

                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        documentRealmResults.add(document);
                        realm.commitTransaction();


                        getDocuments(documentRealmResults,idTreatment,listener);
                    } else {
                        if (response.body().getMessage() != null) {
                            listener.OnErrorSetNewDocumentResult(response.body().getMessage());
                        } else {
                            listener.OnErrorSetNewDocumentResult(App.getContext().getString(R.string.generic_error_message));
                        }
                    }
                }else{
                    listener.OnErrorSetNewDocumentResult(App.getContext().getString(R.string.generic_error_message));
                }
            }

            @Override
            public void onFailure(Call<DocumentSaveResponse> call, Throwable t) {
                Log.e("LOGIN FAILURE", t.getLocalizedMessage() + " MESSAGE: " + t.getMessage() + " TO STRING: " + t.toString());
                listener.OnErrorSetNewDocumentResult(App.getContext().getString(R.string.generic_error_message));

            }
        });
    }

    public void getDocuments(final RealmList<Document> documentRealmList, final int idTreatment, final OnSetNewDocumentResult listener) {
        GenericRepositoryRealm<User> userGenericRepositoryRealm  = new GenericRepositoryRealm<>(User.class);
        userGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());
        for (int i=0; i<documentRealmList.size() ; i++) {
            Call<String> getDocument = RestClient.get().getDocument(idTreatment, documentRealmList.get(i).getPath(), App.getContext().getString(R.string.API_KEY), userGenericRepositoryRealm.getFirst().getAccessToken());
            final int finalI = i;
            getDocument.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {

                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        documentRealmList.get(finalI).setPicture(response.body());
                        realm.commitTransaction();
                        if((finalI +1) == documentRealmList.size() || documentRealmList.size() == 0) {

                            GenericRepositoryRealm<Treatment>genericRepositoryRealmTreatment = new GenericRepositoryRealm<>(Treatment.class);
                            genericRepositoryRealmTreatment.setRealm(Realm.getDefaultInstance());
                            RealmList<Document> documentRealmList = new RealmList<>();
                            documentRealmList.addAll(genericRepositoryRealmTreatment.get(idTreatment,"id").getDocumentRealmList());
                            listener.OnSuccessDocumentResult(documentRealmList);
                        }
                    } else {
                        listener.OnErrorSetNewDocumentResult(App.getContext().getString(R.string.generic_error_message));
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e("Show Document", t.getLocalizedMessage() + " MESSAGE: " + t.getMessage() + " TO STRING: " + t.toString());
                    listener.OnErrorSetNewDocumentResult(App.getContext().getString(R.string.generic_error_message));

                }
            });
        }
    }

}
