package com.fundaciones.andrearodriguez.fundaciones.perdidoslist.di;

import com.fundaciones.andrearodriguez.fundaciones.FundacionAppModule;
import com.fundaciones.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.fundaciones.andrearodriguez.fundaciones.libs.di.LibsModule;
import com.fundaciones.andrearodriguez.fundaciones.perdidoslist.ui.PerdidosListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 3/24/17.
 */

@Singleton
@Component(modules = {PerdidosListModule.class, DomainModule.class, LibsModule.class, FundacionAppModule.class})
public interface PerdidoListComponent {
    void inject(PerdidosListFragment fragment);
}
