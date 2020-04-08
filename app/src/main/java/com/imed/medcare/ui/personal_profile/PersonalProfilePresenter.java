package com.imed.medcare.ui.personal_profile;

import com.imed.medcare.model.PersonalProfile;
import com.imed.medcare.model.User;

import io.realm.RealmResults;

public class PersonalProfilePresenter implements PersonalProfileContract.Presenter, PersonalProfileContract.Interactor.onResult{


    PersonalProfileContract.View view;
    PersonalProfileContract.Interactor interactor;

    public PersonalProfilePresenter(PersonalProfileContract.View view){
        this.view = view;
        this.interactor = new PersonalProfileInteractor();

    }

    @Override
    public void getProfilePersonal(int type) {
        view.showLoader();
        interactor.getProfilePersonal(this, type);
    }



    @Override
    public void success(User user, RealmResults<PersonalProfile> profilePersonalRealmResults) {
        view.showLoader();
        view.setData(user,profilePersonalRealmResults);
    }

    @Override
    public void error(String error) {
        view.showLoader();
        view.showError(error);
    }
}
