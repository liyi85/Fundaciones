package com.example.andrearodriguez.fundaciones.domine;

import com.firebase.client.FirebaseError;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public interface FirebaseActionListenerCallback {
    void onSucces();
    void onError(FirebaseError error);
}
