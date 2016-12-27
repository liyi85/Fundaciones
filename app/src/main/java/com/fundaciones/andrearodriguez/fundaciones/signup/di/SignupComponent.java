package com.fundaciones.andrearodriguez.fundaciones.signup.di;

import com.fundaciones.andrearodriguez.fundaciones.FundacionAppModule;
import com.fundaciones.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.fundaciones.andrearodriguez.fundaciones.libs.di.LibsModule;
import com.fundaciones.andrearodriguez.fundaciones.signup.ui.SignupActivity;

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
