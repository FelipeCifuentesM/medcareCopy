package com.imed.medcare.ui.home.profile;

import com.imed.medcare.model.User;
import com.imed.medcare.model.repository.GenericRepositoryRealm;

import io.realm.Realm;

/**
 * Created by Ramiro on 09-05-2018.
 */

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View profileView;

    public ProfilePresenter(ProfileContract.View profileView) {
        this.profileView = profileView;
    }

    @Override
    public User getProfile() {
        GenericRepositoryRealm<User> genericRepositoryRealmUser = new GenericRepositoryRealm<>(User.class);
        genericRepositoryRealmUser.setRealm(Realm.getDefaultInstance());
        return genericRepositoryRealmUser.getFirst();
    }
}
