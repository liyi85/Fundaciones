package com.fundaciones.andrearodriguez.fundaciones.perdidoslist;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 3/24/17.
 */

public class PerdidosListInteractorImp implements PerdidosListInteractor{

    PerdidosListRepository repository;

    public PerdidosListInteractorImp(PerdidosListRepository repository) {
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
    public void removePerdido(Paticas paticas) {
        repository.removePerdido(paticas);
    }

}
