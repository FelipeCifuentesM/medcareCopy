package com.imed.medcare.ui.home;

/**
 * Created by Ramiro on 09-05-2018.
 */

public class HomeInteractor implements HomeContract.Interactor {
    private HomeContract.Presenter homePresenter;

    public HomeInteractor(HomeContract.Presenter homePresenter) {
        this.homePresenter = homePresenter;
    }
}
