package com.imed.medcare.ui.treatment;

import android.util.Log;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.User;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.network.RestClient;
import com.imed.medcare.network.response.TreatmentPollResponse;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.imed.medcare.utils.MedcareUtils.ping;

public class TreatmentInteractor implements TreatmentContract.Interactor{
    @Override
    public void getPolls(final int idTreatment, final TreatmentContract.Interactor.onPollsResultListener listener) {

        GenericRepositoryRealm<User> userGenericRepository = new GenericRepositoryRealm<>(User.class);
        userGenericRepository.setRealm(Realm.getDefaultInstance());

        Call<TreatmentPollResponse> getTreatment = RestClient.get().getTreatmentPollsResponse(idTreatment,App.getContext().getString(R.string.API_KEY),userGenericRepository.getFirst().getAccessToken());
        getTreatment.enqueue(new Callback<TreatmentPollResponse>() {
            @Override
            public void onResponse(Call<TreatmentPollResponse> call, Response<TreatmentPollResponse> response) {

                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        if(response.body().getData().size()>0) {
                            listener.onSuccessPolls(idTreatment, response.body().getData().get(0));
                        }else {
                            listener.onSuccessPolls(idTreatment, null);
                        }
                    } else {
                        if (response.body().getMessage() != null) {
                            listener.onErrorPolls(response.body().getMessage());
                        } else {
                            listener.onErrorPolls(App.getContext().getString(R.string.generic_error_message));
                        }
                    }
                }else{
                    listener.onErrorPolls(App.getContext().getString(R.string.generic_error_message));
                }
            }
            @Override
            public void onFailure(Call<TreatmentPollResponse> call, Throwable t) {
                Log.e("POLLTREATMENT FAILURE", t.getLocalizedMessage() + " MESSAGE: " + t.getMessage() + " TO STRING: " + t.toString());
                listener.onErrorPolls(ping());

            }
        });
    }
}
