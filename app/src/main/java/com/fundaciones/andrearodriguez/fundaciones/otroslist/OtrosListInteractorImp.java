package com.fundaciones.andrearodriguez.fundaciones.otroslist;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 1/24/17.
 */

public class OtrosListInteractorImp implements OtrosListInteractor{

    private OtrosListRepository repository;

    public OtrosListInteractorImp(OtrosListRepository repository) {
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
    public void removeOtros(Paticas paticas) {
        repository.removeOtros(paticas);
    }
}
