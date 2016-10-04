package com.example.andrearodriguez.fundaciones.login.di;

import com.example.andrearodriguez.fundaciones.domine.FirebaseLoginAPI;
import com.example.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.example.andrearodriguez.fundaciones.login.LoginInteractor;
import com.example.andrearodriguez.fundaciones.login.LoginInteractorImp;
import com.example.andrearodriguez.fundaciones.login.LoginPresenter;
import com.example.andrearodriguez.fundaciones.login.LoginPresenterImp;
import com.example.andrearodriguez.fundaciones.login.LoginRepository;
import com.example.andrearodriguez.fundaciones.login.LoginRepositoryImp;
import com.example.andrearodriguez.fundaciones.login.ui.LoginView;

import org.greenrobot.eventbus.EventBus;

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
