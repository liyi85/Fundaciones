package com.fundaciones.andrearodriguez.fundaciones.eventoslist.di;

import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseEventosAPI;
import com.fundaciones.andrearodriguez.fundaciones.entities.Eventos;
import com.fundaciones.andrearodriguez.fundaciones.eventoslist.EventosListInteractor;
import com.fundaciones.andrearodriguez.fundaciones.eventoslist.EventosListInteractorImp;
import com.fundaciones.andrearodriguez.fundaciones.eventoslist.EventosListPresenter;
import com.fundaciones.andrearodriguez.fundaciones.eventoslist.EventosListPresenterImp;
import com.fundaciones.andrearodriguez.fundaciones.eventoslist.EventosListRespository;
import com.fundaciones.andrearodriguez.fundaciones.eventoslist.EventosListRespositoryImp;
import com.fundaciones.andrearodriguez.fundaciones.eventoslist.ui.EventosListView;
import com.fundaciones.andrearodriguez.fundaciones.eventoslist.ui.adapter.EventosListAdapter;
import com.fundaciones.andrearodriguez.fundaciones.eventoslist.ui.adapter.OnItemClickListenerEvento;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 1/26/17.
 */

@Module
public class EventoListModule {

    private EventosListView view;
    private OnItemClickListenerEvento clickListener;

    public EventoListModule(EventosListView view, OnItemClickListenerEvento clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    EventosListView providesEventoListView (){
        return this.view;
    }
    @Provides
    @Singleton
    EventosListPresenter providesEventosListPresenter (EvenBus eventBus, EventosListView view, EventosListInteractor interactor){
        return new EventosListPresenterImp(eventBus, view, interactor);
    }
    @Provides
    @Singleton
    EventosListInteractor providesEventoListInteractor (EventosListRespository repository){
        return new EventosListInteractorImp(repository);
    }
    @Provides
    @Singleton
    EventosListRespository providesEventosListRepository (EvenBus eventBus, FirebaseEventosAPI firebaseEventosAPI){
        return new EventosListRespositoryImp(eventBus, firebaseEventosAPI);
    }
    @Provides
    @Singleton
    EventosListAdapter providesEventoListAdapter (List<Eventos> eventosList, ImageLoader imageLoader, OnItemClickListenerEvento clickListener){
        return new EventosListAdapter(eventosList, imageLoader, clickListener);
    }
    @Provides
    @Singleton
    OnItemClickListenerEvento providesOnItemClickListener (){
        return this.clickListener;
    }
    @Provides
    @Singleton
    List<Eventos> providesEventosList (){
        return new ArrayList<Eventos>();
    }
}
