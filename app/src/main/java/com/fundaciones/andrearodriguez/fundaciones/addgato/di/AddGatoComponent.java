package com.fundaciones.andrearodriguez.fundaciones.addgato.di;

import com.fundaciones.andrearodriguez.fundaciones.FundacionAppModule;
import com.fundaciones.andrearodriguez.fundaciones.addgato.ui.AddGatoFragment;
import com.fundaciones.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.fundaciones.andrearodriguez.fundaciones.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 12/22/16.
 */
@Singleton
@Component(modules = {AddGatoModule.class, DomainModule.class, LibsModule.class, FundacionAppModule.class})
public interface AddGatoComponent {
    void inject(AddGatoFragment fragment);
}
