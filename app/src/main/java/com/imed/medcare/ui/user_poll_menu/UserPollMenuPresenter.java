package com.imed.medcare.ui.user_poll_menu;

import com.imed.medcare.model.UserPoll;
import com.imed.medcare.model.repository.GenericRepositoryRealm;
import com.imed.medcare.network.response.UserPollResponse;

import io.realm.Realm;

public class UserPollMenuPresenter implements UserPollMenuContract.userPollMenuPresenter, UserPollMenuContract.userPollMenuInteractor.onUserPollMenuListener {

    UserPollMenuContract.UserPollMenuView view;
    UserPollMenuContract.userPollMenuInteractor interactor;

    public UserPollMenuPresenter(UserPollMenuContract.UserPollMenuView view){
        this.view = view;
        this.interactor = new  UserPollMenuInteractor();
    }

    @Override
    public void getData() {
        view.showLoading();
        interactor.getData(this);
    }

    @Override
    public void onUserPollMenuSuccess(UserPollResponse.DataBean dataBean) {
        GenericRepositoryRealm<UserPoll> userPollGenericRepositoryRealm = new GenericRepositoryRealm<>(UserPoll.class);
        userPollGenericRepositoryRealm.setRealm(Realm.getDefaultInstance());
        userPollGenericRepositoryRealm.clear();
        UserPoll.saveToRealm(dataBean, Realm.getDefaultInstance());
        view.setData(userPollGenericRepositoryRealm.getFirst());
    }

    @Override
    public void onUserPollMenuError(String message) {
        view.showError(message);
    }
}
