package com.example.andrearodriguez.fundaciones.main.di;

import com.example.andrearodriguez.fundaciones.domine.FirebaseLoginAPI;
import com.example.andrearodriguez.fundaciones.main.MainPresenter;
import com.example.andrearodriguez.fundaciones.main.MainPresenterImp;
import com.example.andrearodriguez.fundaciones.main.MainRepository;
import com.example.andrearodriguez.fundaciones.main.MainRepositoryImp;
import com.example.andrearodriguez.fundaciones.main.SessionInteractor;
import com.example.andrearodriguez.fundaciones.main.SessionInteractorImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 9/25/16.
 */
@Module
public class MainModule {
    @Provides
    @Singleton
    MainPresenter providesMainPresenter (SessionInteractor sesionInteractor){
        return new MainPresenterImp(sesionInteractor);
    }

    @Provides
    @Singleton
    SessionInteractor providesSesionInteractor (MainRepository repository){
        return new SessionInteractorImp(repository);
    }

    @Provides
    @Singleton
    MainRepository providesMainRepository (FirebaseLoginAPI firebaseLoginAPI){
        return new MainRepositoryImp(firebaseLoginAPI);
    }
}
