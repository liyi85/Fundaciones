package com.example.andrearodriguez.fundaciones.perrolist.di;

import com.example.andrearodriguez.fundaciones.domine.FirebasePerrosAPI;
import com.example.andrearodriguez.fundaciones.entities.Paticas;
import com.example.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.example.andrearodriguez.fundaciones.libs.base.ImageLoader;
import com.example.andrearodriguez.fundaciones.perrolist.PerroListInteractor;
import com.example.andrearodriguez.fundaciones.perrolist.PerroListInteractorImp;
import com.example.andrearodriguez.fundaciones.perrolist.PerroListPresenter;
import com.example.andrearodriguez.fundaciones.perrolist.PerroListPresenterImp;
import com.example.andrearodriguez.fundaciones.perrolist.PerroListRepository;
import com.example.andrearodriguez.fundaciones.perrolist.PerroListRepositoryImp;
import com.example.andrearodriguez.fundaciones.perrolist.ui.PerroListView;
import com.example.andrearodriguez.fundaciones.perrolist.ui.adapter.OnItemClickListener;
import com.example.andrearodriguez.fundaciones.perrolist.ui.adapter.PerroListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 8/22/16.
 */

@Module
public class PerroListModule {

    private PerroListView view;
    private OnItemClickListener clickListener;

    public PerroListModule(PerroListView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    PerroListView providesPerroListView (){
        return this.view;
    }
    @Provides
    @Singleton
    PerroListPresenter providesPerroListPresenter (EvenBus eventBus, PerroListView view, PerroListInteractor interactor){
        return new PerroListPresenterImp(eventBus, view, interactor);
    }
    @Provides
    @Singleton
    PerroListInteractor providesPerroListInteractor (PerroListRepository repository){
        return new PerroListInteractorImp(repository);
    }
    @Provides
    @Singleton
    PerroListRepository providesPerroListRepository (EvenBus eventBus, FirebasePerrosAPI firebasePerrosAPI){
        return new PerroListRepositoryImp(eventBus, firebasePerrosAPI);
    }
    @Provides
    @Singleton
    PerroListAdapter providesPerroListAdapter (List<Paticas> perroList, ImageLoader imageLoader, OnItemClickListener clickListener){
        return new PerroListAdapter(perroList, imageLoader, clickListener);
    }
    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener (){
        return this.clickListener;
    }
    @Provides
    @Singleton
    List<Paticas> providesPerroList (){
        return new ArrayList<Paticas>();
    }
}
