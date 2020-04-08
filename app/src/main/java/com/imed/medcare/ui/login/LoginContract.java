package com.imed.medcare.ui.login;

import com.imed.medcare.network.response.ProfileResponse;
import com.imed.medcare.network.response.TreatmentResponse;
import com.imed.medcare.network.response.UserPollResponse;

/**
 * Created by felipe on 23-02-18.
 */

public interface LoginContract {
    interface View {

        void sendCredentials(String rut, String password);
        void showAlertDialogOnBack();
        void closeKeyboard();
        void showMessage(String message);
        //void showLoading(String message);
        //void hideLoading();
        void showHome();
        void manageLoader();
    }

    interface Presenter{

        void setCredentials(String rut, String password);
        void setPhone(String phone);
        void setCode(String code);
        String getPhone();
    }

    interface Interactor{
        void postLogin(String rut,String password, OnLoginResultListener loginListener);
        void setFirebaseToken(String firebaseToken, onFirebaseTokenResultListener listener );
        interface onFirebaseTokenResultListener{
            void onFirebaseTokenSuccess();
            void onFirebaseTokenError(String errorMessage);
        }

        interface OnLoginResultListener {
            void onLoginSuccess(UserPollResponse userPollResponse, TreatmentResponse treatmentResponse, ProfileResponse profileResponse, String accessToken, String refreshToken);
            void onLoginError(String errorMessage);
        }
    }
}
