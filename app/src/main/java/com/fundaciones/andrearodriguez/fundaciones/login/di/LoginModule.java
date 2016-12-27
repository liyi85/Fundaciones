package com.fundaciones.andrearodriguez.fundaciones.login.di;

import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseLoginAPI;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.login.LoginInteractor;
import com.fundaciones.andrearodriguez.fundaciones.login.LoginInteractorImp;
import com.fundaciones.andrearodriguez.fundaciones.login.LoginPresenter;
import com.fundaciones.andrearodriguez.fundaciones.login.LoginPresenterImp;
import com.fundaciones.andrearodriguez.fundaciones.login.LoginRepository;
import com.fundaciones.andrearodriguez.fundaciones.login.LoginRepositoryImp;
import com.fundaciones.andrearodriguez.fundaciones.login.ui.LoginView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 9/21/16.
 */
@Module
public class LoginModule {
    LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    LoginView providesLoginView (){
        return this.view;
    }
    @Provides
    @Singleton
    LoginPresenter providesLoginPresenter (EvenBus eventBus, LoginView loginView, LoginInteractor loginInteractor){
        return new LoginPresenterImp(eventBus, loginView, loginInteractor);
    }
    @Provides
    @Singleton
    LoginInteractor providesLoginInteractor (LoginRepository repository){
        return new LoginInteractorImp(repository);
    }
    @Provides
    @Singleton
    LoginRepository providesLoginRepository (EvenBus eventBus, FirebaseLoginAPI firebaseLoginAPI){
        return new LoginRepositoryImp(eventBus, firebaseLoginAPI);
    }
}
