package com.example.andrearodriguez.fundaciones.signup;

import com.example.andrearodriguez.fundaciones.domine.FirebaseActionListenerCallback;
import com.example.andrearodriguez.fundaciones.domine.FirebaseLoginAPI;
import com.example.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.example.andrearodriguez.fundaciones.signup.events.SignupEvent;
import com.firebase.client.FirebaseError;

/**
 * Created by andrearodriguez on 9/22/16.
 */
public class SignupRepositoryImp implements SignupRepository{

    private EvenBus evenBus;
    private FirebaseLoginAPI firebaseLoginAPI;

    public SignupRepositoryImp(EvenBus evenBus, FirebaseLoginAPI firebaseLoginAPI) {
        this.evenBus = evenBus;
        this.firebaseLoginAPI = firebaseLoginAPI;
    }

    @Override
    public void SignUp(final String email, final String password) {
        firebaseLoginAPI.signup(email, password, new FirebaseActionListenerCallback() {
            @Override
            public void onSucces() {
                postEvent(SignupEvent.onSignUpSuccess);
                SignIn(email, password);
            }

            @Override
            public void onError(FirebaseError error) {
                postEvent(SignupEvent.onSignUpError, error.getMessage(), null);
            }

        });
    }
    @Override
    public void SignIn(String email, String password) {
        if(email != null && password!=null){
            firebaseLoginAPI.login(email, password, new FirebaseActionListenerCallback() {

                @Override
                public void onSucces() {
                    String email = firebaseLoginAPI.getAuthEmail();
                    postEvent(SignupEvent.onSignInSuccess, email);
                }

                @Override
                public void onError(FirebaseError error) {
                    postEvent(SignupEvent.onSignInError, error.getMessage(), null);
                }
            });
        }else{
            firebaseLoginAPI.checkForSession(new FirebaseActionListenerCallback() {
                @Override
                public void onSucces() {
                    String email = firebaseLoginAPI.getAuthEmail();
                    postEvent(SignupEvent.onSignInSuccess, email);
                }

                @Override
                public void onError(FirebaseError error) {
                    postEvent(SignupEvent.onFailedRecoverSession);
                }
            });
        }
    }

    private void postEvent(int type, String errorMessage, String currentUserEmail){
        SignupEvent loginEvent = new SignupEvent();
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