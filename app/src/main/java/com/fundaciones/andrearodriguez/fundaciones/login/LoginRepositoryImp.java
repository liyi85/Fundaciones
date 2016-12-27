package com.fundaciones.andrearodriguez.fundaciones.login;

import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseActionListenerCallback;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseLoginAPI;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.login.events.LoginEvent;
import com.firebase.client.FirebaseError;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public class LoginRepositoryImp implements LoginRepository{

    private EvenBus evenBus;
    private FirebaseLoginAPI firebaseLoginAPI;

    public LoginRepositoryImp(EvenBus evenBus, FirebaseLoginAPI firebaseLoginAPI) {
        this.evenBus = evenBus;
        this.firebaseLoginAPI = firebaseLoginAPI;
    }

    @Override
    public void doSignIn(String email, String password) {
        if(email != null && password!=null){
            firebaseLoginAPI.login(email, password, new FirebaseActionListenerCallback() {

                @Override
                public void onSucces() {
                    String email = firebaseLoginAPI.getAuthEmail();
                    postEvent(LoginEvent.onSignInSuccess, email);
                }

                @Override
                public void onError(FirebaseError error) {
                    postEvent(LoginEvent.onSignInError, error.getMessage(), null);
                }
            });
        }else{
            firebaseLoginAPI.checkForSession(new FirebaseActionListenerCallback() {
                @Override
                public void onSucces() {
                    String email = firebaseLoginAPI.getAuthEmail();
                    postEvent(LoginEvent.onSignInSuccess, email);
                }

                @Override
                public void onError(FirebaseError error) {
                    postEvent(LoginEvent.onFailedRecoverSession);
                }
            });
        }
    }
    private void postEvent(int type, String errorMessage, String currentUserEmail){
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        loginEvent.setCurrenUserEmail(currentUserEmail);
        evenBus.post(loginEvent);
    }

    private void postEvent(int type){
        postEvent(type, null, null);
    }

    private void postEvent(int type, String currentUserEmail){
        postEvent(type, null, currentUserEmail);
    }
}
