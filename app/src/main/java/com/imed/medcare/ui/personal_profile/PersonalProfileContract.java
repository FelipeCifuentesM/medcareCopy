package com.imed.medcare.ui.personal_profile;

import com.imed.medcare.model.PersonalProfile;
import com.imed.medcare.model.User;

import io.realm.RealmResults;

public interface PersonalProfileContract {

    interface View {
        void showLoader();
        void setData(User user, RealmResults<PersonalProfile> profilePersonalRealmResults);
        void showError(String message);
    }


    interface Presenter {
        void getProfilePersonal(int type);
    }

    interface Interactor{
        void getProfilePersonal(onResult listener, int type);

        interface onResult{
            void success(User user, RealmResults<PersonalProfile> profilePersonalRealmResults);
            void error(String error);
        }
    }

}
