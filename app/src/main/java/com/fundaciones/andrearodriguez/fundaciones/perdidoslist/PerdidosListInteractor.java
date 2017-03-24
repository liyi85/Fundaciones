package com.fundaciones.andrearodriguez.fundaciones.perdidoslist;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 3/24/17.
 */

public interface PerdidosListInteractor {
    void subscribe();
    void unsubscribe();

    void removePerdido(Paticas paticas);
}
