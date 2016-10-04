package com.example.andrearodriguez.fundaciones.signup.di;

import com.example.andrearodriguez.fundaciones.FundacionAppModule;
import com.example.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.example.andrearodriguez.fundaciones.libs.di.LibsModule;
import com.example.andrearodriguez.fundaciones.login.di.LoginModule;
import com.example.andrearodriguez.fundaciones.signup.ui.SignupActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 9/22/16.
 */
@Singleton
@Component(modules = {SignupModule.class, AddFundacionesModule.class, DomainModule.class, LibsModule.class, FundacionAppModule.class})
public interface SignupComponent {
    void inject(SignupActivity activity);
}
