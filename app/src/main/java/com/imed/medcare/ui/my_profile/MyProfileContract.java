package com.imed.medcare.ui.my_profile;

import android.app.Activity;

import com.imed.medcare.model.User;

public interface MyProfileContract {

    interface MyProfileView{
        void showUserData(User user);
        void navigateToLogin();
        void showErrorMessage(String message);
        //void showLoader();
        //void closeLoader();

    }

    interface MyProfilePresenter{
        void getUserData();
        void logOut(Activity activity);
    }

    interface MyProfileInteractor{
        void logOut(String firebaseToken, onLogOutListener listener);

        interface onLogOutListener{
            void logOutSucces();
            void logOutError(String message);
        }
    }


}
