package com.fundaciones.andrearodriguez.fundaciones.addgato;

import com.fundaciones.andrearodriguez.fundaciones.addgato.event.AddGatoEvent;

/**
 * Created by andrearodriguez on 12/22/16.
 */

public interface AddGatoPresenter {

    void onCreate();
    void onDestroy();

    void uploadPhoto(String name, String edad, String sexo, String tamano, String path, String esteril, String vacuna, String discapacidad);

    void onEventMainThread(AddGatoEvent event);
}
