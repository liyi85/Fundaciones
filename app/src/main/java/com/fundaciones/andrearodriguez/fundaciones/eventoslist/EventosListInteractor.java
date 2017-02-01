package com.fundaciones.andrearodriguez.fundaciones.eventoslist;

import com.fundaciones.andrearodriguez.fundaciones.entities.Eventos;

/**
 * Created by andrearodriguez on 1/25/17.
 */

public interface EventosListInteractor {
    void subscribe();
    void unsubscribe();
    void removeEvento(Eventos eventos);
}
