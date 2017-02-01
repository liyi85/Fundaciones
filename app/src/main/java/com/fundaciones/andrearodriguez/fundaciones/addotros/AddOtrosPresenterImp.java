package com.fundaciones.andrearodriguez.fundaciones.addotros;

import com.fundaciones.andrearodriguez.fundaciones.addotros.events.AddOtrosEvent;
import com.fundaciones.andrearodriguez.fundaciones.addotros.ui.AddOtrosView;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by andrearodriguez on 1/25/17.
 */

public class AddOtrosPresenterImp implements AddOtrosPresenter{
    private AddOtrosView view;
    private EvenBus eventBus;
    private AddOtrosInteractor interactor;

    public AddOtrosPresenterImp(AddOtrosView view, EvenBus eventBus, AddOtrosInteractor interactor) {
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
    @Subscribe
    public void onEventMainThread(AddOtrosEvent event) {
        if(this.view != null){
            switch (event.getType()){
                case AddOtrosEvent.UPLOAD_INIT:
                    view.onUploadInit();
                    break;
                case AddOtrosEvent.UPLOAD_COMPLETE:
                    view.onUploadComplete();
                    break;
                case AddOtrosEvent.UPLOAD_ERROR:
                    view.onUploadError(event.getError());
                    break;
            }
        }
    }
}
