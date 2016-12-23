package com.example.andrearodriguez.fundaciones.gatolist;

import com.example.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 12/21/16.
 */

public class GatoListInteractorImp implements GatoListInteractor {

    private GatoListRepository repository;

    public GatoListInteractorImp(GatoListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void subscribe() {
        repository.subscribe();
    }

    @Override
    public void unsubscribe() {
        repository.unsubscribe();
    }

    @Override
    public void removeGato(Paticas paticas) {
        repository.removeGato(paticas);
    }
}
