package com.fundaciones.andrearodriguez.fundaciones.addotros.di;

import com.fundaciones.andrearodriguez.fundaciones.addotros.AddOtrosInteractor;
import com.fundaciones.andrearodriguez.fundaciones.addotros.AddOtrosInteractorImp;
import com.fundaciones.andrearodriguez.fundaciones.addotros.AddOtrosPresenter;
import com.fundaciones.andrearodriguez.fundaciones.addotros.AddOtrosPresenterImp;
import com.fundaciones.andrearodriguez.fundaciones.addotros.AddOtrosRepository;
import com.fundaciones.andrearodriguez.fundaciones.addotros.AddOtrosRepositoryImp;
import com.fundaciones.andrearodriguez.fundaciones.addotros.ui.AddOtrosView;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseOtrosAPI;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 1/25/17.
 */

@Module
public class AddOtrosModule {

    private AddOtrosView view;

    public AddOtrosModule(AddOtrosView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    AddOtrosView providesAddOtrosView(){
        return this.view;
    }
    @Provides
    @Singleton
    AddOtrosPresenter providesAddOtrosPresenter (AddOtrosView view, EvenBus eventBus, AddOtrosInteractor interactor){
        return new AddOtrosPresenterImp(view, eventBus, interactor);
    }
    @Provides
    @Singleton
    AddOtrosInteractor providesAddOtrosInteractor (AddOtrosRepository repository){
        return new AddOtrosInteractorImp(repository);
    }

    @Provides
    @Singleton
    AddOtrosRepository providesAddOtrosRepository (EvenBus eventBus, FirebaseOtrosAPI firebaseOtrosAPI, ImageStorage imageStorage){
        return new AddOtrosRepositoryImp(eventBus, firebaseOtrosAPI, imageStorage);
    }
}
