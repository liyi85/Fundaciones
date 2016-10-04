package com.example.andrearodriguez.fundaciones.login.ui;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSignIn();
    void handleSignUp();

    void navigationToMainScreen();
    void loginError(String error);

    void setUserEmail(String email);
}
