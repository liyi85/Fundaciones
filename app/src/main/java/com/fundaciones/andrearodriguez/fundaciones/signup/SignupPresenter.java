package com.fundaciones.andrearodriguez.fundaciones.signup;

import com.fundaciones.andrearodriguez.fundaciones.signup.events.SignupEvent;

/**
 * Created by andrearodriguez on 9/22/16.
 */
public interface SignupPresenter {
    void onCreate();
    void onDestroy();
    void onResume();
    void onPause();
    void registerNewUser(String email, String password);
    void onEventMainThread(SignupEvent event);


}
