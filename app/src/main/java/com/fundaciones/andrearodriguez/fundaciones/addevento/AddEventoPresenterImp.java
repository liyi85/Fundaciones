package com.fundaciones.andrearodriguez.fundaciones.addevento;

import com.fundaciones.andrearodriguez.fundaciones.addevento.event.AddEventoEvent;
import com.fundaciones.andrearodriguez.fundaciones.addevento.ui.AddEventoView;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by andrearodriguez on 1/26/17.
 */

public class AddEventoPresenterImp implements AddEventoPresenter{

    private AddEventoView view;
    private EvenBus eventBus;
    private AddEventoInteractor interactor;

    public AddEventoPresenterImp(AddEventoView view, EvenBus eventBus, AddEventoInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);

    }

    @Override
    public void uploadPhoto(String nombreevento, String fecha, String lugar, String hora, String path, String tipo) {
        interactor.execute(nombreevento, fecha, lugar, hora, path, tipo);
    }
    @Subscribe
    @Override
    public void onEventMainThread(AddEventoEvent event) {
        if(this.view != null){
            switch (event.getType()){
                case AddEventoEvent.UPLOAD_INIT:
                    view.onUploadInit();
                    break;
                case AddEventoEvent.UPLOAD_COMPLETE:
                    view.onUploadComplete();
                    break;
                case AddEventoEvent.UPLOAD_ERROR:
                    view.onUploadError(event.getError());
                    break;
            }
        }
    }
}
