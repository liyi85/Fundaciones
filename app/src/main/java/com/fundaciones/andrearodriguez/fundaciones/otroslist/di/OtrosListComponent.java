package com.fundaciones.andrearodriguez.fundaciones.otroslist.di;

import com.fundaciones.andrearodriguez.fundaciones.FundacionAppModule;
import com.fundaciones.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.fundaciones.andrearodriguez.fundaciones.libs.di.LibsModule;
import com.fundaciones.andrearodriguez.fundaciones.otroslist.ui.OtrosListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 1/24/17.
 */
@Singleton
@Component(modules = {OtrosListModule.class, DomainModule.class, LibsModule.class, FundacionAppModule.class})
public interface OtrosListComponent {
    void inject(OtrosListFragment fragment);
}
