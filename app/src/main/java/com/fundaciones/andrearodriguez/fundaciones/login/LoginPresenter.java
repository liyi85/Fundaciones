package com.fundaciones.andrearodriguez.fundaciones.login;

import com.fundaciones.andrearodriguez.fundaciones.login.events.LoginEvent;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void onResume();
    void onPause();
    void validateLogin(String email, String password);
    void onEventMainThread(LoginEvent event);
}
