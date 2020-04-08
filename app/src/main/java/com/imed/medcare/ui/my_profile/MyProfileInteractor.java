package com.imed.medcare.ui.my_profile;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.User;
import com.imed.medcare.network.RestClient;
import com.imed.medcare.network.request.FirebaseTokenRequest;
import com.imed.medcare.network.response.FirebaseTokenResponse;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.imed.medcare.model.repository.GenericRepositoryRealm.closeSession;
import static com.imed.medcare.utils.MedcareUtils.cancelAlarm;

public class MyProfileInteractor implements  MyProfileContract.MyProfileInteractor {

    @Override
    public void logOut(String firebaseToken, final onLogOutListener listener) {

        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();
        FirebaseTokenRequest firebaseTokenRequest = new FirebaseTokenRequest(firebaseToken, user.getId());

        Call<FirebaseTokenResponse> setNotificationRecipientResponseCall = RestClient.get().postDeleteFirebaseTokenResponse(App.getContext().getString(R.string.API_KEY),user.getAccessToken(), firebaseTokenRequest);

        setNotificationRecipientResponseCall.enqueue(new Callback<FirebaseTokenResponse>() {
            @Override
            public void onResponse(Call<FirebaseTokenResponse> call, Response<FirebaseTokenResponse> response) {
                if (response.isSuccessful()) {

                    closeSession();
                    cancelAlarm();
                    listener.logOutSucces();

                } else {
                    listener.logOutError(App.getContext().getString(R.string.generic_error_message));
                }
            }

            @Override
            public void onFailure(Call<FirebaseTokenResponse> call, Throwable t) {
                Log.e("TOKEN FIREBASE DELETE","ERROR");
                listener.logOutError(App.getContext().getString(R.string.generic_error_message));
            }
        });



    }
}
