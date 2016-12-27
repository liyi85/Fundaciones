package com.fundaciones.andrearodriguez.fundaciones.gatolist;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.events.GatoListEvent;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.ui.GatoLisView;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by andrearodriguez on 12/21/16.
 */

public class GatoListPresenterImp implements GatoListPresenter {

    private EvenBus eventBus;
    private GatoLisView view;
    private GatoListInteractor interactor;

    private final static String EMPTY_LIST = "Listado vacío";

    public GatoListPresenterImp(EvenBus eventBus, GatoLisView view, GatoListInteractor interactor) {
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
    public void removeGato(Paticas paticas) {
        interactor.removeGato(paticas);
    }

    @Override
    @Subscribe
    public void onEventMainThread(GatoListEvent event) {
        if (this.view != null) {
            if (view != null){
                view.hideProgress();
                view.showList();
            }
            String error = event.getError();
            if (error != null) {
                if (error.isEmpty()) {
                    view.onGatoError(EMPTY_LIST);
                } else {
                    view.onGatoError(error);
                }
            } else {
                if (event.getType() == GatoListEvent.READ_EVENT) {
                    view.addGato(event.getPaticas());
                } else if (event.getType() == GatoListEvent.DELETE_EVENT) {
                    view.removeGato(event.getPaticas());
                }
            }
        }

    }
}
