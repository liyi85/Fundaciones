package com.fundaciones.andrearodriguez.fundaciones.addperro.di;


import com.fundaciones.andrearodriguez.fundaciones.addperro.ui.AddPerroFragment;
import com.fundaciones.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.fundaciones.andrearodriguez.fundaciones.FundacionAppModule;
import com.fundaciones.andrearodriguez.fundaciones.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 8/22/16.
 */

@Singleton
@Component (modules = {AddPerroModule.class, DomainModule.class, LibsModule.class, FundacionAppModule.class})
public interface AddPerroComponent {
    void inject(AddPerroFragment fragment);
}
