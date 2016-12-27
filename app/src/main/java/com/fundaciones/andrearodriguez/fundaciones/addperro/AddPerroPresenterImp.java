package com.fundaciones.andrearodriguez.fundaciones.addperro;

import com.fundaciones.andrearodriguez.fundaciones.addperro.event.AddPerroEvent;
import com.fundaciones.andrearodriguez.fundaciones.addperro.ui.AddPerroView;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;

import org.greenrobot.eventbus.Subscribe;


/**
 * Created by andrearodriguez on 8/17/16.
 */
public class AddPerroPresenterImp implements AddPerroPresenter{

    private AddPerroView view;
    private EvenBus eventBus;
    private AddPerroInteractor interactor;

    public AddPerroPresenterImp(AddPerroView view, EvenBus eventBus, AddPerroInteractor interactor) {
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
    public void uploadPhoto(String name, String edad, String sexo, String tamano, String path, String esteril, String vacuna, String discapacidad) {
        interactor.excecute(name, edad, sexo, tamano, path, esteril, vacuna, discapacidad);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddPerroEvent event) {
        if(this.view != null){
            switch (event.getType()){
                case AddPerroEvent.UPLOAD_INIT:
                    view.onUploadInit();
                    break;
                case AddPerroEvent.UPLOAD_COMPLETE:
                    view.onUploadComplete();
                    break;
                case AddPerroEvent.UPLOAD_ERROR:
                    view.onUploadError(event.getError());
                    break;
            }
        }
    }

}
