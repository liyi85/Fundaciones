package com.example.andrearodriguez.fundaciones.perrolist;

import com.example.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 9/26/16.
 */
public interface PerroListRepository {
    void subscribe();
    void unsubscribe();

    void removePerro(Paticas paticas);
}
