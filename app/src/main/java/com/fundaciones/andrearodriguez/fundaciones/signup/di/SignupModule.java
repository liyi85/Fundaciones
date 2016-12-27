package com.fundaciones.andrearodriguez.fundaciones.signup.di;

import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseLoginAPI;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.signup.SignupInteractor;
import com.fundaciones.andrearodriguez.fundaciones.signup.SignupInteractorImp;
import com.fundaciones.andrearodriguez.fundaciones.signup.SignupPresenter;
import com.fundaciones.andrearodriguez.fundaciones.signup.SignupPresenterImp;
import com.fundaciones.andrearodriguez.fundaciones.signup.SignupRepository;
import com.fundaciones.andrearodriguez.fundaciones.signup.SignupRepositoryImp;
import com.fundaciones.andrearodriguez.fundaciones.signup.ui.SignupView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 9/22/16.
 */
@Module
public class SignupModule {
    SignupView view;

    public SignupModule(SignupView view) {
        this.view = view;
    }
    @Provides
    @Singleton
    SignupView providesSignupView (){
        return this.view;
    }
    @Provides
    @Singleton
    SignupPresenter providesSignupPresenter (EvenBus evenBus, SignupView view, SignupInteractor interactor){
        return new SignupPresenterImp(evenBus, view, interactor);
    }
    @Provides
    @Singleton
    SignupInteractor providesSignupInteractor (SignupRepository repository){
        return new SignupInteractorImp(repository);
    }
    @Provides
    @Singleton
    SignupRepository providesSignupRepository (EvenBus evenBus, FirebaseLoginAPI firebaseLoginAPI){
        return new SignupRepositoryImp(evenBus, firebaseLoginAPI);
    }
}
