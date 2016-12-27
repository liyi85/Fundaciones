package com.fundaciones.andrearodriguez.fundaciones.signup;

import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.signup.events.AddFundacionEvent;
import com.fundaciones.andrearodriguez.fundaciones.signup.ui.AddFundacionView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by andrearodriguez on 9/22/16.
 */
public class AddFundacionPresenterImp implements AddFundacionPresenter{

    private AddFundacionView view;
    private EvenBus evenBus;
    private AddFundacionInteractor fundacionInteractor;

    public AddFundacionPresenterImp(AddFundacionView view, EvenBus evenBus, AddFundacionInteractor fundacionInteractor) {
        this.view = view;
        this.evenBus = evenBus;
        this.fundacionInteractor = fundacionInteractor;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        view = null;

    }

    @Override
    public void onResume() {
        evenBus.register(this);
    }

    @Override
    public void onPause() {
        evenBus.unregister(this);
    }


    @Override
    public void uploadFundacion(String name, String direccion, String telefono, String persona, String correo) {
        fundacionInteractor.excecute(name, direccion, telefono, persona, correo);
    }

    @Subscribe
    @Override
    public void onEventMainThread(AddFundacionEvent event) {
        if(this.view != null){
            switch (event.getType()){
                case AddFundacionEvent.UPLOAD_INIT:
                    view.onUploadInit();
                    break;
                case AddFundacionEvent.UPLOAD_COMPLETE:
                    view.onUploadComplete();
                    break;
                case AddFundacionEvent.UPLOAD_ERROR:
                    view.onUploadError(event.getError());
                    break;
            }
        }
    }
}
