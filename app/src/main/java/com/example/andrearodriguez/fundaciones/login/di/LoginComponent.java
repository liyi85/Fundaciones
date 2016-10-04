package com.example.andrearodriguez.fundaciones.login.di;

import com.example.andrearodriguez.fundaciones.FundacionAppModule;
import com.example.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.example.andrearodriguez.fundaciones.libs.di.LibsModule;
import com.example.andrearodriguez.fundaciones.login.ui.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 9/21/16.
 */
@Singleton
@Component(modules = {LoginModule.class, DomainModule.class, LibsModule.class, FundacionAppModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}
