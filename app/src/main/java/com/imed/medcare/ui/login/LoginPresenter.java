package com.imed.medcare.ui.login;

import android.app.Activity;
import android.content.Context;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.imed.medcare.App;
import com.imed.medcare.R;
import com.imed.medcare.model.Prescription;
import com.imed.medcare.model.Treatment;
import com.imed.medcare.model.User;
import com.imed.medcare.model.UserPoll;
import com.imed.medcare.network.response.ProfileResponse;
import com.imed.medcare.network.response.TreatmentResponse;
import com.imed.medcare.network.response.UserPollResponse;

import io.realm.Realm;

import static com.imed.medcare.utils.MedcareUtils.getCosetsTime;
import static com.imed.medcare.utils.MedcareUtils.getDateToday;

/**
 * Created by Ramiro on 02-04-2018.
 */

public class LoginPresenter implements LoginContract.Presenter, LoginContract.Interactor.OnLoginResultListener, LoginContract.Interactor.onFirebaseTokenResultListener {
    private LoginContract.View loginView;
    private LoginContract.Interactor loginInteractor;
    //private LoginRequest loginRequest;

    private User user;
    private Context context;
    private Activity activity;

    public LoginPresenter(LoginContract.View loginActivity, Activity activity) {
        this.loginView = loginActivity;
        loginInteractor = new LoginInteractor();
        this.activity = activity;
        user = new User();
        context = App.getContext();
    }

    @Override
    public void setCredentials(String rut, String password) {
        //loginRequest.setRut(rut);
        //loginRequest.setPassword(password);

        //loginView.showLoading(context.getResources().getString(R.string.messageLoading));
        loginView.manageLoader();
        loginInteractor.postLogin(rut, password, this);

    }


    @Override
    public void setPhone(String phone) {
        //loginRequest.setPhone(phone);

        //user.setPhone(phone);
    }

    @Override
    public void setCode(String code) {
        //loginRequest.setCode(code);

        //user.setValidationCode(code);
    }

    @Override
    public String getPhone() {
        return user.getPhone();
    }


    @Override
    public void onLoginSuccess(UserPollResponse userPollResponse, TreatmentResponse treatmentResponse, ProfileResponse profileResponse, String accessToken, String refreshToken) {

        Realm realm = Realm.getDefaultInstance();

        User.saveToRealm(profileResponse, accessToken, refreshToken, getDateToday(), false, Realm.getDefaultInstance());
        Treatment.saveToRealm(treatmentResponse, realm);

        if (treatmentResponse.getData() != null && treatmentResponse.getData().size() > 0 && treatmentResponse.getData().get(0).getPrescriptions() != null) {
            Prescription.saveToRealm(treatmentResponse.getData().get(0).getPrescriptions(), realm);
            getCosetsTime();
        }
        if (userPollResponse.getData() != null && userPollResponse.getData().size() > 0) {
            UserPoll.saveToRealm(userPollResponse.getData().get(0), realm);
        }

        realm.close();
        //updateAlarm();

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(activity,new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                loginInteractor.setFirebaseToken(instanceIdResult.getToken(), LoginPresenter.this);

            }
        });

    }


    @Override
    public void onLoginError(String errorMessage) {
        //loginView.hideLoading();
        loginView.manageLoader();
        loginView.showMessage(errorMessage);
    }

    @Override
    public void onFirebaseTokenSuccess() {
        //loginView.hideLoading();
        loginView.manageLoader();
        loginView.showHome();
    }

    @Override
    public void onFirebaseTokenError(String errorMessage) {
        //loginView.hideLoading();
        loginView.manageLoader();
        loginView.showMessage(errorMessage);
    }
}
