package com.imed.medcare.ui.personal_profile;


import android.util.Log;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.PersonalProfile;
import com.imed.medcare.model.User;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.network.RestClientLogin;
import com.imed.medcare.network.response.PersonalProfileResponse;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.imed.medcare.utils.MedcareUtils.ping;

public class PersonalProfileInteractor implements PersonalProfileContract.Interactor {


    @Override
    public void getProfilePersonal(final onResult listener, final int type) {

        GenericRepositoryRealm<User> userGenericRepositoryRealm = new GenericRepositoryRealm<>(User.class);
        userGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());
        final User user = userGenericRepositoryRealm.getFirst();
        Call<PersonalProfileResponse> postLoginUser;
        switch (type){
            case 1:
                postLoginUser = RestClientLogin.get().getProfilePersonal(App.getContext().getString(R.string.API_KEY), user.getAccessToken());
                break;
            case 3:
                postLoginUser = RestClientLogin.get().getPersonalHabits(App.getContext().getString(R.string.API_KEY), user.getAccessToken());
                break;
            default:
                postLoginUser = RestClientLogin.get().getPersonalMedics(App.getContext().getString(R.string.API_KEY), user.getAccessToken());
                break;
        }


        postLoginUser.enqueue(new Callback<PersonalProfileResponse>() {
            @Override
            public void onResponse(Call<PersonalProfileResponse> call, Response<PersonalProfileResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("success") ) {
                        Realm realm = Realm.getDefaultInstance();
                        GenericRepositoryRealm<PersonalProfile> genericRepositoryRealm = new GenericRepositoryRealm<>(PersonalProfile.class);
                        genericRepositoryRealm.setRealm(realm);
                        if(genericRepositoryRealm.getFirst() != null){
                            realm.beginTransaction();
                            genericRepositoryRealm.getAll().deleteAllFromRealm();
                            realm.commitTransaction();
                        }
                        PersonalProfile.saveToRealm(response.body(),realm);
                        realm.close();
                        listener.success(user,genericRepositoryRealm.getAll().sort("id"));
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
            public void onFailure(Call<PersonalProfileResponse> call, Throwable t) {
                Log.e("LOGIN FAILURE", t.getLocalizedMessage() + " MESSAGE: " + t.getMessage() + " TO STRING: " + t.toString());
                listener.error(ping());

            }
        });
    }
}
