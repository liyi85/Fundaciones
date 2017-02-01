package com.fundaciones.andrearodriguez.fundaciones.addevento.di;

import com.fundaciones.andrearodriguez.fundaciones.addevento.AddEventoInteractor;
import com.fundaciones.andrearodriguez.fundaciones.addevento.AddEventoInteractorImp;
import com.fundaciones.andrearodriguez.fundaciones.addevento.AddEventoPresenter;
import com.fundaciones.andrearodriguez.fundaciones.addevento.AddEventoPresenterImp;
import com.fundaciones.andrearodriguez.fundaciones.addevento.AddEventoRespository;
import com.fundaciones.andrearodriguez.fundaciones.addevento.AddEventoRespositoryImp;
import com.fundaciones.andrearodriguez.fundaciones.addevento.ui.AddEventoView;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseEventosAPI;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 1/27/17.
 */

@Module
public class AddEventoModule {

    private AddEventoView view;

    public AddEventoModule(AddEventoView view) {
        this.view = view;
    }
    @Provides
    @Singleton
    AddEventoView providesAddEventoView(){
        return this.view;
    }
    @Provides
    @Singleton
    AddEventoPresenter providesAddEventoPresenter (AddEventoView view, EvenBus eventBus, AddEventoInteractor interactor){
        return new AddEventoPresenterImp(view, eventBus, interactor);
    }
    @Provides
    @Singleton
    AddEventoInteractor providesAddEventoInteractor (AddEventoRespository repository){
        return new AddEventoInteractorImp(repository);
    }

    @Provides
    @Singleton
    AddEventoRespository providesAddEventoRepository (EvenBus eventBus, FirebaseEventosAPI firebaseEventosAPI, ImageStorage imageStorage){
        return new AddEventoRespositoryImp(eventBus, firebaseEventosAPI, imageStorage);
    }
}
