package com.fundaciones.andrearodriguez.fundaciones.perdidoslist;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.perdidoslist.event.PerdidosListEvent;

/**
 * Created by andrearodriguez on 3/24/17.
 */

public interface PerdidosListPresenter {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();

    void removePerdido(Paticas paticas);
    void onEventMainThread (PerdidosListEvent event);
}
