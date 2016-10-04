package com.example.andrearodriguez.fundaciones.main;

import com.example.andrearodriguez.fundaciones.domine.FirebaseLoginAPI;

/**
 * Created by andrearodriguez on 9/25/16.
 */
public class MainRepositoryImp implements MainRepository{

    FirebaseLoginAPI firebaseLoginAPI;

    public MainRepositoryImp(FirebaseLoginAPI firebaseLoginAPI) {
        this.firebaseLoginAPI = firebaseLoginAPI;
    }

    @Override
    public void logout() {
        firebaseLoginAPI.logout();
    }
}
