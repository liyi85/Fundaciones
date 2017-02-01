package com.fundaciones.andrearodriguez.fundaciones.addevento;

import com.fundaciones.andrearodriguez.fundaciones.addevento.event.AddEventoEvent;

/**
 * Created by andrearodriguez on 1/26/17.
 */

public interface AddEventoPresenter {
    void onCreate();
    void onDestroy();

    void uploadPhoto(String nombreevento, String fecha, String lugar, String hora, String path, String tipo);

    void onEventMainThread(AddEventoEvent event);
}
