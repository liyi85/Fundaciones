package com.fundaciones.andrearodriguez.fundaciones.otroslist.di;

import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseOtrosAPI;
import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageLoader;
import com.fundaciones.andrearodriguez.fundaciones.otroslist.OtrosListInteractor;
import com.fundaciones.andrearodriguez.fundaciones.otroslist.OtrosListInteractorImp;
import com.fundaciones.andrearodriguez.fundaciones.otroslist.OtrosListPresenter;
import com.fundaciones.andrearodriguez.fundaciones.otroslist.OtrosListPresenterImp;
import com.fundaciones.andrearodriguez.fundaciones.otroslist.OtrosListRepository;
import com.fundaciones.andrearodriguez.fundaciones.otroslist.OtrosListRepositoryImp;
import com.fundaciones.andrearodriguez.fundaciones.otroslist.ui.OtrosListView;
import com.fundaciones.andrearodriguez.fundaciones.otroslist.ui.adapter.OnItemClickListenerOtros;
import com.fundaciones.andrearodriguez.fundaciones.otroslist.ui.adapter.OtrostListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 1/24/17.
 */
@Module
public class OtrosListModule {

    private OtrosListView view;
    private OnItemClickListenerOtros clickListener;

    public OtrosListModule(OtrosListView view, OnItemClickListenerOtros clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    OtrosListView providesOtrosListView (){
        return this.view;
    }
    @Provides
    @Singleton
    OtrosListPresenter providesOtrosListPresenter (EvenBus eventBus, OtrosListView view, OtrosListInteractor interactor){
        return new OtrosListPresenterImp(eventBus, view, interactor);
    }
    @Provides
    @Singleton
    OtrosListInteractor providesOtrosListInteractor (OtrosListRepository repository){
        return new OtrosListInteractorImp(repository);
    }
    @Provides
    @Singleton
    OtrosListRepository providesOtrosListRepository (EvenBus eventBus, FirebaseOtrosAPI firebaseOtrosAPI){
        return new OtrosListRepositoryImp(eventBus, firebaseOtrosAPI);
    }
    @Provides
    @Singleton
    OtrostListAdapter providesOtrosListAdapter (List<Paticas> perroList, ImageLoader imageLoader, OnItemClickListenerOtros clickListener){
        return new OtrostListAdapter(perroList, imageLoader, clickListener);
    }
    @Provides
    @Singleton
    OnItemClickListenerOtros providesOnItemClickListener (){
        return this.clickListener;
    }
    @Provides
    @Singleton
    List<Paticas> providesOtrosList (){
        return new ArrayList<Paticas>();
    }
}
