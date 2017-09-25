package com.fundaciones.andrearodriguez.fundaciones.addperdidos;

import com.fundaciones.andrearodriguez.fundaciones.addperdidos.events.AddPerdidosEvent;
import com.fundaciones.andrearodriguez.fundaciones.addperdidos.ui.AddPerdidosView;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by andrearodriguez on 9/25/17.
 */

public class AddPerdidosPresenterImp implements AddPerdidosPresenter{

    private AddPerdidosView view;
    private EvenBus eventBus;
    private AddPerdidosInteractor interactor;

    public AddPerdidosPresenterImp(AddPerdidosView view, EvenBus eventBus, AddPerdidosInteractor interactor) {
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
    public void uploadPhoto(String name, String especie, String edad, String sexo, String path, String esteril, String vacuna, String discapacidad) {
        interactor.excecute(name, especie, edad, sexo, path, esteril, vacuna, discapacidad);
    }

    @Override
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(AddPerdidosEvent event) {
        if(this.view != null){
            switch (event.getType()){
                case AddPerdidosEvent.UPLOAD_INIT:
                    view.onUploadInit();
                    break;
                case AddPerdidosEvent.UPLOAD_COMPLETE:
                    view.onUploadComplete();
                    break;
                case AddPerdidosEvent.UPLOAD_ERROR:
                    view.onUploadError(event.getError());
                    break;
            }
        }
    }
}
