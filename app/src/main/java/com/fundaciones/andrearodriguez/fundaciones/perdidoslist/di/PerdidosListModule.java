package com.fundaciones.andrearodriguez.fundaciones.perdidoslist.di;

import com.fundaciones.andrearodriguez.fundaciones.domine.FirebasePerdidosAPI;
import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageLoader;
import com.fundaciones.andrearodriguez.fundaciones.perdidoslist.PerdidosListInteractor;
import com.fundaciones.andrearodriguez.fundaciones.perdidoslist.PerdidosListInteractorImp;
import com.fundaciones.andrearodriguez.fundaciones.perdidoslist.PerdidosListPresenter;
import com.fundaciones.andrearodriguez.fundaciones.perdidoslist.PerdidosListPresenterImp;
import com.fundaciones.andrearodriguez.fundaciones.perdidoslist.PerdidosListRepository;
import com.fundaciones.andrearodriguez.fundaciones.perdidoslist.PerdidosListRepositoryImp;
import com.fundaciones.andrearodriguez.fundaciones.perdidoslist.adapter.OnItemClickPerdidos;
import com.fundaciones.andrearodriguez.fundaciones.perdidoslist.adapter.PerdidosListAdapter;
import com.fundaciones.andrearodriguez.fundaciones.perdidoslist.ui.PerdidosListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 3/24/17.
 */

@Module
public class PerdidosListModule {

    private PerdidosListView view;
    private OnItemClickPerdidos clickListener;

    public PerdidosListModule(PerdidosListView view, OnItemClickPerdidos clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }
    @Provides
    @Singleton
    PerdidosListView providesPerdidosListView (){
        return this.view;
    }
    @Provides
    @Singleton
    PerdidosListPresenter providesPerdidosListPresenter (EvenBus eventBus, PerdidosListView view, PerdidosListInteractor interactor){
        return new PerdidosListPresenterImp(eventBus, view, interactor);
    }
    @Provides
    @Singleton
    PerdidosListInteractor providesPerdidosListInteractor (PerdidosListRepository repository){
        return new PerdidosListInteractorImp(repository);
    }
    @Provides
    @Singleton
    PerdidosListRepository providesPerdidosListRepository (EvenBus eventBus, FirebasePerdidosAPI firebasePerdidosAPI){
        return new PerdidosListRepositoryImp(eventBus, firebasePerdidosAPI);
    }
    @Provides
    @Singleton
    PerdidosListAdapter providesPerdidosListAdapter (List<Paticas> perdidosList, ImageLoader imageLoader, OnItemClickPerdidos clickListener){
        return new PerdidosListAdapter(perdidosList, imageLoader, clickListener);
    }
    @Provides
    @Singleton
    OnItemClickPerdidos providesOnItemClickPerdidos (){
        return this.clickListener;
    }
    @Provides
    @Singleton
    List<Paticas> providesPerdidosList (){
        return new ArrayList<Paticas>();
    }
}
