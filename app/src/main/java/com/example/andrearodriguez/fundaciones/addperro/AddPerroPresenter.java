package com.example.andrearodriguez.fundaciones.addperro;

import com.example.andrearodriguez.fundaciones.addperro.event.AddPerroEvent;


/**
 * Created by andrearodriguez on 8/17/16.
 */
public interface AddPerroPresenter {
    void onCreate();
    void onDestroy();

    void uploadPhoto(String name, String edad, String sexo, String tamano, String path, String esteril, String vacuna);

    void onEventMainThread(AddPerroEvent event);
}
