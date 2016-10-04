package com.example.andrearodriguez.fundaciones.main;

/**
 * Created by andrearodriguez on 9/25/16.
 */
public class MainPresenterImp implements MainPresenter{

    SessionInteractor sessionInteractor;

    public MainPresenterImp(SessionInteractor sessionInteractor) {
        this.sessionInteractor = sessionInteractor;
    }

    @Override
    public void logout() {
        sessionInteractor.logout();
    }
}
