package com.example.andrearodriguez.fundaciones.login;

import android.util.Log;

import com.example.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.example.andrearodriguez.fundaciones.login.events.LoginEvent;
import com.example.andrearodriguez.fundaciones.login.ui.LoginView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public class LoginPresenterImp implements LoginPresenter{

    private EvenBus evenBus;
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImp(EvenBus evenBus, LoginView loginView, LoginInteractor loginInteractor) {
        this.evenBus = evenBus;
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        loginView = null;

    }

    @Override
    public void onResume() {
        evenBus.register(this);
    }

    @Override
    public void onPause() {
        evenBus.unregister(this);
    }

    @Override
    public void validateLogin(String email, String password) {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignIn(email, password);
    }

    @Subscribe
    @Override
    public void onEventMainThread(LoginEvent event) {
        Log.e("LoginPresenterImpl", "onEventMainThread");
        switch (event.getEventType()) {
            case LoginEvent.onSignInSuccess:
                onSignInSucces(event.getCurrenUserEmail());
                break;
            case LoginEvent.onSignInError:
                onSignInError(event.getErrorMessage());
                break;
            case LoginEvent.onFailedRecoverSession:
                onFailedRecoverSession();
                break;
        }
    }
    private void onFailedRecoverSession() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
        }
    }
    private void onSignInSucces(String email) {
        if (loginView != null) {
            loginView.setUserEmail(email);
            loginView.navigationToMainScreen();

        }

    }
    private void onSignInError(String error) {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.loginError(error);
        }

    }
}
