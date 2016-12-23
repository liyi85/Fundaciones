package com.example.andrearodriguez.fundaciones.perrolist.di;

import com.example.andrearodriguez.fundaciones.FundacionAppModule;
import com.example.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.example.andrearodriguez.fundaciones.libs.di.LibsModule;
import com.example.andrearodriguez.fundaciones.perrolist.ui.PerroListFragment;

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
