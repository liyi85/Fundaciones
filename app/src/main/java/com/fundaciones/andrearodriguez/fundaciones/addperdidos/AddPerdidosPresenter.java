package com.fundaciones.andrearodriguez.fundaciones.addperdidos;

import com.fundaciones.andrearodriguez.fundaciones.addperdidos.events.AddPerdidosEvent;

/**
 * Created by andrearodriguez on 9/25/17.
 */

public interface AddPerdidosPresenter {
    void onCreate();
    void onDestroy();

    void uploadPhoto(String name, String especie, String edad, String sexo, String path, String esteril, String vacuna, String discapacidad);

    void onEventMainThread(AddPerdidosEvent event);
}
