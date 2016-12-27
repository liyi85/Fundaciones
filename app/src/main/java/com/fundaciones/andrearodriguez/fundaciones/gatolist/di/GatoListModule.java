package com.fundaciones.andrearodriguez.fundaciones.gatolist.di;

import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseGatosAPI;
import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.GatoListInteractor;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.GatoListInteractorImp;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.GatoListPresenter;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.GatoListPresenterImp;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.GatoListRepository;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.GatoListRepositoryImp;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.ui.GatoLisView;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.ui.adapter.GatoListAdapater;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.ui.adapter.OnItemClickListener;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 12/21/16.
 */

@Module
public class GatoListModule {
    private GatoLisView view;
    private OnItemClickListener clickListener;

    public GatoListModule(GatoLisView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }
    @Provides
    @Singleton
    GatoLisView providesGatoLisView (){
        return this.view;
    }
    @Provides
    @Singleton
    GatoListPresenter providesGatoListPresenter (EvenBus eventBus, GatoLisView view, GatoListInteractor interactor){
        return new GatoListPresenterImp(eventBus, view, interactor);
    }
    @Provides
    @Singleton
    GatoListInteractor providesGatoListInteractor (GatoListRepository repository){
        return new GatoListInteractorImp(repository);
    }
    @Provides
    @Singleton
    GatoListRepository providesGatoListRepository (EvenBus eventBus, FirebaseGatosAPI firebaseGatosAPI){
        return new GatoListRepositoryImp(eventBus, firebaseGatosAPI);
    }
    @Provides
    @Singleton
    GatoListAdapater providesGatoListAdapater (List<Paticas> perroList, ImageLoader imageLoader, OnItemClickListener clickListener){
        return new GatoListAdapater(perroList, imageLoader, clickListener);
    }
    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener (){
        return this.clickListener;
    }
    @Provides
    @Singleton
    List<Paticas> providesGatoList (){
        return new ArrayList<Paticas>();
    }
}
