package com.fundaciones.andrearodriguez.fundaciones.gatolist;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 12/21/16.
 */

public interface GatoListInteractor {
    void subscribe();
    void unsubscribe();

    void removeGato(Paticas paticas);
}
