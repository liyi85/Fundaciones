package com.fundaciones.andrearodriguez.fundaciones.addevento.di;

import com.fundaciones.andrearodriguez.fundaciones.FundacionAppModule;
import com.fundaciones.andrearodriguez.fundaciones.addevento.ui.AddEventoFragment;
import com.fundaciones.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.fundaciones.andrearodriguez.fundaciones.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 1/27/17.
 */

@Singleton
@Component(modules = {AddEventoModule.class, DomainModule.class, LibsModule.class, FundacionAppModule.class})
public interface AddEventoComponent {
    void inject(AddEventoFragment fragment);
}
