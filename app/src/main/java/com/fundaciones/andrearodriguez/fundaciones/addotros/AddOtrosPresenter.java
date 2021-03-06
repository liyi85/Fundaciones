package com.fundaciones.andrearodriguez.fundaciones.addotros;

import com.fundaciones.andrearodriguez.fundaciones.addotros.events.AddOtrosEvent;

/**
 * Created by andrearodriguez on 1/25/17.
 */

public interface AddOtrosPresenter {
    void onCreate();
    void onDestroy();

    void uploadPhoto(String name, String especie, String edad, String sexo, String path, String esteril, String vacuna, String discapacidad);

    void onEventMainThread(AddOtrosEvent event);
}
