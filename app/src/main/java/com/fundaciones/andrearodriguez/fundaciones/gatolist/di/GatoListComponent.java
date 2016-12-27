package com.fundaciones.andrearodriguez.fundaciones.gatolist.di;

import com.fundaciones.andrearodriguez.fundaciones.FundacionAppModule;
import com.fundaciones.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.ui.GatoListFragment;
import com.fundaciones.andrearodriguez.fundaciones.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 12/21/16.
 */
@Singleton
@Component(modules = {GatoListModule.class, DomainModule.class, LibsModule.class, FundacionAppModule.class})
public interface GatoListComponent {
    void inject(GatoListFragment fragment);
}
