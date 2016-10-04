package com.example.andrearodriguez.fundaciones.signup.ui;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public interface SignupView {
    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();

    void handleSignUp();
    void navigationToMainScreen();

    void newUserSuccess();
    void newUserError(String error);

    void setUserEmail(String email);

}
