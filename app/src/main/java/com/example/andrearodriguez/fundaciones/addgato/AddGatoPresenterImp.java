package com.example.andrearodriguez.fundaciones.addgato;

import com.example.andrearodriguez.fundaciones.addgato.event.AddGatoEvent;
import com.example.andrearodriguez.fundaciones.addgato.ui.AddGatoView;
import com.example.andrearodriguez.fundaciones.libs.base.EvenBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by andrearodriguez on 12/22/16.
 */

public class AddGatoPresenterImp implements AddGatoPresenter{

    private AddGatoView view;
    private EvenBus evenBus;
    private AddGatoInteractor interactor;

    public AddGatoPresenterImp(AddGatoView view, EvenBus evenBus, AddGatoInteractor interactor) {
        this.view = view;
        this.evenBus = evenBus;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        evenBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        evenBus.unregister(this);
    }


    @Override
    public void uploadPhoto(String name, String edad, String sexo, String tamano, String path, String esteril, String vacuna, String discapacidad) {
        interactor.excecute(name, edad, sexo, tamano, path, esteril, vacuna, discapacidad);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddGatoEvent event) {
        if(this.view != null){
            switch (event.getType()){
                case AddGatoEvent.UPLOAD_INIT:
                    view.onUploadInit();
                    break;
                case AddGatoEvent.UPLOAD_COMPLETE:
                    view.onUploadComplete();
                    break;
                case AddGatoEvent.UPLOAD_ERROR:
                    view.onUploadError(event.getError());
                    break;
            }
        }
    }
}
