package com.fundaciones.andrearodriguez.fundaciones.perrolist;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 9/26/16.
 */
public interface PerroListInteractor {
    void subscribe();
    void unsubscribe();

    void removePerro(Paticas paticas);
}
