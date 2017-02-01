package com.fundaciones.andrearodriguez.fundaciones.eventoslist;

import com.fundaciones.andrearodriguez.fundaciones.entities.Eventos;
import com.fundaciones.andrearodriguez.fundaciones.eventoslist.events.EventosListEvent;

/**
 * Created by andrearodriguez on 1/25/17.
 */

public interface EventosListPresenter {
    void onCreate();
    void onDestroy();

    void subscribe();
    void unsubscribe();

    void removeEvento(Eventos eventos);
    void onEventMainThread (EventosListEvent event);
}
