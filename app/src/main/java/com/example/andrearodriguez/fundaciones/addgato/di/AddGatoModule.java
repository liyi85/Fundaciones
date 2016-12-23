package com.example.andrearodriguez.fundaciones.addgato.di;

import com.example.andrearodriguez.fundaciones.addgato.AddGatoInteractor;
import com.example.andrearodriguez.fundaciones.addgato.AddGatoInteractorImp;
import com.example.andrearodriguez.fundaciones.addgato.AddGatoPresenter;
import com.example.andrearodriguez.fundaciones.addgato.AddGatoPresenterImp;
import com.example.andrearodriguez.fundaciones.addgato.AddGatoRepository;
import com.example.andrearodriguez.fundaciones.addgato.AddGatoRepositoryImp;
import com.example.andrearodriguez.fundaciones.addgato.ui.AddGatoView;
import com.example.andrearodriguez.fundaciones.domine.FirebaseGatosAPI;
import com.example.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.example.andrearodriguez.fundaciones.libs.base.ImageStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 12/22/16.
 */
@Module
public class AddGatoModule {

    private AddGatoView view;

    public AddGatoModule(AddGatoView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    AddGatoView providesAddGatoView(){
        return this.view;
    }
    @Provides
    @Singleton
    AddGatoPresenter providesAddGatoPresenter (AddGatoView view, EvenBus eventBus, AddGatoInteractor interactor){
        return new AddGatoPresenterImp(view, eventBus, interactor);
    }
    @Provides
    @Singleton
    AddGatoInteractor providesAddGatoInteractor (AddGatoRepository repository){
        return new AddGatoInteractorImp(repository);
    }

    @Provides
    @Singleton
    AddGatoRepository providesAddGatoRepository (EvenBus eventBus, FirebaseGatosAPI firebaseGatosAPI, ImageStorage imageStorage){
        return new AddGatoRepositoryImp(eventBus, firebaseGatosAPI, imageStorage);
    }
}
