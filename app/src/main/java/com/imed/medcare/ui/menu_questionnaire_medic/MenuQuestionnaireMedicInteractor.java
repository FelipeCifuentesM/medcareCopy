package com.imed.medcare.ui.menu_questionnaire_medic;

import android.util.Log;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.MedicPollQuestion;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.model.User;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.network.RestClient;
import com.imed.medcare.network.response.MedicPollResponse;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuQuestionnaireMedicInteractor implements  MenuQuestionnaireMedicContract.Interactor{
    @Override
    public void getQuestionnaireMedic(final int idTreatment, final MenuQuestionnaireMedicListener listener) {
        
        GenericRepositoryRealm<User> userGenericRepositoryRealm = new GenericRepositoryRealm<>(User.class);
        userGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());

        Call<MedicPollResponse> postLoginUser = RestClient.get().getQuestionaryResponse(idTreatment,App.getContext().getString(R.string.API_KEY),userGenericRepositoryRealm.getFirst().getAccessToken());
        postLoginUser.enqueue(new Callback<MedicPollResponse>() {
            @Override
            public void onResponse(Call<MedicPollResponse> call, Response<MedicPollResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("ok") && response.body().getData()!=null) {
                        MedicPollQuestion.saveToRealm(response.body(),Realm.getDefaultInstance());
                        GenericRepositoryRealm<MedicPollQuestion> medicPollQuestionGenericRepositoryRealm = new GenericRepositoryRealm<>(MedicPollQuestion.class);
                        medicPollQuestionGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());
                        GenericRepositoryRealm<Treatment> treatmentGenericRepositoryRealm = new GenericRepositoryRealm<>(Treatment.class);
                        treatmentGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());
                        listener.success(medicPollQuestionGenericRepositoryRealm.getAll(), treatmentGenericRepositoryRealm.get(idTreatment,"id"));
                    } else {
                        if (response.body().getMessage() != null) {
                            listener.error(response.body().getMessage());
                        } else {
                            listener.error(App.getContext().getString(R.string.generic_error_message));
                        }
                    }
                }else{
                    listener.error(App.getContext().getString(R.string.generic_error_message));
                }
            }

            @Override
            public void onFailure(Call<MedicPollResponse> call, Throwable t) {
                Log.e("POST POLLTREATMENT FAIL", t.getLocalizedMessage() + " MESSAGE: " + t.getMessage() + " TO STRING: " + t.toString());
                listener.error(App.getContext().getString(R.string.generic_error_message));

            }
        });
    }
}
