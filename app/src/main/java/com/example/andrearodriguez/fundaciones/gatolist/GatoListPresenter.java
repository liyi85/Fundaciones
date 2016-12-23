package com.example.andrearodriguez.fundaciones.gatolist;

import com.example.andrearodriguez.fundaciones.entities.Paticas;
import com.example.andrearodriguez.fundaciones.gatolist.events.GatoListEvent;

/**
 * Created by andrearodriguez on 12/21/16.
 */

public interface GatoListPresenter {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();

    void removeGato(Paticas paticas);
    void onEventMainThread (GatoListEvent event);
}
