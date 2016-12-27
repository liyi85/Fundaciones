package com.fundaciones.andrearodriguez.fundaciones.signup;

import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.signup.events.SignupEvent;
import com.fundaciones.andrearodriguez.fundaciones.signup.ui.SignupView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by andrearodriguez on 9/22/16.
 */
public class SignupPresenterImp implements SignupPresenter{

    private EvenBus evenBus;
    private SignupView signupView;
    private SignupInteractor signupInteractor;

    public SignupPresenterImp(EvenBus evenBus, SignupView signupView, SignupInteractor signupInteractor) {
        this.evenBus = evenBus;
        this.signupView = signupView;
        this.signupInteractor = signupInteractor;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        signupView=null;
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
    public void registerNewUser(String email, String password) {
        if (signupView != null) {
            signupView.disableInputs();
            signupView.showProgress();
        }
        signupInteractor.doSignUp(email, password);
    }

    @Subscribe
    @Override
    public void onEventMainThread(SignupEvent event) {
        switch (event.getEventType()) {
            case SignupEvent.onSignUpSuccess:
                onSignUpSucces();
                break;
            case SignupEvent.onSignUpError:
                onSignUpError(event.getErrorMessage());
                break;
        }

    }
    private void onSignUpSucces() {
        if (signupView != null) {
            signupView.newUserSuccess();
            signupView.navigationToMainScreen();
        }
    }
    private void onSignUpError(String error) {
        if (signupView != null) {
            signupView.hideProgress();
            signupView.enableInputs();
            signupView.newUserError(error);
        }

    }
}
