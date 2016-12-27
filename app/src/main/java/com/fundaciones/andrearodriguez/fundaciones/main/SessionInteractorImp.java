package com.fundaciones.andrearodriguez.fundaciones.main;

/**
 * Created by andrearodriguez on 9/25/16.
 */
public class SessionInteractorImp implements SessionInteractor{

    MainRepository mainRepository;

    public SessionInteractorImp(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public void logout() {
        mainRepository.logout();
    }
}
