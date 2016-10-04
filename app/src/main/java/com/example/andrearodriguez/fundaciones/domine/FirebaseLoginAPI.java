package com.example.andrearodriguez.fundaciones.domine;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public class FirebaseLoginAPI {

    private Firebase firebase;


    public FirebaseLoginAPI(Firebase firebase) {
        this.firebase = firebase;
    }

    public void logout(){
        firebase.unauth();
    }

    public void login(String email, String password, final FirebaseActionListenerCallback listenerCallback){
        firebase.authWithPassword(email, password, new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                listenerCallback.onSucces();
            }

            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                listenerCallback.onError(firebaseError);
            }
        });
    }
    public void signup(String email, String password, final FirebaseActionListenerCallback listenerCallback){
        firebase.createUser(email, password, new Firebase.ValueResultHandler<Map<String, Object>>() {

            @Override
            public void onSuccess(Map<String, Object> stringObjectMap) {
                listenerCallback.onSucces();
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                listenerCallback.onError(firebaseError);
            }
        });

    }
    public void checkForSession(FirebaseActionListenerCallback listenerCallback){
        if (firebase.getAuth() != null){
            listenerCallback.onSucces();
        }else {
            listenerCallback.onError(null);
        }
    }
    public String getAuthEmail(){
        String email = null;

        if (firebase.getAuth() != null){
            Map<String, Object> providerData = firebase.getAuth().getProviderData();
            email = providerData.get("email").toString();
        }
        return email;

    }
}
