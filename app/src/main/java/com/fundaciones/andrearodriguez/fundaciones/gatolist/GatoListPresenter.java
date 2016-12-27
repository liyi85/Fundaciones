package com.fundaciones.andrearodriguez.fundaciones.gatolist;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.events.GatoListEvent;

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
