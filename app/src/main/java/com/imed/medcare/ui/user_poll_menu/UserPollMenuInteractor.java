package com.imed.medcare.ui.user_poll_menu;

import android.util.Log;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.User;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.network.RestClient;
import com.imed.medcare.network.response.UserPollResponse;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.imed.medcare.utils.MedcareUtils.ping;

public class UserPollMenuInteractor implements  UserPollMenuContract.userPollMenuInteractor {
    @Override
    public void getData(final onUserPollMenuListener listener) {

        GenericRepositoryRealm<User> userGenericRepository = new GenericRepositoryRealm<>(User.class);
        userGenericRepository.setRealm(Realm.getDefaultInstance());

        Call<UserPollResponse> getTreatment = RestClient.get().getUserPollsResponse(App.getContext().getString(R.string.API_KEY),userGenericRepository.getFirst().getAccessToken());
        getTreatment.enqueue(new Callback<UserPollResponse>() {
            @Override
            public void onResponse(Call<UserPollResponse> call, Response<UserPollResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        if(response.body().getData().size()>0) {
                            listener.onUserPollMenuSuccess(response.body().getData().get(0));
                        }else {
                            listener.onUserPollMenuError(App.getContext().getString(R.string.generic_error_message_userpoll));
                        }
                    } else {
                        if (response.body().getMessage() != null) {
                            listener.onUserPollMenuError(response.body().getMessage());
                        } else {
                            listener.onUserPollMenuError(App.getContext().getString(R.string.generic_error_message));
                        }
                    }
                }else{
                    listener.onUserPollMenuError(App.getContext().getString(R.string.generic_error_message));
                }
            }
            @Override
            public void onFailure(Call<UserPollResponse> call, Throwable t) {
                Log.e("POLLTREATMENT FAILURE", t.getLocalizedMessage() + " MESSAGE: " + t.getMessage() + " TO STRING: " + t.toString());
                listener.onUserPollMenuError(ping());

            }
        });
    }
}
