package com.fundaciones.andrearodriguez.fundaciones.perrolist.di;

import com.fundaciones.andrearodriguez.fundaciones.FundacionAppModule;
import com.fundaciones.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.fundaciones.andrearodriguez.fundaciones.libs.di.LibsModule;
import com.fundaciones.andrearodriguez.fundaciones.perrolist.ui.PerroListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 8/22/16.
 */


@Singleton
@Component (modules = {PerroListModule.class, DomainModule.class, LibsModule.class, FundacionAppModule.class})
public interface PerroLisComponent {
    void inject(PerroListFragment fragment);
}
