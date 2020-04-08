package com.imed.medcare.ui.home;

/**
 * Created by Ramiro on 02-04-2018.
 */

public class HomePresenter implements HomeContract.Presenter{
    private HomeContract.View homeView;
    private HomeContract.Interactor homeInteractor;

    public HomePresenter(HomeContract.View homeActivity) {
        this.homeView = homeActivity;
        homeInteractor = new HomeInteractor(this);
    }

}
