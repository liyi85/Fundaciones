package com.fundaciones.andrearodriguez.fundaciones.otroslist;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 1/24/17.
 */

public interface OtrosListInteractor {
    void subscribe();
    void unsubscribe();

    void removeOtros(Paticas paticas);
}
