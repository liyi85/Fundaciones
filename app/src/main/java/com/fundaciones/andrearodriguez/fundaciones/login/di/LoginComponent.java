package com.fundaciones.andrearodriguez.fundaciones.login.di;

import com.fundaciones.andrearodriguez.fundaciones.FundacionAppModule;
import com.fundaciones.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.fundaciones.andrearodriguez.fundaciones.libs.di.LibsModule;
import com.fundaciones.andrearodriguez.fundaciones.login.ui.LoginActivity;

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
