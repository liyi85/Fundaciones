package com.fundaciones.andrearodriguez.fundaciones.eventoslist;

import com.fundaciones.andrearodriguez.fundaciones.entities.Eventos;

/**
 * Created by andrearodriguez on 1/25/17.
 */

public class EventosListInteractorImp implements EventosListInteractor{

    private EventosListRespository respository;

    public EventosListInteractorImp(EventosListRespository respository) {
        this.respository = respository;
    }

    @Override
    public void subscribe() {
        respository.subscribe();
    }

    @Override
    public void unsubscribe() {
        respository.unsubscribe();
    }

    @Override
    public void removeEvento(Eventos eventos) {
        respository.removeEvento(eventos);
    }
}
