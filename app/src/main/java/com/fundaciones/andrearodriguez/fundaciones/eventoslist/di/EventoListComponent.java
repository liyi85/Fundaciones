package com.fundaciones.andrearodriguez.fundaciones.eventoslist.di;

import com.fundaciones.andrearodriguez.fundaciones.FundacionAppModule;
import com.fundaciones.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.fundaciones.andrearodriguez.fundaciones.eventoslist.ui.EventosListFragment;
import com.fundaciones.andrearodriguez.fundaciones.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 1/26/17.
 */


@Singleton
@Component(modules = {EventoListModule.class, DomainModule.class, LibsModule.class, FundacionAppModule.class})

public interface EventoListComponent {
    void inject(EventosListFragment fragment);
}
