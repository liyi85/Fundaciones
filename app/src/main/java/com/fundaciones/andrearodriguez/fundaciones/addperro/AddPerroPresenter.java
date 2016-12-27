package com.fundaciones.andrearodriguez.fundaciones.addperro;

import com.fundaciones.andrearodriguez.fundaciones.addperro.event.AddPerroEvent;


/**
 * Created by andrearodriguez on 8/17/16.
 */
public interface AddPerroPresenter {
    void onCreate();
    void onDestroy();

    void uploadPhoto(String name, String edad, String sexo, String tamano, String path, String esteril, String vacuna, String discapacidad);

    void onEventMainThread(AddPerroEvent event);
}
