package com.imed.medcare.ui.login;

import android.util.Log;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.HistoryPrescription;
import com.imed.medcare.model.User;
import com.imed.medcare.network.RestClient;
import com.imed.medcare.network.RestClientLogin;
import com.imed.medcare.network.request.FirebaseTokenRequest;
import com.imed.medcare.network.request.LoginRequest;
import com.imed.medcare.network.response.FirebaseTokenResponse;
import com.imed.medcare.network.response.HistoryPrescriptionResponse;
import com.imed.medcare.network.response.LoginResponse;
import com.imed.medcare.network.response.ProfileResponse;
import com.imed.medcare.network.response.TreatmentResponse;
import com.imed.medcare.network.response.UserPollResponse;

import java.util.Calendar;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.imed.medcare.utils.MedcareUtils.YearMonthDayString;
import static com.imed.medcare.utils.MedcareUtils.ping;

/**
 * Created by Ramiro on 03-04-2018.
 */

public class LoginInteractor implements LoginContract.Interactor{

    @Override
    public void postLogin(String rut, String password, final OnLoginResultListener listener) {
        LoginRequest loginRequest = new LoginRequest(rut, password);

        Call<LoginResponse> postLoginUser = RestClientLogin.get().postLogin(App.getContext().getString(R.string.API_KEY),loginRequest);
        postLoginUser.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getCode() == 200) {
                        getProfile(listener,response.body().getData().get(0).getAccess_token(),response.body().getData().get(0).getRefresh_token());
                    } else {
                        if (response.body().getMessage() != null) {
                            listener.onLoginError(response.body().getMessage());
                        } else {
                            listener.onLoginError(App.getContext().getString(R.string.generic_error_message));
                        }
                    }
                }else{
                    listener.onLoginError(App.getContext().getString(R.string.generic_error_message));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("LOGIN FAILURE", t.getLocalizedMessage() + " MESSAGE: " + t.getMessage() + " TO STRING: " + t.toString());
                listener.onLoginError(App.getContext().getString(R.string.generic_error_message));


            }
        });
    }

    @Override
    public void setFirebaseToken(String firebaseToken, final onFirebaseTokenResultListener listener) {

        Realm realm = Realm.getDefaultInstance();
        User user = realm.where(User.class).findFirst();
        FirebaseTokenRequest firebaseTokenRequest = new FirebaseTokenRequest(firebaseToken, user.getId());

        Call<FirebaseTokenResponse> setNotificationRecipientResponseCall = RestClient.get().postCreateFirebaseTokenResponse(App.getContext().getString(R.string.API_KEY),user.getAccessToken(), firebaseTokenRequest);

        setNotificationRecipientResponseCall.enqueue(new Callback<FirebaseTokenResponse>() {
            @Override
            public void onResponse(Call<FirebaseTokenResponse> call, Response<FirebaseTokenResponse> response) {
                if (response.isSuccessful()) {
                    listener.onFirebaseTokenSuccess();

                } else {
                    listener.onFirebaseTokenError(App.getContext().getString(R.string.generic_error_message));
                }
            }

            @Override
            public void onFailure(Call<FirebaseTokenResponse> call, Throwable t) {
                Log.e("TOKEN FIREBASE DELETE","ERROR");
                listener.onFirebaseTokenError(App.getContext().getString(R.string.generic_error_message));
            }
        });
    }

    void getProfile(final OnLoginResultListener listener, final String accessToken, final String refreshToken){

        Call<ProfileResponse> postProfile = RestClient.get().getProfile(App.getContext().getString(R.string.API_KEY),accessToken);
        postProfile.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        getTreatment(listener,response.body(),accessToken,refreshToken);
                    } else {
                        if (response.body().getMessage() != null) {
                            listener.onLoginError(response.body().getMessage());
                        } else {
                            listener.onLoginError(App.getContext().getString(R.string.generic_error_message));
                        }
                    }
                }else{
                    listener.onLoginError(App.getContext().getString(R.string.generic_error_message));
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.e("LOGIN FAILURE", t.getLocalizedMessage() + " MESSAGE: " + t.getMessage() + " TO STRING: " + t.toString());
                listener.onLoginError(App.getContext().getString(R.string.generic_error_message));

            }
        });

    }




    void getTreatment(final OnLoginResultListener listener, final ProfileResponse profileResponse, final String accessToken, final String refreshToken){

        Call<TreatmentResponse> getTreatment = RestClient.get().getTreatment(App.getContext().getString(R.string.API_KEY),accessToken);
        getTreatment.enqueue(new Callback<TreatmentResponse>() {
            @Override
            public void onResponse(Call<TreatmentResponse> call, Response<TreatmentResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        getDataUSerPoll(listener,response.body(),profileResponse,accessToken,refreshToken);
                    } else {
                        if (response.body().getMessage() != null) {
                            listener.onLoginError(response.body().getMessage());
                        } else {
                            listener.onLoginError(App.getContext().getString(R.string.generic_error_message));
                        }
                    }
                }else{
                    listener.onLoginError(App.getContext().getString(R.string.generic_error_message));
                }
            }

            @Override
            public void onFailure(Call<TreatmentResponse> call, Throwable t) {
                Log.e("LOGIN FAILURE", t.getLocalizedMessage() + " MESSAGE: " + t.getMessage() + " TO STRING: " + t.toString());
                listener.onLoginError(ping());

            }
        });

    }



    private void getDataUSerPoll(final OnLoginResultListener listener, final TreatmentResponse treatmentResponse, final ProfileResponse profileResponse, final String accessToken, final String refreshToken) {


        Call<UserPollResponse> getTreatment = RestClient.get().getUserPollsResponse(App.getContext().getString(R.string.API_KEY),accessToken);
        getTreatment.enqueue(new Callback<UserPollResponse>() {
            @Override
            public void onResponse(Call<UserPollResponse> call, Response<UserPollResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        if(treatmentResponse !=null && treatmentResponse.getData() !=null && treatmentResponse.getData().size()>0) {
                            getHistoryPrescription(response.body(), treatmentResponse, profileResponse, accessToken, refreshToken, listener);
                        }else {
                            listener.onLoginSuccess(response.body(),treatmentResponse,profileResponse,accessToken,refreshToken);
                        }
                    } else {
                        if (response.body().getMessage() != null) {
                            listener.onLoginError(response.body().getMessage());
                        } else {
                            listener.onLoginError(App.getContext().getString(R.string.generic_error_message));
                        }
                    }
                }else{
                    listener.onLoginError(App.getContext().getString(R.string.generic_error_message));
                }
            }
            @Override
            public void onFailure(Call<UserPollResponse> call, Throwable t) {
                listener.onLoginError(ping());

            }
        });
    }


    private void getHistoryPrescription(final UserPollResponse userPollResponse, final TreatmentResponse treatmentResponse, final ProfileResponse profileResponse, final String accessToken, final String refreshToken, final OnLoginResultListener listener){
        Calendar dateEnd = Calendar.getInstance();
        Calendar dateStart = Calendar.getInstance();
        dateStart.add(Calendar.DAY_OF_MONTH, -30);
        String dateEndString = YearMonthDayString(dateEnd.getTime());
        String dateStartString = YearMonthDayString(dateStart.getTime());
        Call<HistoryPrescriptionResponse> postProfile = RestClient.get().getHistoryPrescription(App.getContext().getString(R.string.API_KEY),accessToken,treatmentResponse.getData().get(0).getId(),dateStartString,dateEndString);
        postProfile.enqueue(new Callback<HistoryPrescriptionResponse>() {
            @Override
            public void onResponse(Call<HistoryPrescriptionResponse> call, Response<HistoryPrescriptionResponse> response) {
                if (response.isSuccessful()) {
                    if(response.body()!=null) {
                        if (response.body().getStatus().equalsIgnoreCase("ok")) {
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            realm.delete(HistoryPrescription.class);
                            realm.commitTransaction();
                            realm.close();
                            HistoryPrescription.saveToRealm(response.body(), Realm.getDefaultInstance());
                            listener.onLoginSuccess(userPollResponse,treatmentResponse,profileResponse,accessToken,refreshToken);

                        }else {
                            listener.onLoginError(response.body().getMessage());
                        }

                    }else {
                        listener.onLoginError(response.body().getMessage());
                    }
                }else{
                    listener.onLoginError(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<HistoryPrescriptionResponse> re, Throwable t) {
                listener.onLoginError(ping());
            }
        });
    }




}
