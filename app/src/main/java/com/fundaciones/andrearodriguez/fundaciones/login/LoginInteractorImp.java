package com.fundaciones.andrearodriguez.fundaciones.login;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public class LoginInteractorImp implements LoginInteractor{

    private LoginRepository repository;

    public LoginInteractorImp(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void doSignIn(String email, String password) {
        repository.doSignIn(email, password);
    }
}
