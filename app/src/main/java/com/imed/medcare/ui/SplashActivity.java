package com.imed.medcare.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.HistoryPrescription;
import com.imed.medcare.model.Prescription;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.model.User;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.network.RestClient;
import com.imed.medcare.network.response.HistoryPrescriptionResponse;
import com.imed.medcare.network.response.ProfileResponse;
import com.imed.medcare.network.response.TreatmentResponse;
import com.imed.medcare.ui.home.HomeActivity;
import com.imed.medcare.ui.onboarding.OnboardingActivity;

import java.util.Calendar;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.imed.medcare.utils.MedcareUtils.YearMonthDayString;
import static com.imed.medcare.utils.MedcareUtils.getCosetsTime;

/**
 * Created by Ramiro on 04-06-2018.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        isUserLogged();
    }

    public void isUserLogged() {
        GenericRepositoryRealm<User> genericRepositoryRealmUser = new GenericRepositoryRealm<>(User.class);
        genericRepositoryRealmUser.setRealm(Realm.getDefaultInstance());


        if (genericRepositoryRealmUser.getFirst() != null) {
            getProfile(genericRepositoryRealmUser.getFirst());
        } else {
            Intent welcome = new Intent(this, OnboardingActivity.class);
            startActivity(welcome);
            finish();
        }
    }

    void getProfile(final User user) {

        Call<ProfileResponse> postProfile = RestClient.get().getProfile(App.getContext().getString(R.string.API_KEY), user.getAccessToken());
        postProfile.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("ok")) {

                        User.saveToRealm(response.body(), user.getAccessToken(), user.getRefreshToken(), user.getMessageDate(), user.isMessageReaded(), Realm.getDefaultInstance());
                        getTreatment(user.getAccessToken(), user.getRefreshToken());
                    } else {
                        Intent home = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(home);
                        finish();
                    }
                } else {
                    Intent home = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(home);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Intent home = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(home);
                finish();
            }
        });
    }

    void getHistoryPrescription(String accessToken, int idTreatment) {
        Calendar dateEnd = Calendar.getInstance();
        Calendar dateStart = Calendar.getInstance();
        dateStart.add(Calendar.DAY_OF_MONTH, -30);
        String dateEndString = YearMonthDayString(dateEnd.getTime());
        String dateStartString = YearMonthDayString(dateStart.getTime());

        Call<HistoryPrescriptionResponse> postProfile = RestClient.get().getHistoryPrescription(App.getContext().getString(R.string.API_KEY), accessToken, idTreatment, dateStartString, dateEndString);
        postProfile.enqueue(new Callback<HistoryPrescriptionResponse>() {
            @Override
            public void onResponse(Call<HistoryPrescriptionResponse> call, Response<HistoryPrescriptionResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getStatus().equalsIgnoreCase("ok")) {
                            Realm realm = Realm.getDefaultInstance();
                            realm.beginTransaction();
                            realm.delete(HistoryPrescription.class);
                            realm.commitTransaction();
                            realm.close();
                            HistoryPrescription.saveToRealm(response.body(), Realm.getDefaultInstance());
                            getCosetsTime();
                        }
                        Intent home = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(home);
                        finish();
                    } else {
                        Intent home = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(home);
                        finish();
                    }
                } else {
                    Intent home = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(home);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<HistoryPrescriptionResponse> call, Throwable t) {
                Intent home = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(home);
                finish();
            }
        });
    }

    void getTreatment(final String accessToken, final String refreshToken) {

        Call<TreatmentResponse> getTreatment = RestClient.get().getTreatment(App.getContext().getString(R.string.API_KEY), accessToken);
        getTreatment.enqueue(new Callback<TreatmentResponse>() {
            @Override
            public void onResponse(Call<TreatmentResponse> call, Response<TreatmentResponse> response) {
                if (response.isSuccessful()) {

                    if (response.body().getStatus().equalsIgnoreCase("ok")) {
                        Realm realm = Realm.getDefaultInstance();
                        Treatment.saveToRealm(response.body(), realm);
                        if (response.body().getData() != null && response.body().getData().size() > 0) {

                            realm.beginTransaction();
                            realm.delete(Prescription.class);
                            realm.commitTransaction();
                            Prescription.saveToRealm(response.body().getData().get(0).getPrescriptions(), realm);
                            getHistoryPrescription(accessToken, response.body().getData().get(0).getId());
                        } else {
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }, 500);
                        }
                    } else {
                        Intent home = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(home);
                        finish();
                    }
                } else {
                    Intent home = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(home);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<TreatmentResponse> call, Throwable t) {
                Intent home = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(home);
                finish();
            }
        });

    }
}
