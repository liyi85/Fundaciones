package com.fundaciones.andrearodriguez.fundaciones.addperdidos.di;

import com.fundaciones.andrearodriguez.fundaciones.addperdidos.AddPerdidosInteractor;
import com.fundaciones.andrearodriguez.fundaciones.addperdidos.AddPerdidosInteractorImp;
import com.fundaciones.andrearodriguez.fundaciones.addperdidos.AddPerdidosPresenter;
import com.fundaciones.andrearodriguez.fundaciones.addperdidos.AddPerdidosPresenterImp;
import com.fundaciones.andrearodriguez.fundaciones.addperdidos.AddPerdidosRepository;
import com.fundaciones.andrearodriguez.fundaciones.addperdidos.AddPerdidosRepositoryImp;
import com.fundaciones.andrearodriguez.fundaciones.addperdidos.ui.AddPerdidosView;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebasePerdidosAPI;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 9/25/17.
 */
@Module
public class AddPerdidosModule {

    private AddPerdidosView view;

    public AddPerdidosModule(AddPerdidosView view) {
        this.view = view;
    }
    @Provides
    @Singleton
    AddPerdidosView providesAddPerdidosView(){
        return this.view;
    }
    @Provides
    @Singleton
    AddPerdidosPresenter providesAddPerdidosPresenter (AddPerdidosView view, EvenBus eventBus, AddPerdidosInteractor interactor){
        return new AddPerdidosPresenterImp(view, eventBus, interactor);
    }
    @Provides
    @Singleton
    AddPerdidosInteractor providesAddPerdidosInteractor (AddPerdidosRepository repository){
        return new AddPerdidosInteractorImp(repository);
    }

    @Provides
    @Singleton
    AddPerdidosRepository providesAddPerdidosRepository (EvenBus eventBus, FirebasePerdidosAPI firebasePerdidosAPI, ImageStorage imageStorage){
        return new AddPerdidosRepositoryImp(eventBus, firebasePerdidosAPI, imageStorage);
    }
}
