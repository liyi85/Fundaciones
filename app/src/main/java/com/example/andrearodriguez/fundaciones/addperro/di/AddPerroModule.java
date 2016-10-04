package com.example.andrearodriguez.fundaciones.addperro.di;

import com.example.andrearodriguez.fundaciones.addperro.AddPerroInteractor;
import com.example.andrearodriguez.fundaciones.addperro.AddPerroInteractorImp;
import com.example.andrearodriguez.fundaciones.addperro.AddPerroPresenter;
import com.example.andrearodriguez.fundaciones.addperro.AddPerroPresenterImp;
import com.example.andrearodriguez.fundaciones.addperro.AddPerroRepository;
import com.example.andrearodriguez.fundaciones.addperro.AddPerroRepositoryImp;
import com.example.andrearodriguez.fundaciones.addperro.ui.AddPerroView;
import com.example.andrearodriguez.fundaciones.domine.FirebasePerrosAPI;
import com.example.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.example.andrearodriguez.fundaciones.libs.base.ImageStorage;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 8/22/16.
 */

@Module
public class AddPerroModule {
    private AddPerroView view;

    public AddPerroModule(AddPerroView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    AddPerroView providesAddPerroView(){
        return this.view;
    }
    @Provides
    @Singleton
    AddPerroPresenter providesAddPerroPresenter (AddPerroView view, EvenBus eventBus, AddPerroInteractor interactor){
        return new AddPerroPresenterImp(view, eventBus, interactor);
    }
    @Provides
    @Singleton
    AddPerroInteractor providesAddPerroInteractor (AddPerroRepository repository){
        return new AddPerroInteractorImp(repository);
    }

    @Provides
    @Singleton
    AddPerroRepository providesAddPerroRepository (EvenBus eventBus, FirebasePerrosAPI firebasePerrosAPI, ImageStorage imageStorage){
        return new AddPerroRepositoryImp(eventBus, firebasePerrosAPI, imageStorage);
    }
}
