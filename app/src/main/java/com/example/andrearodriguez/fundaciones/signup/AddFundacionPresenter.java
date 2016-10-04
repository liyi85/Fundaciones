package com.example.andrearodriguez.fundaciones.signup;

import com.example.andrearodriguez.fundaciones.signup.events.AddFundacionEvent;

/**
 * Created by andrearodriguez on 9/22/16.
 */
public interface AddFundacionPresenter {
    void onCreate();
    void onDestroy();
    void onResume();
    void onPause();

    void uploadFundacion(String name, String direccion, String telefono, String persona, String correo);

    void onEventMainThread(AddFundacionEvent event);
}
