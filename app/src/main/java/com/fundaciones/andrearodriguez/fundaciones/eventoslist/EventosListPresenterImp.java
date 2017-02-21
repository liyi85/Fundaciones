package com.fundaciones.andrearodriguez.fundaciones.eventoslist;

import com.fundaciones.andrearodriguez.fundaciones.entities.Eventos;
import com.fundaciones.andrearodriguez.fundaciones.eventoslist.events.EventosListEvent;
import com.fundaciones.andrearodriguez.fundaciones.eventoslist.ui.EventosListView;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by andrearodriguez on 1/25/17.
 */

public class EventosListPresenterImp implements EventosListPresenter{

    private EvenBus eventBus;
    private EventosListView view;
    private EventosListInteractor interactor;

    private final static String EMPTY_LIST = "Listado vacío";

    public EventosListPresenterImp(EvenBus eventBus, EventosListView view, EventosListInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);

    }

    @Override
    public void onDestroy() {
        this.view = null;
        eventBus.unregister(this);

    }

    @Override
    public void subscribe() {
        if (view != null){
            view.hideList();
            view.showProgress();
        }
        interactor.subscribe();
    }

    @Override
    public void unsubscribe() {
        interactor.unsubscribe();

    }

    @Override
    public void removeEvento(Eventos eventos) {
        interactor.removeEvento(eventos);

    }

    @Override
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventosListEvent event) {
        if (this.view != null) {
            if (view != null){
                view.hideProgress();
                view.showList();
            }
            String error = event.getError();
            if (error != null) {
                if (error.isEmpty()) {
                    view.onEventoError(EMPTY_LIST);
                } else {
                    view.onEventoError(error);
                }
            } else {
                if (event.getType() == EventosListEvent.READ_EVENT) {
                    view.addEvento(event.getEventos());
                } else if (event.getType() == EventosListEvent.DELETE_EVENT) {
                    view.removeEvento(event.getEventos());
                }
            }
        }

    }
}
