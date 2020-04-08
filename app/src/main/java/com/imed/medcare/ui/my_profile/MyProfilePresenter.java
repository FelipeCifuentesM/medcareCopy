package com.imed.medcare.ui.my_profile;

import android.app.Activity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.imed.medcare.model.User;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.ui.login.LoginPresenter;

import io.realm.Realm;
import static com.imed.medcare.model.repository.GenericRepositoryRealm.closeSession;
import static com.imed.medcare.utils.MedcareUtils.cancelAlarm;

public class MyProfilePresenter implements MyProfileContract.MyProfilePresenter, MyProfileContract.MyProfileInteractor.onLogOutListener {

    private MyProfileContract.MyProfileView view;
    private MyProfileContract.MyProfileInteractor interactor;

    public MyProfilePresenter(MyProfileContract.MyProfileView view){
        this.view = view;
        this.interactor = new MyProfileInteractor();
    }

    @Override
    public void getUserData() {
        GenericRepositoryRealm<User> genericRepositoryRealmUser = new GenericRepositoryRealm<>(User.class);
        genericRepositoryRealmUser.setRealm(Realm.getDefaultInstance());
        view.showUserData(genericRepositoryRealmUser.getFirst());
    }

    @Override
    public void logOut(Activity activity) {

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(activity,new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                interactor.logOut(instanceIdResult.getToken(), MyProfilePresenter.this);

            }
        });
    }

    @Override
    public void logOutSucces() {
        view.navigateToLogin();
    }

    @Override
    public void logOutError(String message) {

    }
}
