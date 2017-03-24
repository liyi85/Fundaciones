package com.fundaciones.andrearodriguez.fundaciones.perdidoslist;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.perdidoslist.event.PerdidosListEvent;
import com.fundaciones.andrearodriguez.fundaciones.perdidoslist.ui.PerdidosListView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by andrearodriguez on 3/24/17.
 */

public class PerdidosListPresenterImp implements PerdidosListPresenter{

    private EvenBus eventBus;
    private PerdidosListView view;
    private PerdidosListInteractor interactor;

    private final static String EMPTY_LIST = "Listado vacío";

    public PerdidosListPresenterImp(EvenBus eventBus, PerdidosListView view, PerdidosListInteractor interactor) {
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
    public void removePerdido(Paticas paticas) {
        interactor.removePerdido(paticas);

    }
    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(PerdidosListEvent event) {
        if (this.view != null) {
            if (view != null){
                view.hideProgress();
                view.showList();
            }
            String error = event.getError();
            if (error != null) {
                if (error.isEmpty()) {
                    view.onPerdidoError(EMPTY_LIST);
                } else {
                    view.onPerdidoError(error);
                }
            } else {
                if (event.getType() == PerdidosListEvent.READ_EVENT) {
                    view.addPerdido(event.getPaticas());
                } else if (event.getType() == PerdidosListEvent.DELETE_EVENT) {
                    view.removePerdido(event.getPaticas());
                }
            }
        }

    }
}
