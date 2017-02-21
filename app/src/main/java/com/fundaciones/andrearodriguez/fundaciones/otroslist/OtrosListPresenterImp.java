package com.fundaciones.andrearodriguez.fundaciones.otroslist;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.otroslist.events.OtrosListEvent;
import com.fundaciones.andrearodriguez.fundaciones.otroslist.ui.OtrosListView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by andrearodriguez on 1/24/17.
 */

public class OtrosListPresenterImp implements OtrosListPresenter {

    private EvenBus eventBus;
    private OtrosListView view;
    private OtrosListInteractor interactor;

    private final static String EMPTY_LIST = "Listado vacío";

    public OtrosListPresenterImp(EvenBus eventBus, OtrosListView view, OtrosListInteractor interactor) {
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
    public void removeOtros(Paticas paticas) {
        interactor.removeOtros(paticas);

    }

    @Override
    @Subscribe (threadMode = ThreadMode.MAIN)
    public void onEventMainThread(OtrosListEvent event) {
        if (this.view != null) {
            if (view != null){
                view.hideProgress();
                view.showList();
            }
            String error = event.getError();
            if (error != null) {
                if (error.isEmpty()) {
                    view.onOtrosError(EMPTY_LIST);
                } else {
                    view.onOtrosError(error);
                }
            } else {
                if (event.getType() == OtrosListEvent.READ_EVENT) {
                    view.addOtros(event.getPaticas());
                } else if (event.getType() == OtrosListEvent.DELETE_EVENT) {
                    view.removeOtros(event.getPaticas());
                }
            }
        }

    }
}
