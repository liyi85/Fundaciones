package com.example.andrearodriguez.fundaciones.perrolist;

import com.example.andrearodriguez.fundaciones.entities.Paticas;


/**
 * Created by andrearodriguez on 8/18/16.
 */
public class PerroListInteractorImp implements PerroListInteractor{
    private PerroListRepository repository;

    public PerroListInteractorImp(PerroListRepository repository) {
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
    public void removePerro(Paticas paticas) {
        repository.removePerro(paticas);
    }
}
